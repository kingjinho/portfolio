package com.kingjinho.portfolio.game.touchtouch.refactor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kingjinho.portfolio.game.GameStatus
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val INITIAL_SCORE = 0
private const val INITIAL_REMAINING_TIME = 15_000L
private const val INTERVAL = 1000L
private const val INITIAL_NUMBER_OF_CATS_TO_DRAW = 30

class TouchTouchGameViewModel : ViewModel() {

    private val _gameStatus = mutableStateOf<GameStatus>(GameStatus.Ready)
    val gameStatus: State<GameStatus>
        get() = _gameStatus

    private val _score = mutableIntStateOf(INITIAL_SCORE)
    val score: State<Int>
        get() = _score

    private val _numberOfCatsToDraw = mutableIntStateOf(INITIAL_NUMBER_OF_CATS_TO_DRAW)
    val numberOfCatsToDraw: State<Int>
        get() = _numberOfCatsToDraw

    private val _remainingTime = mutableLongStateOf(INITIAL_REMAINING_TIME)
    val remainingTime: State<Long>
        get() = _remainingTime

    private var countDownTimerJob: Job? = null

    fun startGame() {
        _gameStatus.value = GameStatus.Playing
        initCountDownTimer()
        startTimer()
    }

    private fun initCountDownTimer() {
        countDownTimerJob = viewModelScope.launch(start = CoroutineStart.LAZY) {
            withContext(Dispatchers.IO) {
                while (_remainingTime.longValue > 0) {
                    delay(INTERVAL)
                    _numberOfCatsToDraw.intValue += 15
                    _remainingTime.longValue -= INTERVAL
                }
                _gameStatus.value = GameStatus.GameOver
            }
        }
    }

    private fun startTimer() {
        countDownTimerJob?.start()
    }

    fun incrementScore() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _score.intValue++
            }
        }
    }

    fun resetGame() {
        _remainingTime.longValue = INITIAL_REMAINING_TIME
        _numberOfCatsToDraw.intValue = INITIAL_NUMBER_OF_CATS_TO_DRAW
        _gameStatus.value = GameStatus.Ready
        _score.intValue = INITIAL_SCORE
    }

    override fun onCleared() {
        super.onCleared()
    }

}


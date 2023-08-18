package com.kingjinho.portfolio.game.touchtouch

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

class TouchTouchGameViewModel : ViewModel() {

    private val gameTimeInMills = 15_000L
    private val interval = 1_000L

    private val _gameStatus = mutableStateOf<GameStatus>(GameStatus.Ready)
    val gameStatus: State<GameStatus>
        get() = _gameStatus

    private val _score = mutableIntStateOf(0)
    val score: State<Int>
        get() = _score

    private val _remainingTime = mutableLongStateOf(gameTimeInMills)
    val remainingTime: State<Long>
        get() = _remainingTime

    private var countDownTimerJob: Job? = null

    fun gameStart() {
        _gameStatus.value = GameStatus.Playing
        initCountDownTimer()
        startTimer()
    }

    private fun initCountDownTimer() {
        countDownTimerJob = viewModelScope.launch(start = CoroutineStart.LAZY) {
            withContext(Dispatchers.IO) {
                while (_remainingTime.longValue > 0) {
                    delay(interval)
                    _remainingTime.longValue -= interval
                }
                _gameStatus.value = GameStatus.GameOver
            }
        }
    }

    private fun startTimer() {
        countDownTimerJob?.start()
    }

    fun gameScore() {
        _score.intValue++
    }

    fun gameRestart() {
        _remainingTime.longValue = gameTimeInMills
        _gameStatus.value = GameStatus.Ready
        _score.intValue = 0
    }

}


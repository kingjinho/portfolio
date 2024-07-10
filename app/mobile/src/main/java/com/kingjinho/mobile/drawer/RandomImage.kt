package com.kingjinho.mobile.drawer

import com.kingjinho.portfolio.R

private val imageListFromDrawable = listOf(
    R.drawable.home_rental_image2,
    R.drawable.home_rental_image3,
    R.drawable.home_rental_image4,
    R.drawable.home_rental_image5,
    R.drawable.home_rental_image6,
    R.drawable.home_rental_image7,
    R.drawable.home_rental_image8,
    R.drawable.home_rental_image9,
    R.drawable.home_rental_image10,
    R.drawable.home_rental_image11,
    R.drawable.home_rental_image12,
    R.drawable.home_rental_image13,
    R.drawable.home_rental_image14,
    R.drawable.home_rental_image15,
    R.drawable.home_rental_image16,
    R.drawable.home_rental_image17,
    R.drawable.home_rental_image18,
    R.drawable.home_rental_image19,
)

fun getRandomImageRes(): Int {
    return imageListFromDrawable.random()
}

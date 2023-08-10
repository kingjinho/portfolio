package com.kingjinho.portfolio.drawer

import com.kingjinho.portfolio.R

sealed class RentalSection {
    abstract val sectionHeaderRes: Int

    object Nearby : RentalSection() {
        override val sectionHeaderRes: Int
            get() = R.string.text_title_near_by_me_section
    }
    object BestDeal : RentalSection() {
        override val sectionHeaderRes: Int
            get() = R.string.text_title_best_deal_section
    }
}

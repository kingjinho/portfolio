package com.kingjinho.portfolio.drawer

sealed class Location {
    object Jakarta : Location()
    object Seoul : Location()
    object Tokyo : Location()
    object NewYork : Location()
}

data class LocationItem(
    val location: Location,
    var isSelected: Boolean = false
) {
    val name: String = location.javaClass.simpleName
}

val locationList = listOf(
    LocationItem(Location.Jakarta, isSelected = true),
    LocationItem(Location.Seoul),
    LocationItem(Location.Tokyo),
    LocationItem(Location.NewYork)
)


package pt.iade.ei.gamestoreviperz.model

import androidx.annotation.DrawableRes
import java.io.Serializable


data class Game(
    val title: String,
    val description: String,
    @DrawableRes val coverImageRes: Int,
    val items: List<PurchasableItem>
) : Serializable
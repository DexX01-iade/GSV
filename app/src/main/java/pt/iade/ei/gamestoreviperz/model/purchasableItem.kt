package pt.iade.ei.gamestoreviperz.model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class PurchasableItem(
    val name: String,
    val description: String,
    val price: String,
    @DrawableRes val imageRes: Int
) : Serializable
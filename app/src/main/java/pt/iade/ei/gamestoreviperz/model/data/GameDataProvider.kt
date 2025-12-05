package pt.iade.ei.gamestoreviperz.model.data

import pt.iade.ei.gamestoreviperz.R
import pt.iade.ei.gamestoreviperz.model.Game
import pt.iade.ei.gamestoreviperz.model.PurchasableItem

object GameDataProvider {
    fun getGames(): List<Game> {
        return listOf(
            Game(
                title = "Brawl Stars",
                description = "Batalhas frenéticas 3v3 e Battle Royale!",
                coverImageRes = R.drawable.brawl_cover,
                items = listOf(
                    PurchasableItem(
                        "Brawl Pass",
                        "Desbloqueia recompensas da temporada e um Brawler épico a tua escolha! Ganha skins, ouro e gemas em dobro!",
                        "10€",
                        R.drawable.brawl_pass
                    ),
                    PurchasableItem(
                        "Skin Fénix Corvo",
                        "Skin lendária com efeitos personalizados do corvo, o renascente das cinzas .",
                        "20€",
                        R.drawable.skin_brawl
                    ),
                    PurchasableItem(
                        "Gemas",
                        "Pacote de gemas para gastares na loja, Brawlers, Items ou skins.",
                        "5€",
                        R.drawable.gemas_brawl
                    )
                )
            ),
            Game(
                title = "League of Legends",
                description = "O MOBA mais divertido e jogado do mundo.",
                coverImageRes = R.drawable.lol_cover,
                items = listOf(
                    PurchasableItem(
                        "Baú Hextech",
                        "Espólios aleatórios.",
                        "2€",
                        R.drawable.bau_hexcet
                    ),
                    PurchasableItem(
                        "Passe Batalha",
                        "Desbloqueia recompensas da temporada! Ganha skins, Essências azuis e RP em dobro!",
                        "15€",
                        R.drawable.battle_lol
                    ),
                    PurchasableItem(
                        "Skin K/DA",
                        "Edição Prestige da Skin mais vendida do ano do grupo K/DA.",
                        "25€",
                        R.drawable.kaisa_lol
                    )
                )
            )
        )
    }
}
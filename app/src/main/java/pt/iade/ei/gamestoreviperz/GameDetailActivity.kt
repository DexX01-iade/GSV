package pt.iade.ei.gamestoreviperz

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.gamestoreviperz.model.Game
import pt.iade.ei.gamestoreviperz.model.PurchasableItem
import pt.iade.ei.gamestoreviperz.ui.theme.components.PurchasableItemRow
import pt.iade.ei.gamestoreviperz.ui.theme.GameStoreViperzTheme

class GameDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        val game = intent.getSerializableExtra("GAME_KEY") as? Game

        setContent {
            GameStoreViperzTheme {
                if (game != null) {
                    GameDetailScreen(
                        game = game,
                        onBackClick = { finish() }
                    )
                } else {
                    Text("Erro ao carregar os dados do jogo.")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(game: Game, onBackClick: () -> Unit) {
    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf<PurchasableItem?>(null) }
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(game.title) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            item {
                Image(
                    painter = painterResource(id = game.coverImageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Text(game.description, modifier = Modifier.padding(16.dp))
            }
            items(game.items) { item ->
                Box(modifier = Modifier.clickable { selectedItem = item }) {
                    PurchasableItemRow(item = item)
                }
            }
        }

        if (selectedItem != null) {
            ModalBottomSheet(onDismissRequest = { selectedItem = null }, sheetState = sheetState) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(selectedItem!!.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(
                        selectedItem!!.price,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Button(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Obrigado! Comprou: ${selectedItem!!.name}",
                                Toast.LENGTH_SHORT
                            ).show()
                            selectedItem = null
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Comprar agora")
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameDetailScreenPreview() {
    val mockGame = Game(
        title = "Brawl Stars",
        description = "Batalhas frenéticas 3v3 e Battle Royale!",
        coverImageRes = R.drawable.brawl_cover,
        items = listOf(
            PurchasableItem(
                name = "Brawl Pass",
                description = "Desbloqueia recompensas da temporada e um Brawler épico a tua escolha! Ganha skins, ouro e gemas em dobro!",
                price = "10€",
                imageRes = R.drawable.brawl_pass
            ),
            PurchasableItem(
                name = "Skin Fénix Corvo",
                description = "Skin lendária com efeitos personalizados do corvo, o renascente das cinzas .",
                price = "20€",
                imageRes = R.drawable.skin_brawl
            ),
            PurchasableItem(
                name = "Gemas",
                description = "Pacote de gemas para gastares na loja, Brawlers, Items ou skins.",
                price = "5€",
                imageRes = R.drawable.gemas_brawl
            )


        )
    )

    GameStoreViperzTheme {
        GameDetailScreen(
            game = mockGame,
            onBackClick = {}
        )
    }
}
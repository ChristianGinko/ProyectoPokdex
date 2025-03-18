package com.example.proyectopokdex.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.proyectopokdex.R
import com.example.proyectopokdex.entities.MyPoke
import com.example.proyectopokdex.navigation.AppScreens
import com.example.proyectopokdex.retrofit.PokemonViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: PokemonViewModel) {
    val pokes by viewModel.pokemonList
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp), // Espaciado entre los elementos
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, bottom = 50.dp)
    ) {
        // MyPokes ahora ocupa el 90% de la altura disponible
        MyPokes(
            navController,
            viewModel,
            pokes,
            modifier = Modifier
                .fillMaxHeight(0.9f) // Ajusta la altura ocupada por la lista
        )

        // Row con el botón
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.navigate(AppScreens.LeagueScreen.route)
                }
            ) {
                Text(text = "Back")
            }
        }
    }
}

@Composable
fun MyComponent(
    poke: MyPoke,
    viewModel: PokemonViewModel,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .border(5.dp, Color.Black)
            .fillMaxWidth()
            .clickable {
                viewModel.setSelectedPokemon(poke) // Guardamos el Pokémon seleccionado
                navController.navigate(AppScreens.DataScreen.route) // Navegamos a DataScreen
            }
    ) {
        Image(
            painter = painterResource(R.drawable.pok_ball),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .offset(x = 50.dp, y = 90.dp)
        ) {
            Box() {
                Text(
                    text = "#${poke.id} ${poke.name}",
                    color = Color(0xFF4052D9),
                    style = TextStyle(
                        fontSize = 35.sp,
                        drawStyle = Stroke(width = 15f)
                    )
                )
                Text(
                    text = "#${poke.id} ${poke.name}",
                    color = Color(0xFFFFE031),
                    style = TextStyle(
                        fontSize = 35.sp
                    ),
                    modifier = Modifier
                )
            }
        }
    }
}

/*
@Composable
fun MyText(poke: MyPoke, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(10.dp)) {
        Box() {
            Text(
                text = poke.name,
                color = Color(0xFF4052D9),
                style = TextStyle(
                    fontSize = 35.sp,
                    drawStyle = Stroke(width = 15f)
                ),
                modifier = Modifier
            )
            Text(
                text = poke.name,
                color = Color(0xFFFFE031),
                style = TextStyle(fontSize = 35.sp),
                modifier = Modifier
            )
        }
    }
}
*/

@Composable
fun MyPokes(navController: NavController, viewModel: PokemonViewModel, pokes: List<MyPoke>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier // Usamos el modificador pasado para controlar la altura
    ) {
        items(pokes.size) { index ->
            MyComponent(poke = pokes.get(index), viewModel = viewModel, navController = navController)
        }
    }
}
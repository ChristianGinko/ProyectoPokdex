package com.example.proyectopokdex.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.proyectopokdex.MyPoke
import com.example.proyectopokdex.R
import com.example.proyectopokdex.navigation.AppScreens
import com.example.proyectopokdex.retrofit.RetrofitInstance
import com.example.proyectopokdex.retrofit.getId
import kotlinx.coroutines.launch

@Composable
fun DataScreen (navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable{navController.navigate(AppScreens.MainScreen.route)}
    ){
        Image(
            painter = painterResource(R.drawable.pokedex_stats),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxSize()
        )
        Stats()
    }
}

@Composable
fun Stats() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .offset(y = (-100).dp)
        ) {
            Column(
                modifier = Modifier
                    .offset(x = (-30).dp)
            ) {
                Text(
                    text = "Name: Charmander",
                    style = TextStyle(fontSize = 25.sp)
                )
                Text(
                    text = "Type: Fire",
                    style = TextStyle(fontSize = 25.sp)
                )
            }
            Image(
                painter = painterResource(R.drawable.charmander),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .offset(x = 20.dp)
            )
        }
        Row(){
            Text(
                text = "Abilities:\nBlaze\nSolar Power",
                style = TextStyle(fontSize = 25.sp)
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DataScreenPreview(){
    DataScreen()
}
*/
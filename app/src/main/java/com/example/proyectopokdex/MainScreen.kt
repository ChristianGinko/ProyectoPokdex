package com.example.proyectopokdex

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.proyectopokdex.navigation.AppScreens

@Composable
fun MainScreen(navController: NavController, pokes: List<MyPoke>) {
    MyPokes(navController, pokes)
}

@Composable
fun MyComponent(
    poke: MyPoke,
){
    Row(modifier = Modifier
        .border(5.dp, Color.Black)
        .padding(20.dp)
        .fillMaxWidth()
    ) {
        MyText(poke)
    }
}

@Composable
fun MyText(poke: MyPoke){
    Column(modifier = Modifier.padding(10.dp)) {
        Box {
            val outlineColor = Color(0xFF4052D9)
            val textColor = Color(0xFFFFE031)

            // Dibujar múltiples veces el texto alrededor para el efecto de contorno
            for (dx in listOf(-2f, 2f)) {
                for (dy in listOf(-2f, 2f)) {
                    Text(
                        text = poke.name,
                        color = outlineColor,
                        style = TextStyle(fontSize = 24.sp),
                        modifier = Modifier.offset(dx.dp, dy.dp)
                    )
                }
            }

            // Texto principal encima del contorno
            Text(
                text = poke.name,
                color = textColor,
                style = TextStyle(fontSize = 24.sp)
            )
        }
        Box {
            val outlineColor = Color(0xFF4052D9)
            val textColor = Color(0xFFFFE031)

            // Dibujar múltiples veces el texto alrededor para el efecto de contorno
            for (dx in listOf(-2f, 2f)) {
                for (dy in listOf(-2f, 2f)) {
                    Text(
                        text = poke.type,
                        color = outlineColor,
                        style = TextStyle(fontSize = 24.sp),
                        modifier = Modifier.offset(dx.dp, dy.dp)
                    )
                }
            }

            // Texto principal encima del contorno
            Text(
                text = poke.type,
                color = textColor,
                style = TextStyle(fontSize = 24.sp)
            )
        }
    }
}

@Composable
fun MyPokes(navController: NavController, pokes: List<MyPoke>) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(
            top = 30.dp,
            bottom = 50.dp
        )
    ) {
        Image(
            painterResource(R.drawable.pok_dex_fondo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
        LazyColumn {
            items(pokes) { poke ->
                MyComponent(
                    poke = poke
                )
            }
        }
    }
}
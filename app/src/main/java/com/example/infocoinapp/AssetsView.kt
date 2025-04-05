package com.example.infocoinapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.infocoinapp.models.Asset

@Composable
fun AssetRow(asset: Asset){
    Row(
        modifier = Modifier.fillMaxWidth().background(Color.Black)
    ) {
//        Icon(
//            imageVector = Icons.Filled.CheckCircle,
//            contentDescription = null,
//            tint = Color.Red,
//            modifier =  Modifier.padding(horizontal = 8.dp).align(Alignment.CenterVertically)
//        )
        AsyncImage(
            model="https://assets.coincap.io/assets/icons/${asset.symbol}@2x.png",
            contentDescription = null
        )
        Column {
            Text(
                text = asset.symbol,
                fontSize = 18.sp,
                color = Color.White
            )
            Text(text = asset.name,
                fontSize = 14.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text= "${asset.percentage}%",
            fontSize = 16.sp,
            color = if(asset.percentage>=0) Color.Green else Color.Red,
            modifier =  Modifier.align(Alignment.CenterVertically)
        )
        Text(text ="$87908",
            fontSize = 14.sp,
            color = Color.White,
            modifier =  Modifier.padding(horizontal = 8.dp).align(Alignment.CenterVertically)
        )
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Favorite",
            tint = if(asset.favorite) Color.Yellow else Color.White,
            modifier = Modifier
                .padding(end = 8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AssetRowPreview(){
    Column(verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        AssetRow(
            Asset(
                id = "01",
                name ="Bitcoin",
                symbol = "BTC",
                price= "5.38",
                percentage = 5.89,
                url = "esiufiwe",
                favorite = true
            )
        )
        AssetRow(
            Asset(
                id = "02",
                name ="Ethereum",
                symbol = "ETH",
                price= "4.98",
                percentage = -7.89,
                url = "esiufiwe",
                favorite = false
            )
        )

    }

}
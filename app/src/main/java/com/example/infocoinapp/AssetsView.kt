package com.example.infocoinapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.infocoinapp.models.Asset
import com.example.infocoinapp.viewModels.AssetsListViewModel
import com.example.infocoinapp.ui.theme.Typography

@Composable
fun AssetsList(viewModel: AssetsListViewModel = hiltViewModel()) {
    val assets by viewModel.assets.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        items(assets, key = { it.id }) { asset ->
            AssetRow(asset)
        }
    }
}

@Composable
fun AssetRow(asset: Asset) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            if (LocalInspectionMode.current) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            } else {
                AsyncImage(
                    model = "https://assets.coincap.io/assets/icons/${asset.symbol.lowercase()}@2x.png",
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        Column {
            Text(
                text = asset.symbol,
                fontSize = 18.sp,
                color = Color.White
            )
            Text(
                text = asset.name,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "${asset.percentage}%",
            color = if (asset.percentage >= 0) Color.Green else Color.Red,
            style = Typography.labelLarge,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Text(
            text = "$${asset.price}",
            color = Color.White,
            style = Typography.labelLarge,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AssetRowPreview() {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        AssetRow(
            Asset(
                id = "01",
                name = "Bitcoin",
                symbol = "BTC",
                price = "53800",
                percentage = 5.89,
            )
        )
        AssetRow(
            Asset(
                id = "02",
                name = "Ethereum",
                symbol = "ETH",
                price = "4980",
                percentage = -7.89,
            )
        )
    }
}

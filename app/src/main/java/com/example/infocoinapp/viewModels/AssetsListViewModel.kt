package com.example.infocoinapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infocoinapp.models.Asset
import com.example.infocoinapp.services.infoCoinApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetsListViewModel @Inject constructor(
    private val apiService: infoCoinApiService
) : ViewModel(){
    private val _assets = MutableStateFlow<List<Asset>>(emptyList())
    val assets: StateFlow<List<Asset>> = _assets

    init{
        fetchAssets()
    }
    private fun fetchAssets(){
        viewModelScope.launch {
            try {
                val result = apiService.getAssets().data
                val mappedAssets = result.map { assetResponse ->
                    val price = String.format("%2f", assetResponse.priceUsd.toDouble())
                    val percentage = String.format("%2f", assetResponse.changePercent24Hr.toDouble()).toDouble()
                    Asset(
                        assetResponse.id,
                        assetResponse.name,
                        assetResponse.symbol,
                        price,
                        percentage,
                    )

                }
                _assets.value = mappedAssets
            }catch (e: Exception){
                print(e.message)
            }

        }
    }
}
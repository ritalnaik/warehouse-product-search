package com.rital.warehouse.presentation.details

import android.R
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.rital.warehouse.core.Constants
import com.rital.warehouse.core.theme.Dimens

@Composable
fun ProductDetailScreen(
    barcode: String,
    viewModel: ProductDetailsViewModel
) {
    val productUiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(barcode) {
        Log.e("ProductDetails","barcode : $barcode")
        viewModel.getProductDetails(barcode)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFB3B3B3)
            )
            .padding(Dimens.Medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            model = productUiState.product?.imageUrls?.first(),
            contentDescription = "Product Image",
            modifier = Modifier.size(Dimens.ImageHeight),
            contentScale = ContentScale.Fit,
            loading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            error = {
                Image(
                    painter = painterResource(
                        R.drawable.ic_menu_info_details
                    ),
                    contentDescription = null
                )
            },
            )

        Spacer(modifier = Modifier.height(Dimens.Medium))

        Text(
            text = productUiState.product?.productName?:Constants.EMPTY_STRING,
            fontSize = 20.sp,
            color = Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Dimens.Medium))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${productUiState.product?.priceInfo?.price?:0.0}",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

//            if (productUiState.product?.product?.Clearance == true) {
//                Spacer( modifier = Modifier.width(8.dp))
//                Icon(
//                    painter = painterResource(
//                        R.mipmap.ic_clearance
//                    ),
//                    contentDescription = null,
//                    modifier = Modifier.size(24.dp)
//                )
//            }
        }

        Spacer(modifier = Modifier.height(Dimens.Medium))
        HorizontalDivider(thickness = 0.5.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(Dimens.Medium))

        Text(
            text = productUiState.product?.promotions?.first()?.dealDescription?:"",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Dimens.ExtraSmall))

        Text(
            text = productUiState.product?.brandDescription?:Constants.EMPTY_STRING,
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
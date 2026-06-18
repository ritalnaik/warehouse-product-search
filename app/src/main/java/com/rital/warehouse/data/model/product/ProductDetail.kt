package com.rital.warehouse.data.model.product

import com.google.gson.JsonArray
import kotlinx.serialization.SerialName
import kotlinx.serialization.builtins.serializer


data class ProductDetail (
    val productName: String,
    val productKey: Long,
    val priceInfo: PriceInfo,

    val productImageUrl: String,

    val productBarcode: String,
    val productSet: Boolean,
    val brandCode: String,
    val brandDescription: String,
    val inventory: Inventory,
    val promotions: List<Promotion>,

    val productId: String,

    val categoryId: String,

    val secondaryCategoryIds: List<String>,

    val productDescription: String,
    val productBadges: List<ProductBadge>,
    val shippingSize: String,
    val isOversized: Boolean,
    val manufacturer: String,
    val manufacturerSku: String,
    val sizeAttribute: String,
    val sizeDescription: String,
    val soldOnline: String,
    val clickAndCollect: String,
    val isClickAndCollect: Boolean,
    val isDigital: Boolean,
    val isGiftcard: Boolean,
    val isEssentialItem: Boolean,

    val subClassId: String,

    val deliveryTime: String,
    val isMarketPlace: Boolean,

    val mdmProductId: String
)

data class Inventory (
    val available: Boolean,
    val preorderable: Boolean,
    val backorderable: Boolean,
    val soh: Long
)

data class PriceInfo (
    val price: Double
)

data class ProductBadge (
    val id: String,
    val definition: Definition
)

data class Definition (
    val position: String,
    val description: String,
    val order: Long
)

data class Promotion (
    val promotionId: String,
    val dealDescription: String,
    val price: Double? = null,
    val isMarketClubExclusive: Boolean,
    val description: String,
    val tags: List<String>
)


data class ProductDetailsList (
    val products: List<ProductDetail>
)

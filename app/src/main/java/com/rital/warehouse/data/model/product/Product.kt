package com.rital.warehouse.data.model.product



data class Product(
    val product: ProductDetail,
    val guest: Boolean,
    val platformDemandWare: String,
    val environment: String,
    val developmentPlatform: Boolean,
    val apiVersion: Double,
    val requestedApiVersion: Double
)


data class ProductDetail (
    val isMaster: Boolean,
    val onSpecial: Boolean,
    val imageUrls: List<String>,
    val productName: String,
    val priceInfo: PriceInfo,
    val productKey: Long,
    val inventory: Inventory,
    val productBarcode: String,
    val promotions: List<Promotion>,
    val brandCode: String,
    val brandDescription: String,
    val imageGroups: List<ImageGroup>,

    val productUrl: String,

    val isDangerousGoods: Boolean,
    val isClearance: Boolean,
    val hasSizingChart: Boolean,
    val compareSpecList: List<Any>,
    val clickAndCollectExcludedBranches: List<String>,

    val productId: String,
    val categoryId: String,
    val categoryHierarchy: List<Hierarchy>,

    val secondaryCategoryIds: List<String>,

    val secondaryCategoriesHierarchy: List<List<Hierarchy>>,
    val productBadges: List<ProductBadge>,
    val shippingSize: String,
    val isOversized: Boolean,
    val partPayRestricted: String,
    val afterPayRestricted: String,
    val giftCardRestricted: String,
    val manufacturer: String,
    val manufacturerSku: String,
    val soldOnline: String,
    val clickAndCollect: String,
    val isClickAndCollect: Boolean,
    val isDigital: Boolean,
    val isGiftcard: Boolean,
    val isEssentialItem: Boolean,

    val subClassId: String,

    val deliveryTime: String,
    val featureList: List<Any>,
    val hierarchy: List<HierarchyElement>,
    val isMarketPlace: Boolean,

    val mdmProductId: String
)

data class Hierarchy (
    val categoryId: String,
    val parentCategoryId: String? = null,

    val parentCategoryName: String? = null,
    val name: String,
    val description: String,
    val sizeChartId: String? = null,

    val productCount: Long,
    val subCategoryCount: Long,
    val excludeFromVisualBrowse: Boolean,
    val showInBrowse: Boolean? = null
)

data class HierarchyElement (
    val level: Long,
    val code: String,
    val description: String
)

data class ImageGroup (
    val colourAttribute: String,
    val imageUrls: List<String>
)

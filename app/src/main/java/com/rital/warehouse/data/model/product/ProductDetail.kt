package com.rital.warehouse.data.model.product

data class ProductDetail(
var machineID: String? = null,
var action: String? = null,
var scanBarcode: String? = null,
var scanID: String? = null,
var userDescription: String? = null,
var product: Product? = null,
var prodQAT: String? = null,
var scanDateTime: String? = null,
var found: String? = null,
var userID: String? = null,
var branch: String? = null
)

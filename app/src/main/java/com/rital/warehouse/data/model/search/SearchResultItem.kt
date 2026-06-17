package com.rital.warehouse.data.model.search

data class SearchResultItem (
    var Description: String? = null,
    var Products: List<ProductWithoutPrice?>? = null
)
package com.rital.warehouse.data.model.search

data class SearchResult(
    var HitCount: String? = null,
    var Results: MutableList<SearchResultItem?>? = null,
    var SearchID: String? = null,
    var ProdQAT: String? = null,
    var Found: String? = null
)
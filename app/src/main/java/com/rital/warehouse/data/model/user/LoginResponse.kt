package com.rital.warehouse.data.model.user

data class LoginResponse (
    val customerId: String,
    val preferredBranchIds: List<Any>,
    val eReceiptsPreferred: Boolean,
    val isTeamMember: Boolean,
    val isStaff: Boolean,
    val masterEmailOptIn: Boolean,
    val expiresDatetime: String,
    val expiryMinutes: Long,
    val guest: Boolean,
    val platformDemandWare: String,
    val environment: String,
    val developmentPlatform: Boolean,
    val apiVersion: Double,
    val requestedApiVersion: Double
)

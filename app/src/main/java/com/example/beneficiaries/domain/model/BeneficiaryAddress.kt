package com.example.beneficiaries.domain.model

data class BeneficiaryAddress(
    val firstLineMailing: String? = null,
    val scndLineMailing: String? = null,
    val city: String? = null,
    val zipCode: String? = null,
    val stateCode: String? = null,
    val country: String? = null
) {
    override fun toString(): String {
        return """
            $firstLineMailing,
            ${if(scndLineMailing.isNullOrBlank()) "" else scndLineMailing}
            $city, $zipCode, $stateCode
            $country
        """.trimIndent()
    }
}

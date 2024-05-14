package com.example.beneficiaries.domain.model

data class Beneficiary(
    val lastName: String? = null,
    val firstName: String? = null,
    val designationCode: String? = null,
    val beneType: String? = null,
    val socialSecurityNumber: String? = null,
    val dateOfBirth: String? = null,
    val middleName: String? = null,
    val phoneNumber: String? = null,
    val beneficiaryAddress: BeneficiaryAddress? = BeneficiaryAddress()
)

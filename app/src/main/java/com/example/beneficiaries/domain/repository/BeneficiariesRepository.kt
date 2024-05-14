package com.example.beneficiaries.domain.repository

import com.example.beneficiaries.domain.model.Beneficiary

interface BeneficiariesRepository {
    fun getBeneficiariesList(): List<Beneficiary>

    fun getBeneficiaryDetail(ssn: String): Beneficiary
}
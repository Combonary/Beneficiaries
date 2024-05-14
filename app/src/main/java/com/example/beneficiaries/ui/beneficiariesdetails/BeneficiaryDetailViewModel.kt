package com.example.beneficiaries.ui.beneficiariesdetails

import androidx.lifecycle.ViewModel
import com.example.beneficiaries.domain.model.Beneficiary
import com.example.beneficiaries.domain.repository.BeneficiariesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeneficiaryDetailViewModel @Inject constructor(
    private val repository: BeneficiariesRepository
): ViewModel() {
    var beneficiary: Beneficiary = Beneficiary()

    fun updateBeneficiary(socialSecurityNumber: String) {
        beneficiary = repository.getBeneficiaryDetail(socialSecurityNumber)
    }
}
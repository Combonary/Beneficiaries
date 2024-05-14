package com.example.beneficiaries.ui.beneficiarieslist

import androidx.lifecycle.ViewModel
import com.example.beneficiaries.domain.repository.BeneficiariesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeneficiariesListViewModel @Inject constructor(
    repository: BeneficiariesRepository
) : ViewModel() {
    //get data from repository
    val beneficiaries = repository.getBeneficiariesList()
}
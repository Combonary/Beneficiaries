package com.example.beneficiaries.ui.beneficiariesdetails

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.beneficiaries.R
import com.example.beneficiaries.domain.model.Beneficiary
import com.example.beneficiaries.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class BeneficiaryDetailActivity : AppCompatActivity() {

    private lateinit var firstNameTextView: TextView
    private lateinit var lastNameTextView: TextView
    private lateinit var beneficiaryTypeTextView: TextView
    private lateinit var designationTextView: TextView
    private lateinit var ssnTextView: TextView
    private lateinit var dobTextView: TextView
    private lateinit var phoneNumberTextView: TextView
    private lateinit var addressTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_beneficiary_detail_acitivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ssn = intent.getStringExtra(Constants.SSN)
        val viewModel: BeneficiaryDetailViewModel by viewModels()
        if (ssn != null) {
            viewModel.updateBeneficiary(ssn)
        }

        initViews(viewModel.beneficiary)
    }

    private fun initViews(beneficiary: Beneficiary) {
        firstNameTextView = findViewById(R.id.beneficiary_first_name_tv)
        lastNameTextView = findViewById(R.id.beneficiary_last_name_tv)
        beneficiaryTypeTextView = findViewById(R.id.beneficiary_type_tv)
        designationTextView = findViewById(R.id.designation_tv)
        ssnTextView = findViewById(R.id.ssn_tv)
        dobTextView = findViewById(R.id.dob_tv)
        phoneNumberTextView = findViewById(R.id.phone_number_tv)
        addressTextView = findViewById(R.id.address_tv)

        firstNameTextView.text = beneficiary.firstName
        lastNameTextView.text = beneficiary.lastName
        beneficiaryTypeTextView.text = beneficiary.beneType
        designationTextView.text = if (beneficiary.designationCode == "P") "Primary" else "Contingent"
        ssnTextView.text = beneficiary.socialSecurityNumber

        val pattern = "MMddyyyy"
        val timeStamp = beneficiary.dateOfBirth
        val dateFormat = DateTimeFormatter.ofPattern(pattern)
        val date = LocalDate.parse(timeStamp, dateFormat)

        dobTextView.text = date.toString()
        phoneNumberTextView.text = beneficiary.phoneNumber
        addressTextView.text = beneficiary.beneficiaryAddress?.toString()
    }
}
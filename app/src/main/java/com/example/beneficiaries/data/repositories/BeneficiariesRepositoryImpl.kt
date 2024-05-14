package com.example.beneficiaries.data.repositories

import android.content.Context
import com.example.beneficiaries.domain.model.Beneficiary
import com.example.beneficiaries.domain.repository.BeneficiariesRepository
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class BeneficiariesRepositoryImpl @Inject constructor(
    gson: Gson,
    @ApplicationContext private val context: Context
) : BeneficiariesRepository {
    //read file and provide required data to ui or domain layer
    private var inputStream: InputStream = context.assets.open("Beneficiaries.json")

    private val jsonString = inputStream.bufferedReader().use { it.readText() }

    private val jsonResponse = gson.fromJson(jsonString, Array<Beneficiary>::class.java)


    override fun getBeneficiariesList(): List<Beneficiary> {
        return jsonResponse.asList()
    }

    override fun getBeneficiaryDetail(ssn: String): Beneficiary {
        var beneficiary = Beneficiary()

        for (item in jsonResponse.asList()) {
            if (item.socialSecurityNumber?.equals(ssn) == true) {
                beneficiary = item
            } else {
                Beneficiary()
            }
        }
        return beneficiary
    }

    private fun initInputStream() {
        try {
            inputStream = context.assets.open("Beneficiaries.json")
        } catch (e: IOException) {
            e.printStackTrace();
        }
    }
}
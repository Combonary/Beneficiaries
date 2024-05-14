package com.example.beneficiaries.ui.beneficiarieslist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beneficiaries.R
import com.example.beneficiaries.ui.beneficiariesdetails.BeneficiaryDetailActivity
import com.example.beneficiaries.ui.beneficiarieslist.adapters.BeneficiaryAdapter
import com.example.beneficiaries.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeneficiariesListActivity : AppCompatActivity() {

    private lateinit var beneficiariesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_beneficiaries_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //initialize view model
        val viewModel: BeneficiariesListViewModel by viewModels()
        //setup recyclerview and populate data
        val adapter = BeneficiaryAdapter(viewModel.beneficiaries)
        beneficiariesRecyclerView = findViewById(R.id.beneficiaries_recycler_view)
        beneficiariesRecyclerView.layoutManager = LinearLayoutManager(this)
        beneficiariesRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object: BeneficiaryAdapter.ClickListener{
            override fun onItemClicked(v: View, position: Int) {
                adapter.getSSN(position)?.let { openDetails(it) }
            }

        })
    }

    /**
     * navigate to details screen
     */
    private fun openDetails(ssn: String) {
        val intent = Intent(this, BeneficiaryDetailActivity::class.java)
        intent.putExtra(Constants.SSN, ssn)
        startActivity(intent)
    }
}
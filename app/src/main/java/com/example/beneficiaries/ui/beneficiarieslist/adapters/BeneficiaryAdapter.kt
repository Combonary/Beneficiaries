package com.example.beneficiaries.ui.beneficiarieslist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beneficiaries.R
import com.example.beneficiaries.domain.model.Beneficiary

class BeneficiaryAdapter(private val beneficiariesList: List<Beneficiary>): RecyclerView.Adapter<BeneficiaryAdapter.BeneficiaryViewHolder>(){

    private var itemClickedListener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeneficiaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.beneficiary_view_holder, parent, false)
        return BeneficiaryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return beneficiariesList.size
    }

    override fun onBindViewHolder(holder: BeneficiaryViewHolder, position: Int) {
        holder.firstNameTextView.text = beneficiariesList[position].firstName
        holder.lastNameTextView.text = beneficiariesList[position].lastName
        holder.beneTypeNameTextView.text = beneficiariesList[position].beneType
        holder.designationTextView.text = if (beneficiariesList[position].designationCode == "P") "Primary" else "Contingent"
    }

    fun setOnItemClickListener(itemClickedListener: ClickListener) {
        this.itemClickedListener = itemClickedListener
    }

    fun getSSN(position: Int): String? {
        return beneficiariesList[position].socialSecurityNumber
    }


    inner class BeneficiaryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var firstNameTextView: TextView
        var lastNameTextView: TextView
        var beneTypeNameTextView: TextView
        var designationTextView: TextView

        init {
            firstNameTextView = itemView.findViewById(R.id.beneficiary_first_name_tv)
            lastNameTextView = itemView.findViewById(R.id.beneficiary_last_name_tv)
            beneTypeNameTextView = itemView.findViewById(R.id.beneficiary_type_tv)
            designationTextView = itemView.findViewById(R.id.designation_tv)

            if (itemClickedListener != null) {
                itemView.setOnClickListener(this)
            }
        }

        override fun onClick(v: View?) {
            if (v != null) {
                itemClickedListener?.onItemClicked(v, adapterPosition)
            }
        }
    }

    interface ClickListener {
        fun onItemClicked(v: View, position: Int)
    }
}
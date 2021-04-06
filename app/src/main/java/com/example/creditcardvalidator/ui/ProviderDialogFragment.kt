package com.example.creditcardvalidator.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ProviderDialogFragment:DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext()).setMessage("Payment Successfull").setPositiveButton("Ok"){_,_->

        }.create()
    }
    companion object{
        const val TAG="DIALOG"
    }
}
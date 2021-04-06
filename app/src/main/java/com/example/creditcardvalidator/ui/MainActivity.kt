package com.example.creditcardvalidator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import com.example.creditcardvalidator.databinding.ActivityMainBinding
import com.example.creditcardvalidator.utils.CreditCardUtil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var cardNumber: EditText
    lateinit var cvv: EditText
    lateinit var expiration: EditText
    lateinit var lastName: EditText
    lateinit var firstName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lastName = binding.lastName
        firstName = binding.firstName
        cvv = binding.cvv
        cardNumber = binding.cardNumber
        expiration = binding.expiration
        setEdiTextListener()
        binding.submitBtn.setOnClickListener {
            if (CreditCardUtil.checkFieldEmpty(
                    cardNumber.text.toString(),
                    cvv.text.toString(),
                    expiration.text.toString(),
                    firstName.text.toString(),
                    lastName.text.toString()
                )
            ) {
                Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                if (CreditCardUtil.checkCardValidation(cardNumber.text.toString())) {
                    if (CreditCardUtil.checkExpiryDate(expiration.text.toString())) {
                        if (CreditCardUtil.checkCardAuthenticity(
                                cardNumber.text.toString(),
                                cvv.text.toString(),
                                firstName.text.toString(),
                                lastName.text.toString()
                            )
                        ) {
//                            Show Dialog
                            ProviderDialogFragment().show(
                                supportFragmentManager,
                                ProviderDialogFragment.TAG
                            )
                        } else {
                            if (CreditCardUtil.firstNameError && CreditCardUtil.lastNameError) {
                                firstName.setError("Invalid First Name")
                                lastName.setError("Invalid Last Name")
                            } else if (CreditCardUtil.firstNameError) {
                                firstName.setError("Invalid First Name")
                            } else if (CreditCardUtil.cvvError) {
                                cvv.setError("Invalid Cvv")
                            } else {
                                lastName.setError("Invalid Last Name")
                            }
                        }
                    } else {
                        expiration.setError("Invalid Date")
                    }
                } else {
                    cardNumber.setError("CardNumber is Incorrect")
                }
            }

        }

    }


    private fun setEdiTextListener() {
        binding.expiration.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, start: Int, removed: Int, added: Int) {
                if (start == 1 && start + added == 2 && p0?.contains('/') == false) {
                    binding.expiration.setText("$p0/")
                    binding.expiration.setSelection("${p0.toString()}/".length)
                } else if (p0 != null && !p0.contains("/") && p0.toString().length == 3) {
                    val oldString = p0.toString().replace("/", "")
                    val newString = oldString.substring(0, 2) + "/" + p0.get(2)
                    binding.expiration.setText(newString)
                    binding.expiration.setSelection(newString.length)
                } else if (start == 3 && start - removed == 2 && p0?.contains('/') == true) {
                    val newString = p0.toString().replace("/", "")
                    binding.expiration.setText(newString)
                    binding.expiration.setSelection(newString.length)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }


}
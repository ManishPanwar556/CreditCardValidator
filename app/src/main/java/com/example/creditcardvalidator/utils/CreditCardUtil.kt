package com.example.creditcardvalidator.utils

import java.util.*

object CreditCardUtil {
    var firstNameError: Boolean = false
    var lastNameError: Boolean = false
    var cvvError: Boolean = false
    var expirationError: Boolean = false
    fun checkCardValidation(cardNumber: String): Boolean {
        if (luhnAlgorithm(cardNumber)) {
            return true
        }
        return false
    }

    private fun luhnAlgorithm(cardNumber: String): Boolean {
        val length = cardNumber.length
        var sum = 0
        var isSecond = false
        for (i in length - 1 downTo 0 step 1) {
            var d: Int = (cardNumber[i] + "").toInt()
            if (isSecond) {
                d *= 2
            }
            sum += d / 10
            sum += d % 10
            isSecond = !isSecond
        }
        return (sum % 10 == 0)
    }

    fun checkFieldEmpty(
        cardNumber: String,
        cvv: String,
        expiration: String,
        firstName: String,
        lastName: String
    ): Boolean {
        if (cardNumber.isEmpty() || cvv.isEmpty() || expiration.isEmpty() || firstName.isEmpty() || lastName.isEmpty()
        ) {
            return true
        }
        return false
    }

    fun checkCardAuthenticity(
        cardNumber: String,
        cvv: String,
        firstName: String,
        lastName: String
    ): Boolean {
        var firstDigit = cardNumber.get(0).toString()
        var firstTwoDigit = cardNumber.substring(0, 2)
        var isAlphaFirst = firstName.checkStringAlphabetic(firstName)
        var isAlphaSecond = lastName.checkStringAlphabetic(lastName)
        if (isAlphaFirst && isAlphaSecond) {
            if ((firstDigit.equals("5") || firstDigit.equals("4"))) {
                if (cvv.length != 3) {
                    cvvError = true
                    return false
                }
                cvvError = false
                firstNameError = false
                lastNameError = false
                return true
            } else if (firstTwoDigit.equals("37") || firstTwoDigit.equals("34")) {
                if (cvv.length != 4) {
                    cvvError = true
                    return false
                }
                cvvError = false
                firstNameError = false
                lastNameError = false
                return true
            }
        } else {
//            both are incorrect
            if (!isAlphaFirst && !isAlphaSecond) {
                firstNameError = true
                lastNameError = true
            } else if (!isAlphaFirst) {
                firstNameError = true
            } else {
                lastNameError = true
            }
            cvvError = false
        }
        return false
    }

    fun checkExpiryDate(expiration: String): Boolean {
        if (expiration.length == 5) {
            var monthString = ""
            var yearString = ""
            var monthNumber = 0
            var yearNumber = 0
            var flag = false
            for (element in expiration) {
                if (element.equals('/')) {
                    flag = true
                } else if (flag) {
                    yearString += element
                } else {
                    monthString += element
                }
            }
            if (monthString.length != yearString.length) {
                return false
            }
            monthNumber = monthString.toInt()
            yearNumber = yearString.toInt()
            val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()
            val lastTwo = currentYear.substring(currentYear.length - 2).toInt()
            val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
//            year equal
            if (yearNumber == lastTwo) {
                if (monthNumber in 1..12 && monthNumber > currentMonth) {
                    return true
                }
            }
//            year not equal
            else if (lastTwo < yearNumber) {
                if (monthNumber in 1..12) {
                    return true
                }
            }


        }
        expirationError = true
        return false
    }

    fun String.checkStringAlphabetic(field: String): Boolean {
        for (ele in field) {
            if (ele.isLetter() || ele == ' ') {
                continue
            } else {
                return false
            }
        }
        return true
    }
}
package com.example.creditcardvalidator

import com.example.creditcardvalidator.utils.CreditCardUtil
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun test_inValidCardNumber() {
        val res = CreditCardUtil.checkCardValidation("4111111111111121")
        assertEquals(false, res)
    }
    @Test
    fun test_validCardNumber(){
        val res= CreditCardUtil.checkCardValidation("4111111111111111")
        assertEquals(true,res)
    }
    @Test
    fun test_validDetails_masterCard_returnTrue(){
        val res= CreditCardUtil.checkCardAuthenticity("5500000000000004","123","Man","Pan")
        assertEquals(res,true)
    }
    @Test
    fun test_invalidCvvDetails_masterCard_returnFalse(){
        val res= CreditCardUtil.checkCardAuthenticity("5500000000000004","1234","Man","Pan")
        assertEquals(res,false)
    }
    @Test
    fun test_validDetails_visaCard_returnTrue() {
        val res = CreditCardUtil.checkCardAuthenticity("4111111111111111", "123", "Man", "Pan")
        assertEquals(true, res)
    }

    @Test
    fun test_invalidCvvDetails_visaCard_returnFalse() {
        val res = CreditCardUtil.checkCardAuthenticity("4111111111111111", "1234", "Man", "Pan")
        assertEquals(false, res)
    }
    @Test
    fun test_validAmericanExpressCard_returnTrue(){
        val res= CreditCardUtil.checkCardAuthenticity("340000000000009","1234","Mani","Pan")
        assertEquals(true,res)
    }
    @Test
    fun test_inValidCvvAmericanExpressCard_returnFalse(){
        val res= CreditCardUtil.checkCardAuthenticity("340000000000009","123","Mani","Pan")
        assertEquals(false,res)
    }
    @Test
    fun test_invalidFirstName_returnFalse(){
        val res= CreditCardUtil.checkCardAuthenticity("4111111111111111","123","Manish1234","Pan")
        assertEquals(false,res)
    }
    @Test
    fun test_invalidLastName_returnFalse(){
        val res= CreditCardUtil.checkCardAuthenticity("4111111111111111","123","Manish","Pan123")
        assertEquals(false,res)
    }
    @Test
    fun test_nameWithSpace_returnTrue(){
        val res= CreditCardUtil.checkCardAuthenticity("4111111111111111","123","Manish Singh"," Panwar")
        assertEquals(true,res)
    }
    @Test
    fun test_invalidExpiryDate_returnFalse(){
        val res= CreditCardUtil.checkExpiryDate("12/20")
        assertEquals(false,res)
    }
    @Test
    fun test_invalidFormatExpiryDate_returnFalse(){
        val res= CreditCardUtil.checkExpiryDate("1/234")
        assertEquals(false,res)
    }

}
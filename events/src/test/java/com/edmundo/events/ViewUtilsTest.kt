package com.edmundo.events

import com.edmundo.events.extensions.isValidEmail
import com.edmundo.events.extensions.toFormattedDate
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import org.junit.Test

class ViewUtilsTest {

    @Test
    fun `long timestamp should return formatted data`() {

        val timestampDate = 1586836850L
        val realDate = "14/04/2020"

        val formattedDate = timestampDate.toFormattedDate("dd/MM/yyyy")
        assertEquals(realDate, formattedDate)
    }

    @Test
    fun `a valid email should return true`()  {

        val email = "edmundo312@gmail.com"
        val isValidEmail = email.isValidEmail()

        assert(isValidEmail)
    }

    @Test
    fun `an invalid email should return false`()  {

        val email = "teste"
        val isValidEmail = email.isValidEmail()

        assertFalse(isValidEmail)
    }
}
package com.sevenpeakssoftware.khinthirisoe.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    var currentFormat = "dd.MM.yyyy hh:mm"

    private fun changeDateFormat(
        currentFormat: String,
        requiredFormat: String,
        dateString: String
    ): String {

        var result = ""
        if (dateString.isEmpty()) {
            return result
        }

        val oldDateFormat = SimpleDateFormat(currentFormat, Locale.getDefault())
        val newDateFormat = SimpleDateFormat(requiredFormat, Locale.getDefault())
        var date: Date? = null
        try {
            date = oldDateFormat.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        if (date != null) {
            result = newDateFormat.format(date)
        }
        return result
    }

    fun checkDate(contentDateTime: String): String {

        val year = Calendar.getInstance().get(Calendar.YEAR)

        val date = contentDateTime.substring(0, contentDateTime.indexOf(' '))
        val time =
            contentDateTime.substring(contentDateTime.indexOf(' '), contentDateTime.length)

        val dateParts = date.split(".")
        val contentYear = dateParts[2]

        return if (year == contentYear.toInt()) {
            val newDate =
                changeDateFormat(currentFormat, "dd LLLL", contentDateTime)
            "$newDate $time"

        } else {
            val newDate =
                changeDateFormat(currentFormat, "dd LLLL yyyy", contentDateTime)
            "$newDate $time"
        }

    }

}
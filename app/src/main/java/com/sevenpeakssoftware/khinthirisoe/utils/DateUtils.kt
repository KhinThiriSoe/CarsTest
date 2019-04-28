package com.sevenpeakssoftware.khinthirisoe.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkDate(contentDateTime: String): String {

        val year = Calendar.getInstance().get(Calendar.YEAR)

        val date = contentDateTime.substring(0, contentDateTime.indexOf(' '))
        val time =
            contentDateTime.substring(contentDateTime.indexOf(' '), contentDateTime.length)

        val contentYear = LocalDate
            .parse(
                date,
                DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.US)
            )
            .year

        return if (year == contentYear) {
            val newDate =
                changeDateFormat("dd.MM.yyyy hh:mm", "dd LLLL ", contentDateTime)
            "$newDate $time"

        } else {
            val newDate =
                changeDateFormat("dd.MM.yyyy hh:mm", "dd LLLL yyyy", contentDateTime)
            "$newDate $time"
        }

    }

}
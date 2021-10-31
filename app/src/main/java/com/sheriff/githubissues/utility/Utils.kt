package com.sheriff.githubissues.utility

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {

        @SuppressLint("SimpleDateFormat")
        private val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

        @SuppressLint("SimpleDateFormat")
        private val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

        /**
         * calculateDateMonthYear
         */
        fun calculateDateMonthYear(time: String): String {
            val inputTime = inputFormat.parse(time)
            inputFormat.timeZone = TimeZone.getTimeZone("GMT") // convert to GMT
            val convertDateMonthYear = outputFormat.format(inputTime!!)
            val timeInMilliseconds = outputFormat.parse(convertDateMonthYear)!!
            val mTime: Calendar = Calendar.getInstance()
            mTime.timeInMillis = timeInMilliseconds.time
            val date_cal = SimpleDateFormat("dd")
            val month_date = SimpleDateFormat("MMM")
            val year_cal = SimpleDateFormat("yy")
            val date = date_cal.format(mTime.time)
            val month_name = month_date.format(mTime.time)
            val year = year_cal.format(mTime.time)
            return "$date $month_name $year"
        }
    }
}

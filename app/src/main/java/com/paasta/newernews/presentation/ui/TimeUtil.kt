package com.paasta.newernews.presentation.ui

import android.content.Context
import com.paasta.newernews.R
import java.text.DateFormat
import java.text.SimpleDateFormat

class TimeUtil {
    companion object {
        const val SEC = 60
        const val MIN = 60
        const val HOUR = 24

        fun timeStampString(context: Context, stringDate: String): String {
            val format = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            val regDate = format.parse(stringDate)

            val curTime = System.currentTimeMillis()
            val regTime = regDate.time
            var diffTime = (curTime - regTime) / 1000

            var msg = ""
            if (diffTime < SEC) msg = context.getString(R.string.time_now)
            else if (MIN.let { diffTime /= it; diffTime } < MIN) msg = diffTime.toString() + context.getString(R.string.time_min_ago)
            else if (HOUR.let { diffTime /= it; diffTime } < HOUR) msg = diffTime.toString() + context.getString(R.string.time_hours_ago)
            else msg = DateFormat.getDateInstance(DateFormat.MEDIUM).format(regDate)

            return msg
        }
    }
}
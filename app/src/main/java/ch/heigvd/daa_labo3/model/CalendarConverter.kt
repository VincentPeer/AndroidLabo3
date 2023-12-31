package ch.heigvd.daa_labo3.model

import androidx.room.TypeConverter
import java.util.*

class CalendarConverter {
    @TypeConverter
    fun toCalendar(dateLong: Long) = Calendar.getInstance().apply {
        time = Date(dateLong)
    }

    @TypeConverter
    fun fromCalendar(date: Calendar) = date.time.time // Long
}
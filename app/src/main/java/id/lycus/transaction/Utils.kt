package id.lycus.transaction

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        val TAG_STORAGE = "id.lycus.transaction.storage"
        val TAG_FLAGS = "id.lycus.transaction.flag"

        fun today(): String {
            return SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault()).format(Calendar.getInstance().time)
        }

        val rupiah = DecimalFormat.getCurrencyInstance() as DecimalFormat
        val format = DecimalFormatSymbols()

        init {
            format.currencySymbol = ""
            format.monetaryDecimalSeparator = ','
            format.groupingSeparator = '.'
            rupiah.decimalFormatSymbols = format
        }

        fun greeting(): String {
            val c = Calendar.getInstance()
            val timeOfDay = c.get(Calendar.HOUR_OF_DAY)

            when (timeOfDay) {
                in 0..11 -> return "Good Morning"
                in 12..15 -> return "Good Afternoon"
                in 16..20 -> return "Good Evening"
            }
            return "Good Night"
        }
    }
}
package id.lycus.transaction.model

import java.text.SimpleDateFormat
import java.util.*

enum class Flag { INCOME, OUTCOME }

data class Transaction(
    val flag: Flag,
    val amount: Long,
    val note: String,
    val date: Date
) {
    override fun toString(): String {
        return note
    }

    fun getDate(): String {
        return SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault()).format(date)
    }
}
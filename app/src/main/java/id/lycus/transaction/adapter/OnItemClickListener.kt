package id.lycus.transaction.adapter

import id.lycus.transaction.model.Transaction

interface OnItemClickListener {
    fun onItemClick(transaction: Transaction)
}

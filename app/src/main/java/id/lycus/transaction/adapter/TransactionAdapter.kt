package id.lycus.transaction.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.lycus.transaction.R
import id.lycus.transaction.model.Flag
import id.lycus.transaction.model.Transaction
import kotlinx.android.synthetic.main.item_transaction.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class TransactionAdapter(val item: List<Transaction>, val context: Context) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    var listener: OnItemClickListener? = null
    val rupiah = DecimalFormat.getCurrencyInstance() as DecimalFormat
    val format = DecimalFormatSymbols()

    init {
        format.currencySymbol = "Rp. "
        format.monetaryDecimalSeparator = ','
        format.groupingSeparator = '.'
        rupiah.decimalFormatSymbols = format
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val flag = view.flag
        val amount = view.amount
        val date = view.date
        val icon = view.icon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_transaction, parent, false))
    }

    override fun getItemCount(): Int = item.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TransactionAdapter.ViewHolder, position: Int) {
        val i = itemCount - position - 1

        holder.flag.text = item[i].flag.toString()
        holder.date.text = item[i].getDate()
        holder.itemView.setOnClickListener {
            listener?.onItemClick(item[i])
        }
        if (item[i].flag == Flag.OUTCOME) {
            holder.amount.text = "- ${rupiah.format(item[i].amount)}"
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.textRed))
            holder.icon.setImageResource(R.drawable.ic_piggy_bank_broken)
        } else {
            holder.amount.text = "+ ${rupiah.format(item[i].amount)}"
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
package id.lycus.transaction

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.hawk.Hawk
import id.lycus.transaction.adapter.OnItemClickListener
import id.lycus.transaction.adapter.TransactionAdapter
import id.lycus.transaction.model.Flag
import id.lycus.transaction.model.Transaction
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    override fun getLayout() = R.layout.activity_main

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        today.text = Utils.today()
        greeting.text = "${Utils.greeting()}, ${resources.getString(R.string.name)}"

        val transactions = Hawk.get<List<Transaction>>(Utils.TAG_STORAGE)
        amount.text = Utils.rupiah.format(balance(transactions))

        transaction_recycler.layoutManager = LinearLayoutManager(this)
        val adapter = TransactionAdapter(transactions, this)
        transaction_recycler.adapter = adapter
        adapter.setOnItemClickListener(object : OnItemClickListener {
            @SuppressLint("InflateParams")
            override fun onItemClick(transaction: Transaction) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                val dialog: View = layoutInflater.inflate(R.layout.dialog_note, null)
                dialog.find<EditText>(R.id.notes).setText(transaction.note)
                dialog.find<TextView>(R.id.date).text = transaction.getDate()
                builder.setView(dialog)
                builder.create().show()
            }
        })
    }

    private fun balance(transcations: List<Transaction>): Long {
        var income = 0L
        var outcome = 0L

        transcations.forEach {
            if (it.flag == Flag.INCOME) {
                income += it.amount
            } else {
                outcome += it.amount
            }
        }
        return income - outcome
    }

    fun income(view: View) {
        startActivity<InsertActivity>(
            Utils.TAG_FLAGS to Flag.INCOME
        )
    }

    fun outcome(view: View) {
        startActivity<InsertActivity>(
            Utils.TAG_FLAGS to Flag.OUTCOME
        )
    }
}

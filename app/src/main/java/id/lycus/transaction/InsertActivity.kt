package id.lycus.transaction

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.orhanobut.hawk.Hawk
import id.lycus.transaction.model.Flag
import id.lycus.transaction.model.Transaction
import kotlinx.android.synthetic.main.activity_insert.*
import org.jetbrains.anko.startActivity
import java.util.*

class InsertActivity : BaseActivity() {
    override fun getLayout() = R.layout.activity_insert

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        today.text = Utils.today()
        greeting.text = "${Utils.greeting()}, ${resources.getString(R.string.name)}"

        amountField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.length == 2 && s[0] == '0')
                    amountField.setText(s[1].toString())
                if (s.isBlank())
                    amountField.setText("0")
                amountField.setSelection(amountField.text.toString().length)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    fun save(view: View) {
        var transactions: ArrayList<Transaction> = Hawk.get<ArrayList<Transaction>>(Utils.TAG_STORAGE)
        transactions.add(
            Transaction(
                intent.getSerializableExtra(Utils.TAG_FLAGS) as Flag,
                amountField.text.toString().toLong(),
                note.text.toString(),
                Calendar.getInstance().time
            )
        )
        Hawk.put(Utils.TAG_STORAGE, transactions)

        startActivity<MainActivity>()
        finish()
    }
}

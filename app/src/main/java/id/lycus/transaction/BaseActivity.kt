package id.lycus.transaction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.hawk.Hawk
import id.lycus.transaction.model.Transaction
import java.util.ArrayList

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        Hawk.init(this).build()

        if (!Hawk.contains(Utils.TAG_STORAGE)) {
            val blank: List<Transaction> = ArrayList<Transaction>()
            Hawk.put(Utils.TAG_STORAGE, blank)
        }
    }

    abstract fun getLayout(): Int
}
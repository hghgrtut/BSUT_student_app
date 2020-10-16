package by.bsut.studapp.utils

import android.content.Context
import android.widget.Toast

object HelperFuns {
    fun showToast(context: Context, resId: Int) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
    }
}
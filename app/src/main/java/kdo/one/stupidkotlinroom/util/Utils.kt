package kdo.one.stupidkotlinroom.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(s: String) {
    Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
}

fun View.snackbar(s: String) {
    Snackbar.make(this, s, Snackbar.LENGTH_LONG).also {snackbar ->
        snackbar.setAction("ok") {snackbar.dismiss()}
    }.show()
}
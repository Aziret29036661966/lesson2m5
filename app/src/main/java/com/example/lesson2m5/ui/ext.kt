package com.example.lesson2m5.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

fun Context.dialogShow(unit: DialogInterface.OnClickListener) {
    val dialog = AlertDialog.Builder(
        this)
        .setCancelable(false)
        .setTitle(TITLE)
        .setMessage(MESSAGE)
        .setPositiveButton(YES, unit)
        .setNegativeButton(NO, unit)
        .create()
    dialog.show()
}

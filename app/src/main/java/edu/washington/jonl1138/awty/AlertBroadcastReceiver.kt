package edu.washington.jonl1138.awty

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlertBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("debugging", "Reached Broadcast Receiver")
        val phoneNumber = intent!!.getStringExtra("PHONE")
        val message = intent!!.getStringExtra("MESSAGE")
        Log.d("debugging", "Intent has been received " + intent.hasExtra("PHONE").toString())
        val completedMessage = phoneNumber + ": " + message
        val toast: Toast = Toast.makeText(context, completedMessage, Toast.LENGTH_LONG)
        toast.show()
    }
}
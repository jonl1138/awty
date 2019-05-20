package edu.washington.jonl1138.awty


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.widget.Toast

class AlertBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val phoneNumber = intent!!.getStringExtra("PHONE")
        val message = intent!!.getStringExtra("MESSAGE")
        val completedMessage = phoneNumber + ": " + message
        val toast: Toast = Toast.makeText(context, completedMessage, Toast.LENGTH_LONG)
        toast.show()

        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
    }
}
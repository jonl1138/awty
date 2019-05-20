package edu.washington.jonl1138.awty

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast

class AlertBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("debugging", "Reached Broadcast Receiver")
        val phoneNumber = intent!!.getStringExtra("PHONE")
        val message = intent!!.getStringExtra("MESSAGE")
        Log.d("debugging", "Message intent has been received " + intent.hasExtra("MESSAGE").toString())
        Log.d("debugging", "Phone intent has been received " + intent.hasExtra("PHONE").toString())
        val completedMessage = phoneNumber + ": " + message
        val toast: Toast = Toast.makeText(context, completedMessage, Toast.LENGTH_LONG)
        toast.show()

        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
    }
}
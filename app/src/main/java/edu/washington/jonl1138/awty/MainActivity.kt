package edu.washington.jonl1138.awty

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import android.util.Log


class MainActivity : AppCompatActivity() {

    private var alarmManager: AlarmManager? = null
    private var pendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set onchange listeners for user inputs
        val startButton = findViewById<Button>(R.id.button)
        val delay = findViewById<EditText>(R.id.delay_time)
        val message = findViewById<EditText>(R.id.message)
        val phoneNumber = findViewById<EditText>(R.id.phone_number)
        startButton.setEnabled(false)

        delay.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, s1:Int, s2:Int, c:Int) {
                onTextChanged(delay, message, phoneNumber, startButton)
            }
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })

        message.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, s1:Int, s2:Int, c:Int) {
                onTextChanged(delay, message, phoneNumber, startButton)
            }
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })

        phoneNumber.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, s1:Int, s2:Int, c:Int) {
                onTextChanged(delay, message, phoneNumber, startButton)
            }
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })

        startButton.setOnClickListener {
            if (startButton.text == "Start") {
                startButton.text = "Stop"
                setAlarm(delay.text.toString().toInt(), phoneNumber.text.toString(), message.text.toString())
            } else {
                startButton.text = "Start"
                stopAlarm()
            }
        }

    }

    // takes in identifiers for user-inputted fields
    // disables button if not are all filled, enables them if they are filled
    private fun onTextChanged(delay: EditText, message:EditText, phone_number:EditText, button: Button) {
        if (!delay.text.isEmpty() && !message.text.toString().isEmpty() && !phone_number.text.isEmpty()) {
            button.setEnabled(true)
        } else {
            button.setEnabled(false)
        }
    }

    private fun setAlarm(minutes: Int, phoneNumber: String, message: String) {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i = Intent(this, AlertBroadcastReceiver::class.java)
        i.putExtra("PHONE", phoneNumber)
        i.putExtra("MESSAGE", message)
        Log.d("debugging", "Message extra placed in intent: " + i.hasExtra("PHONE").toString())
        Log.d("debugging", "Message extra placed in intent: " + i.hasExtra("MESSAGE").toString())
        pendingIntent = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager!!.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() /*+ (minutes * 60000)*/, (minutes * 60000).toLong(), pendingIntent)

    }

    private fun stopAlarm() {
        if (alarmManager != null) {
            alarmManager!!.cancel(pendingIntent)
        }
    }

}

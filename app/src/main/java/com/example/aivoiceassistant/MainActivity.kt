package com.example.aivoiceassistant

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alan.alansdk.AlanCallback
import com.alan.alansdk.AlanConfig
import com.alan.alansdk.button.AlanButton
import com.alan.alansdk.events.EventCommand
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private var alanButton: AlanButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val config = AlanConfig.builder().setProjectId("7f9b3b0deead6cda998f05ed4816ca9b2e956eca572e1d8b807a3e2338fdd0dc/stage").build()
        alanButton = findViewById(R.id.alan_button)
        alanButton?.initWithConfig(config)
        val alanCallback: AlanCallback = object : AlanCallback() {
            /// Handle commands from Alan Studio
            override fun onCommand(eventCommand: EventCommand) {
                try {
                    val command = eventCommand.data
                    val commandName = command.getJSONObject("data").getString("command")
                    Log.d("AlanButton", "onCommand: commandName: $commandName")
//                    when (commandName) {
//                        "showMassage" -> {
//                            Snackbar.make(findViewById(android.R.id.content), "Hello", Snackbar.LENGTH_LONG).show()
//                        }
//                    }

                } catch (e: JSONException) {
                    e.message?.let { Log.e("AlanButton", it) }
                }

            }
        }
        alanButton?.registerCallback(alanCallback)
    }



}
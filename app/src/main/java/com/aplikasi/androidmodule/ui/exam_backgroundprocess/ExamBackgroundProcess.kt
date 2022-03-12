package com.aplikasi.androidmodule.ui.exam_backgroundprocess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.aplikasi.androidmodule.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class ExamBackgroundProcess : AppCompatActivity() {
    private lateinit var lblInfo: TextView
    private lateinit var btnExecutor: Button
    private lateinit var btnCoroutine: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_process)

        lblInfo = findViewById(R.id.btnExecutor)
        btnExecutor = findViewById(R.id.btnExecutor)
        btnCoroutine = findViewById(R.id.btnCoroutine)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        // theard menggunakan executor
        btnExecutor.setOnClickListener {
            executor.execute {
                try {
                    //simulate process in background thread
                    for (i in 0..10) {
                        Thread.sleep(500)
                        val percentage = i * 10
                        handler.post {
                            //update ui in main thread
                            if (percentage == 100) {
                                lblInfo.text = "Task Completed"
                            } else {
                                lblInfo.text = String.format("Compresssing %d %%", percentage)
                            }
                        }
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

        // theard menggunakan coroutine
        btnCoroutine.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Default) {
                //simulate process in background thread
                for (i in 0..10) {
                    delay(500)
                    val percentage = i * 10
                    withContext(Dispatchers.Main) {
                        //update ui in main thread
                        if (percentage == 100) {
                            lblInfo.text = "Task Completed"
                        } else {
                            lblInfo.text = String.format("Compresssing %d %%", percentage)
                        }
                    }
                }
            }
        }
    }
}
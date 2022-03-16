package com.aplikasi.androidmodule.ui.exam_activityresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aplikasi.androidmodule.databinding.ActivityExamResultBinding
import com.aplikasi.androidmodule.databinding.ActivityHttpApiBinding

class ExamActivityResult : AppCompatActivity() {
    private lateinit var binding: ActivityExamResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mode = intent.getStringExtra("mode").toString()

        binding.btnSetResult.setOnClickListener {
            // set result activity
            val intent = Intent()
            intent.putExtra("hasil_result","nama adalah : ${mode}")
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}
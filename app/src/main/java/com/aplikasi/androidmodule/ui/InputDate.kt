package com.aplikasi.androidmodule.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aplikasi.androidmodule.databinding.ActivityExamInterfaceBinding
import com.aplikasi.androidmodule.databinding.ActivityHttpApiBinding
import com.aplikasi.androidmodule.databinding.ActivityInputDateBinding
import com.aplikasi.androidmodule.utils.ModuleGlobal
import com.aplikasi.androidmodule.utils.ViewInputDate
import java.util.*

class InputDate : AppCompatActivity() {
    private lateinit var binding: ActivityInputDateBinding
    private var dialogInputDate = ViewInputDate()

    private var dateBirth : Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        dialogInputDate.init(this)

        dateBirth = Calendar.getInstance() // set now
        binding.edtInputDate.setOnClickListener {
            dialogInputDate.show(dateBirth) {
                dateBirth = it
                binding.edtInputDate.setText(ModuleGlobal().calenderToStringINA(dateBirth!!))
                binding.lblDate.text = "format kirim ke server"+ModuleGlobal().calenderToString(dateBirth!!)
            }
        }
    }
}
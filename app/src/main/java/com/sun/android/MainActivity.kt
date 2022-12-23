package com.sun.android

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sun.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val getReply = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK){
                val resultValue = result.data?.extras?.getString("exReply")
                val mReply: TextView = findViewById(R.id.text_message_reply)
                mReply.setText(resultValue)
            }
        }
        binding.buttonMain.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            val message = binding.editTextMain.text.toString()
            intent.putExtra("name", message)
            binding.editTextMain.text = null
            getReply.launch(intent)
        }

    }
}

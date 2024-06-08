package com.suitmedia.testportal.ui.firstscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.suitmedia.testportal.R
import com.suitmedia.testportal.databinding.ActivityFirstBinding
import com.suitmedia.testportal.ui.secondscreen.SecondActivity

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonCheck.setOnClickListener{
            val inputText = binding.etPalindrome.text.toString()
            val isPalindrome = isPalindrome(inputText)
            var message = if (isPalindrome) "isPalindrome" else "not palindrome"
            if (inputText.isEmpty()) {
                message = "Please input text"
            }
            showDialogAlert("Palindrome Checker", message)
        }

        binding.buttonNext.setOnClickListener {
            if (binding.etName.text.toString().isEmpty()) {
                showDialogAlert("Warning", "Please input your name")
            }else{
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra(SecondActivity.TAG, binding.etName.text.toString())
                startActivity(intent)
            }
        }
    }

    private fun isPalindrome(input: String): Boolean {
        val cleanedInput = input.replace(" ", "").lowercase()
        return cleanedInput == cleanedInput.reversed()
    }

    private fun showDialogAlert(title: String, message: String){
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
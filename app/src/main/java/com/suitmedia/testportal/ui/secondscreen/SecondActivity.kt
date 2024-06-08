package com.suitmedia.testportal.ui.secondscreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.suitmedia.testportal.R
import com.suitmedia.testportal.data.remote.response.DataItem
import com.suitmedia.testportal.databinding.ActivitySecondBinding
import com.suitmedia.testportal.ui.thirdscreen.ThirdActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var selectedUser: DataItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setToolBar()

        binding.tvName.text = intent.getStringExtra(TAG)

        binding.ivPressBack.setOnClickListener {
            finish()
        }

        binding.buttonNext.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            @Suppress("DEPRECATION")
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    private fun setToolBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.show()
        supportActionBar?.title = ""
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                @Suppress("DEPRECATION")
                selectedUser = data?.getSerializableExtra("selected_user") as DataItem?
                binding.tvSelected.text = getString(R.string.user_name_format, selectedUser?.firstName, selectedUser?.lastName)
            }
        }
    }

    companion object {
        const val TAG = "SecondActivity"
        const val REQUEST_CODE = 100
    }
}
package com.suitmedia.testportal.ui.thirdscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.testportal.R
import com.suitmedia.testportal.adapter.LoadingStateAdapter
import com.suitmedia.testportal.adapter.UserAdapter
import com.suitmedia.testportal.databinding.ActivityThirdBinding
import com.suitmedia.testportal.ui.ViewModelFactory
import com.suitmedia.testportal.data.Result
import com.suitmedia.testportal.data.remote.response.DataItem

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var viewModel: ThirdViewModel
    private var selectedUser: DataItem? = null
    private var currentPage = 1
    private val perPage = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val factory: ViewModelFactory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[ThirdViewModel::class.java]

        setToolBar()
        setupUsersList()

        binding.ivPressBack.setOnClickListener {
            finish()
        }

    }

    private fun setToolBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.show()
        supportActionBar?.title = ""
    }

    private fun setupUsersList(){
        val userAdapter = UserAdapter()
        val layout = LinearLayoutManager(this)
        binding.rvListUser.apply {
            layoutManager = layout
            setHasFixedSize(true)
            adapter = userAdapter
            adapter = userAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                   userAdapter.retry()
                }
            )
        }

        viewModel.getUsers(currentPage, perPage).observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    result.data.observe(this) { pagingData ->
                        userAdapter.submitData(lifecycle, pagingData)
                    }
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getUsers(currentPage, perPage).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.swipeRefreshLayout.isRefreshing = true
                    }
                    is Result.Success -> {
                        binding.swipeRefreshLayout.isRefreshing = false
                        result.data.observe(this) { pagingData ->
                            userAdapter.submitData(lifecycle, pagingData)
                        }
                    }
                    is Result.Error -> {
                        binding.swipeRefreshLayout.isRefreshing = false
                        Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        userAdapter.onClick = {
            selectedUser = it
            val intent = Intent()
            val outState = Bundle()
            outState.putSerializable("selected_user", selectedUser)
            intent.putExtras(outState)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
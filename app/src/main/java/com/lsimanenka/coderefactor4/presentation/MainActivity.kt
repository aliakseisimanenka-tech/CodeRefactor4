package com.lsimanenka.coderefactor4.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lsimanenka.coderefactor4.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import androidx.activity.viewModels
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlin.jvm.java

//TODO 5: Why not a separate Activity?
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()
    //TODO 2: Check google guideline for viewBinding
    private lateinit var binding : ActivityMainBinding

    //TODO 6: Why remove lazy?
    private val adapter : UserAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel.getUsers()
        setupViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {state ->
                    when (state) {
                        is UserState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.errorTextView.isVisible = false
                        }
                        is UserState.Success -> {
                            binding.progressBar.isVisible = false
                            adapter.submitList(state.users)
                        }
                        is UserState.Error -> {
                            binding.progressBar.isVisible = false
                            adapter.submitList(emptyList())
                            binding.errorTextView.isVisible = true
                            //TODO 3: All resources in strings.xml
                            binding.errorTextView.text = "Ошибка: ${state.exception.message}"
                            // Или можно показать Snackbar / Toast
                        }
                    }
                }
            }
        }
    }

    private fun setupViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        //TODO 7: What will you do, if I want migrate this to Fragments (theoretically)
        binding.button.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(EXTRA_USER_NAME, "John Doe2")
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_USER_NAME = "USER_NAME"
    }
}
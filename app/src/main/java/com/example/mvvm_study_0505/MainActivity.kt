package com.example.mvvm_study_0505

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm_study_0505.databinding.ActivityMainBinding
import com.example.mvvm_study_0505.factory.MainViewModelFactory
import com.example.mvvm_study_0505.model.User
import com.example.mvvm_study_0505.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        subscribeUi(binding)
    }

    private fun subscribeUi(binding: ActivityMainBinding) {
        var factory = MainViewModelFactory()
        var viewModel: MainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        viewModel.clickConverter.observe(this, Observer {
            Toast.makeText(this, "${binding.user?.name}, ${binding.user?.address}", Toast.LENGTH_SHORT).show()
        })

        binding.user = User("May", "Seoul", R.drawable.profile_sample)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
    }
}

package com.example.w2s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.w2s.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.task1.setOnClickListener(n->{
           Intent intent = new Intent(this,TMainActivity.class);
           startActivity(intent);
        });
        binding.task2.setOnClickListener(n->{
            Intent intent = new Intent(this,MapsActivity.class);
            startActivity(intent);
        });
    }
}
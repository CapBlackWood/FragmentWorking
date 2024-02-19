package com.example.fragmentworking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentworking.fragments.EnterFragment;
import com.example.fragmentworking.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository.fragmentManager = getSupportFragmentManager();  // инициализируем менеджер фагментов

        if(Repository.fragment == null){
            Repository.fragment = new MainFragment();
            Repository.fragmentManager.beginTransaction()
                    .add(R.id.fragment_container,Repository.fragment)
                    .commit();
        }

    }
}


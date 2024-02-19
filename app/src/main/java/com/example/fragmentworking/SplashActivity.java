package com.example.fragmentworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*
        Правило нельзя создавать объекты абстрактных классов и интерфейсов обычным способом, т.к. они
        содержат абстрактные (нереализованные методы), но можно создать объект абстрактного класс или
        интерфейса при помощи анонимного класса по факту мы берем и реализуем абстрактные методы при
        создании объекта
        */


        Runnable runnable = new Runnable() {                 //Создаем объект интерфейса Runnable при помощи анонимного класса
            @Override
            public void run() {                              // Абстрактный метод интерфейса Runnable
                try {                                        // Начинаем блок проверки кода, который может некорректно работать
                    Thread.sleep(3000);
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class); //Указываем активность которую хотим вызвать
                    startActivity(intent);                                                             // вызываем другую активность
                    finish();                                                                          // закрываем текущую активность
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                    // код, который будет вызван в случае ошибки в блоке try
                }
            }
        };
        Thread thread = new Thread(runnable);     //Создаю задачу
        thread.start();                           //запускаю задачу

    }
}

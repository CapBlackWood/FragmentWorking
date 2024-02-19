package com.example.fragmentworking;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class Repository {
    public static FragmentManager fragmentManager;
    public static Fragment fragment;

    public static ArrayList<Integer> nubmers;

    // Если есть необходимость провести инициализацию статических данных в классе, это можно сделать при помощи блока static:
    static { // Блок static будет вызван при загрузке класса
        nubmers = new ArrayList<>();  // Выделение памяти для массива
        nubmers.add(1);               // заполнение

        nubmers.add(2);
    }

}

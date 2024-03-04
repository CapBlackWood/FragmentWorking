package com.example.fragmentworking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmentworking.R;
import com.example.fragmentworking.Repository;

public class BillFragment extends Fragment {

    private TextView txtItems;
    private TextView txtPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bill_fragment, container, false);
        txtItems = view.findViewById(R.id.items);
        txtPrice = view.findViewById(R.id.txt_bill);
        setItems();
        calculate();
        return view;
    }

    /*
    Антипаттерн(как не надо делать) - Божественный метод, т.е. метод выполняющий более одной задачи - всемогущий метод
    Паттерн(Как надо делать) SOLID требует единой ответственности - каждый метод выполняет одну задачу
     */

    private void calculate() {
        int sum = 0;
        for (int i = 0; i < Repository.items.size(); i++) {
            sum+=Repository.items.get(i).getPrice();
        }
        txtPrice.setText("Сумма чека: "+ sum);
    }

    private void setItems() {
        String str = "";
        for (int i = 0; i < Repository.items.size(); i++) {
            str += Repository.items.get(i).getTitle() + "  " + Repository.items.get(i).getPrice() + "\n";
        }
        txtItems.setText(str);
    }
}

package com.example.fragmentworking.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentworking.MapActivity;
import com.example.fragmentworking.R;
import com.example.fragmentworking.Repository;
import com.example.fragmentworking.adapter.ChooseAdapter;
import com.example.fragmentworking.models.Model;

import java.util.ArrayList;

public class EnterFragment extends Fragment implements  View.OnTouchListener{

    private RecyclerView recyclerView;

    private FragmentManager fragmentManager;
    private Fragment fragment;

    private Button btnBill;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_fragment, container, false);
        init(view);
        return view;
    }

    private void init(View view) {                               //Метод для создания начальных значений
        recyclerView = view.findViewById(R.id.list_items);
        btnBill = view.findViewById(R.id.btn_bill);
        ArrayList<Model> models = new ArrayList<>();                   // Список моделей которые будут выводиться на экран
        models.add(new Model(R.drawable.cocktails, "Напитки"));
        models.add(new Model(R.drawable.menu, "Меню"));
        models.add(new Model(R.drawable.map, "Столики"));

        ChooseAdapter adapter = new ChooseAdapter(getActivity(), models);  // Адаптер для связи списками моделей и разметки
        recyclerView.setAdapter(adapter);    //Назначаем адаптер списку разметки
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // Назначаем менеджера компановки (способо отображения элементов списка)
        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repository.fragment = new BillFragment();
                Repository.fragmentManager
                        .beginTransaction()
                        .addToBackStack("enter")
                        .replace(R.id.fragment_container, Repository.fragment)
                        .commit();
            }
        });
        adapter.setListener(new ChooseAdapter.OnItemClickListener() {  //Назначаем слушателя списка
            @Override
            public void onItemClick(int position) {
                if (position == 2){
                    Intent intent = new Intent(getActivity(), MapActivity.class);
                    startActivity(intent);
                } else if (position==0) {
                    fragmentManager = getParentFragmentManager(); // Получаем родительский менеджер фрагментов
                    fragment = new DrinksFragment();
                    fragmentManager.beginTransaction()
                            .addToBackStack("Enter")        // Сохраняем фрагмент
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }
                else if (position==1){
                    fragmentManager = getParentFragmentManager(); // Получаем родительский менеджер фрагментов
                    fragment = new FoodFragment();
                    fragmentManager.beginTransaction()
                            .addToBackStack("Enter")
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}

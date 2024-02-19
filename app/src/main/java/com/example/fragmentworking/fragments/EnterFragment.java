package com.example.fragmentworking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentworking.R;
import com.example.fragmentworking.adapter.ChooseAdapter;
import com.example.fragmentworking.models.Model;

import java.util.ArrayList;

public class EnterFragment extends Fragment {

    private RecyclerView recyclerView;

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
        ArrayList<Model> models = new ArrayList<>();                   // Список моделей которые будут выводиться на экран
        models.add(new Model(R.drawable.cocktails, "Напитки"));
        models.add(new Model(R.drawable.menu, "Меню"));
        models.add(new Model(R.drawable.map, "Столики"));

        ChooseAdapter adapter = new ChooseAdapter(getActivity(), models);  // Адаптер для связи списками моделей и разметки
        recyclerView.setAdapter(adapter);    //Назначаем адаптер списку разметки
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // Назначаем менеджера компановки (способо отображения элементов списка)
    }
}

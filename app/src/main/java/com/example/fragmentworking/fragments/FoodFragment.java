package com.example.fragmentworking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentworking.R;
import com.example.fragmentworking.Repository;
import com.example.fragmentworking.adapter.MenuAdapter;
import com.example.fragmentworking.models.MenuItem;

import java.util.ArrayList;

public class FoodFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Button btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.food_list);
        btn = view.findViewById(R.id.btnBack);
        btn.setOnClickListener(this);
        ArrayList<MenuItem> foods = new ArrayList<>();
        foods.add(new MenuItem("Салат Цезарь", R.drawable.cezar, "Вкуcный салат", 500));
        foods.add(new MenuItem("Шакшука", R.drawable.shakshuka, "Топовая яичница с овощами и травами", 450));
        foods.add(new MenuItem("Картошка фри", R.drawable.free, "Обычная картошка фри", 100));
        foods.add(new MenuItem("Пицца", R.drawable.pizza, "Ингридиенты выбираете Вы", 600));

        ArrayList<MenuItem> orders = new ArrayList<>();

        MenuAdapter adapter = new MenuAdapter(getActivity(), foods);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        adapter.setListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                orders.add(foods.get(position));
                Toast.makeText(getActivity(),"Сумма заказа: " + String.valueOf(countSum(orders)), Toast.LENGTH_LONG).show();
            }
        });
    }

    private int countSum(ArrayList<MenuItem> orders) {
        int res = 0;
        for (int i = 0; i < orders.size(); i++) {
            res += orders.get(i).getPrice();
        }
        return res;
    }

    @Override
    public void onClick(View v) {
        Repository.fragmentManager.popBackStack();
    }
}

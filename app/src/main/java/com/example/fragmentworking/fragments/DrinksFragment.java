package com.example.fragmentworking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentworking.R;
import com.example.fragmentworking.adapter.MenuAdapter;
import com.example.fragmentworking.models.MenuItem;

import java.util.ArrayList;

public class DrinksFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drinks_fragment, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.drinks_list);

        ArrayList<MenuItem> drinks = new ArrayList<>();
        drinks.add(new MenuItem("Пина Колада", R.drawable.cocktail_1, "Легкий и невероятно " +
                "приятный напиток. С легким послевкусием", 400));
        drinks.add(new MenuItem("Золотая Фея", R.drawable.cocktail_2, "Сладкий нежный " +
                "напиток с золотистой посыпкой", 500));
        drinks.add(new MenuItem("Апероль Спритц", R.drawable.cocktail_3, "Легкая классика, " +
                "которая никого не оставит равнодушным", 300));
        drinks.add(new MenuItem("Гранатовый Спритц", R.drawable.cocktail_4, "Классика, " +
                "но на гранатовой основе ", 450));
        ArrayList<MenuItem> orders = new ArrayList<>();
        MenuAdapter adapter = new MenuAdapter(getActivity(), drinks);
        adapter.setListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                orders.add(drinks.get(position));          // Добавляем в заказы напиток
                Toast.makeText(getActivity(), "Сумма заказа: " + String.valueOf(countSum(orders)),
                        Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

    }

    private int countSum(ArrayList<MenuItem> orders) {
        int res = 0;
        for (int i = 0; i < orders.size(); i++) {
            res += orders.get(i).getPrice();
        }
        return res;
    }
}

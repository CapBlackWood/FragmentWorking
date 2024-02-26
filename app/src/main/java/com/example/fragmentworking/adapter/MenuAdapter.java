package com.example.fragmentworking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentworking.R;
import com.example.fragmentworking.models.MenuItem;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    public interface OnItemClickListener{  //Интерфейс для обработки нажатий на элемент списка
        void onItemClick(int position);
    }

    private LayoutInflater inflater;    // нужен для заполнения экрана
    private ArrayList<MenuItem> items;  // То чем будем заполнять экран

    private OnItemClickListener listener;
    /**
     * Конструктор класса
     * @param context контекст позволяет уточнить в каком экране мы находимся
     * @param items то, что союираемся выводить
     */
    public MenuAdapter(Context context,ArrayList<MenuItem> items) {
        this.items = items;
        inflater = LayoutInflater.from(context);   // Уточняем нюансы где работаем
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
        MenuItem item = items.get(position);
        holder.itemImg.setImageResource(item.getImg());
        holder.titleTxt.setText(item.getTitle());
        holder.descriptionTxt.setText(item.getDescription());
        holder.priceTxt.setText(String.valueOf(item.getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {  // Обработка элемента списка

        public ImageView itemImg;
        public TextView titleTxt;
        public TextView descriptionTxt;
        public TextView priceTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.menu_img);
            titleTxt = itemView.findViewById(R.id.item_title);
            descriptionTxt = itemView.findViewById(R.id.item_description);
            priceTxt = itemView.findViewById(R.id.item_price);
        }
    }
}

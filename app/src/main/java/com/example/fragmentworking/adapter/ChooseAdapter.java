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
import com.example.fragmentworking.models.Model;

import java.util.ArrayList;

// Наследуемся от внутреннего класса ReciclerView - Adapter, в скобочках привязываем к нему ViewHolder
public class ChooseAdapter extends RecyclerView.Adapter<ChooseAdapter.ViewHolder> {

    public interface OnItemClickListener{  //Интерфейс для обработки нажатий на элемент списка
        void onItemClick(int position);
    }

    private OnItemClickListener listener;  // Экземпляр интерфейса для обработки нажатий на элемент списка
    private LayoutInflater inflater;                   // Создаем поля классов для доступа к ним в пределах классов

    private ArrayList<Model> models;

    // Создаем объект, в который передаем модели, чтобы их отображать пользователю и контекст,чтобы понимать где выводить данные
    public ChooseAdapter(Context context, ArrayList<Model> models) {
        this.models = models;
        inflater = LayoutInflater.from(context);
    }

    /**
     * Слушатель нажатий на элемент списка
     * @param listener экземпляр интерфейса-слушателя
     */
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // При создании объекта этот метод создаст ViewHolder
    @NonNull
    @Override
    public ChooseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.choose_item, parent, false);   //Привязываемся к рразметке - получаем элементы в которые выводим модели
        return new ViewHolder(view);     // Передаем в разметку в класс который выведет в нее модель
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseAdapter.ViewHolder holder, int position) {   //Метод отвечает за связку модели и виджетов
        Model model = models.get(position);                   // Получаем индекс модели для конкретного элемента списка
        holder.textView.setText(model.getTitle());            // выводим текст в названии элемента списка
        holder.imageView.setImageResource(model.getImg());    // выводим картинку в названии элемента списка
        holder.itemView.setOnClickListener(new View.OnClickListener() { // добавляем слушателя для всего элемента списка
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
        return models.size();                                // Метод возвращает размер списка
    }

    public class ViewHolder extends RecyclerView.ViewHolder {   // Класс для управления виджетами

        ImageView imageView;                                    // Виджеты

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.choose_img);
            textView = itemView.findViewById(R.id.choose_txt);
        }
    }
}

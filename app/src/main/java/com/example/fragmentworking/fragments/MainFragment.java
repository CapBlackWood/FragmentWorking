package com.example.fragmentworking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragmentworking.AgeException;
import com.example.fragmentworking.MainActivity;
import com.example.fragmentworking.R;
import com.example.fragmentworking.Repository;

public class MainFragment extends Fragment {
    private Button btn;
    private EditText edt;
    private TextView txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container,false);
        init(view);                            //Привязка разметки к коду

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age;
                //TODO Решение ДЗ
                /*try {
                    age = Integer.parseInt(edt.getText().toString());         //Получаем возраст из edittext для этого преобразуем(парсим) текст в число
                } catch (Exception ex){
                    Toast.makeText(MainActivity.this, "Некорректный ввод", Toast.LENGTH_SHORT).show();
                    age=-1; // Выполняем необходимую инициализацию, в случае ошибки присваиваем заведомо ложное число
                }*/
                String str = edt.getText().toString();

                if (check(str)) {
                    age = Integer.parseInt(str);        // парсим целое число из строки
                } else {
                    age = -1;
                }
                try {
                    boolean access = pass(age);
                    txt.setText(" " + access);
                    edt.setText("");
                    if (access) {                                       // Если получили доступ запускаем фрагмент
                        Repository.fragment = new EnterFragment();
                        Repository.fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, Repository.fragment)
                                .commit();
                    }
                } catch (AgeException ex) {
                    Toast.makeText(getActivity(), "Вы ввели некорректнный возраст", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    /**
     * Метод для проверки корректности ввода чисел
     * @param str строка введенная пользователем
     * @return результат проверки
     */
    private boolean check(String str) {
        // Строка - массив символов
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',};
// Алгоритм имеет сложность - это количество шагов зависящее от данных обрабатываемых алгоритмов которое потребуется
// компьтеру выполнить чтобы решить задачу. Как правило можно выделить функциональную связь между данными и количеством шагов:
        // квадратичная линейная логарифмическая и т.д.
        if (str.charAt(0) == '0') {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {                //Перебираем строку по размеру символов
            for (int j = 0; j < numbers.length; j++) {          // Перебираем массив цифр
                if (str.charAt(i) == numbers[j]) {
                    break;                                      // Прерывание выполнения цикла
                }
                if (j == numbers.length - 1) {                  // Если символ не является цифрой, то есть не совпал
                    // ни с одним значением массива numders возвращаем false
                    return false;
                }
            }
        }
        return true;
    }

    private void init(View view) {
        btn = view.findViewById(R.id.btn_age);
        txt = view.findViewById(R.id.txt_age);
        edt = view.findViewById(R.id.edt_age);
    }

    private boolean pass(int age) throws AgeException {
        if (age >= 18) {
            return true;
        } else if (age > 0 && age < 18) {
            return false;
        } else
            throw new AgeException("Введен отрицательный возраст");
    }
}


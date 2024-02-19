package com.example.fragmentworking.models;

public class Model {                            // Класс модель, эмулирует сущность, которая будет отвечать за категорию клуба
    private int img;
    private String title;

    public Model(int img, String title) {       // Создаем модель
        this.img = img;
        this.title = title;
    }

    public int getImg() {                       // набор геттеров для доступа к сущностям
        return img;
    }

    public String getTitle() {
        return title;
    }
}

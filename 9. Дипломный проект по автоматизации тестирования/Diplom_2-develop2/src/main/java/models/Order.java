package models; // Объявление пакета models

import java.util.List; // Импорт класса List из пакета java.util

public class Order { // Объявление публичного класса Order

    private List<String> ingredients; // Приватное поле ingredients типа List<String>

    public Order(List<String> ingredients) { // Конструктор класса Order с параметром ingredients типа List<String>
        this.ingredients = ingredients; // Присвоение значений поля ingredients переданному списку
    }

    public Order() { // Конструктор класса Order без параметров
    }

    public List<String> getIngredients() { // Метод для получения списка ингредиентов
        return ingredients; // Возвращает список ингредиентов
    }

    public void setIngredients(List<String> ingredients) { // Метод для установки списка ингредиентов
        this.ingredients = ingredients; // Присваивает полю ingredients переданный список ингредиентов
    }
}

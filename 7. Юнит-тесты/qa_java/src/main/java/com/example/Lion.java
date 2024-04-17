package com.example;

import java.util.List;

public class Lion {

    boolean hasMane;
    Predator predator; //Объявили переменную predator типа Predator

    // Инъекция зависимости через конструктор
    public Lion(String sex, Predator predator) throws Exception {
                // Проверка значения пола и установка hasMane
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            // В случае неверного значения пола выбрасываем исключение
            throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }

        // Инъекция зависимости Predator
        this.predator = predator;
    }

    // Вызов метода getKittens() зависимости predator
    public int getKittens() {
        return predator.getKittens();
    }

    // Возвращение значения hasMane
    public boolean doesHaveMane() {
        return hasMane;
    }

    // Вызов метода eatMeat() зависимости predator
    public List<String> getFood() throws Exception {
        return predator.eatMeat();
    }
}

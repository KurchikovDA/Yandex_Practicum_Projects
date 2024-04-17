package com.example;

import java.util.List;

public interface Predator {

    List<String> eatMeat() throws Exception;


    int getKittens(); //Добавили метод, чтобы произвести инъекцию зависимости в класс Lion




}

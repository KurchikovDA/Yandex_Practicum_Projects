package com.example;

import java.util.Arrays;
import java.util.List;

public class LionAlex extends Lion {

    public LionAlex(String sex, Predator predator) throws Exception {
        super("Самец", predator);
    }

    @Override
    public int getKittens() {
        return 0;
    }

    public List<String> getFriends() {
        return Arrays.asList("Марти", "Глория", "Мелман");
    }

    public String getPlaceOfLiving() {
        return "Нью-Йоркский зоопарк";
    }
}

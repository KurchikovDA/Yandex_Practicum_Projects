package models;

public class User {

    private String email; // Приватное поле email типа String
    private String password; // Приватное поле password типа String
    private String name; // Приватное поле name типа String

    public User(String email, String password, String name) { // Конструктор класса User с параметрами email, password и name
        this.email = email; // Присвоение значения параметра email полю email
        this.password = password; // Присвоение значения параметра password полю password
        this.name = name; // Присвоение значения параметра name полю name
    }

    public User() { // Конструктор класса User без параметров
    }

    public String getEmail() { // Метод для получения значения поля email
        return email; // Возвращает значение поля email
    }

    public void setEmail(String email) { // Метод для установки значения поля email
        this.email = email; // Присваивает полю email переданное значение
    }

    public String getPassword() { // Метод для получения значения поля password
        return password; // Возвращает значение поля password
    }

    public void setPassword(String password) { // Метод для установки значения поля password
        this.password = password; // Присваивает полю password переданное значение
    }

    public String getName() { // Метод для получения значения поля name
        return name; // Возвращает значение поля name
    }

    public void setName(String name) { // Метод для установки значения поля name
        this.name = name; // Присваивает полю name переданное значение
    }
}

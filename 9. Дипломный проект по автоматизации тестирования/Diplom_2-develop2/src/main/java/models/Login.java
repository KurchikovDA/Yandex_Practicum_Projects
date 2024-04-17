package models; // Объявление пакета models

public class Login { // Объявление публичного класса Login

    private String email; // Приватное строковое поле email
    private String password; // Приватное строковое поле password

    public Login(String email, String password) { // Конструктор класса Login с параметрами email и password
        this.email = email; // Присвоение переданного значения email полю email класса
        this.password = password; // Присвоение переданного значения password полю password класса
    }

    public Login() { // Конструктор класса Login без параметров
    }

    public String getEmail() { // Геттер для получения значения поля email
        return email; // Возвращает значение поля email
    }

    public void setEmail(String email) { // Сеттер для установки значения поля email
        this.email = email; // Присваивает полю email переданное значение
    }

    public String getPassword() { // Геттер для получения значения поля password
        return password; // Возвращает значение поля password
    }

    public void setPassword(String password) { // Сеттер для установки значения поля password
        this.password = password; // Присваивает полю password переданное значение
    }
}

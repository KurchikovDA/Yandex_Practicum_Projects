package client;

import lombok.Data; // Импорт аннотации Data из библиотеки Lombok

@Data // Аннотация Lombok для автоматической генерации геттеров, сеттеров и т.д.
public class Client { // Объявление публичного класса Client

    private String email; // Приватное поле для хранения электронной почты
    private String password; // Приватное поле для хранения пароля
    private String name; // Приватное поле для хранения имени

    // Конструктор класса для инициализации объекта Client с заданными значениями электронной почты, пароля и имени
    public Client(String email, String password, String name) {
        this.email = email; // Устанавливаем переданный email
        this.password = password; // Устанавливаем переданный пароль
        this.name = name; // Устанавливаем переданное имя
    }

    // Пустой конструктор класса
    public Client() {
    }
}

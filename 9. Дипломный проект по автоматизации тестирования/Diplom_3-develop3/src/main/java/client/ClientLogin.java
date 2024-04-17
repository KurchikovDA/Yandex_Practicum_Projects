package client;

import lombok.Data; // Импорт аннотации Data из библиотеки Lombok

@Data // Аннотация Lombok для автоматической генерации геттеров, сеттеров и т.д.
public class ClientLogin { // Объявление публичного класса ClientLogin

    private String email; // Приватное поле для хранения email
    private String password; // Приватное поле для хранения пароля

    // Конструктор класса ClientLogin для инициализации email и пароля
    public ClientLogin(String email, String password) {
        this.email = email; // Установка переданного email
        this.password = password; // Установка переданного пароля
    }

    // Приватный конструктор без параметров
    private ClientLogin() {
    }
}

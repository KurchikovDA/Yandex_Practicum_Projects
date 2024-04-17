package client;

import com.github.javafaker.Faker;

public class ClientFaker { // Объявляем класс ClientFaker
    // Метод для генерации случайного пользователя
    public static Client getRandomClient(){
        Faker faker = new Faker();
        final String email = faker.internet().emailAddress(); // Генерируем случайный email
        final String password = faker.internet().password(10, 15); // Генерируем случайный пароль
        final String name = faker.name().fullName(); // Генерируем случайное имя
        return new Client(email, password, name); // Возвращаем новый объект Client с сгенерированными данными
    }

    // Метод для генерации случайного пользователя с неправильным паролем
    public static Client getRandomClientWithWrongPassword(){
        Faker faker = new Faker();
        final String email = faker.internet().emailAddress(); // Генерируем случайный email
        final String password = faker.internet().password(4, 6); // Генерируем случайный неверный пароль
        final String name = faker.name().fullName(); // Генерируем случайное имя
        return new Client(email, password, name); // Возвращаем новый объект Client с сгенерированными данными
    }
}

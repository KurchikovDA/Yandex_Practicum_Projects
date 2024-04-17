package courier;


import java.util.Random;
//Класс CourierData предоставляет тестовые данные для тестирования функционала, связанного с курьерами.
public class CourierData {
    private final String existingLogin;      // Существующий логин курьера
    private final String nonExistentLogin;      // Несуществующий логин курьера
    private final String existingPassword;   // Существующий пароль курьера
    private final String nonExistentPassword;   // Несуществующий пароль курьера
    private final String firstName;          // Имя курьера

    //Конструктор класса, инициализирующий тестовые данные
public CourierData() {
    this.existingLogin = randomLoginOrPass(9);         // Генерация случайного логина
    this.nonExistentLogin = randomLoginOrPass(9) + "kek"; // Генерация случайного несуществующего логина при добавлении "kek"
    this.existingPassword = "existPass";                      // Установка существующего пароля
    this.nonExistentPassword = randomLoginOrPass(8);      // Генерация случайного несуществующего пароля
    this.firstName = "firstName";                             // Установка имени курьера
}

//Метод для генерации случайного логина или пароля заданной длины.
public static String randomLoginOrPass (int length) {
    int leftLimit = 97;   // Код символа 'a' в таблице ASCII
    int rightLimit = 122; // Код символа 'z' в таблице ASCII
    Random random = new Random(); //Создается экземпляр класса Random, который будет использоваться для генерации случайных чисел.
    return random.ints(leftLimit, rightLimit + 1)   //Этот вызов генерирует последовательность случайных целых чисел в заданном диапазоне (от 'a' до 'z' включительно).
            .limit(length)    //Ограничивает количество сгенерированных чисел заданной длиной (length), что соответствует количеству символов в строке.
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)  //Здесь используется метод collect, который собирает сгенерированные числа в объект StringBuilder. StringBuilder::new создает новый объект StringBuilder, StringBuilder::appendCodePoint добавляет к нему каждый сгенерированный код символа.
            .toString();  //Вызывается метод toString(), чтобы преобразовать объект StringBuilder в строку.
}

//Метод для получения существующего логина курьера.
    public String getExistingLogin() {
        return existingLogin;
    }
//Метод для получения несуществующего логина курьера.
    public String getNonExistLogin() {
        return nonExistentLogin;
    }
//Метод для получения существующего пароля курьера.
    public String getExistingPassword() {
        return existingPassword;
    }
//Метод для получения несуществующего пароля курьера.
    public String getNonExistPassword() {
        return nonExistentPassword;
    }
//Метод для получения имени курьера.
    public String getFirstName() {
        return firstName;
    }
}
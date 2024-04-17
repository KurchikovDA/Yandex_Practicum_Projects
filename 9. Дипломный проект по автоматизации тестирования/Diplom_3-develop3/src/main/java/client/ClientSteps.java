package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ClientSteps { // Объявляем публичный класс ClientSteps
    public static String baseURL = "https://stellarburgers.nomoreparties.site/"; // Объявляем переменную baseURL для хранения базового URL
    public static String clientLoginPath = "/api/auth/login"; // Объявляем переменную clientLoginPath для хранения пути к ресурсу логина пользователя
    public static String clientPath = "/api/auth/user"; // Объявляем переменную clientPath для хранения пути к ресурсу пользователя
    public static String clientRegisterPath = "api/auth/register"; // Объявляем переменную clientRegisterPath для хранения пути к ресурсу регистрации пользователя

    @Step("Создание пользователя") // Аннотируем метод для формирования шага в отчете Allure
    public static Response createNewClient(Client client) { // Объявляем метод для создания нового пользователя с параметром client типа Client
        return given() // Отправляем POST-запрос
                .header("Content-type", "application/json") // Устанавливаем заголовок Content-type
                .body(client) // Устанавливаем тело запроса, передавая объект client
                .when() // Выполняем запрос
                .post(clientRegisterPath); // Отправляем запрос по пути clientRegisterPath
    }

    @Step("Логин пользователя") // Аннотируем метод для формирования шага в отчете Allure
    public static Response loginClient(ClientLogin clientLogin) { // Объявляем метод для входа пользователя с параметром clientLogin типа ClientLogin
        return given() // Отправляем POST-запрос
                .header("Content-type", "application/json") // Устанавливаем заголовок Content-type
                .body(clientLogin) // Устанавливаем тело запроса, передавая объект clientLogin
                .when() // Выполняем запрос
                .post(clientLoginPath); // Отправляем запрос по пути clientLoginPath
    }

    @Step("Удаление пользователя") // Аннотируем метод для формирования шага в отчете Allure
    public static Response deleteClient(String accessToken) { // Объявляем метод для удаления пользователя с параметром accessToken типа String
        return given() // Отправляем DELETE-запрос
                .header("Content-type", "application/json") // Устанавливаем заголовок Content-type
                .header("Authorization", accessToken) // Устанавливаем заголовок Authorization с переданным токеном доступа
                .when() // Выполняем запрос
                .delete(clientPath); // Отправляем запрос по пути clientPath
    }
}

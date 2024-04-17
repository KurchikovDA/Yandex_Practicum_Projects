package methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Login;
import models.User;

import static constants.ApiConstants.*;
import static io.restassured.RestAssured.given;

public class UserRequests {

    @Step("Создание пользователя")
    public Response createUser(User user){ // Метод для создания пользователя, принимает объект User и возвращает ответ от сервера
        return given() // Начало построения запроса с использованием метода given() из библиотеки RestAssured
                .header("Content-type", "application/json") // Установка заголовка Content-type как application/json
                .and() // Промежуточный шаг для добавления условий запроса
                .body(user) // Установка тела запроса как объект user
                .when() // Переход к выполнению запроса
                .post(USER_REGISTER); // Выполнение POST-запроса на указанный путь из константы USER_REGISTER
    }

    @Step("Логин пользователя")
    public Response loginUser(Login login){ // Метод для входа пользователя, принимает объект Login и возвращает ответ от сервера
        return given() // Начало построения запроса с использованием метода given() из библиотеки RestAssured
                .header("Content-type", "application/json") // Установка заголовка Content-type как application/json
                .and() // Промежуточный шаг для добавления условий запроса
                .body(login) // Установка тела запроса как объект login
                .when() // Переход к выполнению запроса
                .post(USER_LOGIN); // Выполнение POST-запроса на указанный путь из константы USER_LOGIN
    }

    @Step("Получить информацию о пользователе")
    public Response getUser(String token){ // Метод для получения информации о пользователе, принимает токен и возвращает ответ от сервера
        return given() // Начало построения запроса с использованием метода given() из библиотеки RestAssured
                .header("Authorization", token) // Установка заголовка Authorization с переданным токеном
                .get(USER); // Выполнение GET-запроса на указанный путь из константы USER
    }

    @Step("Обновление данных авторизованного пользователя")
    public Response updateUser(User user, String token){ // Метод для обновления данных авторизованного пользователя, принимает объект User и токен, возвращает ответ от сервера
        return given() // Начало построения запроса с использованием метода given() из библиотеки RestAssured
                .header("Authorization", token) // Установка заголовка Authorization с переданным токеном
                .header("Content-type", "application/json") // Установка заголовка Content-type как application/json
                .and() // Промежуточный шаг для добавления условий запроса
                .body(user) // Установка тела запроса как объект user
                .when() // Переход к выполнению запроса
                .patch(USER); // Выполнение PATCH-запроса на указанный путь из константы USER
    }

    @Step("Обновление данных пользователя без авторизации")
    public Response updateUserWithoutAuthorization(User user){ // Метод для обновления данных пользователя без авторизации, принимает объект User и возвращает ответ от сервера
        return given() // Начало построения запроса с использованием метода given() из библиотеки RestAssured
                .header("Content-type", "application/json") // Установка заголовка Content-type как application/json
                .and() // Промежуточный шаг для добавления условий запроса
                .body(user) // Установка тела запроса как объект user
                .when() // Переход к выполнению запроса
                .patch(USER); // Выполнение PATCH-запроса на указанный путь из константы USER
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String token){ // Метод для удаления пользователя, принимает токен и возвращает ответ от сервера
        return given() // Начало построения запроса с использованием метода given() из библиотеки RestAssured
                .header("Authorization", token) // Установка заголовка Authorization с переданным токеном
                .delete(USER); // Выполнение DELETE-запроса на указанный путь из константы USER
    }
}

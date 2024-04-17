package courierTest;

import static constants.ApiConstants.SCOOTER_URL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import courier.Courier;
import courier.CourierDataForTest;
import courier.CourierStepMethods;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


//Класс тестов для проверки авторизации курьеров через API
public class LoginCourierTest {
    // Объект для получения тестовых данных
    private final CourierDataForTest courierDataForTest = new CourierDataForTest();
    // Переменная для хранения id курьера
    String id = null;

    //Устанавливаю базовый URI для RestAssured
    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
    }

    //Тест на успешный логин курьера.
    @Test
    @DisplayName("Успешный логин курьера")
    @Description("Логин курьера в системе.Курьер может авторизоваться. Успешный запрос возвращает id.")
    public void loginCourier() {
        // Создаю объект курьера с валидными данными
        Courier courier = new Courier(courierDataForTest.getExistingLogin(), courierDataForTest.getExistingPassword());
        // Создаю курьера перед выполнением теста
        CourierStepMethods.createCourier(courier);
        // Получаю id курьера после успешной авторизации
        id = CourierStepMethods.loginCourier(courier)
                .then()
                .extract()
                .path("id")
                .toString();
        // Отправляю запрос на логин и проверяем статус код и наличие ID в теле ответа
        Response response = CourierStepMethods.loginCourier(courier);
        response.then().assertThat().statusCode(200).and().body("id", notNullValue());
            }

    //Тест на авторизацию курьера без логина.
    @Test
    @DisplayName("Авторизация курьера без логина")
    @Description("Для авторизации курьера необходимо передать все обязательные поля. Передаётся пустой логин")
    public void authorizationCourierWithoutLogin() {
        // Создаю объект курьера без логина
        Courier courier = new Courier("", courierDataForTest.getExistingPassword());
        // Отправляю запрос на авторизацию и проверяем статус код, и тело ответа
        Response response = CourierStepMethods.loginCourier(courier);
        response.then().assertThat().statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    //Тест на авторизацию курьера без пароля.
    @Test
    @DisplayName("Авторизация курьера без пароля")
    @Description("Для авторизации курьера необходимо передать все обязательные поля. Передается пустой пароль курьера")
    public void authorizationCourierWithoutPassword() {
        // Создаю объект курьера без пароля
        Courier courier = new Courier(courierDataForTest.getExistingLogin(), "");
        // Отправляю запрос на авторизацию и проверяем статус код и тело ответа
        Response response = CourierStepMethods.loginCourier(courier);
        response.then().assertThat().statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    //Тест на авторизацию курьера с неправильным логином.
    @Test
    @DisplayName("Авторизация курьера c неправильным логином")
    @Description("Для авторизации курьера необходимо передать существующие данные. Передается неправильный логин")
    public void authorizationCourierWithNonExistentLogin() {
        // Создаю объект курьера с неправильным логином
        Courier courier = new Courier(courierDataForTest.getNonExistLogin(), courierDataForTest.getExistingPassword());
        // Отправляю запрос на авторизацию и проверяем статус код и тело ответа
        Response response = CourierStepMethods.loginCourier(courier);
        response.then().assertThat().statusCode(404).and().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    //Тест на авторизацию курьера с неправильным паролем.
    @Test
    @DisplayName("Авторизация курьера c неправильным паролем")
    @Description("Для авторизации курьера необходимо передать существующие данные. Передается неверный пароль курьера")
    public void authorizationCourierWithNonExistentPassword() {
        // Создаю объект курьера с неправильным паролем
        Courier courier = new Courier(courierDataForTest.getExistingLogin(), courierDataForTest.getNonExistPassword());
        // Отправляю запрос на авторизацию и проверяем статус код и тело ответа
        Response response = CourierStepMethods.loginCourier(courier);
        response.then().assertThat().statusCode(404).and().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    //Этот метод выполняется после каждого теста. Он удаляет курьера, если его id не равен null.
    @After
    public void deleteCourier() {
        if (id != null) {
            CourierStepMethods.deleteCourier(id);
        }
    }
}
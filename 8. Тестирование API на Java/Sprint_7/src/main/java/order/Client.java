package order;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static constants.ApiConstants.SCOOTER_URL;

// Класс Client используется для предоставления базовой спецификации запроса (RequestSpecification),
// которая будет использоваться при выполнении запросов к API Яндекс.Самокат.
public class Client {
    protected RequestSpecification requestSpec; // Объявление переменной для хранения спецификации запроса

    // Конструктор класса Client, вызываемый при создании нового объекта класса
    public Client() {
        RestAssured.baseURI = SCOOTER_URL; // Установка базового URI для RestAssured равным SCOOTER_URL

        // Создание нового объекта RequestSpecBuilder для настройки спецификации запроса
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI) // Устанавливает базовый URL для запросов
                .setContentType(ContentType.JSON) // Устанавливает тип контента запроса как JSON
                .build();  // Строит и возвращает экземпляр RequestSpecification
    }

    // Метод getSpec используется для получения предварительно настроенной спецификации запроса
    protected RequestSpecification getSpec() {
        return requestSpec;
    }
}

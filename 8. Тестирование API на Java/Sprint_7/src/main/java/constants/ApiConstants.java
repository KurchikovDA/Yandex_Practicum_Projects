package constants;

//Класс ApiConstants содержит константы для адреса Яндекс.Самоката и ручек API.
 public class ApiConstants {

    // Базовый URL для Яндекс.Самоката
    public static final String SCOOTER_URL = "https://qa-scooter.praktikum-services.ru/";

    // Ручка для работы с курьерами
    public static final String COURIER = "api/v1/courier/";

    // Ручка для авторизации курьера
    public static final String LOGIN = "api/v1/courier/login/";

    // Ручка для работы с заказами
    public static final String ORDER = "api/v1/orders";

    // Ручка для отмены заказа
   public static final String CANCEL_ORDER = "/api/v1/orders/cancel?track=";
}

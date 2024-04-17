import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

//Класс для выбора браузера.
public class BrowserConfiguration {

    protected WebDriver driver; // Объявление переменной driver типа WebDriver
    public static final String PROPERTIES = "src/main/resources/config.properties"; // Путь к файлу с настройками
    private static String browser; // Объявление переменной browser типа String

    @Before
    public void configure() {
        FileInputStream fileInputStream; // Объявление переменной fileInputStream типа FileInputStream
        Properties prop = new Properties(); // Создание объекта Properties для работы с файлом настроек
        try {
            fileInputStream = new FileInputStream(PROPERTIES); // Попытка открыть поток для чтения файла настроек
            prop.load(fileInputStream); // Загрузка содержимого файла настроек в объект Properties
            browser = prop.getProperty("browser"); // Получение значения параметра "browser" из файла настроек
        } catch (IOException e) {
            e.printStackTrace(); // Обработка исключения в случае ошибки при чтении файла настроек
        }
        selectBrowser(); // Выбор браузера на основе полученных настроек
    }

    public void selectBrowser() { // Метод выбора браузера
        if ("chrome".equals(browser)) // Проверка, равен ли параметр "browser" значению "chrome"
            setUpChrome(); // Если да, то настройка Chrome браузера
        else
            setUpYandex(); // Если нет, то настройка Yandex браузера
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Установка неявного ожидания
    }

    // Драйвер для Chrome браузера
    public void setUpChrome() { // Метод настройки Chrome
        ChromeOptions options = new ChromeOptions(); // Создание объекта ChromeOptions для настройки Chrome
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe"); // Установка пути к драйверу Chrome
        options.addArguments("--remote-allow-origins=*"); // Добавление аргумента
        driver = new ChromeDriver(options); // Создание экземпляра ChromeDriver
    }

    // Драйвер для Yandex браузера
    public void setUpYandex() { // Метод настройки Yandex
        ChromeOptions options = new ChromeOptions(); // Создание объекта ChromeOptions для настройки Yandex
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe"); // Установка пути к драйверу Yandex
        options.addArguments("--remote-allow-origins=*"); // Добавление аргумента
        driver = new ChromeDriver(options); // Создание экземпляра ChromeDriver
    }
}

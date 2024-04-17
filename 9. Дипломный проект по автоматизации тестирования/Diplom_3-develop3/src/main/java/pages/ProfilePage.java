package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

// Создаем класс ProfilePage для представления страницы личного кабинета
public class ProfilePage {
    private WebDriver driver; // Поле для хранения экземпляра WebDriver
    private By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']"); // Локатор кнопки с логотипом на странице
    private By logoutButton = By.xpath(".//button[text()='Выход']"); // Локатор кнопки "Выход" в личном кабинете
    private By constructorButton = By.xpath(".//p[text()='Конструктор']"); // Локатор кнопки "Конструктор" в личном кабинете


    // Конструктор класса, принимающий WebDriver в качестве аргумента
    public ProfilePage(WebDriver driver) {
        this.driver = driver; // Присваиваем переданный экземпляр WebDriver полю driver
    }

    // Метод для клика по кнопке "Конструктор" в личном кабинете
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click(); // Находим и кликаем кнопку "Конструктор"
    }

    // Метод для клика по кнопке с логотипом на странице
    public void clickLogoButton() {
        driver.findElement(logoButton).click(); // Находим и кликаем кнопку с логотипом
    }

    // Метод для клика по кнопке "Выход" в личном кабинете
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click(); // Находим и кликаем кнопку "Выход"
    }

    // Метод для получения текста кнопки "Выход"
    public String getLogoutButtonText(){
        return driver.findElement(logoutButton).getText(); // Получаем текст кнопки "Выход"
    }
}





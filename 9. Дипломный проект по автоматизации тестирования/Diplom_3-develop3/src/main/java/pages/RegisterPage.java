package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class RegisterPage { // Объявляем класс RegisterPage

    private WebDriver driver; // Приватное поле для экземпляра WebDriver
    private By nameField = By.xpath(".//label[text()='Имя']/../input"); // Локатор для поля ввода имени
    private By emailField = By.xpath(".//label[text()='Email']/../input"); // Локатор для поля ввода email
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input"); // Локатор для поля ввода пароля
    private By registerButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]"); // Локатор для кнопки регистрации
    private By loginButton = By.xpath(".//a[text()='Войти']"); // Локатор для кнопки входа
    private By passwordError = By.xpath(".//p[text()='Некорректный пароль']"); // Локатор для сообщения об ошибке в пароле

    // Конструктор класса RegisterPage для инициализации объекта WebDriver
    public RegisterPage(WebDriver driver) {
        this.driver = driver; // Устанавливаем переданный WebDriver
    }

    // Метод для ввода имени в поле
    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name); // Находим поле ввода имени и вводим переданное значение
    }

    // Метод для ввода email в поле
    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email); // Находим поле ввода email и вводим переданное значение
    }

    // Метод для ввода пароля в поле
    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password); // Находим поле ввода пароля и вводим переданное значение
    }

    // Метод для клика по кнопке регистрации
    public void clickRegisterButton() {
        driver.findElement(registerButton).click(); // Находим кнопку регистрации и кликаем по ней
    }

    // Метод для клика по кнопке входа
    public void clickLoginButton() {
        driver.findElement(loginButton).click(); // Находим кнопку входа и кликаем по ней
    }

    // Метод для установки информации пользователя (имя, email, пароль)
    public void setClientInfo(String name, String email, String password) {
        setNameField(name); // Устанавливаем имя
        setEmailField(email); // Устанавливаем email
        setPasswordField(password); // Устанавливаем пароль
    }

    // Метод для получения текста сообщения об ошибке в пароле
    public String getPasswordErrorText() {
        return driver.findElement(passwordError).getText(); // Возвращаем текст сообщения об ошибке в пароле
    }
}

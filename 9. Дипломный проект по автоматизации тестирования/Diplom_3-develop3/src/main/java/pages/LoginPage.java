package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class LoginPage { // Объявляем класс LoginPage

    private WebDriver driver; // Приватное поле для экземпляра WebDriver
    private By emailField = By.xpath(".//label[text()='Email']/../input"); // Локатор для поля ввода email
    private By passwordField = By.xpath(".//label[text()='Пароль']/../input"); // Локатор для поля ввода пароля
    private By loginButton = By.xpath(".//button[text()='Войти']"); // Локатор для кнопки входа
    private By registerButton = By.xpath(".//a[(@class='Auth_link__1fOlj' and text()='Зарегистрироваться')]"); // Локатор для кнопки регистрации
    private By forgotPasswordButton = By.xpath(".//a[text()='Восстановить пароль']"); // Локатор для кнопки восстановления пароля
    private By enterLabelText = By.xpath("//h2[text()='Вход']"); // Локатор для текста на странице входа

    // Конструктор класса LoginPage для инициализации объекта WebDriver
    public LoginPage(WebDriver driver) {
        this.driver = driver; // Устанавливаем переданный WebDriver
    }

    // Метод для ввода email в поле
    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email); // Находим поле ввода email и вводим переданное значение
    }

    // Метод для ввода пароля в поле
    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password); // Находим поле ввода пароля и вводим переданное значение
    }

    // Метод для клика по кнопке входа
    public void clickLoginButton() {
        driver.findElement(loginButton).click(); // Находим кнопку входа и кликаем по ней
    }

    // Метод для клика по кнопке регистрации
    public void clickRegisterButton() {
        driver.findElement(registerButton).click(); // Находим кнопку регистрации и кликаем по ней
    }

    // Метод для клика по кнопке восстановления пароля
    public void clickForgotPasswordButton() {
        driver.findElement(forgotPasswordButton).click(); // Находим кнопку восстановления пароля и кликаем по ней
    }

    // Метод для получения текста на странице входа
    public String getEnterLabelText() {
        return driver.findElement(enterLabelText).getText(); // Возвращаем текст на странице входа
    }

    // Метод для установки информации о пользователе (email, пароль)
    public void setClientLoginData(String email, String password) {
        setEmailField(email); // Устанавливаем email
        setPasswordField(password); // Устанавливаем пароль
    }
}

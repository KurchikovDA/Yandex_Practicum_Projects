package pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Класс страницы "Для кого самокат"
public class ForWhomScooterPage {
    private WebDriver driver;
    // Конструктор
    public ForWhomScooterPage(WebDriver driver) {
        this.driver = driver;
    }


    //Локаторы формы "Для кого самокат"
    private By firstNameField = By.xpath("//input[@placeholder='* Имя']"); //Локатор поля "Имя"
    private By lastNameField = By.xpath("//input[@placeholder='* Фамилия']"); //Локатор поля "Фамилия"
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']"); //Локатор поля "Адрес: куда привезти заказ"
    private By metroStationField = By.xpath("//input[@placeholder='* Станция метро']"); //Локатор поля "Станция метро"
    private By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']"); //Локатор поля "Телефон"


    private By nextButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(text(), 'Далее')]"); //Локатор кнопки "Далее"



    private By metroStation = (By.xpath(".//*[text() = 'Черкизовская']"));  //Выбор нужной станции



    //Методы заполнения данными полей "Имя", "Фамилия", "Адрес", "Станция метро" и "Телефон"
    public void setFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }
    public void setLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }
    public void setDeliveryAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }


    public void  setMetro() {
        driver.findElement(metroStationField).click();
        driver.findElement(metroStation).click();
            }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void nextButtonClick() {
        driver.findElement(nextButton).click();
    }

    //Заполнение всех полей одним методом
    public void fillOrderForm1(String firstName, String lastName, String address, String phoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setDeliveryAddress(address);
        setMetro();
        setPhoneNumber(phoneNumber);
    }


    private By forWhomScooterText = By.xpath(".//*[text() = 'Для кого самокат']"); //Локатор заголовка "Для кого самокат"

    //Проверка, что форма "Для кого самокат" открылась
    public void assertOrderDoneTextVisible() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement checkStatusTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(forWhomScooterText));
        Assert.assertTrue("Текст 'Заказ оформлен' не виден", checkStatusTextElement.isDisplayed());
    }


}

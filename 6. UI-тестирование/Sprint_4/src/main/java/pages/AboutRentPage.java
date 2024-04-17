package pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//Класс страницы "Про аренду"
public class AboutRentPage {
    private WebDriver driver;
    // Конструктор
    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы формы "Про аренду"
    private By deliveryDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']"); //Локатор поля "Когда привезти самокат"
    private By rentalPeriodField = By.xpath("//input[@placeholder='* Срок аренды']"); //Локатор поля "Срок аренды"
    private By scooterColorField = By.className("Order_Checkboxes__3lWSI"); //Локатор поля "Цвет самоката"
    private By сolorBlackPearl = By.xpath("//input[@id='black' and @type='checkbox']"); //Локатор чекбокса цвет Чёрная Жемчужина
    private By сolorGrayDesolation = By.xpath("//input[@id='grey' and @type='checkbox']"); //Локатор чекбокса цвет Серая Безысходность
    private By courierCommentField = By.xpath("//input[@placeholder='Комментарий для курьера']"); //Локатор поля "Комментарий для курьера"

    private By dropdownArrow = By.className("Dropdown-arrow");; //Локатор для стрелки "Срок аренды"
    private By placeOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']"); //Локатор кнопки "Заказать"


    //Локаторы появляющегося окна "Хотите оформить заказ?"
    private By confirmOrderPrompt = By.className("Order_Text__2broi"); //Локатор окна "Хотите оформить заказ?"
    private By yesButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']"); //Локатор кнопки "Да"

    //Локаторы появляющегося окна "Заказ оформлен"
    private By checkStatusButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']"); //Локатор кнопки "Посмотреть статус"

    private By getRentalPeriod = (By.xpath(".//*[text() = 'сутки']"));  //Выбор срока аренды


    //Методы заполнения данными полей
public void setDeliveryDate(String deliveryDate) {
    driver.findElement(deliveryDateField).sendKeys(deliveryDate);
    driver.findElement(dropdownArrow).click();
}

    public void setRentalPeriod() {

        driver.findElement(getRentalPeriod).click();
            }

    public void setScooterColor() {
        driver.findElement(сolorBlackPearl).click();
            }

    public void setCourierComment(String comment) {
        driver.findElement(courierCommentField).sendKeys(comment);
    }

    //Заполнение всех полей одним методом
    public void fillOrderForm2(String deliveryDate, String comment) {
        setDeliveryDate(deliveryDate);
        setRentalPeriod();
        setScooterColor();
        setCourierComment(comment);
    }
    // Клик на кнопку "Заказать"
    public void clickPlaceOrderButton() {
        driver.findElement(placeOrderButton).click();
    }


    // Клик на кнопку "Да"
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }
    // Клик на кнопку "Посмотреть статус"
    public void clickCheckStatusButton() {
        driver.findElement(checkStatusButton).click();
    }

    //Проверка, что в окне "Заказ оформлен" появляется кнопка "Посмотреть статус", значит тест работает
    public void assertCheckStatusButtonVisible() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement checkStatusButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(checkStatusButton));
        Assert.assertTrue("Кнопка 'Посмотреть статус' не видна", checkStatusButtonElement.isDisplayed());
    }

    private By orderDoneText = By.xpath(".//*[text() = 'Заказ оформлен']"); //Локатор заголовка "Заказ оформлен"

    //Проверка, что заголовок "Заказ оформлен" видим
    public void assertOrderDoneTextVisible() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement checkStatusTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderDoneText));
        Assert.assertTrue("Текст 'Заказ оформлен' не виден", checkStatusTextElement.isDisplayed());
    }


}


package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Класс главной страницы Яндекс Самоката
public class MainPage {
private WebDriver driver;
    // Конструктор
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private By mainScooterPage = By.xpath("//*[contains(@href,'https://qa-scooter.praktikum-services.ru/')]"); //Локатор URL Самоката

    private By scooterLogo = By.xpath("//img[@alt='Scooter']"); //Локатор логотипа Самокат
    private By orderUpButton = By.xpath("//button[@class='Button_Button__ra12g' and text()='Заказать']"); //Локатор верхней кнопки "Заказать"
    public void clickOrderUpButton() {
        driver.findElement(orderUpButton).click();   // Клик на верхнюю кнопку "Заказать"
    }
    private By orderDownButton = By.xpath("//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp' or (@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать')]");
    //Локатор нижней кнопки "Заказать"

    //Скролл и клик на нижнюю кнопку "Заказать"
    public void scrollToOrderDownButtonAndClick() {
        WebElement element = driver.findElement(orderDownButton);
        // Прокрутка к элементу
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Ожидание
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.elementToBeClickable(orderDownButton));
        // Клик по кнопке
        element.click();
    }
    public void clickOrderDownButton() {
        driver.findElement(orderDownButton).click();   // Клик на нижнюю кнопку "Заказать"
    }

    private By cookieButton = By.id("rcc-confirm-button"); // Локатор кнопки "да все привыкли" у куки
    public void clickCookieButton() {
        driver.findElement(cookieButton).click();   // Клик на куки
    }

    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();   // Клик на логотип Самокат
    }


    //Локаторы кнопок списка:
    private By howMuchDoesItCostButton = By.id("accordion__heading-0"); // Локатор первой кнопки "Сколько это стоит? И как оплатить"
    private By iWantToSeveralScootersButton = By.id("accordion__heading-1"); // Локатор второй кнопки "Хочу сразу несколько самокатов! Так можно?"
    private By howIsRentalTimeCalculatedButton = By.id("accordion__heading-2"); // Локатор третьей кнопки "Как рассчитывается время аренды?"
    private By isItPossibleToOrderScooterTodayButton = By.id("accordion__heading-3"); // Локатор четвёртой кнопки "Можно ли заказать самокат прямо на сегодня?"
    private By isItPossibleToExtendOrderButton = By.id("accordion__heading-4"); // Локатор пятой кнопки "Можно ли продлить заказ или вернуть самокат раньше?"
    private By doYouBringChargerButton = By.id("accordion__heading-5"); // Локатор шестой кнопки "Вы привозите зарядку вместе с самокатом?"
    private By isItPossibleToCancelOrderButton = By.id("accordion__heading-6"); // Локатор седьмой кнопки "Можно ли отменить заказ?"
    private By iLiveOutsideButton = By.id("accordion__heading-7"); // Локатор восьмой кнопки "Я живу за МКАДом, привезёте?"


    private By howMuchDoesItCostText = By.xpath("//p[contains(text(),'Сутки — 400 рублей. Оплата курьеру — наличными или картой.')]"); // Локатор текста первой кнопки
    private By iWantToSeveralScootersText = By.xpath("//p[contains(text(),'Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.')]"); // Локатор текста второй кнопки
    private By howIsRentalTimeCalculatedText = By.xpath("//p[contains(text(),'Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.')]"); // Локатор текста третьей кнопки
    private By isItPossibleToOrderScooterTodayText = By.xpath("//p[contains(text(),'Только начиная с завтрашнего дня. Но скоро станем расторопнее.')]"); // Локатор текста четвёртой кнопки
    private By isItPossibleToExtendOrderText = By.xpath("//p[contains(text(),'Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.')]"); // Локатор текста пятой кнопки
    private By doYouBringChargerText = By.xpath("//p[contains(text(),'Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.')]"); // Локатор текста шестой кнопки
    private By isItPossibleToCancelOrderText = By.xpath("//p[contains(text(),'Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.')]"); // Локатор текста седьмой кнопки
    private By iLiveOutsideText = By.xpath("//p[contains(text(),'Да, обязательно. Всем самокатов! И Москве, и Московской области.')]"); // Локатор текста восьмой кнопки

//Методы:
// Методы для клика по кнопкам.
    public void clickHowMuchDoesItCostButton() {
    driver.findElement(howMuchDoesItCostButton).click();
}
    public void clickIWantToSeveralScootersButton() {
    driver.findElement(iWantToSeveralScootersButton).click();
    }
    public void clickHowIsRentalTimeCalculatedButton() {
        driver.findElement(howIsRentalTimeCalculatedButton).click();
    }
    public void clickIsItPossibleToOrderScooterTodayButton() {
        driver.findElement(isItPossibleToOrderScooterTodayButton).click();
    }
    public void clickIsItPossibleToExtendOrderButton() {
        driver.findElement(isItPossibleToExtendOrderButton).click();
    }
    public void clickDoYouBringChargerButton() {
        driver.findElement(doYouBringChargerButton).click();
    }
    public void clickIsItPossibleToCancelOrderButton() {
        driver.findElement(isItPossibleToCancelOrderButton).click();
    }
    public void clickILiveOutsideButton() {
        driver.findElement(iLiveOutsideButton).click();
    }

    // Методы появляющегося текста
    public String getHowMuchDoesItCostText() {
        return driver.findElement(howMuchDoesItCostText).getText();
    }
    public String getIWantToSeveralScootersText() {
        return driver.findElement(iWantToSeveralScootersText).getText();
    }
    public String getHowIsRentalTimeCalculatedText() {
        return driver.findElement(howIsRentalTimeCalculatedText).getText();
    }
    public String getIsItPossibleToOrderScooterTodayText() {
        return driver.findElement(isItPossibleToOrderScooterTodayText).getText();
    }
    public String getIsItPossibleToExtendOrderText() {
        return driver.findElement(isItPossibleToExtendOrderText).getText();
    }
    public String getDoYouBringChargerText() {
        return driver.findElement(doYouBringChargerText).getText();
    }
    public String getIsItPossibleToCancelOrderText() {
        return driver.findElement(isItPossibleToCancelOrderText).getText();
    }
    public String getILiveOutsideText() {
        return driver.findElement(iLiveOutsideText).getText();
    }


    private String closedDropdownLocatorFormat = "accordion__heading-%d";
    private String textInputLocatorFormat = "accordion__panel-%d";


    // Метод для клика по закрытому дропдауну с использованием форматирования локатора
    public void clickClosedDropdownByIndex(int index) {
        driver.findElement(By.id(String.format(closedDropdownLocatorFormat, index))).click();
    }

    // Метод для получения текста из текстового поля с использованием форматирования локатора
    public String getTextFromTextInputByIndex(int index) {
        return  driver.findElement(By.id(String.format(textInputLocatorFormat, index))).getText();
    }


    //Метод скролла до начала таблицы и клик на первый элемент, в Фаерфоксе тест проходит только с ним.
    public void scrollToTable() {
        WebElement element = driver.findElement(By.id("accordion__heading-0"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }


}

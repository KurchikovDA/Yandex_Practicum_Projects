import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;

import static org.junit.Assert.assertEquals;

//Выпадающий список в разделе «Вопросы о важном». Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.
@RunWith(Parameterized.class)
public class ListTestParameterized {

    private int index;
    private String expected;
    private WebDriver driver;
    private MainPage mainPage;
    public static final String SCOOTER_URL = "https://qa-scooter.praktikum-services.ru/"; //Адрес главной страницы "ЯНдекс Самоката"

//Конструктор класса
    public ListTestParameterized(String expected, int index) {
        this.expected = expected;
        this.index = index;
    }

    @Parameterized.Parameters
    public static Object[][] getListData() {
        return new Object[][]{
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7}
        };
    }
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver(); // Хром
        //System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        //driver = new FirefoxDriver(); // Мозилла
        mainPage = new MainPage(driver);
        driver.get(SCOOTER_URL);
        driver.manage().window().maximize();  //Расширение экрана
        mainPage.clickCookieButton(); //Клик на куки
        //Скролл до начала таблицы, в Фаерфоксе тест проходит только с ним.
        mainPage.scrollToTable();

    }

    @Test
    public void testAccordionDropdown() {
        mainPage.clickClosedDropdownByIndex(index);  //Клик по кнопке списка
        assertEquals(expected, mainPage.getTextFromTextInputByIndex(index)); //Проверка выпадающего текста
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
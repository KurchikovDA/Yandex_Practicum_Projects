import com.example.LionAlex;
import com.example.Predator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

// Указываем использование MockitoJUnitRunner для автоматической инициализации mock-объектов
@RunWith(MockitoJUnitRunner.class)
public class LionAlexTest {

    // Создаем mock для интерфейса Predator
    @Mock
    private Predator predatorMock;

    private LionAlex lionAlex;

    // Метод выполняется перед каждым тестом для настройки поведения mock'а
    @Before
    public void setUp() throws Exception {
        // Настраиваем поведение mock'а для метода eatMeat()
        Mockito.when(predatorMock.eatMeat()).thenReturn(Arrays.asList("Животные", "Птицы", "Рыба"));

        // Создаем экземпляр LionAlex вручную перед каждым тестом
        lionAlex = new LionAlex("Самец", predatorMock);
    }

    // Тест для метода getFriends()
    @Test
    public void testGetFriends() {
        // Ожидаемый список друзей
        List<String> expectedFriends = Arrays.asList("Марти", "Глория", "Мелман");

        // Проверяем, что метод getFriends() возвращает ожидаемый список друзей
        Assert.assertEquals(expectedFriends, lionAlex.getFriends());
    }

    // Тест для метода getPlaceOfLiving()
    @Test
    public void testGetPlaceOfLiving() {
        // Ожидаемое место проживания
        String expectedPlaceOfLiving = "Нью-Йоркский зоопарк";

        // Проверяем, что метод getPlaceOfLiving() возвращает ожидаемое место проживания
        Assert.assertEquals(expectedPlaceOfLiving, lionAlex.getPlaceOfLiving());
    }

    // Тест для метода getKittens()
    @Test
    public void testGetKittens() {
        // Ожидаемое количество детенышей
        int expectedKittens = 0;

        // Проверяем, что метод getKittens() возвращает ожидаемое количество детенышей
        Assert.assertEquals(expectedKittens, lionAlex.getKittens());
    }

    // Тест для метода getFood()
    @Test
    public void testGetFood() throws Exception {
        // Ожидаемый список еды
        List<String> expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");

        // Проверяем, что метод getFood() возвращает ожидаемый список еды
        Assert.assertEquals(expectedFood, lionAlex.getFood());
    }
}

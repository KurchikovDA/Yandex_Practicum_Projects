import com.example.Feline;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;

// Указываем использование MockitoJUnitRunner для автоматической инициализации mock-объектов
@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Mock  //создаём мок объекта Feline для использования в тестах
    Feline felineMock;

    @InjectMocks //Внедряем зависимости моков в объект feline, чтобы использовать их в тестах. Решил использовать разок для удобства.
    private Feline feline;

    // Тест для метода eatMeat()
    @Test
    public void testEatMeat() throws Exception {
        // Устанавливаем поведение мока: при вызове метода eatMeat() возвращаем список с элементами
        Mockito.when(felineMock.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));

        // Вызываем метод eatMeat() на реальном объекте и сравниваем результат с ожидаемым
        Assert.assertEquals(feline.eatMeat(), felineMock.eatMeat());
    }

    // Тест для метода getFamily()
    @Test
    public void testGetFamily() {
        // Создаем объект реального класса Feline
        Feline feline = new Feline();

        // Выполняем тест, вызывая реальный метод getFamily()
        String result = feline.getFamily();

        // Проверяем, что результат соответствует ожидаемому значению
        String expectedFamily = "Кошачьи";
        Assert.assertEquals(expectedFamily, result);
    }

    // Тест для метода getKittens()
    @Test
    public void testGetKittens() {
        // Устанавливаем поведение мока: при вызове метода getKittens() возвращаем значение 1
        Mockito.when(felineMock.getKittens()).thenReturn(1);

        // Вызываем метод getKittens() и проверяем, что результат равен ожидаемому значению 1
        int result = felineMock.getKittens();
        Assert.assertEquals(1, result);

        // Проверяем, что метод getKittens() был вызван один раз
        Mockito.verify(felineMock, times(1)).getKittens();
    }

    // Тест для метода getKittens() с использованием реального объекта Feline
    @Test
    public void getKittensTest() {
        // Устанавливаем поведение мока: при вызове метода getKittens() возвращаем значение 1
        Mockito.when(felineMock.getKittens()).thenReturn(1);

        // Вызываем метод getKittens() на реальном объекте и сравниваем результат с ожидаемым
        Assert.assertEquals(feline.getKittens(), felineMock.getKittens());
    }
}

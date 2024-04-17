import com.example.Cat;
import com.example.Feline;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.mock;

// Указываем использование MockitoJUnitRunner для автоматической инициализации mock-объектов
@RunWith(MockitoJUnitRunner.class)
public class CatTest {
    //Создается mock-объект Feline, который будет использоваться в тестах.
    @Mock
    private Feline mockFeline;

    // Проверяем метод getSound();
    @Test
    public void testGetSound() {
        // Создаем мок объекта Feline
        Feline mockFeline = mock(Feline.class);

        // Создаем объект Cat, используя мок Feline
        Cat cat = new Cat(mockFeline);

        // Проверяем, что метод getSound() возвращает ожидаемое значение "Мяу"
        Assert.assertEquals("Мяу", cat.getSound());
    }

    // Проверяем метод getFood();
    @Test
    public void testGetFood() throws Exception {
        // Создаем новый объект Cat, используя мок Feline
        Cat cat = new Cat(mockFeline);

        // Задаем поведение мока: при вызове метода eatMeat() возвращаем список с элементом "Хищник"
        Mockito.when(mockFeline.eatMeat()).thenReturn(List.of("Хищник"));

        // Вызываем метод getFood() и проверяем, что возвращается ожидаемый список
        Assert.assertEquals(List.of("Хищник"), cat.getFood());

        // Проверяем, что метод eatMeat() был вызван один раз
        Mockito.verify(mockFeline, Mockito.times(1)).eatMeat();
    }
}

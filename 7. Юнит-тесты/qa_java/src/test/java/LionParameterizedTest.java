import com.example.Lion;
import com.example.Predator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class LionParameterizedTest {
    // Параметры для конструктора, задаваемые в тестовых данных
    String sex;
    boolean expectedHasMane;

    // Конструктор для инициализации параметров из тестовых данных
    public LionParameterizedTest(String sex, boolean expectedHasMane) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
    }

    // Mock объект для интерфейса Predator
    @Mock
    Predator predator;

    // Метод, предоставляющий тестовые данные
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Самка", false},
                {"Самец", true}
        });
    }

    // Тест для метода doesHaveMane()
    @Test
    public void testDoesHaveMane() throws Exception {
        // Создаем реальный объект Lion с использованием параметров
        Lion lion = new Lion(sex, predator);

        // Создаем мок объект Lion для настройки поведения
        Lion mockedLion = Mockito.mock(Lion.class);
        // Устанавливаем поведение мока в зависимости от параметра sex
        if ("Самец".equals(sex)) {
            Mockito.when(mockedLion.doesHaveMane()).thenReturn(true);
        } else if ("Самка".equals(sex)) {
            Mockito.when(mockedLion.doesHaveMane()).thenReturn(false);
        }

        // Проверяем, что метод doesHaveMane() возвращает ожидаемый результат
        Assert.assertEquals(lion.doesHaveMane(), mockedLion.doesHaveMane());
    }

}

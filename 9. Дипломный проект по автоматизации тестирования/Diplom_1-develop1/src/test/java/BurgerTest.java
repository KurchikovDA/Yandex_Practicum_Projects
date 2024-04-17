import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import static org.mockito.Mockito.*;
import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Before
    public void setUp() {
        // Инициализация объекта Burger перед каждым тестом
        burger = new Burger();
    }

    // Тест для метода setBuns класса Burger
    @Test
    public void testSetBuns() {
        // Создание заглушки для объекта Bun с помощью Mockito
        Bun bun = mock(Bun.class);

        // Установка булочки для burger с использованием метода setBuns
        burger.setBuns(bun);

        // Проверка, что булочка установлена правильно с помощью assertSame
        Assert.assertSame(bun, burger.bun);
    }

    // Тест для метода addIngredient класса Burger
    @Test
    public void testAddIngredient() {
        // Создаем мок (заглушку) для объекта Ingredient с помощью Mockito
        Ingredient ingredient = mock(Ingredient.class);

        // Добавляем ингредиент в burger с помощью метода addIngredient
        burger.addIngredient(ingredient);

        // Проверяем, что размер списка ingredients увеличился на 1 с помощью assertEquals
        Assert.assertEquals(1, burger.ingredients.size());

        // Проверяем, что добавленный ингредиент содержится в списке ingredients с помощью assertTrue
        Assert.assertTrue(burger.ingredients.contains(ingredient));
    }

    // Тест для метода removeIngredient класса Burger
    @Test
    public void testRemoveIngredient() {
        // Создаем две заглушки (моки) для объектов Ingredient с помощью Mockito
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        // Добавляем оба ингредиента в burger
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        // Удаляем первый ингредиент с индексом 0
        burger.removeIngredient(0);

        // Проверяем, что размер списка ingredients уменьшился на 1 с помощью assertEquals
        Assert.assertEquals(1, burger.ingredients.size());

        // Проверяем, что первый ингредиент больше не содержится в списке ingredients с помощью assertFalse
        Assert.assertFalse(burger.ingredients.contains(ingredient1));

        // Проверяем, что второй ингредиент по-прежнему содержится в списке ingredients с помощью assertTrue
        Assert.assertTrue(burger.ingredients.contains(ingredient2));
    }

    // Тест для метода moveIngredient класса Burger
    @Test
    public void testMoveIngredient() {
        // Создаем три заглушки (моки) для объектов Ingredient с помощью Mockito
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        // Добавляем все три ингредиента в burger
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        // Перемещаем первый ингредиент с индексом 0 на позицию с индексом 2
        burger.moveIngredient(0, 2);

        // Проверяем, что размер списка ingredients остался неизменным (3) с помощью assertEquals
        Assert.assertEquals(3, burger.ingredients.size());

        // Проверяем, что ингредиенты переместились в нужном порядке с помощью assertSame
        Assert.assertSame(ingredient1, burger.ingredients.get(2)); // ингредиент1 на месте индекса 2
        Assert.assertSame(ingredient2, burger.ingredients.get(0)); // ингредиент2 на месте индекса 0
        Assert.assertSame(ingredient3, burger.ingredients.get(1)); // ингредиент3 на месте индекса 1
    }
}

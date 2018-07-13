import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MealServiceTest {

    private MealService mealService;

    @Before
    public void setup() {
        mealService = new MealService();
    }

    @Test
    public void shouldReturnAllTypesOfDishesForMorningPeriod() {
        String input = "morning, 1, 2, 3";
        String expected = "eggs, toast, coffee";

        String actual = mealService.detailMeal(input);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAllTypesOfDishesOrderedForMorningPeriod() {
        String input = "morning, 2, 1, 3";
        String expected = "eggs, toast, coffee";

        String actual = mealService.detailMeal(input);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAllTypesOfDishesAndErrorForUnexpectedDishForMorningPeriod() {
        String input = "morning, 1, 2, 3, 4";
        String expected = "eggs, toast, coffee, error";

        String actual = mealService.detailMeal(input);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAllTypesOfDishesCountingRepetition() {
        String input = "morning, 1, 2, 3, 3, 3";
        String expected = "eggs, toast, coffee(x3)";

        String actual = mealService.detailMeal(input);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAllTypesOfDishesForNightPeriod() {
        String input = "night, 1, 2, 3, 4";
        String expected = "steak, potato, wine, cake";

        String actual = mealService.detailMeal(input);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAllTypesOfDishesForNightPeriodCountingRepetition() {
        String input = "night, 1, 2, 2, 4";
        String expected = "steak, potato(x2), cake";

        String actual = mealService.detailMeal(input);

        assertEquals(expected, actual);
    }
}

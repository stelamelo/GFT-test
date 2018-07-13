import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealService {

    private List<Integer> orderedDishTypes;

    public String detailMeal(String input) {
        input = input.replaceAll("\\s", "").toLowerCase();
        String[] inputSplitted = input.split(",");

        if (inputSplitted.length <= 0) {
            throw new IllegalArgumentException("Input is not correct");
        }

        Map<Integer, String> mealsPerPeriod = this.findMealsPerPeriod(inputSplitted[0]);
        List<Integer> dishTypes = new ArrayList<>();

        for (int i=1; i<inputSplitted.length; i++) {
            dishTypes.add(Integer.parseInt(inputSplitted[i]));
        }

        orderedDishTypes = dishTypes.stream()
                                     .sorted()
                                     .collect(Collectors.toList());

        List<Integer> repeatedDishesTypes = new ArrayList<>();
        String output = "";

        for (Integer dishType : orderedDishTypes) {
            String currentDish = mealsPerPeriod.get(dishType);
            if (currentDish == null || repeatedDishesTypes.contains(dishType)) {
                continue;
            }

            if (!dishType.equals(orderedDishTypes.get(0))) {
                output = output.concat(", ");
            }

            output = output.concat(currentDish);

            int ocurrences = this.getOcurrencesByDishType(dishType);
            if (ocurrences > 1) {
                output = output.concat("(x"+ ocurrences +")");
                repeatedDishesTypes.add(dishType);
            }
        }

        return output;
    }

    private int getOcurrencesByDishType(Integer dishType) {
        int ocurrences = 0;
        for (Integer currentDishType : orderedDishTypes) {
            if (currentDishType.equals(dishType)) {
                ocurrences++;
            }
        }
        return ocurrences;
    }

    private Map<Integer, String> findMealsPerPeriod(String period) {
        Map<Integer, String> meals = new HashMap<>();

        if (period.equals("morning")) {
            meals.put(1, "eggs");
            meals.put(2, "toast");
            meals.put(3, "coffee");
            meals.put(4, "error");

        } else if (period.equals("night")) {
            meals.put(1, "steak");
            meals.put(2, "potato");
            meals.put(3, "wine");
            meals.put(4, "cake");

        } else {
            throw new IllegalArgumentException("Period: " + period + " was not expected");
        }

        return meals;
    }
}

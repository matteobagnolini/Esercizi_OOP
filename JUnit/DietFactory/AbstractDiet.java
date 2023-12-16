package a02a.e1;

import java.util.*;

public abstract class AbstractDiet implements Diet {
    
    private final Map<String, Food> foodMap = new HashMap<>();
    protected Double totalCarbs = 0.0;
    protected Double totalProteins = 0.0;
    protected Double totalFat = 0.0;

    @Override
    public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
        var carbs = nutritionMap.get(Nutrient.CARBS);
        var proteins = nutritionMap.get(Nutrient.PROTEINS);
        var fat = nutritionMap.get(Nutrient.FAT);
        foodMap.put(name, new Food(Double.valueOf(carbs), Double.valueOf(proteins), Double.valueOf(fat)));
    }

    @Override
    public abstract boolean isValid(Map<String, Double> dietMap);

    protected Double totalCal(Map<String, Double> dietMap) {
        totalCarbs = 0.0;
        totalProteins = 0.0;
        totalFat = 0.0;
        for(var elem : dietMap.entrySet()) {
            String foodName = elem.getKey();
            Double gr = elem.getValue();
            totalCarbs += getCarbs(foodName) * gr / 100;
            totalProteins += getProteins(foodName) * gr / 100;
            totalFat += getFat(foodName) * gr / 100;
        }
        return (totalCarbs + totalProteins + totalFat);
    }

    private Double getCarbs(String name) {
        return foodMap.get(name).carbs();
    }

    private Double getProteins(String name) {
        return foodMap.get(name).proteins();
    }

    private Double getFat(String name) {
        return foodMap.get(name).fat();
    }

}

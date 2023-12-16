package a02a.e1;

import java.util.Map;

public class DietFactoryImpl implements DietFactory {



    @Override
    public Diet standard() {
        return new AbstractDiet() {

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                Double tot = totalCal(dietMap);
                return tot >= 1500.0 && tot <= 2000.0;
            }

        };
    }

    @Override
    public Diet lowCarb() {
        return new AbstractDiet() {

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                var tot = totalCal(dietMap);
                return tot >= 1000.0 && tot <= 1500.0 && totalCarbs <= 300;
            }

        };
    }

    @Override
    public Diet highProtein() {
        return new AbstractDiet() {

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                var tot = totalCal(dietMap);
                return tot >= 2000 && tot <= 2500 && totalProteins >= 1300;
            }

        };
    }

    @Override
    public Diet balanced() {
        return new AbstractDiet() {

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                var tot = totalCal(dietMap);
                return tot >= 1600 && tot <= 2000 && totalCarbs >= 600 && totalProteins >= 600 && totalFat >= 400 
                    && (totalFat + totalProteins) <= 1100 ;            
            }
            
        };
    }

}

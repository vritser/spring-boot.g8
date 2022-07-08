package $package$.$name;format="normalize"$.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberCalc {
    
    public static double calc(double number) {
        return BigDecimal.valueOf(number)
            .setScale(2, RoundingMode.HALF_UP)
            .doubleValue();
    }
}

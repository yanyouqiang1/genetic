package mathGenetic;

import java.util.List;

public class MathCalc {
    public double calcFunction(List list) {
        Double d = (Double) list.get(0);

        return Math.sin(d);
    }
}

package microGenetic;

import java.util.List;

public class FitnessFunction {
    public static double a = 0.8;

    public double calcFitness(List list) {
        return 1/(a*this.calcError()+1);
    }

    private double calcError(){
        AbstractDatabase database = new Database();
        List<AbstractDatabase.Data> dataList = database.getDataList();
        int k = dataList.size();

        ...
    }
    private double calcSum(){

    }


}

package mathGenetic;

public class MainTestClass {

    public static void main(String[] args) {

        GeneticAlgorithm genAL = new GeneticAlgorithm(50, 1, 0.0,
                0.0, 99999999, 0.0, 0.8,
                0.8, 0, 0.004, 0, 4, 100);

        genAL.geneticAlgorithmPorcess();
    }
}
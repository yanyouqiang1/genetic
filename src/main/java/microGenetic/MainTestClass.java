package microGenetic;

public class MainTestClass {

    public static void main(String[] args) {

        MicroGeneticAlgorithm genAL = new MicroGeneticAlgorithm(50, 1, 0.0,
                0.0, 99999999, 0.0, 0.8,
                0.8, 0, 0.004, 0, 4, 100);

        genAL.geneticAlgorithmPorcess();
    }
}
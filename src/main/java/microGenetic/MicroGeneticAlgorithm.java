package microGenetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 遗传算法
 * @author：luchi
 * @date:2015/12/9
 * @description：使用遗传算法求解最值问题
 * */
public class MicroGeneticAlgorithm {

    //放置所有的种群基因信息
    private List<MicroGenome> population = new ArrayList<MicroGenome>();
    //种群数量信息
    private int popSize;
    //每条染色体总数目
    private int chromoLength;
    //种群总的适应度数值
    private double totalFitness;
    //种群最好的适应度
    private double bestFitness;
    //种群最坏的适应度
    private double worstFitness;
    //种群平均的适应度
    private double averageFitness;
    //最好的适应度的对应的染色体
    private MicroGenome bestMicroGenome;
    //基因突变概率
    private double mutationRate;
    //基因交叉概率
    private double crossoverRate;
    //遗传的代数(第几代)
    private int generation;
    //最大变异步长
    private double maxPerturbation;
    //种群适应度的范围,left为左，right为右边
    private double leftPoint;
    private double rightPoint;
    //遗传最大的迭代次数
    private int iterNum;

    public int getIterNum() {
        return iterNum;
    }

    public void setIterNum(int iterNum) {
        this.iterNum = iterNum;
    }

    private Random random = new Random();
    private FitnessFunction fitnessFunction = new FitnessFunction();

    public List<MicroGenome> getPopulation() {
        return population;
    }

    public void setPopulation(List<MicroGenome> population) {
        this.population = population;
    }

    public int getPopSize() {
        return popSize;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

    public int getChromoLength() {
        return chromoLength;
    }

    public void setChromoLength(int chromoLength) {
        this.chromoLength = chromoLength;
    }

    public double getTotalFitness() {
        return totalFitness;
    }

    public void setTotalFitness(double totalFitness) {
        this.totalFitness = totalFitness;
    }

    public double getBestFitness() {
        return bestFitness;
    }

    public void setBestFitness(double bestFitness) {
        this.bestFitness = bestFitness;
    }

    public double getWorstFitness() {
        return worstFitness;
    }

    public void setWorstFitness(double worstFitness) {
        this.worstFitness = worstFitness;
    }

    public double getAverageFitness() {
        return averageFitness;
    }

    public void setAverageFitness(double averageFitness) {
        this.averageFitness = averageFitness;
    }

    public MicroGenome getBestMicroGenome() {
        return bestMicroGenome;
    }

    public void setBestMicroGenome(MicroGenome bestMicroGenome) {
        this.bestMicroGenome = bestMicroGenome;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }

    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public double getMaxPerturbation() {
        return maxPerturbation;
    }

    public void setMaxPerturbation(double maxPerturbation) {
        this.maxPerturbation = maxPerturbation;
    }

    public double getLeftPoint() {
        return leftPoint;
    }

    public void setLeftPoint(double leftPoint) {
        this.leftPoint = leftPoint;
    }

    public double getRightPoint() {
        return rightPoint;
    }

    public void setRightPoint(double rightPoint) {

        this.rightPoint = rightPoint;
    }

    //构造函数初始化参数值
    public MicroGeneticAlgorithm(int popSize, int chromoLength, double totalFitness, double bestFitness, double worstFitness,
                                 double averageFitness, double mutationRate, double crossoverRate, int generation, double maxPerturbation,
                                 double leftPoint, double rightPoint, int iterNum) {
        super();
        this.popSize = popSize;
        this.chromoLength = chromoLength;
        this.totalFitness = totalFitness;
        this.bestFitness = bestFitness;
        this.worstFitness = worstFitness;
        this.averageFitness = averageFitness;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.generation = generation;
        this.maxPerturbation = maxPerturbation;
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
        this.iterNum = iterNum;
    }

    /*转盘赌函数
     *注意：这里的转盘赌方法仅适用于适应度的值大于0的染色体序列
     *
     *
     * */

    //初始化种群信息
    public void init() {
        //清空
        population.clear();
        List list;
        double sum = 0.0;
        //赋予种群最初的信息
        for (int i = 0; i < popSize; i++) {
            double gene = random.nextDouble() * (rightPoint - leftPoint) + leftPoint;
            list = new ArrayList<Double>();
            list.add(gene);
            double fitness = fitnessFunction.calcFitness(list);
            sum += fitness;
            MicroGenome microGenome = new MicroGenome(list, fitness);
            population.add(microGenome);
        }
        setGenInfo();
        printGenInfo();
    }

    //转盘赌算法
    public MicroGenome getChromoRoulette() {


        double slice = random.nextDouble() * totalFitness;
        MicroGenome choseMicroGenome = null;
        double sum = 0.0;
        for (int i = 0; i < population.size(); i++) {
            choseMicroGenome = population.get(i);
            sum += choseMicroGenome.getFitness();
            if (sum >= slice && choseMicroGenome.getFitness() > this.averageFitness) {
                break;
            }
        }
        return choseMicroGenome;

    }

    //基因突变
    public void Mutate(List<Double> genomList) {

        for (int i = 0; i < genomList.size(); i++) {

            //根据指定的突变率选择突变与否
            double randomRate = random.nextDouble();
            if (randomRate < this.getMutationRate()) {
                double raw = (double) genomList.get(i);
                raw += (random.nextDouble() - 0.5) * this.getMaxPerturbation();
                if (raw < leftPoint) {
                    raw = leftPoint;
                } else if (raw > rightPoint) {
                    raw = rightPoint;
                }
                genomList.set(i, raw);
            }

        }
    }

    //进化核心方法,每个双亲生成两个子女
    public void Epoch(final List<MicroGenome> popList) {

        //选择父母，产生后代，注意这里需要size是双数，其实在选择父母的过程中就已经包含了淘汰的过程
        List<MicroGenome> newPopList = new ArrayList<MicroGenome>();
        while (newPopList.size() < this.getPopSize()) {

            MicroGenome mum = this.getChromoRoulette();
            MicroGenome dad = this.getChromoRoulette();
            //生成新的基因序列
            List<Double> baby1 = mum.getGenomeList();
            List<Double> baby2 = dad.getGenomeList();
            this.Mutate(baby1);
            this.Mutate(baby2);
            MicroGenome newBabyMicroGenome1 = new MicroGenome(baby1, fitnessFunction.calcFitness(baby1));
            MicroGenome newBabyMicroGenome2 = new MicroGenome(baby2, fitnessFunction.calcFitness(baby1));
            newPopList.add(newBabyMicroGenome1);
            newPopList.add(newBabyMicroGenome2);

        }
        popList.clear();
        //产生新的一代
        for (int i = 0; i < newPopList.size(); i++) {
            popList.add(newPopList.get(i));
        }
        newPopList = null;

    }

    public void setGenInfo() {

        MicroGenome bestMicroGenome = population.get(0);
        MicroGenome worstMicroGenome = population.get(0);
        double totalFit = 0.0;
        for (int i = 0; i < population.size(); i++) {
            MicroGenome genom = population.get(i);
            totalFit += genom.getFitness();
            if (genom.getFitness() > bestMicroGenome.getFitness()) {
                bestMicroGenome = genom;
            }
            if (genom.getFitness() < worstMicroGenome.getFitness()) {
                worstMicroGenome = genom;
            }


        }

        double averageFit = totalFit / population.size();
        this.setTotalFitness(totalFit);
        this.setBestFitness(bestMicroGenome.getFitness());
        this.setWorstFitness(worstMicroGenome.getFitness());
        this.setAverageFitness(averageFit);
        this.setGeneration(this.getGeneration() + 1);

    }

    public void printGenInfo() {
        System.out.print("the generation is:\t");
        System.out.println(this.generation);
        System.out.print("the best fitness is:\t");
        System.out.println(this.getBestFitness());
        System.out.print("the worst fitness is:\t");
        System.out.println(this.getWorstFitness());
        System.out.print("the average fitness is:\t");
        System.out.println(this.getAverageFitness());
        System.out.print("the total fitness is:\t");
        System.out.println(this.getTotalFitness());
    }

    //遗传算法具体操作步骤
    public void geneticAlgorithmPorcess() {

        int iterNum = this.iterNum;
        this.init();
        for (int gen = 0; gen < this.iterNum; gen++) {
            this.Epoch(this.population);
            setGenInfo();
            printGenInfo();
        }


    }


}
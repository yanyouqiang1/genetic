package mathGenetic;
import java.util.ArrayList;
import java.util.List;

/*
 * 基因载体类
 * */
public class Genome {


    private List<Double> genomeList=new ArrayList<Double>();  //存放基因序列
    private double fitness;  //适应度函数值
    public List<Double> getGenomeList() {
        return genomeList;
    }
    public void setGenomeList(List<Double> genomeList) {
        this.genomeList = genomeList;
    }
    public double getFitness() {
        return fitness;
    }
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    public Genome(){
        super();
    }
    public Genome(List<Double> genomeList, double fitness) {
        super();
        this.genomeList = genomeList;
        this.fitness = fitness;
    }

}
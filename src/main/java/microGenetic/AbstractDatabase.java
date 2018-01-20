package microGenetic;

import com.sun.javafx.geom.Matrix3f;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDatabase {
    public static int serviceSize = 4;

    private List<Data> dataList = new LinkedList<Data>();

    public abstract List<Data> getDataList();

    class Data{
        Matrix3f
        int[] y = new int[AbstractDatabase.serviceSize];
        int[] v = new int[AbstractDatabase.serviceSize];
    }
}

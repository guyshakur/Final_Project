import java.util.List;

public class Demo {

    public static void main(String[] args) {
        int[][] myArray = {
                {1,1,1,1,1},
                {0,1,1,1,1},
                {1,1,1,0,0},
                {0,1,0,1,0},
                {0,1,1,1,0}

        };
        int[][] myArray2= {
                {1,1,0,0,1},
                {1,1,0,0,1},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,1,1}

        };
        int[][] myArray3= {
                {1,0,0},
                {1,1,0},
                {1,1,0}
        };

        TraversableMatrix myMatrixGraph = new TraversableMatrix(new Matrix(myArray2));
        ThreadLocalDFS dfs=new ThreadLocalDFS();
        //myMatrixGraph.setStartIndex(new Index(0,4));

        //List<Index> index=dfs.traverse(myMatrixGraph);

       myMatrixGraph.allConnectedComponents();

        List<Index>index=dfs.traverse(myMatrixGraph);
        //System.out.println(index);
        //myMatrixGraph.allConnectedComponents((myArray));


    }
}

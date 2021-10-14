public class Demo {

    public static void main(String[] args) {
        int[][] myArray = {
                {1,1,1,1,1},
                {1,1,1,1,1},
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
                {1,1,1}
        };
        int[][] myArray4= {
                {1,1,0,0},
                {1,1,0,1},
                {0,0,0,1},
                {0,0,0,1}
        };

        TraversableMatrix myMatrixGraph = new TraversableMatrix(new Matrix(myArray2));
        //myMatrixGraph.setStartIndex(new Index(0,4));

        //List<Index> index=dfs.traverse(myMatrixGraph);

       myMatrixGraph.allConnectedComponents();


        ThreadLocalBFS p=new ThreadLocalBFS();

        Index source = new Index(0, 3);
        Index dest = new Index(1, 1);


       // Path path=new Path();
       // List<List<Index>>bla=path.BFS(myArray2, source, dest);
       // path.printPaths(bla.get(0));


        int bb= myMatrixGraph.LegalSubmarines(myMatrixGraph.allConnectedComponents());
        System.out.println(bb);







    }
}

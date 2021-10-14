import java.io.*;
import java.util.*;

public class MatrixHandler implements IHandler{
    private Matrix matrix;
    private Index start,end;
    private TraversableMatrix traversableMatrix;

//    @Override
//    public void handle(InputStream inClient, OutputStream outClient) throws Exception {
//
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outClient);
//        ObjectInputStream objectInputStream = new ObjectInputStream(inClient);
//
//        // this.resetParams();
//
//        boolean dowork = true;
//        while (dowork) {
//            switch (objectInputStream.readObject().toString()) {
//                case "stop": {
//
//                    dowork = false;
//                    break;
//                }
//                case "matrix": {
//                    int[][] primitiveMatrix = (int[][]) objectInputStream.readObject();
//                    this.matrix = new Matrix(primitiveMatrix);
//                    this.matrix.printMatrix();
//                    //this.mRow = matrix.primitiveMatrix.length;
//                    // this.mCol = matrix.primitiveMatrix[0].length;
//                    // updateMatrix();
//                    System.out.println("Matrix after update to Valued Matrix");
//                    this.matrix.printMatrix();
//                    // g = new Graph(v);
//                    // getAllEdges(g, matrix);
//                    //System.out.println("Count: " + v);
//                    break;
//                }
//                case "start index": {
//                    this.start = (Index) objectInputStream.readObject();
//                    //  this.source = matrix.primitiveMatrix[start.row][start.column];
//                    break;
//                }
//                case "end index": {
//                    this.end = (Index) objectInputStream.readObject();
//                    //  this.target = matrix.primitiveMatrix[end.row][end.column];
//                    break;
//                }
//
//            }
//        }
//    }

    @Override
    public void handle(InputStream inClient, OutputStream outClient) throws Exception {
        /*
        Use switch-case in order to get command from the client
        - Client can send a 2d array -> return a Matrix. represented by the command "matrix"
        - Client can send start/end index
        - Client can send an index -> return a collection of neighboring indices
        - Client can send an index -> return a collection of its reachable indices
        - Client can send start + end index and wishes to get all possible paths
         */
//         BufferedReader bufferedReader = new BufferedReader(characterReader)
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outClient);
        ObjectInputStream objectInputStream = new ObjectInputStream(inClient);

        resetParams();

        boolean doWork = true;
        while(doWork){
            switch (objectInputStream.readObject().toString()){

                case "matrix":{
                    //client will send a 2d array. handler will create a new matrix object
                    int[][] primitiveArray = (int[][])objectInputStream.readObject();
                    System.out.println("Server: Got 2d array from client");
                    this.matrix = new Matrix(primitiveArray);
                    traversableMatrix=new TraversableMatrix(matrix);
                    objectOutputStream.writeObject(this.matrix);
                    break;
                }

                case "start index":{
                    this.start=(Index) objectInputStream.readObject();
                    //objectOutputStream.writeObject(start);
                    break;
                }
                case "end index":{
                    this.end=(Index) objectInputStream.readObject();
                    break;
                }

                case "Mission1":{
                    LinkedHashSet<List<Index>> components=new LinkedHashSet<>();
                    components=mission1();
                    objectOutputStream.writeObject(components);
                    break;

                }
                case "Mission2":{
                    List<List<Index>> shortestPaths = new ArrayList<>();
                    shortestPaths = (ArrayList)mission2();
                    objectOutputStream.writeObject(shortestPaths);
                    break;
                }
                case "Mission3":{
                    LinkedHashSet<List<Index>> list=new LinkedHashSet<>();
                    int result=mission3(list);
                    objectOutputStream.writeObject(result);
                    break;
                }

                case "neighbors":{
                    // handler will receive an index, then compute its neighbors
                        Index tempIndex = (Index)objectInputStream.readObject();
                        List<Index> neighbors = new ArrayList<>(this.matrix.getNeighbors(tempIndex));
                        objectOutputStream.writeObject(neighbors);

                    break;
                }

                case "reachables":{
                    Index index = (Index)objectInputStream.readObject();
                    List<Index> neighbors = new ArrayList<>();
                    if (this.matrix!=null){
                        neighbors.addAll(this.matrix.getReachables(index));
                        objectOutputStream.writeObject(neighbors);
                    }
                    break;
                }

                case "stop":{
                    doWork = false;
                    break;
                }


            }
        }

    }

    public int mission3(Collection<List<Index>> arr){
        //arr = new ArrayList<>();
        arr=traversableMatrix.allConnectedComponents();
        int numOfSubs=traversableMatrix.LegalSubmarines(arr);
        return  numOfSubs;

    }

    public List<List<Index>> mission2() {
        List<List<Index>>shortestsPaths=traversableMatrix.getAllShortestsPaths(start,end);
        //traversableMatrix.printAllShortestPats(shortestsPaths);
        return shortestsPaths;

    }

    public LinkedHashSet<List<Index>> mission1() {
        LinkedHashSet<List<Index>> list=new LinkedHashSet<>();
        list=(LinkedHashSet)traversableMatrix.allConnectedComponents();
        return  list;
    }


    private void resetParams(){
        this.matrix=null;
        this.start=null;
        this.end=null;

    }
}

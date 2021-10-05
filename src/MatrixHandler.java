import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MatrixHandler implements IHandler {
    private Matrix matrix;
    private Index start,end;



    @Override
    public void handle(InputStream fromClient, OutputStream toClient) throws IOException, ClassNotFoundException {
        /*
        Use switch-case in order to get command from the client
        - Client can send a 2d array -> return a Matrix. represented by the command "matrix"
        - Client can send start/end index
        - Client can send an index -> return a collection of neighboring indices
        - Client can send an index -> return a collection of its reachable indices
        - Client can send start + end index and wishes to get all possible paths
         */
//         InputStreamReader characterReader = new InputStreamReader(fromClient);
//         BufferedReader bufferedReader = new BufferedReader(characterReader)
        ObjectInputStream objectInputStream = new ObjectInputStream(fromClient);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(toClient);
        boolean doWork = true;
        while(doWork){
            switch (objectInputStream.readObject().toString()){
                case "matrix":{
                    // client will send a 2d array. handler will create a new matrix object
                    int[][] primitiveArray = (int[][])objectInputStream.readObject();
                    System.out.println("Server: Got 2d array from client");
                    this.matrix = new Matrix(primitiveArray);
                    objectOutputStream.writeObject(this.matrix);
                    break;
                }

                case "neighbors":{
                    Index index = (Index)objectInputStream.readObject();
                    List<Index> neighbors = new ArrayList<>();
                    if (this.matrix!=null){
                        neighbors.addAll(this.matrix.getNeighbors(index));
                    }
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
}

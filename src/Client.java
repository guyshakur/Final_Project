
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        // In order to request something over TCP from a server, we need a port number and an IP address
//        //socket is an abstraction of 2-way data pipe
//        Socket socket = new Socket("127.0.0.1", 8010);
//
//        System.out.println("Created client Socket");
//
//        InputStream inputStream = socket.getInputStream();
//        OutputStream outputStream = socket.getOutputStream();
//
////
////         // use decorators
////
//
//        ObjectInputStream fromServer = new ObjectInputStream(inputStream);
//        ObjectOutputStream toServer = new ObjectOutputStream(outputStream);
//
////        }
//
//
//
//        int[][] source = {
//                {1, 1, 1, 1, 1},
//                {1, 1, 1, 1, 1},
//                {1, 1, 1, 0, 0},
//                {0, 1, 0, 1, 0},
//                {0, 1, 1, 1, 0}
//
//        };
//
//        toServer.writeObject("matrix");
//        // according to protocol, after "matrix" string, send 2d int array
//        toServer.writeObject(source);
//
//        toServer.writeObject("start index");
//        // according to protocol, after "matrix" string, send 2d int array
//        toServer.writeObject(new Index(0,0));
//
//
//        toServer.writeObject("end index");
//        // according to protocol, after "matrix" string, send 2d int array
//        toServer.writeObject(new Index(2,3));
//
//        toServer.writeObject("neighbors");
//        toServer.writeObject(new Index(0,0));
//        Collection<Index> Neighbors = new ArrayList<>((Collection<Index>) fromServer.readObject());
//       System.out.println("Neighbors: " + Neighbors);


        // toServer.writeObject(new Index(0,0));
        //Index index=(Index)fromServer.readObject();
        //index.toString();
        //according to protocol, after "start index" string, send the start index

        //toServer.writeObject(new Index(0, 0));
        //System.out.println((Index)fromServer.readObject());
//
//        toServer.writeObject("end index");
//        // according to protocol, after "start index" string, send the end index
//        toServer.writeObject(new Index(2, 3));
//        Index index=(Index)fromServer.readObject();
//        System.out.println("aad");


//        toServer.writeObject("neighbors");
//        Collection<Index> Neighbors = new ArrayList<>((Collection<Index>) fromServer.readObject());
//        System.out.println("Neighbors: " + Neighbors);

//        toServer.writeObject("Mission1");
//        LinkedHashSet<List<Index>> components=new LinkedHashSet<>();
//        components=(LinkedHashSet<List<Index>>) fromServer.readObject();

        //Collection<Index> connectedComponents = new ArrayList<>((Collection<Index>) fromServer.readObject());
//        System.out.println("Mission1 results : " + components);

//        toServer.writeObject("Mission3");
////        toServer.writeObject(new Index(1,1));
//        int numberOfSubs = (int) fromServer.readObject();
//        System.out.println("Mission3 results : Number of legal Subs:" + numberOfSubs);
//
//        toServer.writeObject("Mission4");
////        toServer.writeObject(new Index(1,1));
//        List<List<Integer>> allPaths = new ArrayList<>((ArrayList) fromServer.readObject());
//        System.out.println("Mission4 results :" + allPaths);
//
//        toServer.writeObject("Mission2");
////        toServer.writeObject(new Index(1,1));
//        List<List<Integer>> allPaths2 = new ArrayList<>((ArrayList) fromServer.readObject());
//        System.out.println("Mission2 results :" + allPaths2);

//        toServer.writeObject("stop");
//        fromServer.close();
//        toServer.close();
//        socket.close();
//        System.out.println("All streams are closed");
//    }

        // In order to request something over TCP from a server, we need a port number and an IP address
//        Socket socket = new Socket("127.0.0.1",8010);
//        // socket is an abstraction of 2-way data pipe
//        InputStream inputStream = socket.getInputStream();
//        OutputStream outputStream = socket.getOutputStream();
//
//        // use decorators
//        ObjectInputStream fromServer = new ObjectInputStream(inputStream);
//        ObjectOutputStream toServer = new ObjectOutputStream(outputStream);
//        System.out.println("ffga");
//
//        int[][] source = {
//                {1,1,0,0,0},
//                {1,0,0,0,0},
//                {1,1,1,1,1}
//        };
//
//        toServer.writeObject("matrix");
//        // according to protocol, after "matrix" string, send 2d int array
//        toServer.writeObject(source);
//
//        toServer.writeObject("start index");
//        // according to protocol, after "matrix" string, send 2d int array
//        toServer.writeObject(new Index(0,0));
//
//        toServer.writeObject("end index");
//        // according to protocol, after "matrix" string, send 2d int array
//        toServer.writeObject(new Index(2,3));
//
//    }

        // In order to request something over TCP from a server, we need a port number and an IP address
        Socket socket = new Socket("127.0.0.1", 8010);
        // socket is an abstraction of 2-way data pipe
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // use decorators
        ObjectInputStream fromServer = new ObjectInputStream(inputStream);
        ObjectOutputStream toServer = new ObjectOutputStream(outputStream);

        int[][] source = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0}
        };
        int[][] source2= {
                {1,1,0,0,1},
                {1,1,0,0,1},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,1,1}

        };

        toServer.writeObject("matrix");
        // according to protocol, after "matrix" string, send 2d int array
        toServer.writeObject(source2);
        Matrix matrix=(Matrix) fromServer.readObject();
        System.out.println("the matrix:");
        matrix.printMatrix();
        System.out.println();


        toServer.writeObject("start index");
        toServer.writeObject(new Index(0,1));

        toServer.writeObject("end index");
        toServer.writeObject(new Index(1,1));

        toServer.writeObject("neighbors");
        toServer.writeObject(new Index(0,0));
        List<Index> neighbors=(ArrayList)fromServer.readObject();

        toServer.writeObject("Mission1");
        LinkedHashSet<List<Index>> components=(LinkedHashSet<List<Index>>)fromServer.readObject();
        System.out.println("mission 1 result: "+components);

        toServer.writeObject("Mission2");
        List<List<Index>> shortestPaths=(List<List<Index>>)fromServer.readObject();
        TraversableMatrix traversableMatrix=new TraversableMatrix(matrix);
        System.out.print("mission 2 result:");
        traversableMatrix.printAllShortestPats(shortestPaths);
        System.out.println();

        toServer.writeObject("Mission3");
        //LinkedHashSet<List<Index>> list=(LinkedHashSet<List<Index>>)fromServer.readObject();
        int numOfSubs=(int)fromServer.readObject();
        System.out.println("mission 3 result - num of submarines: "+numOfSubs);
        System.out.println();

        toServer.writeObject("Mission4");

        toServer.writeObject("stop");
        fromServer.close();
        toServer.close();
        socket.close();
        System.out.println("All streams are closed");








        //System.out.println(m.primitiveMatrix[0][0]);



//        toServer.writeObject("neighbors");
//        toServer.writeObject(new Index(1, 1));
//
//        // get neighboring indices as list
//        List<Index> AdjacentIndices = new ArrayList<Index>((List<Index>)fromServer.readObject());
//        System.out.println("from client - Neighboring Indices are: " + AdjacentIndices);
//
//        toServer.writeObject("start index");
//        // according to protocol, after "matrix" string, send 2d int array
//        toServer.writeObject(new Index(0, 0));
//
//        toServer.writeObject("end index");
//        // according to protocol, after "matrix" string, send 2d int array
//        toServer.writeObject(new Index(2, 3));
//
//        toServer.writeObject("TaskOne");
//        toServer.writeObject(new Index(1, 1));
//
//        Collection<Index> adjacentIndices = new ArrayList<>((Collection<Index>) fromServer.readObject());
//        System.out.println("Neighbors: " + adjacentIndices);
//
//        toServer.writeObject("Mission1");
////        toServer.writeObject(new Index(1,1));
//        Collection<Index> connectedComponents = new ArrayList<>((Collection<Index>) fromServer.readObject());
//        System.out.println("Mission1 results : " + connectedComponents);
//
//        toServer.writeObject("Mission3");
////        toServer.writeObject(new Index(1,1));
//        int numberOfSubs = (int) fromServer.readObject();
//        System.out.println("Mission3 results : Number of legal Subs:" + numberOfSubs);
//
//        toServer.writeObject("Mission4");
////        toServer.writeObject(new Index(1,1));
//        List<List<Integer>> allPaths = new ArrayList<>((ArrayList) fromServer.readObject());
//        System.out.println("Mission4 results :" + allPaths);
//
//        toServer.writeObject("Mission2");
////        toServer.writeObject(new Index(1,1));
//        List<List<Integer>> allPaths2 = new ArrayList<>((ArrayList) fromServer.readObject());
//        System.out.println("Mission2 results :" + allPaths2);
//
//        toServer.writeObject("stop");
//        fromServer.close();
//        toServer.close();
//        socket.close();
//        System.out.println("All streams are closed");
//
//    }
    }
}
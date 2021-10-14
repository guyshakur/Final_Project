// Java program to find the shortest
// path between a given source cell
// to a destination cell.
import java.io.Serializable;
import java.util.*;

public class ThreadLocalBFS
{
    final ThreadLocal<Queue<Node<Index>>> threadLocalQueue = ThreadLocal.withInitial(() -> new LinkedList<Node<Index>>());
    // check whether given cell (row, col)
// is a valid cell or not.


    // These arrays are used to get row and column

    // function to find the shortest path between
// a given source cell to a destination cell.
    public List<List<Index>> BFS(int mat[][], Index src,
                   Index dest)
    {
        Matrix m=new Matrix(mat);
        List<Index>reachables=new ArrayList<>();
        List<List<Index>>allShortestPaths=new ArrayList<>();
        List<Index>shortPath=new ArrayList<>();
        // check source and destination cell
        // of the matrix have value 1
        if (mat[src.row][src.column] != 1 ||
                mat[dest.row][dest.column] != 1)
            //return -1;
            return allShortestPaths;

        boolean [][]visited = new boolean[m.primitiveMatrix.length][m.primitiveMatrix.length];

        // Mark the source cell as visited
        visited[src.row][src.column] = true;

        // Create a queue for BFS


        // Distance of source cell is 0
        Node s = new Node(src,null
        );
        threadLocalQueue.get().add(s); // Enqueue source cell

        // Do a BFS starting from source cell
        while (!threadLocalQueue.get().isEmpty())
        {
            Node currNode = threadLocalQueue.get().peek();
            Index index = (Index)currNode.getData();
            // If we have reached the destination cell,
            // we are done

                if (index.row == dest.row && index.column == dest.column){
                    shortPath.add(new Index(dest.row, dest.column));
                    //ps.add(new queueNode(dest,0,null));
                    Node curr2=currNode;


                    while(curr2.getParent()!=null)
                    {
                        curr2=curr2.getParent();
                        shortPath.add((Index)curr2.getData());
                        //ps.add(curr2);
                        //System.out.println(ps);
                    }
                    allShortestPaths.add(shortPath);
            }

            // Otherwise dequeue the front cell
            // in the queue and enqueue
            // its adjacent cells

            threadLocalQueue.get().remove();

            reachables=(ArrayList)m.getReachables(index);
            for(int i=0;i<reachables.size();i++){
                if(!visited[reachables.get(i).row][reachables.get(i).column]){
                    visited[reachables.get(i).row][reachables.get(i).column]=true;
                    Node neibghors=new Node<Index>(new Index(reachables.get(i).row,reachables.get(i).column),currNode);
                    neibghors.setDist(currNode.getDist()+1);
                    threadLocalQueue.get().add(neibghors);
                }

            }

        }
        return allShortestPaths;
    }

    public void printPaths(List<Index>path){
        for(int i=path.size()-1;i>=0;i--){
            if(i==0){
                System.out.print(path.get(0));
                return;
            }
            System.out.print(path.get(i)+"->");
        }
    }



}
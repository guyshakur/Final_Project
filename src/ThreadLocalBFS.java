import java.util.*;

public class ThreadLocalBFS<T> {
    final List<List<Integer>> allPaths=new ArrayList<>();
    final ThreadLocal<Queue<Node<T>>> threadLocalQueue = ThreadLocal.withInitial(LinkedList::new);
    Collection<List<Index>> adjListArray;
    TraversableMatrix matrix;

    public ThreadLocalBFS(TraversableMatrix matrix) {
        this.matrix=matrix;
        adjListArray=new ArrayList<>();

    }
    public void setAdjListArray(){
        for(int i=0;i<matrix.getSizeOfGraph();i++){


        }
    }

    public void findAllPathsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList)
    {
        if (u.equals(d)) {
            allPaths.add(new ArrayList<>(localPathList));
            // if match found then no need to traverse more till depth
            return;
        }
        isVisited[u] = true;

        // Recur for all the vertices adjacent to current vertex
        //for (Integer i : adjListArray.get(u)) {
           // if (!isVisited[i]) {
                // store current node in path[]
               // localPathList.add(i);
                //findAllPathsUtil(i, d, isVisited, localPathList);

                // remove current node in path[]
                //localPathList.remove(i);
            }

        // Mark the current node
       // isVisited[u] = false;


}





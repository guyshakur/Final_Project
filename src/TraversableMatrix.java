import java.util.*;
import java.util.stream.Collectors;

/**
 * This class implements adapter/wrapper/decorator design pattern
 */
public class TraversableMatrix implements Traversable<Index> {
    protected final Matrix matrix;
    protected Index startIndex;
    protected int V;


    public TraversableMatrix(Matrix matrix) {
        this.matrix = matrix;
        V = matrix.primitiveMatrix.length;
    }

    public Index getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Index startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public Node<Index> getOrigin() throws NullPointerException {
        if (this.startIndex == null) throw new NullPointerException("start index is not initialized");
        return new Node<>(this.startIndex);

    }



    @Override
    public Collection<Node<Index>> getReachableNodes(Node<Index> someNode) {
        List<Node<Index>> reachableIndices = new ArrayList<>();
        if(matrix.getValue(someNode.getData())==0)
        {
            reachableIndices.add(someNode);
            return  reachableIndices;
        }
        for (Index index : this.matrix.getNeighbors(someNode.getData())) {
            if (matrix.getValue(index) == 1) {
                // A neighboring index whose value is 1
                Node<Index> indexNode = new Node<>(index, someNode);
                reachableIndices.add(indexNode);
            }
        }
        return reachableIndices;
    }

    @Override
    public int getSizeOfGraph() {
        return getV();
    }

    public Collection<Node<Index>> getReachableNodesWithStreams(Node<Index> someNode) {
        return this.matrix.getNeighbors(someNode.getData())
                .stream().filter(index -> matrix.getValue(index) == 1).map(index -> new Node<>(index, someNode))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return matrix.toString();
    }


    public Collection<List<Index>> allConnectedComponents() {
        ThreadLocalDFS dfs = new ThreadLocalDFS();


        List<Index> visited=new ArrayList<>();
        LinkedHashSet<List<Index>> components=new LinkedHashSet<>();

            for (int row=0;row<getSizeOfGraph();row++)
                for(int col=0;col<V;col++)
                {

                    List<Index>sameConnectedComponentArr=new ArrayList<Index>();
                    if(this.matrix.primitiveMatrix[row][col]==1&&!visited.contains(new Index(row,col))){
                        this.setStartIndex(new Index(row, col));
                        sameConnectedComponentArr=dfs.traverse(this);
                        Collections.sort(sameConnectedComponentArr);
                        components.add(sameConnectedComponentArr);

                        //creating a list of all visited to create unique
                        for(int i=0;i<sameConnectedComponentArr.size();i++)
                        {
                            visited.add(sameConnectedComponentArr.get(i));
                        }

                    }

                }

        System.out.println(components);
        return components;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public int getV() {
        return V;
    }
}

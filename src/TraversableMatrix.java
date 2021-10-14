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
    public Collection<Node<Index>> getReachableNodes(Node<Index> someNode,boolean isNeedDiagonal) {
        List<Node<Index>> reachableIndices = new ArrayList<>();
        if(matrix.getValue(someNode.getData())==0)
        {
            reachableIndices.add(someNode);
            return  reachableIndices;
        }
        if(isNeedDiagonal) {
            for (Index index : this.matrix.getNeighbors(someNode.getData())) {
                if (matrix.getValue(index) == 1) {
                    // A neighboring index whose value is 1
                    Node<Index> indexNode = new Node<>(index, someNode);
                    reachableIndices.add(indexNode);
                }
            }
        }
            else{
                for (Index index : this.matrix.getReachablesWithoutdiagonals(someNode.getData())) {
                    if (matrix.getValue(index) == 1) {
                        // A neighboring index whose value is 1
                        Node<Index> indexNode = new Node<>(index, someNode);
                        reachableIndices.add(indexNode);
                    }
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
                        sameConnectedComponentArr=dfs.traverse(this,true);
                        Collections.sort(sameConnectedComponentArr);
                        components.add(sameConnectedComponentArr);

                        //creating a list of all visited
                        for(int i=0;i<sameConnectedComponentArr.size();i++)
                        {
                            visited.add(sameConnectedComponentArr.get(i));
                        }

                    }

                }

        //System.out.println(components);
        return components;
    }

    public List<List<Index>>getAllShortestsPaths(Index src,Index dest){
        ThreadLocalBFS path=new ThreadLocalBFS();

        return path.BFS(matrix.primitiveMatrix,src,dest);
    }

    public void printAllShortestPats(List<List<Index>>paths){
        ThreadLocalBFS path=new ThreadLocalBFS();
        for(int i=0;i<paths.size();i++){
            path.printPaths(paths.get(i));
        }
    }

    public int LegalSubmarines(Collection<List<Index>> allComponentsArr){

        int numOfSubmarines = 0;
        int counter = 0;
        boolean diagonalFlag = true;
        Iterator itr = allComponentsArr.iterator();
        while (itr.hasNext()){
            counter = 0;
            diagonalFlag = true;
            HashSet<Index> help = new HashSet<>((ArrayList)itr.next());
           // help = (HashSet<Index>) itr.next();
            Iterator itr2 = help.iterator();
            while (itr2.hasNext()){
                counter++;
                if(!matrix.findIfIndexHaveNoDiagonals((Index)itr2.next()))
                    diagonalFlag = false;
            }
            if(diagonalFlag && counter>1)
                numOfSubmarines++;
        }
        return numOfSubmarines;

    }

    public Matrix getMatrix() {
        return matrix;
    }

    public int getV() {
        return V;
    }
}

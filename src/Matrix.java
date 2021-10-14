import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Matrix implements Serializable {

    public void setPrimitiveMatrix(int[][] primitiveMatrix) {
        this.primitiveMatrix = primitiveMatrix;
    }

    public int[][] primitiveMatrix;

    public Matrix(int[][] oArray) {
        List<int[]> list = new ArrayList<>();
        for (int[] row : oArray) {
            int[] clone = row.clone();
            list.add(clone);
        }
        primitiveMatrix = list.toArray(new int[0][]);
    }

    public void printMatrix() {
        for (int[] row : primitiveMatrix) {
            String s = Arrays.toString(row);
            System.out.println(s);
        }
    }

    public final int[][] getPrimitiveMatrix() {
        return primitiveMatrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : primitiveMatrix) {
            stringBuilder.append(Arrays.toString(row));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
    public Collection<Index> getNeighborsWithoutDiagonals(final Index index){
        Collection<Index> list = new ArrayList<>();
        int extracted = -1;
        try {
            extracted = primitiveMatrix[index.row + 1][index.column];
            list.add(new Index(index.row + 1, index.column));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row][index.column + 1];
            list.add(new Index(index.row, index.column + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row - 1][index.column];
            list.add(new Index(index.row - 1, index.column));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row][index.column - 1];
            list.add(new Index(index.row, index.column - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return  list;
    }
    public Collection<Index> getNeighbors(final Index index) {
        Collection<Index> list = new ArrayList<>();
        int extracted = -1;
        try {
            extracted = primitiveMatrix[index.row + 1][index.column];
            list.add(new Index(index.row + 1, index.column));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row][index.column + 1];
            list.add(new Index(index.row, index.column + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row - 1][index.column];
            list.add(new Index(index.row - 1, index.column));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row][index.column - 1];
            list.add(new Index(index.row, index.column - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row+1][index.column + 1];
            list.add(new Index(index.row+1, index.column + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row+1][index.column - 1];
            list.add(new Index(index.row+1, index.column - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row-1][index.column - 1];
            list.add(new Index(index.row-1, index.column - 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            extracted = primitiveMatrix[index.row-1][index.column + 1];
            list.add(new Index(index.row-1, index.column + 1));
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return list;
    }

    public boolean findDiagonalWithIndex(Index index){
            int counter1, counter2, counter3, counter4;
            counter1 = counter2 = counter3 = counter4 = 0;
            int extracted = -1;
            try{
                extracted = primitiveMatrix[index.row+1][index.column];
                if(extracted!=-1) {
                    counter3 += extracted;
                    counter4 += extracted;
                }
            }catch (ArrayIndexOutOfBoundsException ignored){}
            try{
                extracted = primitiveMatrix[index.row][index.column+1];
                if(extracted!=-1) {
                    counter2 += extracted;
                    counter4 += extracted;
                }
            }catch (ArrayIndexOutOfBoundsException ignored){}
            try{
                extracted = primitiveMatrix[index.row-1][index.column];
                if(extracted!=-1) {
                    counter1 += extracted;
                    counter2 += extracted;
                }
            }catch (ArrayIndexOutOfBoundsException ignored){}
            try{
                extracted = primitiveMatrix[index.row][index.column-1];
                if(extracted!=-1) {
                    counter1 += extracted;
                    counter3 += extracted;
                }
            }catch (ArrayIndexOutOfBoundsException ignored){}
            try{
                extracted = primitiveMatrix[index.row-1][index.column-1];
                if(extracted!=-1) {
                    counter1 += extracted;
                }
            }catch (ArrayIndexOutOfBoundsException ignored){}
            try{
                extracted = primitiveMatrix[index.row+1][index.column-1];
                if(extracted!=-1) {
                    counter3 += extracted;
                }
            }catch (ArrayIndexOutOfBoundsException ignored){}
            try{
                extracted = primitiveMatrix[index.row+1][index.column+1];
                if(extracted!=-1) {
                    counter4 += extracted;
                }
            }catch (ArrayIndexOutOfBoundsException ignored){}
            try{
                extracted = primitiveMatrix[index.row-1][index.column+1];
                if(extracted!=-1) {
                    counter2 += extracted;
                }
            }catch (ArrayIndexOutOfBoundsException ignored){}
            if((counter1 == 2) || (counter2 == 2) || (counter3 == 2) || (counter4 == 2)){
                return false;
            }
            else
                return true;
        }


    public int getValue(Index index) {
        return primitiveMatrix[index.row][index.column];
    }

    public Collection<Index> getReachables(Index index) {
        ArrayList<Index> filteredIndices = new ArrayList<>();
        this.getNeighbors(index).stream().filter(i -> getValue(i) == 1)
                .map(neighbor -> filteredIndices.add(neighbor)).collect(Collectors.toList());
        return filteredIndices;
    }
    public Collection<Index> getReachablesWithoutdiagonals(Index index) {
        ArrayList<Index> filteredIndices = new ArrayList<>();
        this.getNeighborsWithoutDiagonals(index).stream().filter(i -> getValue(i) == 1)
                .map(neighbor -> filteredIndices.add(neighbor)).collect(Collectors.toList());
        return filteredIndices;
    }
}

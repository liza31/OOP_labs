import java.util.Arrays;
import java.util.Random;

public class MatrixGeneric<T> {
    private final T[][] matrixNumbers;

    private Object[][] createArray(int size1, int size2){
        return new Object[size1][size2];
    }
    private T[][] createArray(T[][] startArray){
        int[] matrixSizes = new int[]{startArray.length,startArray[0].length};
        T[][] newArray = (T[][]) createArray(matrixSizes[0],matrixSizes[1]);
        for (int i=0;i<matrixSizes[0];i++){
            System.arraycopy(startArray[i], 0, newArray[i], 0, matrixSizes[1]);
        }
        return newArray;
    }

    public MatrixGeneric(){
        matrixNumbers = (T[][]) createArray(0,0);
    }
    public MatrixGeneric(int a, int b){
        matrixNumbers = (T[][]) createArray(a,b);
    }
    public MatrixGeneric(MatrixGeneric<T> oldMatrixGeneric){
        matrixNumbers = createArray(oldMatrixGeneric.matrixNumbers);
    }

    public void setMatrixNumbers(int rowNum, int columnNum, T newNumber){
        matrixNumbers[rowNum][columnNum] = newNumber;
    }
    public T getMatrixNumbers(int rowNum, int columnNum){
        return  matrixNumbers[rowNum][columnNum];
    }
    public String getMatrixRow(int rowNum){
        return Arrays.toString(matrixNumbers[rowNum]);
    }
    public String getMatrixColumn(int columnNum){
        StringBuilder stringBuilder = new StringBuilder();
        for (T[] row: matrixNumbers) stringBuilder.append(row[columnNum]).append('\n');
        return String.valueOf(stringBuilder);
    }

    public int[] getMatrixSize(){
        return new int[]{matrixNumbers.length,matrixNumbers[0].length};
    }


    public void ShowMatrix(){
        for (T[] arr: matrixNumbers)
            System.out.println(Arrays.toString(arr));
    }


}

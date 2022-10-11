import java.util.Arrays;
import java.util.Random;

public class Matrix {
    private final double[][] matrixNumbers;

    private static double[][] createArray(int size1, int size2){
        return new double[size1][size2];
    }
    private static double[][] createArray(double[][] startArray){
        int[] matrixSizes = new int[]{startArray.length,startArray[0].length};
        double[][] newArray = createArray(matrixSizes[0],matrixSizes[1]);
        for (int i=0;i<matrixSizes[0];i++){
            newArray[i] = Arrays.copyOf(startArray[i],matrixSizes[1]);
        }
        return newArray;
    }

    public Matrix(){
        matrixNumbers = createArray(0,0);
    }
    public Matrix(int a, int b){
        matrixNumbers = createArray(a,b);
    }
    public Matrix(Matrix oldMatrix){
        matrixNumbers = createArray(oldMatrix.matrixNumbers);
    }

    public void setMatrixNumbers(int rowNum, int columnNum, double newNumber){
        matrixNumbers[rowNum][columnNum] = newNumber;
    }
    public double getMatrixNumbers(int rowNum, int columnNum){
        return  matrixNumbers[rowNum][columnNum];
    }
    public String getMatrixRow(int rowNum){
        return Arrays.toString(matrixNumbers[rowNum]);
    }
    public String getMatrixColumn(int columnNum){
        StringBuilder stringBuilder = new StringBuilder();
        for (double[] row: matrixNumbers) stringBuilder.append(row[columnNum]).append('\n');
        return String.valueOf(stringBuilder);
    }

    public int[] getMatrixSize(){
        return new int[]{matrixNumbers.length,matrixNumbers[0].length};
    }

    public boolean equals(Matrix anotherMatrix){
        int[] sizes = getMatrixSize();
        if (sizes[0]!=anotherMatrix.getMatrixSize()[0] || sizes[1]!=anotherMatrix.getMatrixSize()[1]) return false;
        for (int i=0;i<sizes[0];i++)
            for (int j=0;j<sizes[1];j++)
                if(matrixNumbers[i][j]!=anotherMatrix.matrixNumbers[i][j])
                    return false;
        return true;
    }

    public int hashCode(){
        int hash = 0;
        for(double[] row:matrixNumbers) hash+=Arrays.hashCode(row);
        return hash;
    }

    public static Matrix CreateRandomRowMatrix(int columnSize){
        Matrix RowMatrix = new Matrix(1,columnSize);
        Random random = new Random();
        for (int i=0;i<columnSize;i++)
            RowMatrix.matrixNumbers[0][i] = (int)(random.nextDouble()*20-10);
        return RowMatrix;
    }

    public Matrix add(Matrix anotherMatrix) throws Exception {
        int[] sizes = getMatrixSize();
        if (sizes[0]!=anotherMatrix.getMatrixSize()[0] || sizes[1]!=anotherMatrix.getMatrixSize()[1])
            throw new Exception("Cannot add matrixes with different sizes");
        Matrix matrixAfterAdd = new Matrix(sizes[0],sizes[1]);
        for (int i=0; i<sizes[0];i++)
            for (int j=0; j<sizes[1]; j++)
                matrixAfterAdd.matrixNumbers[i][j] = matrixNumbers[i][j]+anotherMatrix.matrixNumbers[i][j];
        return matrixAfterAdd;
    }

    public Matrix MultiplyScalar(double number){
        int[] sizes = getMatrixSize();
        Matrix matrixAfterMultiply = new Matrix(sizes[0],sizes[1]);
        for (int i=0; i<sizes[0];i++)
            for (int j=0; j<sizes[1]; j++)
                matrixAfterMultiply.matrixNumbers[i][j] = matrixNumbers[i][j]*number;
        return matrixAfterMultiply;

    }

    public void ShowMatrix(){
        for (double[] arr: matrixNumbers)
            System.out.println(Arrays.toString(arr));
    }


}

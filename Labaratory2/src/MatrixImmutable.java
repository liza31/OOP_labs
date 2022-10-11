import java.util.Arrays;
import java.util.Random;

public class MatrixImmutable {
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

    public MatrixImmutable(){
        matrixNumbers = createArray(0,0);
    }
    public MatrixImmutable(int a, int b){
        matrixNumbers = createArray(a,b);
    }
    public MatrixImmutable(MatrixImmutable oldMatrixImmutable){
        matrixNumbers = createArray(oldMatrixImmutable.matrixNumbers);
    }

    public MatrixImmutable(Matrix oldMatrix){
        int[] oldMatrixSizes = oldMatrix.getMatrixSize();
            matrixNumbers = createArray(oldMatrixSizes[0],oldMatrixSizes[1]);
            for (int i = 0; i < oldMatrixSizes[0]; i++) {
                for (int j = 0; j < oldMatrixSizes[1]; j++)
                    matrixNumbers[i][j] = oldMatrix.getMatrixNumbers(i, j);
            }
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

    public boolean equals(MatrixImmutable anotherMatrixImmutable){
        int[] sizes = getMatrixSize();
        if (sizes[0]!= anotherMatrixImmutable.getMatrixSize()[0] || sizes[1]!= anotherMatrixImmutable.getMatrixSize()[1]) return false;
        for (int i=0;i<sizes[0];i++)
            for (int j=0;j<sizes[1];j++)
                if(matrixNumbers[i][j]!= anotherMatrixImmutable.matrixNumbers[i][j])
                    return false;
        return true;
    }

    public int hashCode(){
        int hash = 0;
        for(double[] row:matrixNumbers) hash+=Arrays.hashCode(row);
        return hash;
    }

    public static MatrixImmutable CreateRandomRowMatrix(int columnSize){
        MatrixImmutable rowMatrixImmutable = new MatrixImmutable(1,columnSize);
        Random random = new Random();
        for (int i=0;i<columnSize;i++)
            rowMatrixImmutable.matrixNumbers[0][i] = (int)(random.nextDouble()*20-10);
        return rowMatrixImmutable;
    }

    public MatrixImmutable add(MatrixImmutable anotherMatrixImmutable) throws Exception {
        int[] sizes = getMatrixSize();
        if (sizes[0]!= anotherMatrixImmutable.getMatrixSize()[0] || sizes[1]!= anotherMatrixImmutable.getMatrixSize()[1])
            throw new Exception("Cannot add mtrixes with different sizes");
        MatrixImmutable matrixImmutableAfterAdd = new MatrixImmutable(sizes[0],sizes[1]);
        for (int i=0; i<sizes[0];i++)
            for (int j=0; j<sizes[1]; j++)
                matrixImmutableAfterAdd.matrixNumbers[i][j] = matrixNumbers[i][j]+ anotherMatrixImmutable.matrixNumbers[i][j];
        return matrixImmutableAfterAdd;
    }

    public MatrixImmutable MultiplyScalar(double number){
        int[] sizes = getMatrixSize();
        MatrixImmutable matrixImmutableAfterMultiply = new MatrixImmutable(sizes[0],sizes[1]);
        for (int i=0; i<sizes[0];i++)
            for (int j=0; j<sizes[1]; j++)
                matrixImmutableAfterMultiply.matrixNumbers[i][j] = matrixNumbers[i][j]*number;
        return matrixImmutableAfterMultiply;
    }

    public void ShowMatrix(){
        for (double[] arr: matrixNumbers)
            System.out.println(Arrays.toString(arr));
    }


}

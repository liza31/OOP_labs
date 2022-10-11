import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        int[] sizes = new int[]{2, 2};
        Matrix matrix = new Matrix(sizes[0], sizes[1]);
        for (int i = 0; i < sizes[0]; i++)
            for (int j = 0; j < sizes[1]; j++)
                matrix.setMatrixNumbers(i, j, (int) (random.nextDouble() * 20 - 10));
        matrix.ShowMatrix();
        /*System.out.println();

        System.out.println(matrix.getMatrixColumn(1));

        System.out.println(matrix.getMatrixRow(1));

        matrix.setMatrixNumbers(1,1,1);

        System.out.println(matrix.getMatrixNumbers(1,1));

        MatrixGeneric<String> matrixGeneric = new MatrixGeneric<String>(2,2);
        matrixGeneric.setMatrixNumbers(0,0,"I");
        matrixGeneric.setMatrixNumbers(0,1,"Love");
        matrixGeneric.setMatrixNumbers(1,1,"Java");
        matrixGeneric.setMatrixNumbers(1,0, "Maybe");
        matrixGeneric.ShowMatrix();
        matrix = matrix.MultiplyScalar(2);
        matrix.ShowMatrix();
        Matrix matrix1 = Matrix.CreateRandomRowMatrix(4);
        matrix1.ShowMatrix();*/
        sizes = new int[]{2, 2};
        Matrix matrix1 = new Matrix(sizes[0], sizes[1]);
        for (int i = 0; i < sizes[0]; i++)
            for (int j = 0; j < sizes[1]; j++)
                matrix1.setMatrixNumbers(i, j, (int) (random.nextDouble() * 20 - 10));
        matrix1.ShowMatrix();
        matrix = matrix.add(matrix1);
        matrix. ShowMatrix();
        MatrixImmutable m1 = new MatrixImmutable(matrix);
        MatrixImmutable m2 = new MatrixImmutable(matrix1);
        MatrixImmutable m3 = m1.add(m2);
        m3. ShowMatrix();
        m3 = m3.MultiplyScalar(-2);
        m3. ShowMatrix();






    }
}


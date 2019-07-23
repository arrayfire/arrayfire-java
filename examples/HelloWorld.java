import java.util.Random;
import com.arrayfire.*;

public class HelloWorld {

  public static void main(String[] args) {

    Array a = new Array(), b = new Array(), c = new Array(), d = new Array();
    Array f = new Array();
    try {
        Util.info();
        System.out.println("Create a 5-by-3 matrix of random floats on the GPU");
        Data.randu(a, new int[] {5, 3}, Array.FloatType);
        a.print("a");

        System.out.println("Element-wise arithmetic");
        Arith.sin(b, a);
        b.print("b");

        System.out.println("Fourier transform the result");
        Signal.fft(c, b);
        c.print("c");

        System.out.println("Matmul b and c");
        Arith.mul(d, b, c);
        d.print("d");


        Array forMean = new Array();
        Array weights = new Array();
        Data.randn(forMean, new int[] {3, 3}, Array.FloatComplexType);
        Data.randn(weights, new int[] {3, 3}, Array.FloatType);
        forMean.print("forMean");

        FloatComplex abc = Statistics.mean(forMean, weights, FloatComplex.class);
        System.out.println(String.format("Mean is: %f and %f", abc.real(), abc.imag()));

        System.out.println("Create a 2-by-3 matrix from host data");
        int[] dims = new int[] { 2, 3 };
        int total = 1;
        for (int dim : dims) {
          total *= dim;
        }
        float[] data = new float[total];
        Random rand = new Random();

        for (int i = 0; i < total; i++) {
            double tmp = Math.ceil(rand.nextDouble() * 10) / 10;
            data[i] = (float) (tmp);
        }
        Array e = new Array(dims, data);
        e.print("e");

        System.out.println("Add e and random array");
        Array randa = new Array();
        Data.randu(randa, dims, Array.FloatType);
        Arith.add(f, e, randa);
        f.print("f");


        System.out.println("Copy result back to host.");
        float[] result = f.getFloatArray();
        for (int i = 0; i < dims[0]; i++) {
            for (int y = 0; y < dims[1]; y++) {
                System.out.print(result[y * dims[0] + i] + " ");
            }
            System.out.println();
        }
        a.close();
        b.close();
        c.close();
        d.close();
        e.close();
        f.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
  }
}

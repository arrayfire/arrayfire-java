import java.util.Random;
import com.arrayfire.*;

public class HelloWorld {

  public static void main(String[] args) {

    Array a = new Array(), b = new Array(), c = new Array(), d = new Array();
    Array f = new Array();
    try {
      Util.info();
      System.out.println("Create a 5-by-3 matrix of random floats on the GPU");
      Data.randu(a, new int[] { 5, 3 }, Array.FloatType);
      System.out.println(a.toString("a"));

      System.out.println("Element-wise arithmetic");
      Arith.sin(b, a);
      System.out.println(a.toString("b"));

      System.out.println("Fourier transform the result");
      Signal.fft(c, b);
      System.out.println(a.toString("c"));

      System.out.println("Matmul b and c");
      Arith.mul(d, b, c);
      System.out.println(a.toString("d"));

      System.out.println("Calculate weighted variance.");
      Array forVar = new Array();
      Array weights = new Array();
      Data.randn(forVar, new int[] { 5, 3 }, Array.DoubleType);
      Data.randn(weights, new int[] { 5, 3 }, Array.DoubleType);
      System.out.println(a.toString("forVar"));

      double abc = Statistics.var(forVar, weights, Double.class);
      System.out.println(String.format("Variance is: %f", abc));

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
      System.out.println(a.toString("e"));

      System.out.println("Add e and random array");
      Array randa = new Array();
      Data.randu(randa, dims, Array.FloatType);
      Arith.add(f, e, randa);
      System.out.println(a.toString("f"));

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

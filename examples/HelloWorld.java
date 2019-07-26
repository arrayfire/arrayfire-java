import java.util.Random;
import com.arrayfire.*;

public class HelloWorld {

  public static void main(String[] args) {

    Array a = new Array(), b = new Array(), c = new Array(), d = new Array();
    Array f = new Array();
    try {
      Util.info();

      System.out.println("Create a 5-by-3 matrix of random floats on the GPU");
      Data.randu(a, new int[] { 5, 3 }, Array.Type.Float);
      System.out.println(a.toString("a"));

      System.out.println("Element-wise arithmetic");
      Arith.sin(b, a);
      System.out.println(b.toString("b"));

      System.out.println("Fourier transform the result");
      Signal.fft(c, b);
      System.out.println(c.toString("c"));

      System.out.println("Matmul b and c");
      Arith.mul(d, b, c);
      System.out.println(d.toString("d"));

      System.out.println("Calculate weighted variance.");
      Array forVar = new Array();
      Array weights = new Array();
      Data.randn(forVar, new int[] { 5, 5 }, Array.Type.Double);
      Data.randn(weights, new int[] { 5, 5 }, Array.Type.Double);
      System.out.println(forVar.toString("forVar"));

      double abc = Statistics.var(forVar, weights, Double.class);
      System.out.println(String.format("Variance is: %f", abc));
      forVar.close();
      weights.close();

      System.out.println("Median");
      Array forMedian = new Array();
      Data.randu(forMedian, new int[] { 3, 5 }, Array.Type.Double);
      System.out.println(forMedian.toString("forMedian"));
      double median = Statistics.median(forMedian, Double.class);
      System.out.printf("Median = %f\n", median);
      forMedian.close();

      System.out.println("Calculate standard deviation");
      Array forStdev = new Array();
      Data.randu(forStdev, new int[] { 5, 3 }, Array.Type.Double);
      System.out.println(forStdev.toString("forStdev"));
      double stdev = Statistics.stdev(forStdev, Double.class);
      System.out.println(String.format("Stdev is: %f", stdev));
      forStdev.close();

      System.out.println("Covariance");
      Array x = new Array();
      Array z = new Array();
      Data.randu(x, new int[] { 5, 3 }, Array.Type.Double);
      Data.randu(z, new int[] { 5, 3 }, Array.Type.Double);
      System.out.println(x.toString("x"));
      System.out.println(z.toString("z"));
      Array cov = Statistics.cov(x, z, false);
      System.out.println(cov.toString("cov"));

      System.out.println("Correlation coefficient of the 2 previous arrays");
      double corrcoef = Statistics.corrcoef(x, z, Double.class);
      System.out.printf("Corrcoef = %f\n", corrcoef);
      x.close();
      z.close();

      System.out.println("Topk");
      Array forTopk = new Array();
      Data.randu(forTopk, new int[] { 3, 3 }, Array.Type.Double);
      System.out.println(forTopk.toString("forTopk"));
      Array[] results = Statistics.topk(forTopk, 3, 0, Statistics.TopkOrder.DEFAULT);
      System.out.println(results[0].toString("Indicies"));
      System.out.println(results[1].toString("Values"));

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
      System.out.println(e.toString("e"));

      System.out.println("Add e and random array");
      Array randa = new Array();
      Data.randu(randa, dims, Array.Type.Float);
      Arith.add(f, e, randa);
      System.out.println(f.toString("f"));

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

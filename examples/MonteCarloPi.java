import java.util.Random;
import com.arrayfire.*;

public class MonteCarloPi {

  public static double hostCalcPi(int size) {
    Random rand = new Random();
    int count = 0;
    for (int i = 0; i < size; i++) {
      float x = rand.nextFloat();
      float y = rand.nextFloat();
      boolean lt1 = (x * x + y * y) < 1;
      if (lt1)
        count++;
    }

    return 4.0 * ((double) (count)) / size;
  }

  public static double deviceCalcPi(int size) throws Exception {
    Array x = new Array(), y = new Array(), res = new Array();
    try {

      int[] dims = new int[] { size, 1 };
      Data.randu(x, dims, Array.FloatType);
      Data.randu(y, dims, Array.FloatType);

      Arith.mul(x, x, x);
      Arith.mul(y, y, y);

      Arith.add(res, x, y);
      Arith.lt(res, res, 1);

      double count = Algorithm.sumAll(res);
      return 4.0 * ((double) (count)) / size;

    } catch (Exception e) {
      throw e;
    } finally {
      x.close();
      y.close();
      res.close();
    }
  }

  public static void main(String[] args) {

    try {
      int size = 5000000;
      int iter = 100;

      double devicePi = deviceCalcPi(size);
      System.out.println("Results from device: " + devicePi);

      double hostPi = hostCalcPi(size);
      System.out.println("Results from host: " + hostPi);

      long deviceStart = System.currentTimeMillis();
      for (int i = 0; i < iter; i++) {
        devicePi = deviceCalcPi(size);
      }
      double deviceElapsed = (double) (System.currentTimeMillis() - deviceStart) / iter;
      System.out.println("Time taken for device (ms): " + deviceElapsed);

      long hostStart = System.currentTimeMillis();
      for (int i = 0; i < iter; i++) {
        hostPi = hostCalcPi(size);
      }
      double hostElapsed = (double) (System.currentTimeMillis() - hostStart) / iter;
      System.out.println("Time taken for host (ms): " + hostElapsed);

      System.out.println("Speedup: " + Math.round((hostElapsed) / (deviceElapsed)));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

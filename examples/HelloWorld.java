import java.util.Random;
import com.arrayfire.*;

public class HelloWorld {
  public static void main(String[] args) {
    System.out.println("Demonstrating Array Addition using ArrayFire");

    int[] dims;
    int total = 1;
    float[] left, right, res;
    Random rand = new Random();
    String str;

    dims = new int[2];
    dims[0] = 10;
    dims[1] = 1;

    for (int i = 0; i < dims.length; i++) {
      total *= dims[i];
    }

    left = new float[total];
    right = new float[total];

    for (int i = 0; i < total; i++) {
      left[i] = (float) i;
      double tmp = Math.ceil(rand.nextDouble() * 10) / 10;
      right[i] = (float) (tmp);
    }

    try {
      // Get info about arrayfire information
      Util.info();

      // Send data to ArrayFire
      Array A = new Array(dims, left);
      Array B = new Array(dims, right);
      Array C = new Array();
      Array D = Array.identity(new int[] { 0, 5 }, Array.FloatType);
      float[] values = D.getFloatArray();
      for (int x = 0; x < 5; x++) {
        for (int y = 0; y < 5; y++) {
          System.out.print(Float.toString(values[5 * x + y]) + " ");
        }

        System.out.println();
      }

      // Do vector addition on the device
      Arith.add(C, A, B);

      // Get result back to host memory
      res = C.getFloatArray();

      for (int i = 0; i < total; i++) {
        str = Integer.toString(i) + ". ";
        float val = left[i] + right[i];
        str = str + Float.toString(left[i]) + " + ";
        str = str + Float.toString(right[i]) + " = ";
        str = str + Float.toString(res[i]);
        System.out.println(str);
      }
      A.close();
      B.close();
      C.close();
      D.close();

    } catch (Exception e) {
      System.out.println("Failed to use ArrayFire");
      e.printStackTrace();
    }

  }
}

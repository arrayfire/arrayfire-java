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
            left[i] = (float)i;
            double tmp = Math.ceil(rand.nextDouble() * 10) / 10;
            right[i] = (float)(tmp);
        }

        try {
            // Get info about arrayfire information
            Array.info();

            // Send data to ArrayFire
            Array A = Data.createArray(dims, left);
            Array B = Data.createArray(dims, right);

            // Create container for output
            Array C = new Array();

            // Do vector addition on the device
            C = Arith.add(A, B);

            // Get result back to host memory
            res = Data.getFloatArray(C);

            for(int i = 0; i < total; i++) {
                str = Integer.toString(i) + ". ";
                float val = left[i] + right[i];
                str = str + Float.toString(left[i] ) + " + ";
                str = str + Float.toString(right[i]) + " = ";
                str = str + Float.toString(res[i]);
                System.out.println(str);
            }

        } catch (Exception e) {
            System.out.println("Failed to use ArrayFire");
            System.out.println(e.getMessage());
        }

    }
}

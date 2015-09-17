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
            if (lt1) count++;
        }

        return 4.0 * ((double)(count)) / size;
    }

    public static double deviceCalcPi(int size) throws Exception {
        Array x = null, y = null, res = null;
        try {

            int[] dims =  new int[] {size, 1};
            x = Data.randu(dims, Array.FloatType);
            y = Data.randu(dims, Array.FloatType);

            x = Arith.mul(x, x);
            y = Arith.mul(y, y);

            res = Arith.add(x , y);
            res = Arith.lt(res, 1);
            double count = Algorithm.sumAll(res);
            return 4.0 * ((double)(count)) / size;

        } finally {
            if (x != null) x.close();
            if (y != null) y.close();
            if (res != null) res.close();
        }
    }

    public static void main(String[] args) {

        try {
            int size = 5000000;
            int iter = 100;
            double hostPi = hostCalcPi(size);
            double devicePi = deviceCalcPi(size);

            System.out.println("Results from host: " + hostPi);
            System.out.println("Results from device: " + devicePi);

            long hostStart = System.currentTimeMillis();
            for (int i = 0; i < iter; i++) {
                hostPi = hostCalcPi(size);
            }
            double hostElapsed = (double)(System.currentTimeMillis() - hostStart)/iter;

            long deviceStart = System.currentTimeMillis();
            for (int i = 0; i < iter; i++) {
                devicePi = deviceCalcPi(size);
            }
            double deviceElapsed = (double)(System.currentTimeMillis() - deviceStart)/iter;

            System.out.println("Time taken for host (ms): " + hostElapsed);
            System.out.println("Time taken for device (ms): " + deviceElapsed);
            System.out.println("Speedup: " + Math.round((hostElapsed) / (deviceElapsed)));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

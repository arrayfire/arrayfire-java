import com.arrayfire.*;

public class WindowEx {
    public static void main(String[] args) {
      System.out.println("Creating window");
      try {
          Array img = new Array();
          Data.randu(img, new int[] { 200, 200 }, Array.IntType);
          Window window = new Window();
          while (!window.closeWindow()) {
              window.image(img, "Image");
              window.show();
          }
          window.close();
      } catch (Exception ex) {
          ex.printStackTrace();
      }
    }
}

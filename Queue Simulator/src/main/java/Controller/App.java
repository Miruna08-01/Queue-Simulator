import java.io.FileNotFoundException;
import java.io.IOException;


public class App {

    public static void main(String[] args) throws IOException {
        View v = new View();
        ViewController controller = new ViewController(v);
        controller.start();
    }
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewController extends View {

    private View v;

    public ViewController(View v) {
        this.v = v;
    }

    public View getV() {
        return v;
    }

    public void start() {
        View w = this.getV();
        v.startListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = v.getTimpMaxim();
                int b = v.getTimpServiceMax();
                int c = v.getTimpServiceMin();
                int d = v.getTimpArrivalMax();
                int p = v.getTimpArrivalMin();
                int f = v.getNumarCozi();
                int g = v.getNumarClienti();
                Simulation s = new Simulation(a, b, c, d, p, f, g);
                s.setV(w);
                s.setOutput("rez.txt");
                Thread t = new Thread(s);
                t.start();

            }
        });
    }
}



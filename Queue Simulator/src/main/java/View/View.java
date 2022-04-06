import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImageOp;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class View extends Component {
    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    JPanel panelPrincipal = new JPanel();
    JPanel panelUnu = new JPanel();
    JPanel panelDoii = new JPanel();
    JPanel panel11 = new JPanel();
    JPanel panel12 = new JPanel();
    JPanel panel13 = new JPanel();
    JPanel panel14 = new JPanel();
    JPanel panel15 = new JPanel();
    JPanel panel16 = new JPanel();
    JPanel panel17 = new JPanel();
    JButton start = new JButton("START");
    JTextField numarClienti = new JTextField();
    JTextField numarCozi = new JTextField();
    JTextField timpMaxim = new JTextField();
    JTextField timpArrivalMin = new JTextField();
    JTextField timpArrivalMax = new JTextField();
    JTextField timpServiceMin = new JTextField();
    JTextField timpServiceMax = new JTextField();
    //ViewController viewC=new ViewController(this);
    JTextArea textArea = new JTextArea();
    JFrame frame;

    public View() throws IOException {
        this.frame = new JFrame();
        this.frame.setSize(1000, 500);
        this.frame.setTitle("Queue Simulator");
        this.frame.setLocationRelativeTo(null);


        //Panel 1

        JLabel numarClientiLabel = new JLabel("Introduceti numarul de clienti  ");

        numarClienti.setMaximumSize(new Dimension(50, 20));
        numarClienti.setEditable(true);
        numarClienti.setBackground(Color.WHITE);
        numarClienti.setBorder(border);


        JLabel numarCoziLabel = new JLabel("Introduceti numarul de cozi  ");
        numarCozi.setMaximumSize(new Dimension(50, 20));
        numarCozi.setEditable(true);
        numarCozi.setBackground(Color.WHITE);
        numarCozi.setBorder(border);


        JLabel timpMaximLabel = new JLabel("Introduceti timpul maxim  ");
        timpMaxim.setMaximumSize(new Dimension(50, 20));
        timpMaxim.setEditable(true);
        timpMaxim.setBackground(Color.WHITE);
        timpMaxim.setBorder(border);


        JLabel timpArrivalMinLabel = new JLabel("Introduceti timpul minim de sosire  ");
        timpArrivalMin.setMaximumSize(new Dimension(50, 20));
        timpArrivalMin.setEditable(true);
        timpArrivalMin.setBackground(Color.WHITE);
        timpArrivalMin.setBorder(border);


        JLabel timpArrivalMaxLabel = new JLabel("Introduceti timpul maxim de sosire  ");
        timpArrivalMax.setMaximumSize(new Dimension(50, 20));
        timpArrivalMax.setEditable(true);
        timpArrivalMax.setBackground(Color.WHITE);
        timpArrivalMax.setBorder(border);


        JLabel timpServiceMinLabel = new JLabel("Introduceti timpul minim de procesare  ");
        timpServiceMin.setMaximumSize(new Dimension(50, 20));
        timpServiceMin.setEditable(true);
        timpServiceMin.setBackground(Color.WHITE);
        timpServiceMin.setBorder(border);


        JLabel timpServiceMaxLabel = new JLabel("Introduceti timpul maxim de procesare  ");
        timpServiceMax.setMaximumSize(new Dimension(50, 20));
        timpServiceMax.setEditable(true);
        timpServiceMax.setBackground(Color.WHITE);
        timpServiceMax.setBorder(border);

        panelUnu.setLayout(new BoxLayout(panelUnu, BoxLayout.Y_AXIS));
        panel11.setLayout(new BoxLayout(panel11, BoxLayout.X_AXIS));
        panel12.setLayout(new BoxLayout(panel12, BoxLayout.X_AXIS));
        panel13.setLayout(new BoxLayout(panel13, BoxLayout.X_AXIS));
        panel14.setLayout(new BoxLayout(panel14, BoxLayout.X_AXIS));
        panel15.setLayout(new BoxLayout(panel15, BoxLayout.X_AXIS));
        panel16.setLayout(new BoxLayout(panel16, BoxLayout.X_AXIS));
        panel17.setLayout(new BoxLayout(panel17, BoxLayout.X_AXIS));
        panel11.add(numarClientiLabel);
        panel11.add(numarClienti);
        panel11.setBorder(border);

        panel12.add(numarCoziLabel);
        panel12.add(numarCozi);
        panel12.setBorder(border);

        panel13.add(timpMaximLabel);
        panel13.add(timpMaxim);
        panel13.setBorder(border);

        panel14.add(timpArrivalMinLabel);
        panel14.add(timpArrivalMin);
        panel14.setBorder(border);

        panel15.add(timpArrivalMaxLabel);
        panel15.add(timpArrivalMax);
        panel15.setBorder(border);

        panel16.add(timpServiceMinLabel);
        panel16.add(timpServiceMin);
        panel16.setBorder(border);

        panel17.add(timpServiceMaxLabel);
        panel17.add(timpServiceMax);
        panel17.setBorder(border);

        panelUnu.add(panel11);
        panelUnu.add(panel12);
        panelUnu.add(panel13);
        start.setActionCommand("start");

        JPanel aux1 = new JPanel();
        aux1.add(panel14);
        aux1.add(panel15);
        aux1.add(panel16);
        aux1.add(panel17);
        aux1.setLayout(new BoxLayout(aux1, BoxLayout.Y_AXIS));
        JPanel aux3 = new JPanel();
        aux3.setLayout(new BoxLayout(aux3, BoxLayout.X_AXIS));
        aux3.add(panelUnu);
        aux3.add(aux1);
        aux3.add(start);
        BufferedImage img=ImageIO.read(new File("queue.png"));
        JLabel pic=new JLabel((new ImageIcon(img)));


        //Panel 2
        JPanel panelDoi=new JPanel();
        panelDoi.setLayout(new BoxLayout(panelDoi, BoxLayout.Y_AXIS));
        JPanel panelDoi1=new JPanel();
        panelDoi.add(textArea);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelDoi.add(scroll);
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 5);
        pic.setBorder(border1);
        panelDoi1.add(pic);
        panelDoii.add(panelDoi);
        panelDoii.add(panelDoi1);
        scroll.setPreferredSize(new Dimension(400,300));
        panelDoii.setLayout(new BoxLayout(panelDoii, BoxLayout.X_AXIS));
        panelDoi1.setBackground(Color.pink);

        //Panel principal
        panelPrincipal.add(aux3);
        panelPrincipal.add(panelDoii);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        this.frame.add(panelPrincipal);
        this.frame.setVisible(true);
        panelDoii.add(panelDoi);
        panelDoii.add(panelDoi1);

    }

    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

    public JButton getStart() {
        return start;
    }

    public void setStart(JButton start) {
        this.start = start;
    }

    public int getNumarClienti() {
        return Integer.parseInt(String.valueOf(numarClienti.getText()));
    }

    public void setNumarClienti(JTextField numarClienti) {
        this.numarClienti = numarClienti;
    }

    public int getNumarCozi() {
        return Integer.parseInt(String.valueOf(numarCozi.getText()));
    }

    public void setNumarCozi(JTextField numarCozi) {
        this.numarCozi = numarCozi;
    }

    public int getTimpMaxim() {
        return Integer.parseInt(String.valueOf(timpMaxim.getText()));
    }

    public void setTimpMaxim(JTextField timpMaxim) {
        this.timpMaxim = timpMaxim;
    }

    public int getTimpArrivalMin() {
        return Integer.parseInt(String.valueOf(timpArrivalMin.getText()));
    }

    public void setTimpArrivalMin(JTextField timpArrivalMin) {
        this.timpArrivalMin = timpArrivalMin;
    }

    public int getTimpArrivalMax() {
        return Integer.parseInt(String.valueOf(timpArrivalMax.getText()));
    }

    public void setTimpArrivalMax(JTextField timpArrivalMax) {
        this.timpArrivalMax = timpArrivalMax;
    }

    public int getTimpServiceMin() {
        return Integer.parseInt(String.valueOf(timpServiceMin.getText()));
    }

    public void setTimpServiceMin(JTextField timpServiceMin) {
        this.timpServiceMin = timpServiceMin;
    }

    public int getTimpServiceMax() {
        return Integer.parseInt(String.valueOf(timpServiceMax.getText()));
    }

    public void setTimpServiceMax(JTextField timpServiceMax) {
        this.timpServiceMax = timpServiceMax;
    }

    public void setTextArea(String s) {
        textArea.setText(s);
    }

    public String getTextArea() {
        return textArea.getText();
    }

    void startListener(ActionListener mal) {
        start.addActionListener(mal);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by Дмитрий Криволап on 17.02.2017.
 */
public class MainForm extends JFrame {
    private int count_points;
    private int count_kernels;
    //private JPanel rootPanel = new JPanel();
    private JButton button1 = new JButton("Страт");
    private JPanel content ;
    private JTextField jtf_class = new JTextField(10);
    private JTextField jtf_points= new JTextField(10);
    private JLabel lb1 = new JLabel("Классы");
    private JLabel lb2 = new JLabel("Точки");
    private ClickButton handler= new ClickButton();




    public MainForm(String s,int ck,int cp) {
        super(s);
        //setLayout(new FlowLayout());
        setVisible(true);
        setSize(1000, 650);
        if (ck!=0||cp!=0) {
            content = new Kmeans(ck, cp);
        }
        else
            content = new Kmeans(5, 10000);
        button1.setSize(150,50);
        button1.setLocation(820,150);
        jtf_class.setLocation(880,50);
        jtf_class.setSize(100,20);

        lb1.setSize(100,20);
        lb2.setSize(100,20);

        lb1.setLocation(820,50);
        lb2.setLocation(820,80);


        jtf_points.setLocation(880,80);
        jtf_points.setSize(100,20);

        add(button1);
        add(jtf_class);
        add(jtf_points);

        add(lb1);
        add(lb2);

        content.setSize(800,600);
        content.setLocation(0,0);
        add(content);
        button1.addActionListener(handler);
        setLocationRelativeTo(null);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



    }


    public static void main(String[] args) {
        new MainForm("laba_1",0,0);

    }

    public class ClickButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==button1){
                count_points = Integer.parseInt(jtf_points.getText());
                count_kernels = Integer.parseInt(jtf_class.getText());
                new MainForm("laba1",count_kernels,count_points);
            }
        }
    }

}
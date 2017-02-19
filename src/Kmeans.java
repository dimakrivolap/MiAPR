
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Дмитрий Криволап on 18.02.2017.
 */
public class Kmeans extends JPanel {
    Color[] colors = new  Color[30];
    boolean move = true;
    boolean first_itirate = true;
    PointK[] coordinate_kernel = new PointK[30];
    public Graphics2D g2;
    private int count_kernels;
    private int count_points;
    public ArrayList<PointK> kernels = new ArrayList<>() ;
    public ArrayList<PointK> points = new ArrayList<>();
    public Kmeans(int count_kernels,int count_points)  {
        this.count_kernels = count_kernels;
        Random r = new Random();
        for (int i=0;i<30;i++) {
            colors[i] = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        }

        int x,y;
        for(int i=0;i<count_points;i++){
            x=r.nextInt(800);
            y=r.nextInt(600);
            PointK p = new PointK(x,y);
            p.kernel = 0;
            points.add(p);

        }
        int index;
        for(int i=0;i<count_kernels;i++) {
            index = r.nextInt(count_points);
            kernels.add(points.get(index));

        }
    }



    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g2=(Graphics2D)g;

        do {
            itirate();
            moveKernels();
        }while (move==true) ;

            for (PointK p : points) {
                g2.setColor(colors[p.kernel]);
                g2.fillOval(p.x, p.y, 4, 4);
            }

            for (PointK k : kernels) {
                g2.fillOval(k.x, k.y, 30, 30);
            }
/*
            try {
                Thread.sleep(1000);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
*/




    }


    public void itirate(){
        for (PointK p: points) {
            double distance=0;
            double mindistance=Double.MAX_VALUE;
            int currentkernel=0;
            for (PointK k: kernels){
                distance = Math.sqrt(Math.pow(p.x-k.x,2)+(Math.pow(p.y-k.y,2)));
                if (distance<=mindistance){
                    p.kernel=currentkernel;
                    mindistance = distance;
                }
                currentkernel++;
            }
        }
    }
    public void moveKernels(){
        int[] x = new int[count_kernels];
        int[] y = new int[count_kernels];
        int[] count_point_in_class = new int[count_kernels];
        double x_avr,y_avr;
        for (PointK p : points){
            x[p.kernel]+=p.x;
            y[p.kernel]+=p.y;
            count_point_in_class[p.kernel]++;
        }
        for(int i = 0;i< count_kernels;i++){
            x_avr = x[i]/count_point_in_class[i];
            y_avr = y[i]/count_point_in_class[i];
            if(first_itirate!=true) {
                if (coordinate_kernel[i].x == x_avr && coordinate_kernel[i].y == y_avr) {
                    move = false;
                }
            }
            coordinate_kernel[i] = new PointK((int)x_avr,(int)y_avr,i);
            kernels.set(i,coordinate_kernel[i]);
        }
        first_itirate=false;




    }


}
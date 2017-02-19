
import java.awt.*;

/**
 * Created by Дмитрий Криволап on 18.02.2017.
 */
public class PointK extends Point {
    public PointK(int x, int y) {
        super(x, y);
    }
    public PointK(int x, int y, int kernel){
        super(x,y);
        this.kernel = kernel;

    }

    public int kernel;

}
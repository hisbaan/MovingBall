import java.awt.*;
import javax.swing.*;

public class Drawing extends JPanel {
    public void Drawing() {
        repaint();
    }
    
    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.fillRect(0, 0, MovingBall.FRAME_X, MovingBall.FRAME_Y);

        g.setColor(Color.white);
        g.fillOval(MovingBall.xPos - 5, MovingBall.yPos - 5, 10, 10);

        g.setColor(Color.red);
        g.fillOval(MovingBall.xPos, MovingBall.yPos, 1, 1);
    }
}

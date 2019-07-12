//File: MovingBall.java
//Created: 12/07/2019
//Finished: 12/07/2019
//Name: Hisbaan Noorani
//
//This program 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovingBall implements MouseListener {
    JFrame simulationFrame = new JFrame("MovingBall");
    Drawing board = new Drawing();

    public static final int FRAME_X = 800;
    public static final int FRAME_Y = 800;
    public static final boolean DEBUG = true;
    public static int ballDiameter = 10;
    public static int ballRadius = ballDiameter / 2;

    public static int xInitial = 0;
    public static int yInitial = 0;

    public static int xFinal = 0;
    public static int yFinal = 0;

    public static int xDragDelta = 0;
    public static int yDragDelta = 0;

    public static double angle = 0.0;

    public static int xPos = 400;
    public static int yPos = 400;

    public static int xVelocity = 0;
    public static int yVelocity = 0;

    public Timer movement;

    public static void main(String[] args) {
        new MovingBall();
    }

    MovingBall() {
        movement = new Timer(10, e -> {
            move();
            collision();
        });

        simulation();
    }

    public void simulation() {
        movement.start();
        simulationFrame.setSize(FRAME_X, FRAME_Y + 22);
        simulationFrame.setResizable(false);
        simulationFrame.setLayout(new BorderLayout());

        if (simulationFrame.getMouseListeners().length < 1) simulationFrame.addMouseListener(this);

        simulationFrame.add(board, BorderLayout.CENTER);
        board.setSize(FRAME_X, FRAME_Y);

        simulationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simulationFrame.setVisible(true);
    }

    public void move() {
        if (xDragDelta != 0 || yDragDelta != 0) {
            xPos += xVelocity;
            yPos += yVelocity;

            board.validate();
            board.repaint();
        }
    }

    public void collision() {
        if (xPos >= FRAME_X - ballRadius) {
            xVelocity *= -1;

            if (DEBUG) System.out.println("Hit east wall:  (" + xPos + ", " + yPos + ")");
        }

        if (xPos <= ballRadius) {
            xVelocity *= -1;

            if (DEBUG) System.out.println("Hit west wall:  (" + xPos + ", " + yPos + ")");
        }

        if (yPos >= FRAME_Y - ballDiameter) {

            yVelocity += -1;

            if (DEBUG) System.out.println("Hit south wall: (" + xPos + ", " + yPos + ")");
        }

        if (yPos <= ballRadius) {
            yVelocity *= -1;

            if (DEBUG) System.out.println("Hit north wall: (" + xPos + ", " + yPos + ")");
        }
    }

    public void getAngle() {
        xDragDelta = xFinal - xInitial;
        yDragDelta = yFinal - yInitial;

        angle = Math.abs(Math.toDegrees(Math.atan((double) yDragDelta / (double) xDragDelta)));

        if (xDragDelta > 0 && yDragDelta > 0) {
            System.out.println("quadrant 1");
        }
        if (xDragDelta < 0 && yDragDelta > 0) {
            angle = 180 - angle;
            System.out.println("quadrant 2");
        }
        if (xDragDelta < 0 && yDragDelta < 0) {
            angle = 180 + angle;
            System.out.println("quadrant 3");
        }
        if (xDragDelta > 0 && yDragDelta < 0) {
            angle = 360 - angle;
            System.out.println("quadrant 4");
        }

        xVelocity = (int) (5 * Math.cos(Math.toRadians(angle)));
        yVelocity = (int) (5 * Math.sin(Math.toRadians(angle)));

        System.out.println("angle: " + Math.toDegrees(angle) + " | xVel: " + xVelocity + " | yVel: " + yVelocity + "");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == simulationFrame) {
            xInitial = xPos;
            yInitial = yPos;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == simulationFrame) {
            xFinal = e.getX();
            yFinal = e.getY();

            getAngle();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

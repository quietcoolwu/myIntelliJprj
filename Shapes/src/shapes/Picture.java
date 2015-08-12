package shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Picture extends JFrame {
    private static final long serialVersionUID = 1L;
    private int width;
    private int height;

    private ArrayList<Shape> listShape = new ArrayList<Shape>();

    public Picture(int width, int height) {
        add(new ShapesPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.width = width;
        this.height = height;
    }

    public void add(Shape s) {
        listShape.add(s);
    }

    public void draw() {
        setLocationRelativeTo(null);
        setSize(width, height);
        setVisible(true);
    }

    private class ShapesPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Shape s : listShape) {
                s.draw(g);
            }
        }

    }
}
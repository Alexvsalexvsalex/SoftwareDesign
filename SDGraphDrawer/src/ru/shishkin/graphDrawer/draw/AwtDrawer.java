package ru.shishkin.graphDrawer.draw;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class AwtDrawer implements DrawingApi {
    private final Graphics2D graphics2D;
    private final int width = 600;
    private final int height = 600;

    public AwtDrawer() {
        Frame frame = new Frame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        frame.setSize(width, height);
        frame.setVisible(true);
        graphics2D = (Graphics2D) frame.getGraphics();
    }

    @Override
    public long getDrawingAreaWidth() {
        return width;
    }

    @Override
    public long getDrawingAreaHeight() {
        return height;
    }

    @Override
    public void drawCircle(long x, long y, long r) {
        graphics2D.setPaint(Color.green);
        graphics2D.fill(new Ellipse2D.Float(x - r, y - r, 2 * r, 2 * r));
    }

    @Override
    public void drawLine(long ax, long ay, long bx, long by) {
        graphics2D.setPaint(Color.black);
        graphics2D.draw(new Line2D.Float(ax, ay, bx, by));
    }
}

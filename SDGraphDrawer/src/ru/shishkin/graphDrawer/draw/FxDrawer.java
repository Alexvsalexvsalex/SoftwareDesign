package ru.shishkin.graphDrawer.draw;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FxDrawer extends Application implements DrawingApi {
    private static GraphicsContext context;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing circle");
        Group group = new Group();
        Canvas canvas = new Canvas(800, 800);
        context = canvas.getGraphicsContext2D();
        group.getChildren().add(canvas);
        primaryStage.setScene(new Scene(group));
        primaryStage.show();
    }

    public static void run() {
        new Thread(FxDrawer::launch).start();
    }

    @Override
    public long getDrawingAreaWidth() {
        return (long) context.getCanvas().getWidth();
    }

    @Override
    public long getDrawingAreaHeight() {
        return (long) context.getCanvas().getHeight();
    }

    @Override
    public void drawCircle(long x, long y, long r) {
        context.setFill(Color.GREEN);
        context.fillOval(x - r, y - r, 2 * r, 2 * r);
    }

    @Override
    public void drawLine(long ax, long ay, long bx, long by) {
        context.setStroke(Color.BLUE);
        context.strokeLine(ax, ay, bx, by);
    }
}

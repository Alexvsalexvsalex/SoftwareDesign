package ru.shishkin.graphDrawer.graph;

import ru.shishkin.graphDrawer.draw.DrawingApi;

public abstract class Graph {
    /**
     * Bridge to drawing api
     */
    protected DrawingApi drawingApi;
    protected final int n;

    protected Graph(DrawingApi drawingApi, int n) {
        this.drawingApi = drawingApi;
        this.n = n;
    }

    public abstract void drawGraph();

    private Pair<Long, Long> circlePos(long height, long width, int i) {
        long hC = height / 2;
        long wC = width / 2;
        long r = Math.min(height, width) / 3;
        double angle = (i * 2 * Math.PI) / n;
        return new Pair<>((long) (hC + r * Math.cos(angle)), (long) (wC + r * Math.sin(angle)));
    }

    protected void drawCircles() {
        long height = drawingApi.getDrawingAreaHeight();
        long width = drawingApi.getDrawingAreaWidth();
        long circleSize = Math.min(height, width) / (8L * n);
        for (int i = 0; i < n; i++) {
            Pair<Long, Long> pos = circlePos(height, width, i);
            drawingApi.drawCircle(pos.getFirst(), pos.getSecond(), circleSize);
        }
    }

    protected void connectCircles(int a, int b) {
        long height = drawingApi.getDrawingAreaHeight();
        long width = drawingApi.getDrawingAreaWidth();
        Pair<Long, Long> pos1 = circlePos(height, width, a);
        Pair<Long, Long> pos2 = circlePos(height, width, b);
        drawingApi.drawLine(pos1.getFirst(), pos1.getSecond(), pos2.getFirst(), pos2.getSecond());
    }
}
package ru.shishkin.graphDrawer.draw;

public interface DrawingApi {
    long getDrawingAreaWidth();
    long getDrawingAreaHeight();

    /**
     * @param x horizontal position of center
     * @param y vertical position of center
     * @param r radius
     */
    void drawCircle(long x, long y, long r);

    void drawLine(long ax, long ay, long bx, long by);
}

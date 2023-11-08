package ru.shishkin.graphDrawer.graph;

import ru.shishkin.graphDrawer.draw.DrawingApi;

import java.util.List;

public class GraphOnMatrix extends Graph {
    private final List<List<Integer>> matrix;

    public GraphOnMatrix(DrawingApi drawingApi, List<List<Integer>> matrix) {
        super(drawingApi, matrix.size());
        this.matrix = matrix;
        if (n == 0) {
            throw new RuntimeException("Empty matrix");
        }
        if (matrix.get(0).size() != n) {
            throw new RuntimeException("Matrix is not square");
        }
    }

    @Override
    public void drawGraph() {
        drawCircles();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix.get(i).get(j) == 1) {
                    connectCircles(i, j);
                }
            }
        }
    }
}

package ru.shishkin.graphDrawer.graph;

import ru.shishkin.graphDrawer.draw.DrawingApi;

import java.util.List;

public class GraphOnList extends Graph {
    private final List<Pair<Integer, Integer>> edges;

    public GraphOnList(DrawingApi drawingApi, int n, List<Pair<Integer, Integer>> edges) {
        super(drawingApi, n);
        this.edges = edges;
        for (Pair<Integer, Integer> edge : edges) {
            if (edge.getFirst() < 1 || edge.getFirst() > n || edge.getSecond() < 1 || edge.getSecond() > n) {
                throw new RuntimeException("Incorrect edge " + edge.getFirst() + " - " + edge.getSecond());
            }
        }
    }

    @Override
    public void drawGraph() {
        drawCircles();
        for (Pair<Integer, Integer> edge : edges) {
            connectCircles(edge.getFirst() - 1, edge.getSecond() - 1);
        }
    }
}

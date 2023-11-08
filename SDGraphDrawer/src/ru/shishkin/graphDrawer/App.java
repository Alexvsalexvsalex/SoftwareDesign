package ru.shishkin.graphDrawer;

import ru.shishkin.graphDrawer.draw.AwtDrawer;
import ru.shishkin.graphDrawer.draw.DrawingApi;
import ru.shishkin.graphDrawer.draw.FxDrawer;
import ru.shishkin.graphDrawer.graph.Graph;
import ru.shishkin.graphDrawer.graph.GraphOnList;
import ru.shishkin.graphDrawer.graph.GraphOnMatrix;
import ru.shishkin.graphDrawer.graph.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run(String apiS, String graphS) {
        Scanner scanner = new Scanner(System.in);

        DrawingApi api = getApi(apiS);
        Graph graph = getGraph(graphS, api, scanner);

        graph.drawGraph();
    }

    private DrawingApi getApi(String arg) {
        switch (arg) {
            case "awt": {
                return new AwtDrawer();
            }
            case "fx": {
                FxDrawer.run();
                return new FxDrawer();
            }
            default: {
                throw new RuntimeException("Unrecognized drawing api type " + arg);
            }
        }
    }

    private Graph getGraph(String arg, DrawingApi api, Scanner scanner) {
        switch (arg) {
            case "list": {
                return getGraphOnList(api, scanner);
            }
            case "matrix": {
                return getGraphOnMatrix(api, scanner);
            }
            default: {
                throw new RuntimeException("Unrecognized graph type " + arg);
            }
        }
    }

    private GraphOnMatrix getGraphOnMatrix(DrawingApi api, Scanner scanner) {
        System.out.println("Enter vertexes count");
        int n = scanner.nextInt();
        System.out.println("Enter matrix");
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int x = scanner.nextInt();
                row.add(x);
            }
            matrix.add(row);
        }
        return new GraphOnMatrix(api, matrix);
    }

    private GraphOnList getGraphOnList(DrawingApi api, Scanner scanner) {
        System.out.println("Enter vertexes count and edges count");
        int n = scanner.nextInt(), m = scanner.nextInt();
        System.out.println("Enter " + m + " edges");
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt(), b = scanner.nextInt();
            edges.add(new Pair<>(a, b));
        }
        return new GraphOnList(api, n, edges);
    }
}

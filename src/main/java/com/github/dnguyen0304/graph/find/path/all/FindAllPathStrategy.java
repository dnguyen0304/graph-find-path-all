package com.github.dnguyen0304.graph.find.path.all;

import java.util.ArrayList;
import java.util.List;

public class FindAllPathStrategy {

    public List<List<Integer>> findPaths(List<List<Integer>> adjacencyMatrix) {
        return findPaths(adjacencyMatrix, 0, new ArrayList<Integer>());
    }

    private List<List<Integer>> findPaths(List<List<Integer>> adjacencyMatrix,
                                          Integer fromNode,
                                          List<Integer> currentPath) {
        List<List<Integer>> paths = new ArrayList<>();

        // Handle null and zero-valued inputs.
        if (adjacencyMatrix == null || adjacencyMatrix.isEmpty()) {
            return paths;
        }

        for (int row = 0; row < adjacencyMatrix.size(); row++) {
            currentPath.add(fromNode);
            Boolean hasAdjacent = false;

            // Handle loops.
            if (currentPath.size() >= 2 &&
                currentPath.get(currentPath.size() - 2) == currentPath.get(currentPath.size() - 1)) {
                    paths.add(currentPath);
                    return paths;
            }

            List<Integer> children = adjacencyMatrix.get(row);
            for (int toNode = 0; toNode < children.size(); toNode++) {
                if (children.get(toNode) == 1) {
                    hasAdjacent = true;
                    paths.addAll(this.findPaths(
                        adjacencyMatrix.subList(toNode, adjacencyMatrix.size()),
                        toNode,
                        currentPath));
                }
            }

            if (currentPath.size() >= 2 && !hasAdjacent) {
                paths.add(currentPath);
            }

            currentPath = new ArrayList<Integer>();
        }

        return paths;
    }

}

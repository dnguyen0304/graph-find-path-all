package com.github.dnguyen0304.graph.find.path.all;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllPathStrategy {

    public Set<List<Integer>> fromOneNode(
        List<List<Integer>> adjacencyMatrix,
        Integer current) {

        return fromOneNode(adjacencyMatrix,
                           null,
                           current,
                           new HashSet<>());
    }

    private Set<List<Integer>> fromOneNode(
        List<List<Integer>> adjacencyMatrix,
        Integer previous,
        Integer current,
        Set<Integer> visited) {

        // Should these throw IllegalArgumentException instead?
        // Base Case: null or zero-valued adjacency matrix
        if (adjacencyMatrix == null || adjacencyMatrix.isEmpty()) {
            return null;
        }
        // Base Case: null nodes
        if (current == null) {
            return null;
        }
        // Base Case: invalid nodes
        if ((previous != null && previous < 0) || current < 0) {
            return null;
        }
        // Base Case: null seen
        if (visited == null) {
            return null;
        }

        // A boolean array could be used instead of a set. However, the time
        // complexity of asserting membership then degrades from O(1) to O(n).
        visited.add(current);

        Set<List<Integer>> paths = new HashSet<>();
        Boolean isStart = previous == null;
        Boolean hasAdjacent = false;

        for (int next = 0; next < adjacencyMatrix.size(); next++) {
            // Base Case: not an adjacent node
            if (adjacencyMatrix.get(current).get(next) == 0) {
                continue;
            }
            // Base Case: looping path
            if (next == current) {
                continue;
            }
            // Base Case: cyclic path
            if (visited.contains(next)) {
                paths.add(Arrays.asList(current, next));
                continue;
            }

            // Recursive Case: adjacent node
            hasAdjacent = true;

            if (!isStart) {
                paths.add(Arrays.asList(current));
            }
            Set<List<Integer>> partialPaths = this.fromOneNode(
                adjacencyMatrix,
                current,
                next,
                new HashSet<>(visited));
            for (List<Integer> partialPath : partialPaths) {
                // Base Case: cyclic paths
                Integer tail = partialPath.get(partialPath.size() - 1);
                if (!isStart && current == tail) {
                    continue;
                }
                List<Integer> path = new ArrayList<Integer>();
                path.add(current);
                path.addAll(partialPath);
                paths.add(path);
            }
        }

        // Base Case: leaf node
        if (!isStart && !hasAdjacent) {
            paths.add(Arrays.asList(current));
        }

        return paths;
    }

    public Set<List<Integer>> fromAllNodes(
        List<List<Integer>> adjacencyMatrix) {

        Set<List<Integer>> paths = new HashSet<>();

        // Base Case: null or zero-valued adjacency matrix
        if (adjacencyMatrix == null || adjacencyMatrix.isEmpty()) {
            // Should this throw IllegalArgumentException instead?
            return null;
        }

        // Iterative Case
        for (int row = 0; row < adjacencyMatrix.size(); row++) {
            Set<List<Integer>> subset = this.fromOneNode(adjacencyMatrix, row);
            paths.addAll(subset);
        }

        return paths;
    }

}

package com.github.dnguyen0304.graph.find.path.all;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllPathStrategy {

    public Set<List<Integer>> fromOneNode(
        List<List<Integer>> adjacencyMatrix,
        Integer root) {

        return fromOneNode(adjacencyMatrix,
                           root,
                           root,
                           new HashSet<>());
    }

    private Set<List<Integer>> fromOneNode(
        List<List<Integer>> adjacencyMatrix,
        Integer root,
        Integer current,
        Set<Integer> seen) {

        // Should these throw IllegalArgumentException instead?
        // Base Case: null or zero-valued adjacency matrix
        if (adjacencyMatrix == null || adjacencyMatrix.isEmpty()) {
            return null;
        }
        // Base Case: null nodes
        if (root == null || current == null) {
            return null;
        }
        // Base Case: invalid nodes
        if (root < 0 || current < 0) {
            return null;
        }
        // Base Case: null seen
        if (seen == null) {
            return null;
        }

        seen.add(current);

        Set<List<Integer>> paths = new HashSet<>();
        List<Integer> children = adjacencyMatrix.get(current);
        Boolean hasAdjacent = false;

        for (int next = 0; next < children.size(); next++) {
            // Base Case: not an adjacent node
            if (children.get(next) == 0) {
                continue;
            }
            // Base Case: looping path
            if (next == current) {
                continue;
            }
            // Base Case: cyclic path
            // Lower in the stack, the tail of cyclic paths is naively
            // returned.
            if (seen.contains(next)) {
                paths.add(Arrays.asList(current, next));
                continue;
            }

            // Recursive Case: adjacent node
            hasAdjacent = true;

            if (root != current) {
                paths.add(Arrays.asList(current));
            }
            Set<List<Integer>> partialPaths = this.fromOneNode(
                adjacencyMatrix,
                current,
                next,
                new HashSet<>(seen));
            for (List<Integer> partialPath : partialPaths) {
                // Higher in the stack, discard cyclic paths.
                Integer tail = partialPath.get(partialPath.size() - 1);
                if (root != current && current == tail) {
                    continue;
                }
                List<Integer> path = new ArrayList<Integer>();
                path.add(current);
                path.addAll(partialPath);
                paths.add(path);
            }
        }

        // Base Case: leaf node
        if (!hasAdjacent && root != current) {
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

package com.github.dnguyen0304.graph.find.path.all;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllPathStrategy {

    public List<List<Integer>> fromOneNode(
        List<List<Integer>> adjacencyMatrix, Integer fromNode) {

        return fromOneNode(adjacencyMatrix, fromNode, fromNode);
    }

    private List<List<Integer>> fromOneNode(
        List<List<Integer>> adjacencyMatrix,
        Integer rootNode,
        Integer fromNode) {

        // Should these throw IllegalArgumentException instead?
        // Base Case #1: null or zero-valued adjacency matrices
        if (adjacencyMatrix == null || adjacencyMatrix.isEmpty()) {
            return null;
        }
        // Base Case #2: null nodes
        if (rootNode == null || fromNode == null) {
            return null;
        }
        // Base Case #3: invalid nodes
        if (rootNode < 0 || fromNode < 0) {
            return null;
        }

        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> children = adjacencyMatrix.get(fromNode);
        // The runtime complexity of clear() is O(n) because it sets
        // every element to null. The runtime complexity of removeAll()
        // is O(n^2) because there is a nested call to contains(), which
        // has a runtime complexity of O(n). Both of these methods
        // garbage collect the elements themselves.
        Boolean hasAdjacent = false;

        for (int toNode = 0; toNode < children.size(); toNode++) {
            // Base Case #4: looping paths
            // The current node has an adjacent node.
            // The adjacent node is the current node.
            if (children.get(toNode) == 1 && toNode == fromNode) {
                continue;
            // Recursive Case #1: adjacent nodes
            } else if (children.get(toNode) == 1) {
                hasAdjacent = true;
                // The current node becomes the new root node and the adjacent
                // node becomes the new current node.
                List<List<Integer>> partialPaths = this.fromOneNode(
                    adjacencyMatrix,
                    fromNode,
                    toNode);
                for (List<Integer> partialPath : partialPaths) {
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(fromNode);
                    path.addAll(partialPath);
                    paths.add(path);
                }
            }
        }

        // Base Case #5: leaf nodes
        // The current node is a leaf node.
        // The current node is not the root node.
        if (!hasAdjacent && rootNode != fromNode) {
            paths.add(Arrays.asList(fromNode));
        }

        return paths;
    }

    public List<List<Integer>> fromAllNodes(
        List<List<Integer>> adjacencyMatrix) {

        List<List<Integer>> paths = new ArrayList<>();

        // Base Case #1: null or zero-valued adjacency matrices
        if (adjacencyMatrix == null || adjacencyMatrix.isEmpty()) {
            // Should this throw IllegalArgumentException instead?
            return null;
        }

        // Iterative Case #1
        for (int row = 0; row < adjacencyMatrix.size(); row++) {
            paths.addAll(fromOneNode(adjacencyMatrix, row, row));
        }

        return paths;
    }

}

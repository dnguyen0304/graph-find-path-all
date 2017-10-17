package com.github.dnguyen0304.graph.find.path.all;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllPathStrategy {

    private static final Integer ROOT_NODE_INDEX = 0;

    public List<List<Integer>> findPaths(List<List<Integer>> adjacencyMatrix) {
        return findPaths(adjacencyMatrix, null, 0);
    }

    private List<List<Integer>> findPaths(List<List<Integer>> adjacencyMatrix,
                                          Integer rootNode,
                                          Integer fromNode) {
        List<List<Integer>> paths = new ArrayList<>();

        // Base Case #1: null and zero-valued inputs
        if (adjacencyMatrix == null || adjacencyMatrix.isEmpty()) {
            return paths;
        }

        for (int row = fromNode; row < adjacencyMatrix.size(); row++) {
            List<Integer> children = adjacencyMatrix.get(row);
            // The runtime complexity of clear() is O(n) because it sets
            // every element to null. The runtime complexity of removeAll()
            // is O(n^2) because there is a nested call to contains(), which
            // has a runtime complexity of O(n). Both of these methods
            // garbage collect the elements themselves.
            List<Integer> basePath = new ArrayList<>();

            if (rootNode != null) {
                basePath.add(rootNode);
            }

            basePath.add(fromNode);
            Boolean hasAdjacent = false;

            for (int toNode = 0; toNode < children.size(); toNode++) {
                // Base Case #2: looping paths
                if (children.get(toNode) == 1 &&
                    rootNode != null &&
                    rootNode == toNode) {
                        paths.add(Arrays.asList(toNode));
                        continue;
                }

                // Recursive Case #1: adjacent nodes
                if (children.get(toNode) == 1) {
                    hasAdjacent = true;
                    List<List<Integer>> partialPaths = this.findPaths(
                        adjacencyMatrix,
                        fromNode,
                        toNode);
                    for (List<Integer> partialPath : partialPaths) {
                        List<Integer> path = new ArrayList<Integer>();
                        path.addAll(basePath);
                        path.addAll(partialPath);
                        paths.add(path);
                    }
                }
            }

            // Base Case #3: leaf nodes
            if (this.isLeafNode(basePath, fromNode, hasAdjacent)) {
                paths.add(Arrays.asList(fromNode));
            }
        }

        return paths;
    }

    private boolean isLeafNode(List<Integer> basePath,
                               Integer currentNode,
                               boolean hasAdjacent) {
        // The base path must have at least one node.
        // The current node must not have any adjacent nodes.
        // The current node must not be the root node.
        if (!basePath.isEmpty() &&
            !hasAdjacent &&
            currentNode != basePath.get(ROOT_NODE_INDEX)) {
                return true;
        } else {
            return false;
        }
    }

}

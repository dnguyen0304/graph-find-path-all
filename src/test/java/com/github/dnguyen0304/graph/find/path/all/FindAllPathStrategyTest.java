package com.github.dnguyen0304.graph.find.path.all;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FindAllPathStrategyTest {

    private FindAllPathStrategy strategy;
    private List<List<Integer>> adjacencyMatrix;
    private List<List<Integer>> expectedPaths;

    @Before
    public void setUp() {
        this.strategy = new FindAllPathStrategy();
        this.adjacencyMatrix = new ArrayList<>();
        this.expectedPaths = new ArrayList<>();
    }

    @Test
    public void testFromOneNodeReturnsNullOnNullAdjacencyMatrix() {
        List<List<Integer>> paths = this.strategy.fromOneNode(null, null);
        assertNull(paths);
    }

    @Test
    public void testFromOneNodeReturnsNullOnZeroValuedAdjacencyMatrix() {
        List<List<Integer>> paths = this.strategy.fromOneNode(
            this.adjacencyMatrix,
            null);
        assertNull(paths);
    }

    @Test
    public void testFromOneNodeReturnsNullOnNullFromNode() {
        this.adjacencyMatrix.add(null);
        List<List<Integer>> paths = this.strategy.fromOneNode(
            this.adjacencyMatrix,
            null);
        assertNull(paths);
    }

    @Test
    public void testFromOneNodeReturnsNullOnInvalidFromNode() {
        this.adjacencyMatrix.add(null);
        List<List<Integer>> paths = this.strategy.fromOneNode(
            this.adjacencyMatrix,
            -1);
        assertNull(paths);
    }

    @Test
    public void testFromOneNodeAcross2Nodes() {
        //   A B
        // A 0 1
        // B 0 0
        List<Integer> aRow = new ArrayList<Integer>();
        aRow.add(0);
        aRow.add(1);
        List<Integer> bRow = new ArrayList<Integer>();
        bRow.add(0);
        bRow.add(0);
        this.adjacencyMatrix.add(aRow);
        this.adjacencyMatrix.add(bRow);

        this.expectedPaths.add(Arrays.asList(0, 1));

        List<List<Integer>> paths = this.strategy.fromOneNode(adjacencyMatrix,
                                                              0);
        assertEquals(expectedPaths, paths);
    }

    @Test
    public void testFromOneNodeAcross3Nodes() {
        //   A B C D E F
        // A 0 1 1 0 0 0
        // B 0 0 1 1 1 0
        // C 0 0 0 0 0 1
        // D 0 0 0 0 0 0
        // E 0 0 0 0 0 0
        // F 0 0 0 0 0 0
        List<Integer> aRow = new ArrayList<Integer>();
        aRow.add(0);
        aRow.add(1);
        aRow.add(1);
        List<Integer> bRow = new ArrayList<Integer>();
        bRow.add(0);
        bRow.add(0);
        bRow.add(1);
        List<Integer> cRow = new ArrayList<Integer>();
        cRow.add(0);
        cRow.add(0);
        cRow.add(0);
        this.adjacencyMatrix.add(aRow);
        this.adjacencyMatrix.add(bRow);
        this.adjacencyMatrix.add(cRow);

        this.expectedPaths.add(Arrays.asList(0, 1, 2));
        this.expectedPaths.add(Arrays.asList(0, 2));

        List<List<Integer>> paths = this.strategy.fromOneNode(adjacencyMatrix,
                                                              0);
        assertEquals(expectedPaths, paths);
    }

    @Test
    public void testFromOneNodeNoLoopingPaths() {
        //   A B
        // A 0 1
        // B 0 1
        List<Integer> aRow = new ArrayList<Integer>();
        aRow.add(0);
        aRow.add(1);
        List<Integer> bRow = new ArrayList<Integer>();
        bRow.add(0);
        bRow.add(0);
        this.adjacencyMatrix.add(aRow);
        this.adjacencyMatrix.add(bRow);

        this.expectedPaths.add(Arrays.asList(0, 1));

        List<List<Integer>> paths = this.strategy.fromOneNode(adjacencyMatrix,
                                                              0);
        assertEquals(expectedPaths, paths);
    }

}

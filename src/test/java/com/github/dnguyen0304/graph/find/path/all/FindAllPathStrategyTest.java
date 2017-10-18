package com.github.dnguyen0304.graph.find.path.all;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class FindAllPathStrategyTest {

    private FindAllPathStrategy strategy;
    private List<List<Integer>> adjacencyMatrix;
    private Set<List<Integer>> expectedPaths;

    @Before
    public void setUp() {
        this.strategy = new FindAllPathStrategy();
        this.adjacencyMatrix = new ArrayList<>();
        this.expectedPaths = new HashSet<>();
    }

    @Test
    public void testFromOneNodeReturnsNullOnNullAdjacencyMatrix() {
        Set<List<Integer>> paths = this.strategy.fromOneNode(null, null);
        assertNull(paths);
    }

    @Test
    public void testFromOneNodeReturnsNullOnZeroValuedAdjacencyMatrix() {
        Set<List<Integer>> paths = this.strategy.fromOneNode(
            this.adjacencyMatrix,
            null);
        assertNull(paths);
    }

    @Test
    public void testFromOneNodeReturnsNullOnNullFromNode() {
        this.adjacencyMatrix.add(null);
        Set<List<Integer>> paths = this.strategy.fromOneNode(
            this.adjacencyMatrix,
            null);
        assertNull(paths);
    }

    @Test
    public void testFromOneNodeReturnsNullOnInvalidFromNode() {
        this.adjacencyMatrix.add(null);
        Set<List<Integer>> paths = this.strategy.fromOneNode(
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

        Set<List<Integer>> paths = this.strategy.fromOneNode(
            this.adjacencyMatrix,
            0);
        assertEquals(expectedPaths, paths);
    }

    @Test
    public void testFromOneNodeAcross3Nodes() {
        //   A B C
        // A 0 1 1
        // B 0 0 1
        // C 0 0 0
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

        this.expectedPaths.add(Arrays.asList(0, 1));
        this.expectedPaths.add(Arrays.asList(0, 1, 2));
        this.expectedPaths.add(Arrays.asList(0, 2));

        Set<List<Integer>> paths = this.strategy.fromOneNode(
            this.adjacencyMatrix,
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

        Set<List<Integer>> paths = this.strategy.fromOneNode(
            this.adjacencyMatrix,
            0);
        assertEquals(expectedPaths, paths);
    }

    @Test
    public void testFromAllNodesReturnsNullOnNullAdjacencyMatrix() {
        Set<List<Integer>> paths = this.strategy.fromAllNodes(null);
        assertNull(paths);
    }

    @Test
    public void testFromAllNodesReturnsNullOnZeroValuedAdjacencyMatrix() {
        Set<List<Integer>> paths = this.strategy.fromAllNodes(
            this.adjacencyMatrix);
        assertNull(paths);
    }

    @Test
    public void testFromAllNodesAcross3Nodes() {
        //   A B C
        // A 0 1 1
        // B 0 0 1
        // C 0 0 0
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

        this.expectedPaths.add(Arrays.asList(0, 1));
        this.expectedPaths.add(Arrays.asList(0, 1, 2));
        this.expectedPaths.add(Arrays.asList(0, 2));
        this.expectedPaths.add(Arrays.asList(1, 2));

        Set<List<Integer>> paths = this.strategy.fromAllNodes(
            this.adjacencyMatrix);
        assertEquals(expectedPaths, paths);
    }

    @Test
    public void testFromAllNodesAcross6Nodes() {
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
        aRow.add(0);
        aRow.add(0);
        aRow.add(0);
        List<Integer> bRow = new ArrayList<Integer>();
        bRow.add(0);
        bRow.add(0);
        bRow.add(1);
        bRow.add(1);
        bRow.add(1);
        bRow.add(0);
        List<Integer> cRow = new ArrayList<Integer>();
        cRow.add(0);
        cRow.add(0);
        cRow.add(0);
        cRow.add(0);
        cRow.add(0);
        cRow.add(1);
        List<Integer> dRow = new ArrayList<Integer>();
        dRow.add(0);
        dRow.add(0);
        dRow.add(0);
        dRow.add(0);
        dRow.add(0);
        dRow.add(0);
        List<Integer> eRow = new ArrayList<Integer>();
        eRow.add(0);
        eRow.add(0);
        eRow.add(0);
        eRow.add(0);
        eRow.add(0);
        eRow.add(0);
        List<Integer> fRow = new ArrayList<Integer>();
        fRow.add(0);
        fRow.add(0);
        fRow.add(0);
        fRow.add(0);
        fRow.add(0);
        fRow.add(0);
        this.adjacencyMatrix.add(aRow);
        this.adjacencyMatrix.add(bRow);
        this.adjacencyMatrix.add(cRow);
        this.adjacencyMatrix.add(dRow);
        this.adjacencyMatrix.add(eRow);
        this.adjacencyMatrix.add(fRow);

        this.expectedPaths.add(Arrays.asList(0, 1));
        this.expectedPaths.add(Arrays.asList(0, 1, 2));
        this.expectedPaths.add(Arrays.asList(0, 1, 2, 5));
        this.expectedPaths.add(Arrays.asList(0, 1, 3));
        this.expectedPaths.add(Arrays.asList(0, 1, 4));
        this.expectedPaths.add(Arrays.asList(0, 2));
        this.expectedPaths.add(Arrays.asList(0, 2, 5));
        this.expectedPaths.add(Arrays.asList(1, 2));
        this.expectedPaths.add(Arrays.asList(1, 2, 5));
        this.expectedPaths.add(Arrays.asList(1, 3));
        this.expectedPaths.add(Arrays.asList(1, 4));
        this.expectedPaths.add(Arrays.asList(2, 5));

        Set<List<Integer>> paths = this.strategy.fromAllNodes(
            this.adjacencyMatrix);
        assertEquals(expectedPaths, paths);
    }

}

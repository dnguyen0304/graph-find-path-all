package com.github.dnguyen0304.graph.find.path.all;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FindAllPathStrategyTest {

    private FindAllPathStrategy strategy;

    @Before
    public void setUp() {
        this.strategy = new FindAllPathStrategy();
    }

    @Test
    public void testFindPathsReturnsOnNullInput() {
        List<List<Integer>> paths = this.strategy.findPaths(null);
        assertEquals(new ArrayList<>(), paths);
    }

    @Test
    public void testFindPathsReturnsOnZeroValueInput() {
        List<List<Integer>> paths = this.strategy.findPaths(new ArrayList<>());
        assertEquals(new ArrayList<>(), paths);
    }

    @Test
    public void testFindPaths() {
        List<List<Integer>> adjacencyMatrix = new ArrayList<>();
        List<List<Integer>> expectedPaths = new ArrayList<>();

        //   A B
        // A 0 1
        // B 0 0

        List<Integer> aRow = new ArrayList<Integer>();
        aRow.add(0);
        aRow.add(1);
        List<Integer> bRow = new ArrayList<Integer>();
        bRow.add(0);
        bRow.add(0);

        adjacencyMatrix.add(aRow);
        adjacencyMatrix.add(bRow);

        expectedPaths.add(Arrays.asList(0, 1));

        List<List<Integer>> paths = this.strategy.findPaths(adjacencyMatrix);
        assertEquals(expectedPaths, paths);
    }

}

package com.doeiqts.pizzavoter.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PositionTest {
    @Test
    public void testCombinePositions_AllNull() {
        Position result = Position.ALL.combinePositions(null);

        assertEquals(Position.ALL, result);
    }

    @Test
    public void testCombinePositions_AllAll() {
        Position result = Position.ALL.combinePositions(Position.ALL);

        assertEquals(Position.ALL, result);
    }

    @Test
    public void testCombinePositions_AllLeft() {
        Position result = Position.ALL.combinePositions(Position.LEFT);

        assertEquals(Position.ALL, result);
    }

    @Test
    public void testCombinePositions_LeftRight() {
        Position result = Position.LEFT.combinePositions(Position.RIGHT);

        assertEquals(Position.ALL, result);
    }

    @Test
    public void testCombinePositions_LeftLeft() {
        Position result = Position.LEFT.combinePositions(Position.LEFT);

        assertEquals(Position.LEFT, result);
    }

    @Test
    public void testCombinePositions_LeftNull() {
        Position result = Position.LEFT.combinePositions(null);

        assertEquals(Position.LEFT, result);
    }
}

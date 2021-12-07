package ss.week3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RookAndBishopTest {
    private Rook rook;
    private Bishop bishop;
    private int[] rookPos = {1, 1};
    private int[] bishopPos = {1, 3};

    @BeforeEach
    void setUp() {
        this.rook = new Rook(this.rookPos[0], this.rookPos[1]);
        this.bishop = new Bishop(this.bishopPos[0], this.bishopPos[1]);
    }

    @Test
    void testValidMove() {
        Assertions.assertTrue(rook.validMove(4, 1));
        Assertions.assertTrue(rook.validMove(1, 4));
        Assertions.assertTrue(bishop.validMove(3, 5));
        Assertions.assertTrue(bishop.validMove(3, 1));
    }

    @Test
    void testInvalidMove() {
        Assertions.assertFalse(rook.validMove(4,4));
        Assertions.assertFalse(bishop.validMove(3, 8));
    }

    @Test
    void testMove() {
        rook.moveTo(4, 1);
        Assertions.assertEquals(1, rook.getColumn());
        Assertions.assertEquals(4, rook.getRow());
        bishop.moveTo(3, 5);
        Assertions.assertEquals(5, bishop.getColumn());
        Assertions.assertEquals(3, bishop.getRow());
    }
}
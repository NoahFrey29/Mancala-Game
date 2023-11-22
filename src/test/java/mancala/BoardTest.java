
package mancala;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BoardTest {

    private Board gameBoard;
    private Player playerOne;
    private Player playerTwo;
    private ArrayList<Pit> pits;
    private ArrayList<Store> stores;

    @BeforeEach
    public void setUp() {
        gameBoard = new Board();
        playerOne = new Player("Player One");
        playerTwo = new Player("Player Two");
    }

   @Test
    public void testGetNumStonesNegative() throws PitNotFoundException{
        assertThrows(PitNotFoundException.class, () -> gameBoard.getNumStones(-1));
    }

    @Test
    public void testGetNumStones() throws PitNotFoundException{
        int numStones = gameBoard.getNumStones(1);

        assertEquals(4, numStones); // Assuming initial setup
    }

    @Test
    public void testGetNumStonesEmpty() throws PitNotFoundException{
        int removed = gameBoard.getPits().get(0).removeStones();
        int numStones = gameBoard.getNumStones(1);
        assertEquals(0, numStones); // Assuming initial setup
    }

    @Test
    public void testIsSideEmptyTrue() throws PitNotFoundException{
        int removed = 0;
        for (int i = 0; i < 12; i++){
            removed = gameBoard.getPits().get(i).removeStones();
        }
        assertTrue(gameBoard.isSideEmpty(1));
    }

    @Test
    public void testIsSideEmptyFalse() throws PitNotFoundException{
        assertFalse(gameBoard.isSideEmpty(1));
    }

    @Test
    public void resetBoardBaseCase() throws PitNotFoundException{
        gameBoard.resetBoard();
        int total = 0;
        total += gameBoard.getSideCount(1);
        total += gameBoard.getSideCount(7);
        assertEquals(total, 48);
    }
    @Test
    public void resetBoardEmptyCase() throws PitNotFoundException{
        int removed = 0;
        for (int i = 0; i < 12; i++){
            removed = gameBoard.getPits().get(i).removeStones();
        }
        gameBoard.resetBoard();
        int total = 0;
        total += gameBoard.getSideCount(1);
        total += gameBoard.getSideCount(7);
        assertEquals(total, 48);
    }
    
    @Test
    public void testEnemyCapture() throws PitNotFoundException{
        int removed = 0; // remove all player 2 pits
        for (int i = 6; i < 12; i++){
            removed = gameBoard.getPits().get(i).removeStones();
        }

        int startingNumber = gameBoard.getNumStones(6);
        int distributed = gameBoard.distributeStones(6);
    
        int shouldZero = distributed - startingNumber;
        assertEquals(shouldZero, 0);
    }

    @Test
    public void testNoCapture() throws PitNotFoundException{

        int startingNumber = gameBoard.getNumStones(1);
        int distributed = gameBoard.distributeStones(1);
    
        int shouldZero = distributed - startingNumber;
        assertEquals(shouldZero, 0);
    }
    
     
    @Test
    public void testCaptureStones() throws PitNotFoundException{
        int removed = gameBoard.getPits().get(4).removeStones();
        int startingNumber = gameBoard.getNumStones(1);
        int distributed = gameBoard.distributeStones(1);
    
        int captured = distributed - startingNumber;
        assertEquals(captured, 4);
    }
    
    
    @Test
    public void testBaseDistribute() throws PitNotFoundException{

        int startingNumber = gameBoard.getNumStones(1);
        int distributed = gameBoard.distributeStones(1);
    
        assertEquals(startingNumber, distributed);
    }

    @Test
    public void testDistributeStore() throws PitNotFoundException{

        int startingNumber = gameBoard.getNumStones(6);
        int distributed = gameBoard.distributeStones(6);
    
        assertEquals(gameBoard.getStores().get(0).getTotalStones(), 1);
        assertEquals(gameBoard.getPits().get(8).getStoneCount(), 5); 
        assertEquals(startingNumber, distributed);
    }
    
    
    @Test
    public void testDistributeWrapping() throws PitNotFoundException{

        for (int i = 0; i < 10; i++){
            gameBoard.getPits().get(0).addStone();
        }
        
        int startingNumber = gameBoard.getNumStones(1);
        int distributed = gameBoard.distributeStones(1);
    
        assertEquals(gameBoard.getStores().get(0).getTotalStones(), 1);
        assertEquals(gameBoard.getStores().get(1).getTotalStones(), 0);
        assertEquals(startingNumber, distributed);
    }
     
    @Test 
    public void registerPlayersCase() {
        gameBoard.registerPlayers(playerOne,playerTwo);
        assertEquals(playerOne.getStore(), gameBoard.getStores().get(0));
        assertEquals(playerTwo.getStore(), gameBoard.getStores().get(1));
    }


}

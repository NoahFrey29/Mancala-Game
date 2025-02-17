package mancala;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules {
    private MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)
    private int freeMove;
    private boolean sideEmpty;
    final private static int SIX = 6;
    final private static int TWELVE = 12;

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
        gameBoard.setUpPits();
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(final int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    /* default */ MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    /* default */ boolean isSideEmpty(final int pitNum) {
        // This method can be implemented in the abstract class.
        if (pitNum >= 1 && pitNum <= 6) {
            for (int i = 1; i <= 6; i++){
                if(getNumStones(i) != 0){
                    sideEmpty = false;
                }
            }
        } else if (pitNum >= 7 && pitNum <= 12) {
            for(int i = 7; i <= 12; i++){
                if(getNumStones(i) != 0){
                    sideEmpty = false;
                }
            }
        }
        sideEmpty = true;
        return sideEmpty;
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(final int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    /* default */ abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    /* default */ abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(final Player one, final Player two) {
        // this method can be implemented in the abstract class.

        final Store playerStoreOne = new Store();
        final Store playerStoreTwo = new Store();

        gameBoard.setStore(playerStoreOne, 1);
        gameBoard.setStore(playerStoreTwo, 2);

        playerStoreOne.setOwner(one);
        playerStoreTwo.setOwner(two);

        one.setStore(playerStoreOne);
        two.setStore(playerStoreTwo);

        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    public int getSideCount(final int pitNum) {
        int total = 0;
            if (pitNum >= 1 && pitNum <= 6) {
                for (int i = 1; i <= 6; i++){
                    total += getNumStones(i);
                }
            } else if (pitNum >= 7 && pitNum <= 12) {
                for (int i = 7; i <= 12; i++){
                    total += getNumStones(i);
                }
            }
        
        return total;
    }

    public void setFreeMove(final int set) {
        freeMove = set;
    }
    public int getFreeMove() {
        return freeMove;
    }

    @Override
    public String toString() {
        // Implement toString() method logic here.
        int count = 1;
        int pitCount = 1;
        final StringBuilder boardString = new StringBuilder("Board\n");
        for (int i = 1; i <= 12; i++) {
            boardString.append("Pit ").append(pitCount++).append(": ").append(gameBoard.getNumStones(i)).append("\n");
            if (i == SIX) {
                boardString.append("Store " + count++ + ":").append(gameBoard.getStoreCount(1)).append("\n");
            } else if (i == TWELVE) {
                boardString.append("Store " + count++ + ":").append(gameBoard.getStoreCount(2)).append("\n");
            }
        }
        return boardString.toString();
    }
}

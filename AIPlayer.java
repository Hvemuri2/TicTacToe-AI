public class AIPlayer extends Player{

    private String name;
    private Player opponent;

    /**
     * constructor
     * @param name String
     * @param opponent a player reference
     */
    public AIPlayer(String name, Player opponent){
        this.opponent = opponent;
        this.name = name;
    }

    /**
     * getter for the opponent field
     * @return a player reference
     */
    public Player getOpponent(){
        return opponent;
    }

    /**
     * setter for this opponent field
     * @param opponent a player reference
     */
    public void setOpponent(Player opponent){
        this.opponent = opponent;
    }

    /**
     * overidden toString method to return the name of this player with an addition(AI)
     * @return a string
     */
    @Override
    public String toString(){
        return name+" (AI)";
    }

    /**
     * this is a part of the mutually recursive methods, this method gets all the minimum values(worst case scenarios)
     * of the possible moves of this player and returns the max value(best case out of all the bad scenarios)
     * out of all the min values. this corresponds to what the AI is supposed to do which assume the opponent is trying
     * to minimize its outcomes and pick the best one out of the minimized outputs.
     * @param t a tictactoe object
     * @return a double
     */
    public double maxValue(TicTacToe t){
        if(t.checkWin(opponent))return -1.0;
        else if(t.checkLose(opponent))return 1.0;
        else if(t.checkDraw())return 0.0;

        TicTacToe[] new_poss_moves = t.possibleMoves(this);
        double minVal = -1.0;
        double d;
        for(TicTacToe b:new_poss_moves){
            d=minValue(b);
            if(d>minVal){
                minVal=d;
            }
        }
        return minVal;
    }

    /**
     *this has the same implementation as the maxValues with slight changes, as this simulates the opponent player
     * so this class gets the maximized possible moves from the opponent player assuming the player will choose optimally
     * and picks the least one out of all the maxes and returns it.
     *@param t a tictactoe object
     * @return a double
     */
    public double minValue(TicTacToe t){
        if(t.checkWin(this))return 1.0;
        else if(t.checkLose(this))return -1.0;
        else if(t.checkDraw())return 0.0;

        TicTacToe[] new_poss_moves = t.possibleMoves(opponent);
        double maxVal = 1.0;
        double d;
        for(TicTacToe b:new_poss_moves){
            d=maxValue(b);
            if(d<maxVal){
                maxVal=d;
            }
        }
        return maxVal;

    }

    /**
     * this is the driver method for the minvalues and maxvalues methods, this method initiates the mutual recursion
     * causing the AI to choose the best move possible.
     * @param t a tictactoe reference
     * @return
     */
    @Override
    public TicTacToe chooseMove(TicTacToe t) {
        TicTacToe new_board = t;
        TicTacToe[] new_poss_moves = new_board.possibleMoves(this);
        double maxVal = -2.0;
        double d;
        for(TicTacToe b:new_poss_moves){
            d=minValue(b);
            if(d>maxVal){
                new_board = b;
                maxVal=d;
            }
        }
        return new_board;
    }

    /**
     * this is for debugging purposes only and im just returning the maxValue of the given tictactoe
     * @param t a tictactoe reference
     * @return
     */
    @Override
    public double boardValue(TicTacToe t){
        return maxValue(t);
    }
}

public abstract class Player {

    /**
     * abstract method that is implemented later on in the child classes
     *  returns one of the possibleMoves. How this move is chosen will depend on the kind of player
     * @param t a tictactoe reference
     * @return a tictactoe reference
     */
    public abstract TicTacToe chooseMove(TicTacToe t);

    /**
     * returns the state of the board if the specified tictactoe board is still in play or not
     * or wheather its a draw , a win, or a lose and returns respective doubles representing these outcomes
     * @param t a tictactoe reference
     * @return a double
     */
    public double boardValue(TicTacToe t){
        if(t.checkWin(this))return 1.0;
        else if(t.checkLose(this))return -1.0;
        return 0;
    }
}

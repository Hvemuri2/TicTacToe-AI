import java.util.Arrays;

public class TicTacToe {

    //this ia a 2D array
    private char[][] board;

    //this a player reference
    private Player x;

    //this a player reference
    private Player o;

    /**
     * constructor for this class
     * also initializes the board to a 2D array of blanks
     * @param x player reference
     * @param o player reference
     */
    TicTacToe(Player x, Player o){
        this.x = x;
        this.o = o;
        board = new char[3][3];
        for(char[] row:board){
            Arrays.fill(row,'_');
        }
    }

    /**
     * getter for O
     * @return a Player
     */
    public Player getO() {
        return o;
    }

    /**
     * getter for X
     * @return a Player
     */
    public Player getX() {
        return x;
    }

    /**
     * getter for board
     * @return a 2D char array
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * setter for X
     * @param x a player reference
     */
    public void setX(Player x){
        this.x = x;
    }

    /**
     * setter for O
     * @param o a player reference
     */
    public void setO(Player o) {
        this.o = o;
    }

    /**
     * setter for board
     * @param board a 2D char array
     */
    public void setBoard(char[][] board) {
        this.board = board;
    }

    /**
     * method to count blanks on the board
     * @return a integer
     */
    public int countBlanks(){
        int count = 0;
        for(char[] c:this.board){
            for(char c1:c){
                if(c1 == '_')count++;
            }
        }
        return count;
    }

    /**
     * method to determine the marker for the specified object
     * @param p player reference
     * @return a character
     */
    public char markerForPlayer(Player p){
        if(p == x){
            return 'X';
        }
        return 'O';
    }

    //helper method1
    private boolean Horizontal_check_win(char mark){
        boolean horzWin = false;

        if(board[0][0] == mark && board[0][1] == mark && board[0][2] == mark){
            horzWin = true;
        }
        else if(board[1][0] == mark && board[1][1] == mark && board[1][2] == mark){
            horzWin = true;
        }
        else if(board[2][0] == mark && board[2][1] == mark && board[2][2] == mark){
            horzWin = true;
        }
        return horzWin;
    }

    //helper method2
    private boolean Vertical_check_win(char mark){
        boolean vertWin = false;
        if(board[0][0] == mark && board[1][0] == mark && board[2][0] == mark){
            vertWin = true;
        }
        else if(board[0][1] == mark && board[1][1] == mark && board[2][1] == mark){
            vertWin = true;
        }
        else if(board[0][2] == mark && board[1][2] == mark && board[2][2] == mark){
            vertWin = true;
        }
        return vertWin;
    }

    //helper method3
    private boolean diagonal_check_win(char mark){
        if(board[0][0] == mark && board[1][1] == mark && board[2][2] == mark){
            return true;
        }
        else if(board[0][2] == mark && board[1][1] == mark && board[2][0] == mark){
            return true;
        }
        return false;
    }

    /**
     * check the whole to decide if the specified player won
     * calls on helper methods to do specific checks
     * @param p player reference
     * @return a boolean
     */
    public boolean checkWin(Player p){
        char mark = markerForPlayer(p);
        return diagonal_check_win(mark) || Horizontal_check_win(mark) || Vertical_check_win(mark);
    }

    /**
     * check the whole to decide if the specified player lost
     * calls on helper methods to do specific checks
     * @param p player reference
     * @return a boolean
     */
    public boolean checkLose(Player p){
        char mark = '*';
        if(markerForPlayer(p)=='X'){
            mark = 'O';
        }
        else if(markerForPlayer(p)=='O'){
            mark = 'X';
        }

        if(diagonal_check_win(mark) || Horizontal_check_win(mark) || Vertical_check_win(mark)){
            return true;
        }
        return false;
    }

    /**
     * checks if the board is a draw
     * follows the same implementation as checkwin and checklose
     * @return a boolean
     */
    public boolean checkDraw(){
        if(countBlanks() == 0 ){
            if(!checkWin(x) && !checkWin(o) && !checkLose(x) && !checkLose(o)){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    /**
     * Overriden toString method to return a string representation of this board
     * @return a string
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (char[] chars : board) {
            for (int j = 0; j < board[0].length; j++) {
                result.append(chars[j]);
            }
            result.append("\n");
        }
        return result.toString();
    }

    //helper method 4
    private char[][] boardDeepCopy(char[][] board){
        char[][] result = new char[board.length][];
        for(int i=0; i<board.length; i++){
            result[i] = Arrays.copyOf(board[i],board[i].length);
        }
        return result;
    }

    /**
     * gets all the possible moves on the board for the specified player
     * by making multiple copies of the same board and placing the mark of the player in each of the
     * empty spaces for each respective copy having the mark in a different empty space
     *this method then makes new tictactoe instances with all the different board possibilities and adds the
     * tictactoe instances to a tictactoe array to return it
     * @param p a player reference
     * @return a array of tictactoes
     */
    public TicTacToe[] possibleMoves(Player p){
        char mark = markerForPlayer(p);
        int num_possible_moves = countBlanks();
        char[][][] temp = new char[num_possible_moves][][];
        int count = 0;

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='_'){
                    char[][] elem = boardDeepCopy(board);
                    elem[i][j] = mark;
                    temp[count] = elem;
                    count++;
                }
            }
        }

        TicTacToe[] result = new TicTacToe[temp.length];
        for(int i=0;i< temp.length;i++){
            TicTacToe t = new TicTacToe(this.x,this.o);
            t.setBoard(temp[i]);
            result[i] = t;
        }

        return result;
    }



}

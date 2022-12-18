import java.util.Scanner;

public class UserPlayer extends Player{

    private String name;
    private Scanner input;

    /**
     * constructor
     * @param input a scanner object
     * @param name a String representing the symbol of the player
     */
    UserPlayer(Scanner input, String name){
        this.input = input;
        this.name = name;
    }

    /**
     * overidden toString method that return the string representing the symbol of the player
     * @return a string
     */
    @Override
    public String toString(){
        return this.name;
    }

    /**
     * prints out all the possiblemoves with each moves option index, and gets input from the
     * user to then select an option and return the respective tictactoe board
     * @param t a tictactoe reference
     * @return a tictactoe reference
     */
    @Override
    public TicTacToe chooseMove(TicTacToe t) {
        System.out.println(t.toString());
        System.out.println();
        System.out.println("Possible moves");
        for(int i = 0;i<t.possibleMoves(this).length;i++){
            System.out.println("Option: "+i);
            System.out.println(t.possibleMoves(this)[i].toString());
        }
        int option = input.nextInt();
        return t.possibleMoves(this)[option];
    }
}

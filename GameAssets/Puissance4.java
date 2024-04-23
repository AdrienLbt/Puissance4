package GameAssets;
/**
 * main
 */
import java.util.Scanner;

import javax.swing.JFrame;

public class Puissance4 {

    public static Scanner scan;
    
    static private GamePanel _gameManager;

    static private boolean isEndGame = false;
    static private int _numPlayers = 2;
    static private boolean isClosed = false;

    public static void main(String[] args) {
        final GamePanel _gameManager = new GamePanel(); 
        JFrame window = new JFrame();

        scan = new Scanner(System.in);

        //Création de la fenêtre
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setName("Puissance 4");
        window.add(_gameManager);
        //window.add(_gameManager.getCustomUI().getTitlePanel());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        

        _gameManager.setupPlayers(_numPlayers);
        while (!isClosed) {
            _gameManager.setupBoard();
            while(!isEndGame){
                isEndGame = _gameManager.play();
            }
            Scanner scan = new Scanner(System.in);
            String playAgain = "";
            while (!playAgain.equalsIgnoreCase("O") && !playAgain.equalsIgnoreCase("N")) {
                isEndGame = false;
                System.out.println("Do you want to play again? : O/N");
                playAgain = scan.nextLine();
                if (playAgain.equalsIgnoreCase("O")) { isClosed = false;}
                else if (playAgain.equalsIgnoreCase("N")) { 
                    isClosed = true;
                    scan.close();
                }
            }
            
        }

    }
}
package GameAssets;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

    //Le nombre de joueurs
    private int numPlayers;

    //Les entités
    private Player[] players;
    private UI ui;
    private Board board;

    private String playersName;
    private final int rows = 6;
    private final int columns = 7;
    private final int originalTileSize = 48;
    private final int scale = 3;
    private final int tileSize = scale * originalTileSize;

    //La fenêtre
    private final int dialogBoxWidth = scale * tileSize;
    private final int screenWidth = columns*tileSize+dialogBoxWidth;
    private final int screenHeight = rows*tileSize;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground((Color.BLACK));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        ui = new UI(this, screenWidth, screenHeight, dialogBoxWidth);
    }

    public UI getCustomUI() {
        return ui;
    }

    public void setupPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
        players = new Player[numPlayers];
        ui.setNumPlayers(numPlayers);

        for (int i=0; i<numPlayers; i++) {
            System.out.println("Player " + (i+1) + " name?");
            playersName = Puissance4.scan.nextLine();

            if (i>0){
                while (playersName.equals(players[i-1].getName())){
                    System.out.println("Noms des joueurs identiques");
                    System.out.println("Player " + (i+1) + " name?");
                    playersName = Puissance4.scan.nextLine();
                }
            }
            players[i] = new Player(playersName);
            System.out.println("Bienvenue " + players[i].getName());
            System.out.println("Vous aurez les jetons " + (i+1));
            ui.addPlayer(playersName);
            players[i].setToken(i+1);
        }
    }
    public void setupBoard() {
        board = new Board(rows, columns, tileSize);
    }

    public Player[] getPlayer() {
        return players;
    }

    public boolean play() {
        boolean isEndGame = false;
        for (int i=0; i<numPlayers; i++) {
            board.addToken(players[i]);
            int result = board.checkWin(players[i]);
            if (result == -1) {
                isEndGame = winSequence(result, "Egalite");
                break;
            }
            if (result > 0) {
                isEndGame = winSequence(result, players[i].getName() + " gagne!");
                break;
            }
            this.repaint();
        }
        return isEndGame;
    }

    public boolean winSequence(int result, String message) {
        System.out.println(message);
        if (result > 0) {
            players[result-1].addScore();
        }
        displayScore();
        return true;
    }

    public void displayScore() {
        System.out.println("SCORE");
        for (int i=0; i<numPlayers; i++) {
            System.out.println(players[i].getName() + " : " + players[i].getScore());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        //UI
        ui.drawTitle(g2);
        ui.drawScore(g2);

        //BOARD
        if(board != null) {
            board.draw(g2);
        }
        g2.dispose();
    }
}

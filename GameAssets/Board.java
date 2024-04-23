package GameAssets;

import java.awt.Color;
import java.awt.Graphics2D;

public class Board {
    //Le support
    private Tile[][] boardPanel;

    //Les lignes, colonnes et tuiles
    private int rows;
    private int columns;
    private int tileSize;


    //Méthodes
    public Board(int rows, int columns, int tileSize) {
        this.rows = rows;
        this.columns = columns;
        this.tileSize = tileSize;
        boardPanel = new Tile[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j< columns; j++) {
                boardPanel[i][j] = new Tile(tileSize);
            }
        }

    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void addToken(Player player) {
        int tokenColumn = 0;
        System.out.println("Joueur : " + player.getName());
        while (tokenColumn ==0) {
            System.out.println("Dans quelle colonne voulez-vous jouer?");
            String sColonne = Puissance4.scan.nextLine();
            try {
                tokenColumn = Integer.parseInt(sColonne);
            } catch (NumberFormatException e) {
                System.out.println("La colonne doit être un chiffre");
            }
            if (tokenColumn <= 0) {
                System.out.println("La colonne choisie doit être supérieure à 0");
                tokenColumn =0;
                continue;
            }
            else if (tokenColumn > columns) {
                System.out.println("La colonne choisie doit être inférieure au nombre de colonnes : " + columns);
                tokenColumn =0;
                continue;
            }
            else if (boardPanel[0][tokenColumn-1].getTileOwner() > 0) {
                System.out.println("La colonne choisie ne doit pas être pleine");
                tokenColumn =0;
                continue;
            }
        }
        for (int i = rows-1; i>= 0; i--){
            if (boardPanel[i][tokenColumn-1].getTileOwner() == 0) {
                boardPanel[i][tokenColumn-1].setTileOwner(player.getToken());
                break;
            }
        }
    }

    public int checkWin(Player player) {
        //Egalite
        boolean full = true;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j< columns; j++) {
                if (boardPanel[i][j].getTileOwner() == 0) {
                    full = false;
                }
            }
        }
        if (full) {
            return -1;
        }
        //Victoire dans la colonne
        for (int i = 0; i < columns; i++){
            for (int j = 0; j < rows-3; j++) {
                boolean colWin = true;
                for (int k = 0; k<4;k++) {
                    if (boardPanel[j+k][i].getTileOwner() != player.getToken()) {
                        colWin = false;
                    }
                }
                if (colWin){
                    return player.getToken();
                }
            }
        }
        //Victoire dans la ligne
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns-3; j++) {
                boolean colWin = true;
                for (int k = 0; k<4;k++) {
                    if (boardPanel[i][j+k].getTileOwner() != player.getToken()) {
                        colWin = false;
                    }
                }
                if (colWin){
                    return player.getToken();
                }
            }
        }
        //Victoire dans la diagonale droite descendante
        for (int i = 0; i < rows-3; i++){
            for (int j = 0; j < columns-3; j++) {
                boolean colWin = true;
                for (int k = 0; k<4;k++) {
                    if (boardPanel[i+k][j+k].getTileOwner() != player.getToken()) {
                        colWin = false;
                    }
                }
                if (colWin){
                    return player.getToken();
                }
            }
        }
        //Victoire dans la diagonale gauche descendante
        for (int i = 3; i < rows; i++){
            for (int j = 0; j < columns-3; j++) {
                boolean colWin = true;
                for (int k = 0; k<4;k++) {
                    if (boardPanel[i-k][j+k].getTileOwner() != player.getToken()) {
                        colWin = false;
                    }
                }
                if (colWin){
                    return player.getToken();
                }
            }
        }
        //Victoire dans la diagonale droite montante
        for (int i = 3; i < rows; i++){
            for (int j = 3; j < columns; j++) {
                boolean colWin = true;
                for (int k = 0; k<4;k++) {
                    if (boardPanel[i-k][j-k].getTileOwner() != player.getToken()) {
                        colWin = false;
                    }
                }
                if (colWin){
                    return player.getToken();
                }
            }
        }
        //Victoire dans la diagonale gauche motante
        for (int i = 0; i < rows-3; i++){
            for (int j = 3; j < columns; j++) {
                boolean colWin = true;
                for (int k = 0; k<4;k++) {
                    if (boardPanel[i+k][j-k].getTileOwner() != player.getToken()) {
                        colWin = false;
                    }
                }
                if (colWin){
                    return player.getToken();
                }
            }
        }
        return 0;
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j< columns; j++) {
                if (boardPanel[i][j].getTileOwner() == 0) {
                    g2.setColor(Color.WHITE);
                }
                else if (boardPanel[i][j].getTileOwner() == 1) {
                    g2.setColor(Color.GREEN);
                }
                else if (boardPanel[i][j].getTileOwner() == 2) {
                    g2.setColor(Color.RED);
                }
                g2.fillOval(j*tileSize, i*tileSize, tileSize, tileSize);
                g2.setColor(Color.WHITE);
                g2.drawRect(j*tileSize, i*tileSize, tileSize, tileSize);
            }
        }
        
    }
}

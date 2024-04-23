package GameAssets;

import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;

public class UI {

    private GamePanel gp;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private int screenWidth, screenHeight, dialogBoxWidth;

    private final Font arial_20, arial_40, arial_80B;
    private final String title = "Puissance 4";
    private String[] playersNames;
    private int playersCount = 0;
    private int titleLength;
    private int numPlayers = 0;
    private int lineHeight = 40;

    public UI (GamePanel gp, int screenWidth, int screenHeight, int dialogBoxWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.dialogBoxWidth = dialogBoxWidth;
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_20 = new Font("Arial", Font.PLAIN, 20);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        //titleLabel = new JLabel("TitleLabel");
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        //titlePanel.add(titleLabel);
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }
    public void addPlayer(String playersName) {
        playersNames[playersCount] = playersName;
        playersCount++;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
        playersNames = new String[numPlayers];
    }

    public void drawTitle (Graphics2D g2) {
        int yTitle = 40;
        g2.setColor(Color.white);
        g2.setFont(arial_40);
        titleLength = (int)g2.getFontMetrics().getStringBounds(title, g2).getWidth();
        g2.drawString(title, screenWidth-dialogBoxWidth/2-titleLength/2, 40);

    }
    public void drawScore (Graphics2D g2) {
        
        g2.setFont(arial_20);
        int yNames= lineHeight*2;
        for (int i = 0; i<numPlayers; i++) {
            if (playersNames[i] != null) {
            g2.drawString(playersNames[i] + " : " + gp.getPlayer()[i].getScore(), screenWidth-dialogBoxWidth/2-titleLength/2, yNames);
            yNames+= lineHeight;
            }
        }
    }
    }

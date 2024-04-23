package GameAssets;

public class Player {
    private String name;
    private int playerScore = 0;
    private int token =0;

    public Player(String name)
    {
        this.name = name;
    }

    public void play()
    {
        
    }

    public String getName() {
        return name;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public int getToken() {
        return token;
    }

    public int getScore() {
        return playerScore;
    }

    public void addScore() {
        playerScore++;
    }

}

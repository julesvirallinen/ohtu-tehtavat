package ohtu;

public class TennisGame {

    private int player1Score;
    private int player2Score;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void awardPoint(String playerName) {
        if (playerName == "player1")
            player1Score += 1;
        else
            player2Score += 1;
    }


    public String advantageOrWin() {
        int difference = player1Score - player2Score;
        if (difference == 1)
            return "Advantage " + player1Name;
        else if (difference == -1)
            return "Advantage " + player2Name;
        else if (difference >= 2)
            return "Win for " + player1Name;
        else
            return "Win for " + player2Name;
    }

    public String getScoreName(int score) {
        switch (score) {
        case 0:
            return "Love";
        case 1:
            return "Fifteen";
        case 2:
            return "Thirty";
        case 3:
            return "Forty";
        default:
            return "Deuce";
        }
    }

    public String getScore() {
        String score = "";

        if (player1Score == player2Score) {
            score += getScoreName(player1Score);
            if (!score.equals("Deuce")) score += "-All";
            return score;

        } else if (player1Score >= 4 || player2Score >= 4) {
            return advantageOrWin();
        }

        score += getScoreName(player1Score);
        score += "-";
        score += getScoreName(player2Score);
        return score;
    }
}
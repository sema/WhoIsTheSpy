package dk.sema.whoisthespy.game;

import java.util.Random;

public class State {

    private Random mRandom;

    private int mNumPlayers;

    private String[] mPlayerNames;
    private boolean[] mPlayerIsAlive;
    private int mSpyId;

    private String mWordNormal;
    private String mWordSpy;

    public State(int numPlayers) {
        mRandom = new Random();
        mNumPlayers = numPlayers >= 3 ? numPlayers : 3;

        mPlayerNames = new String[mNumPlayers];
        mPlayerIsAlive = new boolean[mNumPlayers];

        for (int i = 0; i < mNumPlayers; ++i) {
            mPlayerNames[i] = "Player " + (i + 1);
        }

        resetState();
    }

    // Inspect state

    public int getSpyId() {
        return mSpyId;
    }

    public int getNumPlayers() {
        return mNumPlayers;
    }

    public String getPlayerName(int playerId) {
        if (playerId < mNumPlayers) {
            return mPlayerNames[playerId];
        } else {
            return "";
        }
    }

    public String getWord(int playerId) {
        if (playerId == mSpyId) {
            return mWordSpy;
        } else {
            return mWordNormal;
        }
    }

    public boolean isPlayerAlive(int playerId) {
        return playerId < mNumPlayers && mPlayerIsAlive[playerId];
    }

    public boolean isGameFinished() {
        return !mPlayerIsAlive[mSpyId] || getNumberPlayersAlive() <= 2;
    }

    public int getNumberPlayersAlive() {
        int num = 0;
        for (int i = 0; i < mNumPlayers; ++i) {
            if (mPlayerIsAlive[i]) {
                ++num;
            }
        }

        return num;
    }

    // Actions

    public void killPlayer(int playerId) {
        if (playerId < mNumPlayers) {
            mPlayerIsAlive[playerId] = false;
        }
    }

    public void newGame() {
        resetState();
    }

    public void renamePlayer(int playerId, String newName) {
        if (playerId < mNumPlayers) {
            mPlayerNames[playerId] = newName;
        }
    }

    // State

    private void resetState() {
        revivePlayers();
        selectNewSpy();
        selectNewWords();
    }

    private void revivePlayers() {
        for (int i = 0; i < mNumPlayers; i++) {
            mPlayerIsAlive[i] = true;
        }
    }

    private void selectNewSpy() {
        mSpyId = mRandom.nextInt(mNumPlayers);
    }

    private void selectNewWords() {
        mWordNormal = "Apple";
        mWordSpy = "Pear";
    }

}

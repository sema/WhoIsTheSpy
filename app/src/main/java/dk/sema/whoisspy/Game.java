package dk.sema.whoisspy;

import java.util.Random;

/**
 * Created by semadk on 23/08/14.
 */

public class Game {

    private Random mRandom;

    private int mNumPlayers;

    private String[] mPlayerNames;
    private boolean[] mPlayerIsAlive;
    private int mSpyId;

    private String mWordNormal;
    private String mWordSpy;

    public Game(int numPlayers) {
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

    int getSpyId() {
        return mSpyId;
    }

    int getNumPlayers() {
        return mNumPlayers;
    }

    String getPlayerName(int playerId) {
        if (playerId < mNumPlayers) {
            return mPlayerNames[playerId];
        } else {
            return "";
        }
    }

    String getWord(int playerId) {
        if (playerId == mSpyId) {
            return mWordSpy;
        } else {
            return mWordNormal;
        }
    }

    boolean isPlayerAlive(int playerId) {
        return playerId < mNumPlayers && mPlayerIsAlive[playerId];
    }

    boolean isGameFinished() {
        return !mPlayerIsAlive[mSpyId] || getNumberPlayersAlive() <= 2;
    }

    int getNumberPlayersAlive() {
        int num = 0;
        for (int i = 0; i < mNumPlayers; ++i) {
            if (mPlayerIsAlive[i]) {
                ++num;
            }
        }

        return num;
    }

    // Actions

    void killPlayer(int playerId) {
        if (playerId < mNumPlayers) {
            mPlayerIsAlive[playerId] = false;
        }
    }

    void newGame() {
        resetState();
    }

    void renamePlayer(int playerId, String newName) {
        if (playerId < mNumPlayers) {
            mPlayerNames[playerId] = newName;
        }
    }

    // State

    void resetState() {
        revivePlayers();
        selectNewSpy();
        selectNewWords();
    }

    void revivePlayers() {
        for (int i = 0; i < mNumPlayers; i++) {
            mPlayerIsAlive[i] = true;
        }
    }

    void selectNewSpy() {
        mSpyId = mRandom.nextInt(mNumPlayers);
    }

    void selectNewWords() {
        mWordNormal = "Apple";
        mWordSpy = "Pear";
    }

}

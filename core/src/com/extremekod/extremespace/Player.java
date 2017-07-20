package com.extremekod.extremespace;


import com.extremekod.extremespace.ships.Ship;

import java.util.HashMap;

/**
 * Created by REraVe on 24.06.2017.
 */

public class Player {

    public static final int SINGLE_PLAYER = -1;
    public static final int PLAYER_ONE    = 1;
    public static final int PLAYER_TWO    = 2;

    private static HashMap<Integer, Player> playersMap = new HashMap<Integer, Player>();

    private static Player myNetworkPlayer;

    private int playerNumber;
    private Ship ship;

    private boolean myPlayer = false;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;

        int networkPlayerNumber = NetworkClient.getNetworkPlayerNumber();

        if (networkPlayerNumber == this.playerNumber) {
            this.myPlayer = true;
            myNetworkPlayer = this;
        }

        playersMap.put(this.playerNumber, this);

        System.out.println("Created player № " + this.playerNumber);
    }

    public static Player getMyNetworkPlayer() {
        return myNetworkPlayer;
    }

    public static int getMyNetworkPlayerNumber() {
        return NetworkClient.getNetworkPlayerNumber();
    }

    public static HashMap<Integer, Player> getPlayersMap() {
        return playersMap;
    }

    public boolean isMyPlayer() {
        return this.myPlayer;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public Ship getShip() {
        return this.ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}

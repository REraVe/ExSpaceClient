package com.extremekod.extremespace;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by REraVe on 24.06.2017.
 */

public class NetworkClient {

    public static boolean MULTIPLAYER = true;

    private static final String TAG = "Client";

    private static final String HOST = "192.168.1.1";
    private static final int PORT = 100;
    private static final String EMPTY_MESSAGE = "-1";

    private static int networkSessionNumber;
    private static int networkPlayerNumber;

    private Socket socket;

    private NetworkClient(Socket socket) {
        this.socket = socket;
    }

    public static NetworkClient createNetworkClient() {
        if (!MULTIPLAYER)
            return null;

        NetworkClient client = null;
        Socket newSocket     = null;

        try {
            newSocket = new Socket(HOST, PORT);
            System.out.println(TAG + ". Подключение к серверу установлено");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (newSocket == null) {
            client = new NetworkClient(newSocket);
        }

        return client;
    }

    public static int getNetworkSessionNumber() {
        return networkSessionNumber;
    }

    public static int getNetworkPlayerNumber() {
        if (MULTIPLAYER && networkPlayerNumber == 0) {
            Socket newSocket = null;

            try {
                newSocket = new Socket(HOST, PORT);
                System.out.println(TAG + ". Подключение к серверу установлено");
            }
            catch (IOException e) {
                MULTIPLAYER = false;
            }

            if (newSocket != null) {
                NetworkClient client = new NetworkClient(newSocket);
                client.createNewNetworkPlayer();
            }
        }

        return networkPlayerNumber;
    }

    public void createNewNetworkPlayer() {
        String receivedMessage = sendMessage("createNewNetworkPlayer");

        JSONObject jsonObject = new JSONObject(receivedMessage);
        networkSessionNumber = jsonObject.getInt("networkSessionNumber");
        networkPlayerNumber  = jsonObject.getInt("networkPlayerNumber");

        System.out.println("Created network player № " + networkPlayerNumber + " for network session № " + networkSessionNumber);
    }

    public String sendMessage(String messageToSend) {
        String receivedMessage = EMPTY_MESSAGE;

        if (socket != null) {
            try {
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();

                out.write(messageToSend.getBytes());
                out.flush();
                System.out.println(TAG + ". Client > " + messageToSend);

                byte[] bufferedData = new byte[32 * 1024];
                int readBytes = in.read(bufferedData);
                receivedMessage = new String(bufferedData, 0, readBytes);

                System.out.println(TAG + ". Client < " + receivedMessage);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return receivedMessage;
    }

}

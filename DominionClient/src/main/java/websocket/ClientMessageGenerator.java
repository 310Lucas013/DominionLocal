package websocket;


import dominionshared.communication.messages.PlayerReadyMessage;
import dominionshared.communication.messages.RegisterPlayerMessage;

import java.util.List;

public class ClientMessageGenerator implements IClientMessageGenerator {

    private IClientWebSocket clientSocket;

    public ClientMessageGenerator(IClientWebSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void registerPlayerOnServer(String name, String password) {
        clientSocket.send(new RegisterPlayerMessage(name, password));
    }

    public void playerReady(int playerNumber) {
        clientSocket.send(new PlayerReadyMessage(playerNumber));
    }

}

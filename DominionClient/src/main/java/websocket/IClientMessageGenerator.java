package websocket;

public interface IClientMessageGenerator {
    void registerPlayerOnServer(String name, String password);

    void playerReady(int playerNumber);

}

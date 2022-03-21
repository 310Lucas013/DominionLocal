package dominionserver;

import dominionserver.messageHandlers.ServerMessageHandlerFactory;
import dominionshared.dominiongame.DominionGame;
import dominionshared.dominiongame.IDominionGame;
import dominionshared.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import dominionshared.communication.messaging.IMessageHandlerFactory;
import dominionshared.communication.websockets.IServerMessageGenerator;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

public class DominionServer {

    private static final int PORT = 8095;

    public static void main(String[] args) {
        startWebSocketServer();
    }

    private static void startWebSocketServer() {

        IMessageHandlerFactory factory = new ServerMessageHandlerFactory();
        IServerMessageProcessor messageProcessor = new ServerMessageProcessor(factory);
        final ServerWebSocket socket = new ServerWebSocket();
        socket.setMessageProcessor(messageProcessor);
        IServerMessageGenerator messageGenerator = new ServerMessageGenerator(socket);
        IDominionGame game = new DominionGame(messageGenerator);
        messageProcessor.registerGame(game);
        Server webSocketServer = new Server();
        ServerConnector connector = new ServerConnector(webSocketServer);
        connector.setPort(PORT);
        webSocketServer.addConnector(connector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler webSocketContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        webSocketContext.setContextPath("/");
        webSocketServer.setHandler(webSocketContext);

//        try {
//            // Initialize javax.websocket layer
//            ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(webSocketContext);
//
//            // Add WebSocket endpoint to javax.websocket layer
//            wscontainer.addEndpoint(ServerWebSocket.class);
//
//            webSocketServer.start();
//            //server.dump(System.err);
//
//            webSocketServer.join();
//        } catch (Throwable t) {
//            t.printStackTrace(System.err);
//        }

        try {
            // Initialize javax.websocket layer
            ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(webSocketContext);

            // Add WebSocket endpoint to javax.websocket layer
            ServerEndpointConfig config = ServerEndpointConfig.Builder.create(socket.getClass(), socket.getClass().getAnnotation(ServerEndpoint.class).value())
                    .configurator(new ServerEndpointConfig.Configurator() {
                        @Override
                        public <T> T getEndpointInstance(Class<T> endpointClass) {
                            return (T) socket;
                        }
                    })
                    .build();
            wscontainer.addEndpoint(config);
            webSocketServer.start();
            webSocketServer.join();

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
    }

}

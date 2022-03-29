module dominionclient {
  requires slf4j.api;
  requires javafx.graphics;
  requires javafx.controls;
  requires javax.websocket.client.api;
  requires gson;
  requires dominionshared;
  requires java.persistence;
  requires java.sql;
  requires net.bytebuddy;
  requires java.xml.bind;
  requires com.sun.xml.bind;

  exports dominiongui;
  exports websocket;

}
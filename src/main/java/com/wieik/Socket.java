package com.wieik;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/websocket")
public class Socket {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(String.format("WebSocket opened: %s", session.getId()));
    }

    @OnMessage
    public void onMessage(String txt, Session session) throws IOException {
        System.out.println(String.format("Message received: %s", txt));
        session.getBasicRemote().sendText(txt.toUpperCase());
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println(String.format("Closing a WebSocket (%s) due to %s", session.getId(), reason.getReasonPhrase()));
    }

    @OnError
    public void onError(Session session, Throwable t) {
        System.err.println(String.format("Error in WebSocket session %s%n", session == null ? "null" : session.getId()));
        t.printStackTrace(System.err);
    }

}

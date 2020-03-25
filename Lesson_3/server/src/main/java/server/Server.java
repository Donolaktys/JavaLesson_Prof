package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Logger;

public class Server {
    public static final Logger logger = Logger.getLogger(Server.class.getName());

    private Vector<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public Server() {

        clients = new Vector<>();
        if (!SQLHandler.connect()) {
            throw new RuntimeException("Не удалось подключиться к БД");
        }
        authService = new DBAuthServise();

        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            logger.info("Сервер запустился");

            while (true) {
                socket = server.accept();
                final Socket fSocket = socket;
                logger.info("Открыт новый сокет");
                new ClientHandler(fSocket, this);
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.throwing("Server", "close", e);
        } finally {
            SQLHandler.disconnect();
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(String nick, String msg) {
        for (ClientHandler c : clients) {
            c.sendMsg(nick + " : " + msg);
        }
    }

    public void privateMsg(ClientHandler sender, String receiver, String msg) {
        String message = String.format("[ %s ] private [ %s ] : %s", sender.getNick(), receiver, msg);

        for (ClientHandler c : clients) {
            if (c.getNick().equals(receiver)) {
                c.sendMsg(message);
                if (!sender.getNick().equals(receiver)) {
                    sender.sendMsg(message);
                }
                return;
            }
        }

        sender.sendMsg("not found user :" + receiver);
    }


    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
        broadcastClientList();
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastClientList();
    }


    public boolean isLoginAuthorized(String login) {
        for (ClientHandler c : clients) {
            if (c.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastClientList() {
        StringBuilder sb = new StringBuilder("/clientlist ");

        for (ClientHandler c : clients) {
            sb.append(c.getNick()).append(" ");
        }

        String msg = sb.toString();
        for (ClientHandler c : clients) {
            c.sendMsg(msg);
        }
    }
}

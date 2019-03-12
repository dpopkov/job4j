package ru.job4j.io.netw.bot;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import static ru.job4j.io.netw.bot.Constants.*;

/**
 * Application that starts a server for {@link WiseOracle}.
 */
public class WiseOracleServerApp {
    /** Path to resource file containing answers of the oracle. */
    private static final String XML_FILE = "/oracle_answers.xml";

    /** Established application port after the server socket is created. */
    private static volatile int establishedAppPort = -1;

    /** Oracle that can reply to requests. */
    private Oracle oracle;

    /** Server socket that waits for requests from clients. */
    private ServerSocket serverSocket;

    /** Local port on which the server is listening. */
    private int localPort;

    /**
     * Constructs an instance of the app using any free port.
     * @param answers collection of keyword-answer pairs
     * @throws IOException if an I/O error occurs opening the socket, or waiting for the connection
     */
    WiseOracleServerApp(Map<String, String> answers) throws IOException {
        this(0, answers);
    }

    /**
     * Constructs instance of app using the specified port.
     * @param port port number
     * @param answers collection of keyword-answer pairs
     * @throws IOException if an I/O error occurs opening the socket, or waiting for the connection
     */
    private WiseOracleServerApp(int port, Map<String, String> answers) throws IOException {
        serverSocket = new ServerSocket(port);
        localPort = serverSocket.getLocalPort();
        oracle = new WiseOracle(answers);
    }

    /**
     * Accepts an incoming connection and starts the server.
     * @throws IOException if an I/O error occurs
     */
    public void start() throws IOException {
        try (Socket incoming = serverSocket.accept()) {
            new Server(incoming, oracle).start();
        }
    }

    /**
     * @return local port on which the server is listening
     */
    int getLocalPort() {
        return localPort;
    }

    /**
     * Main method starting the application.
     * @param args args[0] - port number (optional)
     */
    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        try {
            DataLoader data = new DataLoader();
            data.loadFromXML(WiseOracleServerApp.class.getResourceAsStream(XML_FILE));
            Map<String, String> answers = data.getMap();
            var app = new WiseOracleServerApp(port, answers);
            establishedAppPort = app.getLocalPort();
            app.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the established application port for testing purposes.
     * @return number of the port used by the application after the server socket is created
     */
    static int getEstablishedAppPort() {
        return establishedAppPort;
    }
}

package ru.job4j.io.netw.bot;

/**
 * Constants used in the application.
 */
public class Constants {
    /** Port number. */
    public static final int PORT = 8000;

    /** New line string that designates end of client message. */
    public static final String NL = "\n";

    /** The marker that designates the end of server response. */
    public static final String END = NL + NL;

    /** Exit word that is used to stop communication with the server. */
    public static final String EXIT_WORD = "bye";
}

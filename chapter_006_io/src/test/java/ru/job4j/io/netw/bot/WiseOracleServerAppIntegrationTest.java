package ru.job4j.io.netw.bot;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class WiseOracleServerAppIntegrationTest {
    private static final int TEST_PORT = 0;

    @Test
    public void whenStartWithAnswersThenSendsAnswers() throws IOException {
        Map<String, String> answers = Map.of("KeyWord1", " Answer1", "KeyWord2", "Answer2");
        WiseOracleServerApp app = new WiseOracleServerApp(answers);
        int testPort = app.getLocalPort();
        new Thread(() -> {
            try {
                app.start();
            } catch (IOException e) {
                e.printStackTrace();
                fail();
            }
        }).start();
        String fromClient = "KeyWord2\nbye\n";
        String response = receiveResponse(testPort, fromClient);
        assertThat(response, is("Answer2"));
    }

    @Test
    public void whenMainThenReadAnswersFromResourceXmlFile() throws Exception {
        new Thread(() -> WiseOracleServerApp.main(new String[] {Integer.toString(TEST_PORT)})).start();
        while (WiseOracleServerApp.getEstablishedAppPort() == -1) {
            Thread.sleep(20);
        }
        int testPort = WiseOracleServerApp.getEstablishedAppPort();
        String fromClient = "Python\nbye\n";
        String response = receiveResponse(testPort, fromClient);
        assertThat(response, is("Python удобный язык, но идеальных языков не существует."));
    }

    private String receiveResponse(int port, String request) throws IOException {
        String response;
        try (Socket socket = new Socket("localhost", port);
             OutputStream out = socket.getOutputStream();
             Scanner in = new Scanner(socket.getInputStream())
        ) {
            out.write(request.getBytes());
            response = in.nextLine();
        }
        return response;
    }
}

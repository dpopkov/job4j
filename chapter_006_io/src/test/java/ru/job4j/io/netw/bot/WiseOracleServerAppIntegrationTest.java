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
import static ru.job4j.io.netw.bot.Constants.*;

public class WiseOracleServerAppIntegrationTest {
    @Test
    public void whenStartWithAnswersThenSendsAnswers() throws IOException {
        Map<String, String> answers = Map.of("KeyWord1", " Answer1", "KeyWord2", "Answer2");
        WiseOracleServerApp app = new WiseOracleServerApp();
        new Thread(() -> {
            try {
                app.start(PORT, answers);
            } catch (IOException e) {
                e.printStackTrace();
                fail();
            }
        }).start();
        String fromClient = "KeyWord2\nbye\n";
        String response = receiveResponse(fromClient);
        assertThat(response, is("Answer2"));
    }

    @Test
    public void whenMainThenReadAnswersFromResourceXmlFile() throws IOException {
        new Thread(() -> WiseOracleServerApp.main(null)).start();
        String fromClient = "Python\nbye\n";
        String response = receiveResponse(fromClient);
        assertThat(response, is("Python удобный язык, но идеальных языков не существует."));
    }

    private String receiveResponse(String request) throws IOException {
        String response;
        try (Socket socket = new Socket("localhost", PORT);
             OutputStream out = socket.getOutputStream();
             Scanner in = new Scanner(socket.getInputStream())
        ) {
            out.write(request.getBytes());
            response = in.nextLine();
        }
        return response;
    }
}

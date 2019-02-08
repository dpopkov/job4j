package ru.job4j.io.chars;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StreamConverterTest {
    @Test
    public void whenDropAbusesOnInputContainingAbusingRussianWordsThenClearsInput() throws IOException {
        String[] abuse = {"пхп", "жаба"};
        final Charset charset = StandardCharsets.UTF_8;
        byte[] bytes = "мне не нравится жаба мне нравится пхп".getBytes(charset);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new StreamConverter().dropAbuses(in, out, abuse, charset);
        String result = new String(out.toByteArray(), charset);
        assertThat(result, is("мне не нравится  мне нравится "));
    }

    @Test
    public void whenDropAbusesOnInputContainingAbusingEnglishWordsThenClearsInput() throws IOException {
        String[] abuse = {"xyz", "foobar"};
        byte[] bytes = "Let me xyz speak foobar from my heart".getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new StreamConverter().dropAbuses(in, out, abuse);
        String result = new String(out.toByteArray());
        assertThat(result, is("Let me  speak  from my heart"));
    }
}

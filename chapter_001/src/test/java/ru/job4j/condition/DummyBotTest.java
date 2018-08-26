package ru.job4j.condition;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Denis Popkov
 */
public class DummyBotTest {
    @Test
    public void whenGreetThenGreet() {
        DummyBot bot = new DummyBot();
        assertThat(
                bot.answer("Привет, Бот."),
                is("Привет, умник."));
    }

    @Test
    public void whenByeThenBye() {
        DummyBot bot = new DummyBot();
        assertThat(
                bot.answer("Пока."),
                is("До скорой встречи."));
    }

    @Test
    public void whenUnknownThenAskOther() {
        DummyBot bot = new DummyBot();
        assertThat(
                bot.answer("Что такое ИИ?"),
                is("Это ставит меня в тупик. Спросите другой вопрос."));
    }
}

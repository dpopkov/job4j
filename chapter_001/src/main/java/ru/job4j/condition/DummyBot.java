package ru.job4j.condition;

/**
 * The simplest bot that understands two phrases.
 */
public class DummyBot {
    /**
     * Answers the question.
     * @param question question
     * @return answer to the question
     */
    public String answer(String question) {
        String result = "Это ставит меня в тупик. Спросите другой вопрос.";
        if ("Привет, Бот.".equals(question)) {
            result = "Привет, умник.";
        } else if ("Пока.".equals(question)) {
            result = "До скорой встречи.";
        }
        return result;
    }

}

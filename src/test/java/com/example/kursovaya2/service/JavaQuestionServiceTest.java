package com.example.kursovaya2.service;

import com.example.kursovaya2.exceptions.NoSuchQuestionException;
import com.example.kursovaya2.model.Question;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {
    private final QuestionService out = new JavaQuestionService();

    public static Stream<Arguments> paramsForAddMethod() {
        return Stream.of(
                Arguments.of("question?", "answer!"),
                Arguments.of("q", "a"),
                Arguments.of(" ", " ")
        );
    }

    private static Collection<Question> questions = Set.of(
            new Question("question", "answer"),
            new Question("Question", "Answer"),
            new Question("Quest", "Ans")
    );

    @BeforeEach
    public void init() {
        out.add(new Question("question", "answer"));
        out.add(new Question("Question", "Answer"));
        out.add(new Question("Quest", "Ans"));
    }

    @ParameterizedTest
    @MethodSource("paramsForAddMethod")
    public void addMethodTesting(String question, String answer) {
        Question actual = new Question(question, answer);
        Question expected = out.add(question, answer);
        assertEquals(actual, expected);
        assertTrue(out.getAll().contains(actual));
    }

    @Test
    public void shouldReturnQuestion() {
        Question q = new Question("question1", "answer1");

        assertEquals(q, out.add(q));
        assertEquals(out.getAll().size(), 4);
        assertTrue(out.getAll().contains(q));

        assertEquals(q, out.remove(q));
        assertEquals(out.getAll().size(), 3);
        assertFalse(out.getAll().contains(q));

        Question randomQuestion = out.getRandomQuestion();
        assertNotNull(randomQuestion);
        assertTrue(out.getAll().contains(randomQuestion));
    }

    @Test
    public void shouldReturnCollection() {
        assertEquals(questions, out.getAll());
        assertEquals(questions.size(), out.getAll().size());
    }

    @Test
    public void shouldThrowException() {
        assertThrows(NoSuchQuestionException.class,
                () -> out.remove(new Question("question2", "answer2")));
    }
}

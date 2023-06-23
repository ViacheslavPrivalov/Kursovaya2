package com.example.kursovaya2.service;

import com.example.kursovaya2.exceptions.NoSuchQuestionException;
import com.example.kursovaya2.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();


    @Override
    public Question add(String question, String answer) {

        Question q = new Question(question, answer);
        questions.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {

        if (questions.contains(question)) {
            questions.remove(question);
            return question;
        }
        throw new NoSuchQuestionException();
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        List<Question> questionList = new ArrayList<>(questions);
        return questionList.get(random.nextInt(questions.size()));
    }
}

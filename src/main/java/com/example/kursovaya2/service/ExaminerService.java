package com.example.kursovaya2.service;

import com.example.kursovaya2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}

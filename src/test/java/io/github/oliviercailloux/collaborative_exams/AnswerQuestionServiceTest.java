/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.oliviercailloux.collaborative_exams;

import io.github.oliviercailloux.collaborative_exams.Service.AnswerQuestionService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.AnswerQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Exam;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import junit.framework.TestCase;

/**
 *
 * @author Khaled
 */
public class AnswerQuestionServiceTest extends TestCase {

    AnswerQuestion answerQuestion
            = new AnswerQuestion("",
                    new Person("a@a.a"),
                    new Exam(),
                    new Question(),
                    new Answer("answer", false));

    public void testIncrementCountSelect() {
        new AnswerQuestionService().updateCountAnswerSelect(answerQuestion);
        assertEquals(answerQuestion.getAnswer().getCountSelect(), 1);
    }
}

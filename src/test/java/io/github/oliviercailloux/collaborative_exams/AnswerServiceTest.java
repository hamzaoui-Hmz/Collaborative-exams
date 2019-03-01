/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.oliviercailloux.collaborative_exams;

import io.github.oliviercailloux.collaborative_exams.Service.AnswerService;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;

/**
 *
 * @author Khaled
 */
public class AnswerServiceTest extends TestCase {

    AnswerService service = new AnswerService();
    List<Answer> listeAnswers = new ArrayList<>();

    @Before
    @Override
    public void setUp() {
        listeAnswers.add(new Answer("Reponse1", false, 10, 3));
        listeAnswers.add(new Answer("Reponse2", false, 10, 5));
        listeAnswers.add(new Answer("Reponse3", false, 10, 7));
    }

    public void testEasyDifficulty() {
        service.getEasyDifficultyAnswer(listeAnswers);

        assertEquals(service.getEasyDifficultyAnswer(listeAnswers).size(), 1);
        assertEquals(service.getEasyDifficultyAnswer(listeAnswers).get(0).getText(), "Reponse1");
    }

    public void testMediumDifficulty() {
        service.getMediumDifficultyAnswer(listeAnswers);

        assertEquals(service.getMediumDifficultyAnswer(listeAnswers).size(), 1);
        assertEquals(service.getMediumDifficultyAnswer(listeAnswers).get(0).getText(), "Reponse2");
    }

    public void testHardDifficulty() {
        service.getHardDifficultyAnswer(listeAnswers);

        assertEquals(service.getHardDifficultyAnswer(listeAnswers).size(), 1);
        assertEquals(service.getHardDifficultyAnswer(listeAnswers).get(0).getText(), "Reponse3");
    }
}

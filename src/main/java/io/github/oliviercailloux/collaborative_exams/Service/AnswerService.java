package io.github.oliviercailloux.collaborative_exams.Service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.DifficultyType;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import java.util.ArrayList;

@RequestScoped
public class AnswerService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private QueryHelper helper;

    @Transactional
    public List<Answer> getAll() {
        return em.createQuery(helper.selectAll(Answer.class)).getResultList();
    }

    @Transactional
    public void persist(Answer Answer) {
        em.persist(Answer);
    }

    @Transactional
    public void findAnswer(int id) {
        em.find(Answer.class, id);
    }

    /**
     *
     * @param question
     * @return la liste des reponse difficile selon la question
     *
     */
    @Transactional
    public List<Answer> findAnswerByHardDifficultyAndQuestion(Question question) {
        return getHardDifficultyAnswer((List<Answer>) em.createNamedQuery("Answer.findByIdQuestion").setParameter("idQuestion", question.getId()).getResultList());
    }

    /**
     *
     * @param question
     * @return la liste des reponse de difficulté moyenne selon la question
     *
     */
    @Transactional
    public List<Answer> findAnswerByMediumDifficultyAndQuestion(Question question) {
        return getMediumDifficultyAnswer((List<Answer>) em.createNamedQuery("Answer.findByIdQuestion").setParameter("idQuestion", question.getId()).getResultList());
    }

    /**
     *
     * @param question
     * @return la liste des reponse facile selon la question
     *
     */
    @Transactional
    public List<Answer> findAnswerByEasyDifficultyAndQuestion(Question question) {
        return getEasyDifficultyAnswer((List<Answer>) em.createNamedQuery("Answer.findByIdQuestion").setParameter("idQuestion", question.getId()).getResultList());
    }

    /**
     *
     * @param listeAnswersIn
     * @return listeAnswersOut
     *
     * Retourne la liste des reponses de difficulté moyenne, avec ratio >= 0.66
     * ratio = getCountSelect / getCountParticipat
     */
    public List<Answer> getHardDifficultyAnswer(List<Answer> listeAnswersIn) {
        List<Answer> listeAnswersOut = new ArrayList<>();
        double ratio;
        for (Answer answer : listeAnswersIn) {
            ratio = (double) answer.getCountSelect() / (double) answer.getCountParticipat();
            if (ratio >= 0.66) {
                answer.setDifficultyType(DifficultyType.Easy);
                listeAnswersOut.add(answer);
            }
        }
        return listeAnswersOut;
    }

    /**
     *
     * @param listeAnswersIn
     * @return listeAnswersOut
     *
     * Retourne la liste des reponses de difficulté moyenne, avec 0.66 > ratio
     * && ratio >= 0.33 ratio = getCountSelect / getCountParticipat
     */
    public List<Answer> getMediumDifficultyAnswer(List<Answer> listeAnswersIn) {
        List<Answer> listeAnswersOut = new ArrayList<>();
        double ratio;
        for (Answer answer : listeAnswersIn) {
            ratio = (double) answer.getCountSelect() / (double) answer.getCountParticipat();
            if (0.66 > ratio && ratio >= 0.33) {
                answer.setDifficultyType(DifficultyType.Easy);
                listeAnswersOut.add(answer);
            }
        }
        return listeAnswersOut;
    }

    /**
     *
     * @param listeAnswersIn
     * @return listeAnswers
     *
     * Retourne la liste des reponses de difficulté facile, avec 0.33 > ratio
     * ratio = getCountSelect / getCountParticipat
     */
    public List<Answer> getEasyDifficultyAnswer(List<Answer> listeAnswersIn) {
        List<Answer> listeAnswersOut = new ArrayList<>();
        double ratio;
        for (Answer answer : listeAnswersIn) {
            ratio = (double) answer.getCountSelect() / (double) answer.getCountParticipat();
            if (0.33 > ratio) {
                answer.setDifficultyType(DifficultyType.Easy);
                listeAnswersOut.add(answer);
            }
        }
        return listeAnswersOut;
    }
}

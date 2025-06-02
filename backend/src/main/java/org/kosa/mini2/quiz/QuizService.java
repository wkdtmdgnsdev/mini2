package org.kosa.mini2.quiz;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepository;

	public int register(List<Quiz> quizList) {
		quizRepository.saveAll(quizList);
		return quizList.size();
	}

	public Quiz getRandomQuiz() {
		Quiz quiz = quizRepository.selectRandomQuiz();
		if (quiz == null) {
            throw new NoSuchElementException("퀴즈를 찾을 수 없습니다.");
        }
		return quiz;
    }
}

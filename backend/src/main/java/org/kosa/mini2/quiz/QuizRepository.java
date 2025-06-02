package org.kosa.mini2.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
	@Query(value = "SELECT * FROM (SELECT * FROM quiz ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM = 1", nativeQuery = true)
	Quiz selectRandomQuiz();
}

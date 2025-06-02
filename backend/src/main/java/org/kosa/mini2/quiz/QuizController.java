package org.kosa.mini2.quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

	@Autowired
	QuizService quizService;
	
	@PostMapping
	public ResponseEntity<String> bulkRegister(@RequestBody List<Quiz> quizList) {
		int result = quizService.register(quizList);
	   
		return ResponseEntity.ok("총 " + result + "개의 퀴즈가 등록되었습니다.");
	}
	
	@GetMapping("/random")
	@ResponseBody
	public ResponseEntity<Quiz> getRandomQuiz() {
		 Quiz quiz = quizService.getRandomQuiz();
	     return ResponseEntity.ok(quiz);
	}
}

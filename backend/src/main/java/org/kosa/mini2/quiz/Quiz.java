package org.kosa.mini2.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "M_QUIZ")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiz_seq_gen")
	@SequenceGenerator(
	    name = "quiz_seq_gen",         // JPA 내에서 사용할 시퀀스 생성기 이름
	    sequenceName = "SEQ_M_QUIZ",   // 실제 오라클 시퀀스 이름
	    allocationSize = 1              // 반드시 1로 설정 (오라클과 일치)
	)
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    
}

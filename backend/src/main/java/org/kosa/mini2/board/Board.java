package org.kosa.mini2.board;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "M_BOARD")  // 오라클은 테이블, 컬럼 이름 대소문자 구분 X (보통 대문자 사용)
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
	@SequenceGenerator(
	    name = "board_seq_gen",         // JPA 내에서 사용할 시퀀스 생성기 이름
	    sequenceName = "SEQ_M_BOARD",   // 실제 오라클 시퀀스 이름
	    allocationSize = 1              // 반드시 1로 설정 (오라클과 일치)
	)
    private int bno;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String content;

    @Column(nullable = false, length = 50)
    private String writer;

    @Column(nullable = false, length = 100)
    private String passwd;

    @Column(name = "REG_DATE", nullable = false)
    private LocalDateTime regDate = LocalDateTime.now();

    @Column(name = "VIEW_COUNT", nullable = false)
    private int viewCount = 0;

    public boolean matchPasswd(String passwd) {
        return this.passwd != null && this.passwd.equals(passwd);
    }
}

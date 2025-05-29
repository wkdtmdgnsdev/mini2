package org.kosa.mini2.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	public boolean existsByBnoAndPasswd(int bno, String passwd);
	public Page<Board> findByTitleContainingOrWriterContaining(String title, String writer, Pageable pageable);
}

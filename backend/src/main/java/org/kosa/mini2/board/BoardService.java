package org.kosa.mini2.board;

import java.util.Optional;

import org.kosa.mini2.page.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardService {
	
	@Autowired
	BoardRepository  boardRepository;

	@Transactional(readOnly = true)
	public PageResponseVO<Board> list(String searchValue, int pageNo, int size) {
        Pageable pageable = PageRequest.of(pageNo - 1, size, Sort.by("bno").descending());

        Page<Board> page;

        if (searchValue == null || searchValue.isEmpty()) {
            page = boardRepository.findAll(pageable);
        } else {
            page = boardRepository.findByTitleContainingOrWriterContaining(searchValue, searchValue, pageable);
        }

        return new PageResponseVO<>(page);
    }

	public void writer(Board board) {
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
    public Board readBoard(String bno) {
        int id = Integer.parseInt(bno);
        Optional<Board> opt = boardRepository.findById(id);
        return opt.orElse(null);
    }

	public Board modifyBoard(Board board) {
		Board existing = boardRepository.findById(board.getBno()).orElse(null);
	    if (existing == null) return null;

	    // 2. 변경할 필드만 덮어쓰기 (조회수 등은 유지)
	    existing.setTitle(board.getTitle());
	    existing.setContent(board.getContent());
	    existing.setWriter(board.getWriter());
	    // 조회수는 건드리지 않음

	    return boardRepository.save(existing);
	}
	
	public void addViewCount(String bno) {
		Board board = readBoard(bno);
		if(board != null) {
			board.setViewCount(board.getViewCount() +1);
			boardRepository.save(board);
		}
	}

	public int delete(int bno) {
		 if (boardRepository.existsById(bno)) {
		        boardRepository.deleteById(bno);
		        return 1;
		    }
		 return 0;
	}

	@Transactional(readOnly = true)
	public boolean passwdCheck(String bno, String passwd) {
		Board board = readBoard(bno);
		
		return board.matchPasswd(passwd);
	}

}

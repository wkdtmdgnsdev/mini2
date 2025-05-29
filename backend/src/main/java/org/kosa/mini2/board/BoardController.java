package org.kosa.mini2.board;

import org.kosa.mini2.page.PageResponseVO;
import org.kosa.mini2.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping
	public ResponseEntity<?> list(@RequestParam(required = false) String pageNo,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String searchValue) {
		PageResponseVO<Board> list = boardService.list(searchValue, Util.parseInt(pageNo, 1), Util.parseInt(size, 10));
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody Board board) {
		if(board == null) {
			return ResponseEntity.badRequest().build();
		}
		
		boardService.writer(board);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{bno}")
    public ResponseEntity<?> detail(@PathVariable String bno, HttpSession session) {
        Boolean noViewCount = (Boolean) session.getAttribute("noViewCount");
        if (noViewCount == null || !noViewCount) {
            boardService.addViewCount(bno);
        }
        session.setAttribute("noViewCount", false);

        Board board = boardService.readBoard(bno);
        if (board == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(board);
    }
	
	// 게시글 수정
    @PutMapping("/{bno}")
    public ResponseEntity<?> update(@PathVariable String bno, @RequestBody Board board, HttpSession session) {
        Board modifyBoard = boardService.modifyBoard(board);
        session.setAttribute("noViewCount", true);

        if (modifyBoard == null) {
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok().build();
    }
	
    @DeleteMapping("/{bno}")
    public ResponseEntity<?> delete(@PathVariable int bno) {
        int result = boardService.delete(bno);
        if (result == 0) {
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok().build();
    }
	
    @PostMapping("/check-password")
    public ResponseEntity<?> passwdCheck(@RequestBody Board board) {
        boolean succ = boardService.passwdCheck(String.valueOf(board.getBno()), board.getPasswd());

        return ResponseEntity.ok(succ);
    }
}

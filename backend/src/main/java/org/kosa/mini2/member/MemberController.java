package org.kosa.mini2.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.kosa.mini2.exception.LoginFailedException;
import org.kosa.mini2.exception.MemberLockedException;
import org.kosa.mini2.page.PageResponseVO;
import org.kosa.mini2.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping("/login")
	public ResponseEntity<Member> login(@RequestBody Member member) {
	    try {
	        // 로그인 유효성 검사
	        if (!member.validateLogin()) {
	            return ResponseEntity.status(401).build();
	        }

	        // 로그인 처리
	        member = memberService.login(member.getUserid(), member.getPasswd());
	        if (member == null) {
	        	return ResponseEntity.status(401).build();
	        }

	        return ResponseEntity.ok(member);

	    } catch (LoginFailedException e) {
	    	return ResponseEntity.status(401).build();
	    } catch (MemberLockedException e) {
	    	return ResponseEntity.status(403).build();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return ResponseEntity.status(500).build();
	    }
	}
	
	@PostMapping
	public ResponseEntity<Member> register(@RequestBody @Valid Member member, BindingResult bindingResult) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 유효성 검사 오류가 있을 경우
        if (bindingResult.hasErrors()) {
        	return ResponseEntity.badRequest().build();
        }
        
        // 유효성 검사 통과 후 회원가입 처리
        return ResponseEntity.ok(memberService.register(member));
	}
	
	//Get /{userid}
	@PostMapping("/isExistUserId/{userid}")
	// ResponseEntity
	public Map<String, Object> isExistUserId(@RequestBody Map<String, String> param) {
	    String userid = param.get("userid");
	    boolean exists = memberService.isUserIdExists(userid);

	    Map<String, Object> response = new HashMap<>();
	    response.put("existUserId", exists);
	    return response;
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<Member> getMember(@PathVariable String userid) {
		Optional<Member> member = memberService.findByUserid(userid);
		System.out.println(userid);
		System.out.println(member.get());
        return member.map(ResponseEntity::ok)
        					.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{userid}")
	public ResponseEntity<Member> updateMember(@PathVariable String userid, @RequestBody Member member) {
		return ResponseEntity.ok(memberService.update(member));
	}
	
	@DeleteMapping("/{userid}")
	public ResponseEntity<Member> delete(@PathVariable String userid) {
		memberService.delete(userid); // 내부에서 존재 여부 확인 및 예외 처리 포함 권장
	    return ResponseEntity.ok().build(); // 204 No Content
	}
	
	@GetMapping
	public ResponseEntity<?> list(
	        @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
	        @RequestParam(value = "size", defaultValue = "10") int size,
	        @RequestParam(value = "searchValue", required = false) String searchValue
	    ) {

	        PageResponseVO<Member> pageResponse = memberService.list(searchValue, pageNo, size);
	        return ResponseEntity.ok(pageResponse);
	    }
	
//	@RequestMapping("unLock")
//	public String unLock(String userid, Model model) {
//		memberService.unlockMemberByAdmin(userid);
//		
//		return "redirect:/member/detail?userid=" +userid;
//	}
//	
//	@RequestMapping("logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		
//		return "redirect:/";
//	}
}

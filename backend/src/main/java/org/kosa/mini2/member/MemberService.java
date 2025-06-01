package org.kosa.mini2.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.kosa.mini2.exception.LoginFailedException;
import org.kosa.mini2.exception.MemberLockedException;
import org.kosa.mini2.page.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	public Member login(String userid, String passwd) {
		Member dbMember = memberRepository.findById(userid).get();
//		validateLogin(dbMember, passwd);
		return dbMember;
	}

//	private void validateLogin(Member dbMember, String passwd) {
//		if(dbMember == null)
//			  throw new LoginFailedException("아이디 또는 비밀번호가 틀렸습니다.");
//		
//		if(dbMember.isUser_lock()) {
//			throw new MemberLockedException("계정이 잠겨있습니다.");
//		}
//		
//		if(!dbMember.matchPassword(passwd)) {
//			addLoginFailCount(dbMember);
//			throw new LoginFailedException("아이디 또는 비밀번호가 틀렸습니다.");
//		}
//	}

//	private void addLoginFailCount(Member dbMember) {
//		dbMember.addLoginFail();
//		memberRepository.updateLoginFailCount(dbMember.getUserid(), dbMember.getLogin_fail());
//		lockMember(dbMember);
//	}
//
//	private void lockMember(Member dbMember) {
//		if(dbMember.isLimit()) {
//			dbMember.isUserLock(true);
//			memberRepository.lockMember(dbMember.getUserid());
//			throw new MemberLockedException("5회 실패, 계정 잠금");
//		}
//	}

//	public void unlockMemberByAdmin(String userid) {
//		memberRepository.unlockMemberByAdmin(userid);
//	}

	public Member register(Member member) {
		return memberRepository.save(member);
	}

	public Optional<Member> findByUserid(String userid) {
		return memberRepository.findById(userid);
	}

	public boolean isUserIdExists(String userid) {
		return memberRepository.findById(userid).isPresent();
	}

	public Member update(Member member) {
		return memberRepository.save(member);
	}

	public Member delete(String userid) {
		Member member = memberRepository.findById(userid).get();
		memberRepository.delete(member);
	    return member;
	}

	public PageResponseVO<Member> list(String searchValue, int pageNo, int size) {
		Pageable pageable = PageRequest.of(pageNo - 1, size, Sort.by(Sort.Direction.DESC, "userid"));
		Page<Member> memberPage;
		
		if (searchValue != null && !searchValue.trim().isEmpty()) {
	        memberPage = memberRepository.findByNameContainingIgnoreCase(searchValue, pageable);
	    } else {
	        memberPage = memberRepository.findAll(pageable);
	    }
		
		return new PageResponseVO<>(memberPage);
	}

}
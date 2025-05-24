# mini2

## 회원(Member) URL
| 기능                | HTTP Method | URL                           | 요청 데이터                            | 설명                    |
| ----------------- | ----------- | ----------------------------- | --------------------------------- | --------------------- |
| 로그인 폼 요청 (뷰 반환)   | GET         | `/members/login-form`         | 없음                                | 로그인 페이지 뷰 반환 (템플릿용)   |
| 로그인               | POST        | `/members/login`              | JSON Body: `{ userid, passwd }`   | 로그인 처리, 세션에 사용자 정보 저장 |
| 회원가입 폼 요청 (뷰 반환)  | GET         | `/members/register-form`      | 없음                                | 회원가입 페이지 뷰 반환         |
| 회원가입              | POST        | `/members`                    | JSON Body: 회원 정보 (유효성 검사 포함)      | 신규 회원 가입              |
| 아이디 중복 확인         | POST        | `/members/check-userid`       | JSON Body: `{ userid }`           | 아이디 존재 여부 확인          |
| 회원 상세 조회 (뷰 반환)   | GET         | `/members/{userid}`           | PathVariable: userid              | 회원 상세정보 조회 (템플릿용)     |
| 회원 수정 폼 요청 (뷰 반환) | GET         | `/members/{userid}/edit-form` | PathVariable: userid              | 회원 정보 수정 폼 뷰 반환       |
| 회원 수정             | PUT         | `/members/{userid}`           | JSON Body: 수정할 회원 정보              | 회원 정보 수정              |
| 회원 삭제             | DELETE      | `/members/{userid}`           | PathVariable: userid              | 회원 탈퇴 및 세션 종료         |
| 회원 리스트 조회 (관리자)   | GET         | `/members`                    | 쿼리 파라미터: `page`, `size`, `search` | 회원 목록 조회 (관리자 권한 필요)  |
| 회원 잠금 해제 (관리자)    | POST        | `/members/{userid}/unlock`    | 없음                                | 관리자에 의한 회원 잠금 해제      |
| 로그아웃              | POST        | `/members/logout`             | 없음                                | 세션 무효화 후 로그아웃 처리      |


## 게시판(Board) URL
| 기능          | HTTP Method | URL                           | 요청 데이터                             | 설명                      |
| ----------- | ----------- | ----------------------------- | ---------------------------------- | ----------------------- |
| 게시물 목록 조회   | GET         | `/boards`                     | 쿼리 파라미터: `page`, `size`, `search`  | 페이징 및 검색어에 따른 게시물 목록 조회 |
| 게시물 상세 조회   | GET         | `/boards/{id}`                | PathVariable: 게시물 ID               | 게시물 상세 정보 조회            |
| 게시물 등록      | POST        | `/boards`                     | JSON Body: 게시물 데이터                 | 새 게시물 등록                |
| 게시물 수정      | PUT         | `/boards/{id}`                | JSON Body: 수정할 게시물 데이터             | 게시물 수정                  |
| 게시물 삭제      | DELETE      | `/boards/{id}`                | PathVariable: 게시물 ID               | 게시물 삭제                  |
| 게시물 비밀번호 확인 | POST        | `/boards/{id}/check-password` | JSON Body: `{ "password": "..." }` | 게시물 비밀번호 일치 여부 확인       |


## 퀴즈(Quiz) URL
| 기능         | HTTP Method | URL                      | 요청 데이터                                          | 설명                  |
| ---------- | ----------- | ------------------------ | ----------------------------------------------- | ------------------- |
| 퀴즈 등록 폼 요청 | GET         | `/quizzes/register-form` | 없음                                              | 퀴즈 등록 폼 뷰 반환 (템플릿용) |
| 퀴즈 일괄 등록   | POST        | `/quizzes`               | JSON Body: 퀴즈 리스트 (List\<Map\<String, Object>>) | 여러 개 퀴즈를 한꺼번에 등록    |
| 랜덤 퀴즈 조회   | GET         | `/quizzes/random`        | 없음                                              | 랜덤 퀴즈 1개 반환 (JSON)  |

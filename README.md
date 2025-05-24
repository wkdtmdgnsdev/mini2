# mini2

# Vue 라우팅 URL

| URL 경로            | 설명                  | 컴포넌트 예시           | 비고                      |
|---------------------|-----------------------|------------------------|---------------------------|
| `/`                 | 메인 페이지            | `Home.vue`             | 기본 진입 페이지            |
| `/member/login`      | 로그인 페이지          | `MemberLogin.vue`      | 로그인 폼                  |
| `/member/register`   | 회원가입 페이지        | `MemberRegister.vue`   | 회원가입 폼                |
| `/member/detail/:userid` | 회원 상세보기        | `MemberDetail.vue`     | 파라미터로 userid 전달     |
| `/member/update`     | 회원 정보 수정 페이지  | `MemberUpdate.vue`     | 로그인 후 접근              |
| `/member/list`       | 회원 목록 (관리자용)   | `MemberList.vue`       | 관리자 권한 필요            |
| `/member/logout`     | 로그아웃 처리 페이지   | `MemberLogout.vue`     | 로그아웃 처리 (optional)   |
| `/board/list`        | 게시글 목록 페이지     | `BoardList.vue`        | 페이징, 검색 포함           |
| `/board/register`    | 게시글 등록 페이지     | `BoardRegister.vue`    | 게시글 작성 폼              |
| `/board/detail/:bno` | 게시글 상세보기        | `BoardDetail.vue`      | bno(게시글 번호) 전달      |
| `/board/update/:bno` | 게시글 수정 페이지     | `BoardUpdate.vue`      | bno 전달, 수정 폼           |
| `/quiz/register`     | 퀴즈 등록 페이지       | `QuizRegister.vue`     | 다중 퀴즈 등록              |
| `/quiz/random`       | 랜덤 퀴즈 불러오기     | `QuizRandom.vue`       | 퀴즈 출제 페이지            |

---

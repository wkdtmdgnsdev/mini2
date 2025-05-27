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

# 📘 Backend REST API 명세서

## ✅ 공통 사항
- **Base URL**: `/api`
- **데이터 형식**: JSON

---

## 👤 Member API

| 메서드 | URL                             | 설명                  | 요청 데이터                |
|--------|----------------------------------|-----------------------|----------------------------|
| POST   | `/api/member/login`             | 로그인                | `userid`, `password`       |
| POST   | `/api/member/register`          | 회원가입              | 회원 정보 JSON             |
| GET    | `/api/member/{userid}`          | 회원 상세 정보 조회   | 경로 변수: `userid`        |
| PUT    | `/api/member/update`            | 회원 정보 수정        | 수정할 회원 정보 JSON      |
| GET    | `/api/member/list`              | 회원 목록 조회 (관리자용) | 관리자 권한 필요      |
| POST   | `/api/member/logout`            | 로그아웃 처리         | (선택적으로 세션 해제 등)  |

---

## 📝 Board API

| 메서드 | URL                             | 설명                  | 요청 데이터                    |
|--------|----------------------------------|-----------------------|--------------------------------|
| GET    | `/api/board/list`               | 게시글 목록 조회      | 페이징, 검색 쿼리 파라미터 가능 |
| POST   | `/api/board/register`           | 게시글 등록           | 게시글 JSON                    |
| GET    | `/api/board/{bno}`              | 게시글 상세 조회      | 경로 변수: `bno`               |
| PUT    | `/api/board/{bno}`              | 게시글 수정           | 게시글 수정 JSON               |

---

## 🎯 Quiz API

| 메서드 | URL                             | 설명                  | 요청 데이터          |
|--------|----------------------------------|-----------------------|----------------------|
| POST   | `/api/quiz/register`            | 퀴즈 다중 등록        | 퀴즈 배열(JSON)      |
| GET    | `/api/quiz/random`              | 랜덤 퀴즈 1개 조회    | 없음                 |

---

## 📎 참고 사항
- 모든 API는 `/api` 하위 경로로 동작합니다.


# mini2

## 🧭 프로젝트 개요

본 프로젝트는 Vue.js를 활용하여 회원 관리와 게시판 기능을 구현한 웹 애플리케이션입니다.  
사용자는 회원가입, 로그인, 정보 수정, 게시글 작성 및 수정, 퀴즈 출제 등의 기능을 이용할 수 있습니다.

---

## 🛠️ 기술 스택

- **프론트엔드**: Vue.js, Vue Router
- **백엔드**: Spring Boot
- **데이터베이스**: Oracle

---

## 🔧 주요 기능

### 1. 회원 관리

- 회원가입: 사용자 정보를 입력하여 회원가입을 진행합니다.
- 로그인/로그아웃: 인증된 사용자만 특정 기능에 접근할 수 있습니다.
- 회원 정보 수정: 로그인한 사용자는 자신의 정보를 수정할 수 있습니다.
- 회원 상세보기: 관리자는 다른 사용자의 정보를 조회할 수 있습니다.

### 2. 게시판 기능

- 게시글 목록 조회: 게시글 목록을 조회하고, 페이징 및 검색 기능을 제공합니다.
- 게시글 작성/수정: 사용자는 게시글을 작성하고 수정할 수 있습니다.
- 게시글 상세보기: 게시글의 상세 내용을 확인할 수 있습니다.

### 3. 퀴즈 기능

- 퀴즈 등록: 관리자는 다중 퀴즈를 등록할 수 있습니다.
- 랜덤 퀴즈 출제: 사용자는 랜덤으로 퀴즈를 풀 수 있습니다.

---

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
| `/board/`        | 게시글 목록 페이지     | `BoardList.vue`        | 페이징, 검색 포함           |
| `/board`    | 게시글 등록 페이지     | `BoardRegister.vue`    | 게시글 작성 폼              |
| `/board/:bno` | 게시글 상세보기        | `BoardDetail.vue`      | bno(게시글 번호) 전달      |
| `/board/:bno` | 게시글 수정 페이지     | `BoardUpdate.vue`      | bno 전달, 수정 폼           |
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
| POST   | `/api/member`                   | 회원가입              | 회원 정보 JSON             |
| GET    | `/api/member/{userid}`          | 회원 상세 정보 조회   | 경로 변수: `userid`        |
| PUT    | `/api/member/{userid}`          | 회원 정보 수정        | 수정할 회원 정보 JSON      |
| GET    | `/api/member`                   | 회원 목록 조회 (관리자용) | 관리자 권한 필요      |
| POST   | `/api/member/logout`            | 로그아웃 처리         | (선택적으로 세션 해제 등)  |

---

## 📝 Board API

| 메서드 | URL                             | 설명                  | 요청 데이터                                      |
|--------|----------------------------------|-----------------------|--------------------------------------------------|
| GET    | `/api/board`                    | 게시글 목록 조회      | 쿼리 파라미터: `pageNo`, `size`, `searchValue` *(선택)* |
| POST   | `/api/board`                    | 게시글 등록           | 요청 바디: 게시글 JSON (`Board`)               |
| GET    | `/api/board/{bno}`              | 게시글 상세 조회      | 경로 변수: `bno` (`String`)                    |
| PUT    | `/api/board/{bno}`              | 게시글 수정           | 경로 변수: `bno`, 요청 바디: 게시글 JSON (`Board`) |
| DELETE | `/api/board/{bno}`              | 게시글 삭제           | 경로 변수: `bno` (`int`)                        |
| POST   | `/api/board/check-password`     | 게시글 비밀번호 확인  | 요청 바디: 게시글 JSON (`bno`, `passwd`)       |


## 🎯 Quiz API

| 메서드 | URL                             | 설명                  | 요청 데이터          |
|--------|----------------------------------|-----------------------|----------------------|
| POST   | `/api/quiz/register`            | 퀴즈 다중 등록        | 퀴즈 배열(JSON)      |
| GET    | `/api/quiz/random`              | 랜덤 퀴즈 1개 조회    | 없음                 |

---

## 📎 참고 사항
- 모든 API는 `/api` 하위 경로로 동작합니다.


# mini2
# 📆 7일 일정표 (Spring Boot + Vue.js 마이그레이션 with Bootstrap 재사용)

| 날짜 | 주요 목표 | 상세 작업 | 시간 배분 힌트 |
|------|------------|-----------|----------------|
| **Day 1** | ✅ URL 정의 + 라우팅 구성 | - 전체 서비스 흐름 정리<br>- Vue Router 설정<br>- Bootstrap CSS 연결<br>- 페이지 컴포넌트 생성 | 💡 디자인 고민 거의 안 해도 되므로 페이지 구성만 신경 쓰세요 |
| **Day 2** | ✅ 상태 변수 & 기본 UI 동작 구현 | - 상태 관리 (`ref`, `reactive`, `computed`) 적용<br>- 기본 동작 구현 (폼 입력, 버튼 동작)<br>- Vue 이벤트 처리 학습 (`@click`, `v-model` 등) | 💡 UI만 먼저 구현, 서버 연동은 나중 |
| **Day 3** | ✅ 백엔드 API 정의 + Spring Boot 구조 구성 | - 기존 컨트롤러 기반으로 REST API URI 정의<br>- Spring Boot 프로젝트 생성<br>- Entity, DTO, Controller 설계 | 💡 기존 DB 사용하므로 JPA만 잘 매핑하면 OK |
| **Day 4** | ✅ API 구현 (CRUD 중심) | - 주요 API 구현: 게시판, 회원 등<br>- 예외 처리, 유효성 검사<br>- Postman으로 테스트 | 💡 기능 완성 후 Swagger 적용해도 좋음 |
| **Day 5** | ✅ 프론트-백 연동 (Axios 사용) | - axios 기본 설정<br>- 리스트 불러오기, 등록 기능부터 연동<br>- 에러/응답 처리 | 💡 kosaFetch는 이후 교체 가능하니 axios로 먼저 |
| **Day 6** | ✅ 나머지 기능 연동 + UX 보완 | - 수정/삭제 기능 연동<br>- 스피너 UI 적용 (axios 인터셉터 또는 v-if)<br>- 사용자 피드백 요소 추가 (alert 등) | 💡 스피너는 사용자 경험에 중요, 꼭 넣으세요 |
| **Day 7** | ✅ 테스트, 문서화, 배포 | - 전 기능 점검 및 디버깅<br>- README 작성 (기능 설명, 실행 방법 등)<br>- 간단한 배포 (Spring Boot + 정적 파일 포함) | 💡 시간이 남으면 kosaFetch 도입, 리팩토링도 가능 |

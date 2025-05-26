<template>
  <div class="container mt-5">
    <h2 class="text-center">회원 정보 수정</h2>
    <form @submit.prevent="submitForm">
      <div class="mb-3">
        <label for="userid" class="form-label">아이디:</label>
        <input
          type="text"
          class="form-control"
          id="userid"
          v-model="form.userid"
          readonly
        />
      </div>

      <div class="mb-3">
        <label for="passwd" class="form-label">비밀번호:</label>
        <input
          type="password"
          class="form-control"
          id="passwd"
          v-model="form.passwd"
          required
          @input="validatePassword"
        />
        <div id="passwdMessage" class="form-text text-danger">{{ passwdMessage }}</div>
      </div>

      <div class="mb-3">
        <label for="name" class="form-label">이름:</label>
        <input
          type="text"
          class="form-control"
          id="name"
          v-model="form.name"
          required
        />
      </div>

      <div class="mb-3">
        <label for="age" class="form-label">나이:</label>
        <input
          type="number"
          class="form-control"
          id="age"
          v-model.number="form.age"
          required
        />
      </div>

      <div class="d-flex justify-content-between">
        <button type="submit" class="btn btn-primary">수정하기</button>
        <RouterLink :to="`/member/detail/${form.userid}`" class="btn btn-secondary">
          뒤로 가기
        </RouterLink>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter, useRoute } from 'vue-router';

// 1. 라우터
const router = useRouter();
const route = useRoute();

// 2. 데이터 초기화 (실제로는 API로 불러올 수도 있음)
const form = ref({
  userid: route.params.userid || 'hong123', // 테스트용 기본값
  passwd: '',
  name: '홍길동',
  age: 30,
});

// 3. 유효성 메시지
const passwdMessage = ref('');

// 4. 비밀번호 유효성 검증 함수
function validatePassword() {
  if (form.value.passwd.length < 6) {
    passwdMessage.value = '비밀번호는 최소 6자 이상이어야 합니다.';
  } else {
    passwdMessage.value = '';
  }
}

// 5. 폼 제출
function submitForm() {
  if (passwdMessage.value) {
    alert('비밀번호를 확인해주세요.');
    return;
  }

  // 여기에 axios 또는 fetch 요청으로 전송 가능
  console.log('수정된 데이터:', form.value);

  alert('회원 정보가 수정되었습니다.');
  router.push(`/member/detail/${form.value.userid}`);
}
</script>

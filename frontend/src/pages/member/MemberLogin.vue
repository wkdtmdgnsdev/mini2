<template>
  <div class="container mt-5">
    <h2 class="text-center">로그인</h2>
    <div class="card mx-auto" style="max-width: 600px;">
      <div class="card-body">
        <form @submit.prevent="handleLogin">
          <div class="mb-3">
            <label for="userid" class="form-label">아이디</label>
            <input
              v-model="userid"
              type="text"
              class="form-control"
              id="userid"
              required
            />
          </div>
          <div class="mb-3">
            <label for="passwd" class="form-label">비밀번호</label>
            <input
              v-model="passwd"
              type="password"
              class="form-control"
              id="passwd"
              required
            />
          </div>

          <div class="d-flex justify-content-between align-items-center">
            <!-- 로그인 버튼 -->
            <button type="submit" class="btn btn-primary">로그인</button>

            <!-- 에러 메시지 -->
            <div
              v-if="errorMessage"
              class="text-danger text-center mx-3"
            >
              {{ errorMessage }}
            </div>

            <!-- 회원가입 버튼 -->
            <router-link to="/member/register" class="btn btn-primary">
              회원가입
            </router-link>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const userid = ref('')
const passwd = ref('')
const errorMessage = ref('')
const router = useRouter()

const handleLogin = () => {
  // 실제 로그인 연동 전용 코드 대체 가능
  if (userid.value === 'test' && passwd.value === '1234') {
    // 로그인 성공 시 예시 라우팅
    router.push('/')
  } else {
    errorMessage.value = '아이디 또는 비밀번호가 올바르지 않습니다.'
  }
}
</script>

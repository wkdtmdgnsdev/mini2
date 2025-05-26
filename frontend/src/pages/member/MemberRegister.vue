<template>
  <div class="container mt-5">
    <div class="register-container mx-auto" style="max-width: 500px;">
      <h2 class="text-center mb-4">회원가입</h2>

      <form @submit.prevent="handleSubmit">
        <div class="mb-3">
          <label for="userid" class="form-label">아이디</label>
          <input
            type="text"
            class="form-control"
            id="userid"
            v-model="form.userid"
            required
          />
          <div class="form-text text-danger" v-if="errors.userid">
            {{ errors.userid }}
          </div>
        </div>

        <div class="mb-3">
          <label for="passwd" class="form-label">비밀번호</label>
          <input
            type="password"
            class="form-control"
            id="passwd"
            v-model="form.passwd"
            required
          />
          <div class="form-text text-danger" v-if="errors.passwd">
            {{ errors.passwd }}
          </div>
        </div>

        <div class="mb-3">
          <label for="name" class="form-label">이름</label>
          <input
            type="text"
            class="form-control"
            id="name"
            v-model="form.name"
            required
          />
        </div>

        <div class="mb-3">
          <label for="age" class="form-label">나이</label>
          <input
            type="number"
            class="form-control"
            id="age"
            v-model="form.age"
            required
          />
        </div>

        <button type="submit" class="btn btn-primary w-100">회원가입</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const form = reactive({
  userid: '',
  passwd: '',
  name: '',
  age: '',
})

const errors = reactive({
  userid: '',
  passwd: '',
})

// 간단한 유효성 검증
function validate() {
  let valid = true
  errors.userid = ''
  errors.passwd = ''

  if (form.userid.length < 4) {
    errors.userid = '아이디는 최소 4자 이상이어야 합니다.'
    valid = false
  }

  if (form.passwd.length < 6) {
    errors.passwd = '비밀번호는 최소 6자 이상이어야 합니다.'
    valid = false
  }

  return valid
}

function handleSubmit() {
  if (!validate()) return

  // TODO: 서버로 POST 요청 보내기
  console.log('회원가입 데이터:', form)

  // 성공 후 홈 또는 로그인 페이지로 이동
  router.push('/member/login')
}
</script>

<style scoped>
.register-container {
  background: #fff;
  padding: 2rem;
  border-radius: 0.5rem;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}
</style>

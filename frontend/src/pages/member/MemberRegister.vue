<template>
  <div class="container mt-5">
    <div class="register-container mx-auto" style="max-width: 500px;">
      <h2 class="text-center mb-4">회원가입</h2>

      <form @submit.prevent="handleSubmit">
        <!-- 아이디 -->
        <div class="mb-3">
          <label for="userid" class="form-label">아이디</label>
          <input
            type="text"
            class="form-control"
            id="userid"
            v-model="form.userid"
            @input="validateUserId"
            @blur="checkUserIdDuplication"
            required
          />
          <div class="form-text" :class="useridMessageColorClass" v-if="useridMessage">
            {{ useridMessage }}
          </div>
        </div>

        <!-- 비밀번호 -->
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

        <!-- 이름 -->
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

        <!-- 나이 -->
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

        <!-- 제출 버튼 -->
        <button type="submit" class="btn btn-primary w-100">회원가입</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()

const form = reactive({
  userid: '',
  passwd: '',
  name: '',
  age: '',
})

const errors = reactive({
  passwd: ''
})

const useridMessage = ref('')
const isUserIdValid = ref(false)

const useridMessageColorClass = computed(() => {
  if (!useridMessage.value) return ''
  return isUserIdValid.value ? 'text-success' : 'text-danger'
})

function validateUserId() {
  const id = form.userid
  if (id.length < 8) {
    useridMessage.value = '아이디는 최소 8글자 이상이어야 합니다.'
    isUserIdValid.value = false
  } else {
    useridMessage.value = '형식 OK - 중복 확인 중...'
    isUserIdValid.value = false
  }
}

async function checkUserIdDuplication() {
  const id = form.userid
  if (id.length >= 8) {
    try {
      const res = await axios.post('/api/member/isExistUserId', { userid: id })
      if (res.data.existUserId) {
        useridMessage.value = '이미 사용 중인 아이디입니다.'
        isUserIdValid.value = false
      } else {
        useridMessage.value = '사용 가능한 아이디입니다.'
        isUserIdValid.value = true
      }
    } catch (err) {
      useridMessage.value = '중복 확인 중 오류 발생'
      isUserIdValid.value = false
      console.error(err)
    }
  }
}

function validate() {
  let valid = true
  errors.passwd = ''

  if (!isUserIdValid.value) {
    valid = false
    useridMessage.value ||= '아이디를 확인해주세요.'
  }

  if (form.passwd.length < 6) {
    errors.passwd = '비밀번호는 최소 6자 이상이어야 합니다.'
    valid = false
  }

  return valid
}

async function handleSubmit() {
  if (!validate()) return

  try {
    const res = await axios.post('/api/member', form)

    // 성공이면 로그인 페이지로 이동
    router.push('/member/login')
  } catch (err) {
    if (err.response?.status === 400) {
      alert('입력값이 유효하지 않습니다.')
    } else {
      alert('회원가입 중 오류 발생')
    }
    console.error(err)
  }
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

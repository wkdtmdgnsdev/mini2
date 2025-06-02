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
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { useRouter, useRoute } from 'vue-router'

// 라우터 객체
const router = useRouter()
const route = useRoute()

// 초기 상태
const form = ref({
  userid: '',
  passwd: '',
  name: '',
  age: null
})

const passwdMessage = ref('')

// 회원 정보 불러오기
async function fetchMember() {
  try {
    const res = await axios.get(`/api/member/${route.params.userid}`)
    form.value = res.data
  } catch (err) {
    console.error('회원 정보를 가져오는 중 오류 발생:', err)
    alert('회원 정보를 불러올 수 없습니다.')
    router.push('/') // 또는 다른 페이지
  }
}

// 비밀번호 유효성 검사
function validatePassword() {
  if (form.value.passwd.length < 6) {
    passwdMessage.value = '비밀번호는 최소 6자 이상이어야 합니다.'
  } else {
    passwdMessage.value = ''
  }
}

// 회원 정보 수정
async function submitForm() {
  if (passwdMessage.value) {
    alert('비밀번호를 확인해주세요.')
    return
  }

  try {
    const res = await axios.put(`/api/member/${form.value.userid}`, form.value)
    alert('회원 정보가 수정되었습니다.')
    router.push(`/member/detail/${form.value.userid}`)
  } catch (err) {
    console.error('회원 정보 수정 중 오류 발생:', err)
    alert('수정 실패')
  }
}

// 컴포넌트가 마운트될 때 회원 정보 가져오기
onMounted(() => {
  fetchMember()
})
</script>

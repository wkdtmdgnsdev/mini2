<template>
  <div class="container mt-5">
    <h2 class="text-center mb-4">📝 게시물 등록</h2>

    <form @submit.prevent="submitForm">
      <!-- 제목 -->
      <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input v-model="title" type="text" class="form-control" id="title" required />
      </div>

      <!-- 내용 -->
      <div class="mb-3">
        <label for="content" class="form-label">내용</label>
        <textarea v-model="content" class="form-control" id="content" rows="5" required></textarea>
      </div>

      <!-- 작성자 -->
      <div class="mb-3">
        <label for="writer" class="form-label">작성자</label>
        <input v-model="writer" type="text" class="form-control" id="writer" required />
      </div>

      <!-- 비밀번호 -->
      <div class="mb-3">
        <label for="passwd" class="form-label">비밀번호</label>
        <input v-model="passwd" type="password" class="form-control" id="passwd" required />
      </div>

      <!-- 등록 버튼 및 목록 버튼 -->
      <div class="d-flex justify-content-between">
        <button type="submit" class="btn btn-primary">등록하기</button>
        <router-link to="/board/list" class="btn btn-secondary">목록으로 돌아가기</router-link>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
// import axios from 'axios'
import { useRouter } from 'vue-router'

const title = ref('')
const content = ref('')
const writer = ref('')
const passwd = ref('')
const router = useRouter()

// const submitForm = async () => {
//   try {
//     await axios.post('/api/board/register', {
//       title: title.value,
//       content: content.value,
//       writer: writer.value,
//       passwd: passwd.value,
//     })
//     alert('게시물이 등록되었습니다.')
//     router.push('/board/list')
//   } catch (error) {
//     console.error('등록 실패', error)
//     alert('게시물 등록 중 오류가 발생했습니다.')
//   }
// }
</script>

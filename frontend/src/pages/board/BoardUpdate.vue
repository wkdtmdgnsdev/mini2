<template>
  <div class="container mt-5">
    <h2 class="text-center mb-4">게시물 수정</h2>

    <form @submit.prevent="submitUpdate">
      <!-- 게시물 번호 (숨김 처리) -->
      <input type="hidden" :value="bno" />

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

      <!-- 버튼 -->
      <div class="d-flex justify-content-between">
        <button type="submit" class="btn btn-primary">수정하기</button>
        <router-link :to="`/board/detail/${bno}`" class="btn btn-secondary">뒤로 가기</router-link>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const router = useRouter()
const bno = route.params.bno

const title = ref('')
const content = ref('')
const writer = ref('')
const passwd = ref('')

const fetchBoard = async () => {
  try {
    const { data } = await axios.get(`/api/board/${bno}`)
    title.value = data.title
    content.value = data.content
    writer.value = data.writer
  } catch (error) {
    alert('게시물을 불러오지 못했습니다.')
  }
}

const submitUpdate = async () => {
  try {
    await axios.put(`/api/board/${bno}`, {
      bno,
      title: title.value,
      content: content.value,
      writer: writer.value,
      passwd: passwd.value
    })
    alert('수정이 완료되었습니다.')
    router.push(`/board/${bno}`)
  } catch (err) {
    alert('수정 중 오류가 발생했습니다.')
  }
}

onMounted(() => {
  fetchBoard()
})
</script>

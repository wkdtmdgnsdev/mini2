<template>
  <div class="container py-5">
    <h2 class="text-center text-dark mb-5">📋 게시물 목록</h2>

    <!-- 검색 폼 -->
    <form @submit.prevent="searchBoards" class="row g-3 align-items-end mb-4">
      <div class="col-md-2">
        <select v-model="size" class="form-select">
          <option v-for="s in [10, 30, 90, 100]" :key="s" :value="s">{{ s }}</option>
        </select>
      </div>

      <div class="col-md-6">
        <input v-model="searchValue" type="text" class="form-control" placeholder="제목, 작성자 등" />
      </div>

      <div class="col-md-2">
        <button type="submit" class="btn btn-primary w-100">검색</button>
      </div>

      <div class="col-md-2">
        <router-link to="/board/register" class="btn btn-success w-100">글쓰기</router-link>
      </div>
    </form>

    <!-- 게시물 목록 -->
    <div class="table-responsive">
      <table class="table table-bordered table-hover align-middle text-center">
        <thead class="table-dark">
          <tr>
            <th>번호</th>
            <th class="text-start">제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in list.content" :key="item.bno">
            <td>{{ item.bno }}</td>
            <td class="text-start">
              <router-link :to="`/board/${item.bno}`" class="text-decoration-none text-primary">
                {{ item.title }}
              </router-link>
            </td>
            <td>{{ item.writer }}</td>
            <td>{{ item.regDate }}</td>
            <td>{{ item.viewCount }}</td>
          </tr>
          <tr v-if="list.content.length === 0">
            <td colspan="5" class="text-center text-muted">검색 결과가 없습니다.</td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 간단 페이지네이션 -->
    <div class="my-4 d-flex justify-content-center gap-2">
      <button
        class="btn btn-outline-secondary"
        :disabled="pageNo === 1 || !list.previous"
        @click="changePage(pageNo - 1)"
      >
        ◀ 이전
      </button>
      <button
        class="btn btn-outline-secondary"
        :disabled="!list.next"
        @click="changePage(pageNo + 1)"
      >
        다음 ▶
      </button>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios'
import { ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const pageNo = ref(parseInt(route.query.pageNo || 1))
const size = ref(parseInt(route.query.size || 10))
const searchValue = ref(route.query.searchValue || '')

const list = ref({
  content: [],
  previous: false,
  next: false
})

// 게시글 목록 불러오기
const fetchBoards = async () => {
  try {
    const { data } = await axios.get('/api/board', {
      params: {
        pageNo: pageNo.value,
        size: size.value,
        searchValue: searchValue.value
      }
    })
    list.value = data
  } catch (e) {
    console.error('게시글 불러오기 실패:', e)
    alert('게시글을 불러오는 중 오류가 발생했습니다.')
  }
}

// 쿼리 업데이트 및 목록 재요청
const updateQuery = () => {
  router.push({
    path: '/board/list',
    query: {
      pageNo: pageNo.value,
      size: size.value,
      searchValue: searchValue.value || undefined
    }
  })
}

// 검색
const searchBoards = () => {
  pageNo.value = 1
  updateQuery()
}

// 페이지 이동
const changePage = (newPage) => {
  pageNo.value = newPage
  updateQuery()
}

// 쿼리 변화 감지
watch(
  () => route.query,
  (query) => {
    pageNo.value = parseInt(query.pageNo || 1)
    size.value = parseInt(query.size || 10)
    searchValue.value = query.searchValue || ''
    fetchBoards()
  },
  { immediate: true }
)
</script>

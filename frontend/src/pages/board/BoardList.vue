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

    <!-- 페이지네이션 -->
    <Pagination :pageResponse="pageResponse" @page-move="handlePageMove" />

  </div>
</template>

<script setup>
import Pagination from '@/components/Pagination.vue'
import axios from 'axios'
import { ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

// 초기화
const pageNo = ref(parseInt(route.query.pageNo || 1))
const size = ref(parseInt(route.query.size || 10))
const searchValue = ref(route.query.searchValue || '')

const list = ref({
  content: [],
  previous: false,
  next: false,
  startPage: 1,
  endPage: 1,
  totalPages: 1
})

// 페이지 응답 가공해서 Pagination 컴포넌트에 전달
const pageResponse = ref({
  pageNo: pageNo.value,
  startPage: 1,
  endPage: 1,
  prev: false,
  next: false,
  totalPage: 1
})

// 게시글 불러오기
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

    pageResponse.value = {
      pageNo: pageNo.value,
      startPage: data.startPage,
      endPage: data.endPage,
      prev: data.previous,
      next: data.next,
      totalPage: data.totalPages
    }
  } catch (e) {
    console.error('게시글 불러오기 실패:', e)
  }
}

// URL 쿼리 업데이트
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

// 검색 실행
const searchBoards = () => {
  pageNo.value = 1
  updateQuery()
}

// 페이지 이동
const handlePageMove = (newPage) => {
  pageNo.value = newPage
  updateQuery()
}

const goToPreviousPage = () => {
  if (list.value.previous && pageNo.value > 1) {
    pageNo.value--
    updateQuery()
  }
}

const goToNextPage = () => {
  if (list.value.next) {
    pageNo.value++
    updateQuery()
  }
}

// URL 쿼리 변화 감지 → 목록 갱신
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

<style scoped>
.table th,
.table td {
  vertical-align: middle;
}
</style>

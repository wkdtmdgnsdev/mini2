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
        <input
          v-model="searchValue"
          type="text"
          class="form-control"
          placeholder="제목, 작성자 등"
        />
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
          <tr v-for="item in list" :key="item.bno">
            <td>{{ item.bno }}</td>
            <td class="text-start">
              <router-link
                :to="`/board/detail/${item.bno}`"
                class="text-decoration-none text-primary"
              >
                {{ item.title }}
              </router-link>
            </td>
            <td>{{ item.writer }}</td>
            <td>{{ item.reg_date }}</td>
            <td>{{ item.view_count }}</td>
          </tr>
          <tr v-if="list.length === 0">
            <td colspan="5" class="text-center text-muted">검색 결과가 없습니다.</td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 페이지네이션 (간단한 예시) -->
    <div class="my-4 text-center">
      <button
        class="btn btn-outline-secondary me-2"
        :disabled="pageNo <= 1"
        @click="pageNo-- && fetchBoards()"
      >
        이전
      </button>
      <span>{{ pageNo }}</span>
      <button class="btn btn-outline-secondary ms-2" @click="pageNo++ && fetchBoards()">
        다음
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'

const list = ref([])
const pageNo = ref(1)
const size = ref(10)
const searchValue = ref('')

// 더미 데이터 전체 목록
const allBoards = [
  { bno: 10, title: 'Vue 게시판 예제', writer: '홍길동', reg_date: '2025-05-01', view_count: 25 },
  { bno: 9, title: 'Composition API 연습', writer: '김영희', reg_date: '2025-04-28', view_count: 13 },
  { bno: 8, title: '상태 변수로 UI 구성하기', writer: '이철수', reg_date: '2025-04-25', view_count: 9 },
  { bno: 7, title: 'Vue 3 이벤트 바인딩', writer: '박민수', reg_date: '2025-04-20', view_count: 17 },
  { bno: 6, title: 'Props와 Emits 사용법', writer: '최수정', reg_date: '2025-04-18', view_count: 11 },
  { bno: 5, title: '게시판 목록 구성', writer: '정우성', reg_date: '2025-04-15', view_count: 22 },
  { bno: 4, title: 'axios 없이 테스트', writer: '유재석', reg_date: '2025-04-12', view_count: 5 },
  { bno: 3, title: '템플릿 반복 렌더링', writer: '강호동', reg_date: '2025-04-10', view_count: 6 },
  { bno: 2, title: 'Vue에서 조건부 렌더링', writer: '이효리', reg_date: '2025-04-08', view_count: 3 },
  { bno: 1, title: '게시판 시작하기', writer: '장원영', reg_date: '2025-04-05', view_count: 7 }
]

const fetchBoards = () => {
  const filtered = allBoards.filter(item =>
    item.title.includes(searchValue.value) || item.writer.includes(searchValue.value)
  )
  const start = (pageNo.value - 1) * size.value
  const end = start + size.value
  list.value = filtered.slice(start, end)
}

const searchBoards = () => {
  pageNo.value = 1
  fetchBoards()
}

watch([pageNo, size], fetchBoards)

onMounted(() => {
  fetchBoards()
})
</script>



<style scoped>
.table th,
.table td {
  vertical-align: middle;
}
</style>

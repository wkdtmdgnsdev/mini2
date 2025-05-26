<template>
  <div class="container py-5">
    <h2 class="text-center text-primary mb-5">회원 관리</h2>

    <!-- 검색 폼 -->
    <form @submit.prevent="searchMembers" class="row gy-3 align-items-end mb-4">
      <div class="col-md-2">
        <select v-model="size" class="form-select">
          <option v-for="option in [10, 30, 90, 100]" :key="option" :value="option">{{ option }}</option>
        </select>
      </div>

      <div class="col-md-6">
        <input
          type="text"
          class="form-control"
          v-model="searchValue"
          placeholder="회원명 또는 아이디 입력"
        />
      </div>

      <div class="col-md-2">
        <button type="submit" class="btn btn-primary w-100">검색</button>
      </div>
    </form>

    <!-- 회원 목록 테이블 -->
    <div class="table-responsive">
      <table class="table table-hover table-bordered text-center align-middle">
        <thead class="table-light">
          <tr>
            <th>번호</th>
            <th>아이디</th>
            <th>이름</th>
            <th>나이</th>
            <th>잠금</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in members" :key="item.userid">
            <td>{{ totalCount - ((pageNo - 1) * size + index) }}</td>
            <td>
              <router-link :to="`/member/detail/${item.userid}`" class="text-primary text-decoration-none">
                {{ item.userid }}
              </router-link>
            </td>
            <td>{{ item.name }}</td>
            <td>{{ item.age }}</td>
            <td>
              <span v-if="item.user_lock" class="text-danger fw-bold">🔒 잠김</span>
              <span v-else class="text-success fw-bold">✅ 정상</span>
            </td>
          </tr>
          <tr v-if="members.length === 0">
            <td colspan="5" class="text-muted">검색 결과가 없습니다.</td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 페이지네이션 -->
    <div class="my-4 d-flex justify-content-center gap-2">
      <button
        class="btn btn-outline-secondary"
        :disabled="pageNo === 1"
        @click="changePage(pageNo - 1)"
      >
        ◀ 이전
      </button>
      <button
        class="btn btn-outline-secondary"
        :disabled="pageNo >= totalPage"
        @click="changePage(pageNo + 1)"
      >
        다음 ▶
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const members = ref([])
const searchValue = ref('')
const size = ref(10)
const pageNo = ref(1)
const totalCount = ref(0)
const totalPage = ref(1)

// 더미 API 호출
const fetchMembers = async () => {
  // 👇 여기에 API 요청 (axios 등)으로 교체
  const response = await fetch(`/api/members?pageNo=${pageNo.value}&size=${size.value}&searchValue=${searchValue.value}`)
  const data = await response.json()
  members.value = data.list
  totalCount.value = data.totalCount
  totalPage.value = Math.ceil(data.totalCount / size.value)
}

const searchMembers = () => {
  pageNo.value = 1
  fetchMembers()
}

const changePage = (page) => {
  pageNo.value = page
  fetchMembers()
}

onMounted(() => {
  fetchMembers()
})
</script>

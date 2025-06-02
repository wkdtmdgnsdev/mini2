<template>
  <div class="container mt-5">
    <div class="card shadow">
      <div class="card-header bg-primary text-white text-center">
        <h3 class="mb-0">회원 상세 정보</h3>
      </div>
      <div class="card-body" v-if="member">
        <ul class="list-group list-group-flush mb-4">
          <li class="list-group-item d-flex justify-content-between">
            <strong>아이디</strong>
            <span>{{ member.userid }}</span>
          </li>
          <li class="list-group-item d-flex justify-content-between">
            <strong>이름</strong>
            <span>{{ member.name }}</span>
          </li>
          <li class="list-group-item d-flex justify-content-between">
            <strong>나이</strong>
            <span>{{ member.age }}</span>
          </li>
        </ul>

        <div class="d-grid gap-2 d-md-flex justify-content-md-between">
            <!-- v-if="sessionUser?.userid === member.userid" -->
            <router-link :to="`/member/update/${member.userid}`" class="btn btn-primary">회원 정보 수정</router-link>
            <router-link to="#" class="btn btn-danger" @click.prevent="deleteMember">회원 탈퇴</router-link>

          <!-- <template v-if="isAdmin && member.user_lock === true">
            <router-link :to="`/member/unLock?userid=${member.userid}`" class="btn btn-warning">잠금 해제</router-link>
          </template>

          <template v-if="isAdmin">
            <router-link to="/member/list" class="btn btn-secondary">회원 목록으로</router-link>
          </template> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import router from '@/router'

// ✅ props 받기
const { userid } = defineProps({
  userid: String
})

// ✅ member 초기화
const member = ref(null)

// ✅ 데이터 불러오기
async function fetchMember(userid) {
  try {
    const res = await axios.get(`/api/member/${userid}`)
    member.value = res.data
    console.log('회원 정보:', member.value)
  } catch (err) {
    console.error('회원 조회 실패:', err)
    member.value = null
  }
}

async function deleteMember() {
  if (!confirm('정말 탈퇴하시겠습니까?')) return

  try {
    await axios.delete(`/api/member/${userid}`)
    alert('회원 탈퇴가 완료되었습니다.')
    localStorage.clear()
    router.push('/') // 홈으로 리다이렉트 (또는 로그인 페이지 등)
  } catch (err) {
    console.error('회원 탈퇴 실패:', err)
    alert('회원 탈퇴 중 오류가 발생했습니다.')
  }
}

onMounted(() => {
  fetchMember(userid)
})
</script>
<template>
  <div class="container mt-5">
    <div class="card shadow">
      <div class="card-header bg-primary text-white text-center">
        <h3 class="mb-0">회원 상세 정보</h3>
      </div>
      <div class="card-body">
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
          <template v-if="sessionUser?.userid === member.userid">
            <router-link to="/member/update" class="btn btn-primary">회원 정보 수정</router-link>
            <router-link to="/member/delete" class="btn btn-danger">탈퇴</router-link>
          </template>

          <template v-if="isAdmin && member.user_lock === true">
            <router-link :to="`/member/unLock?userid=${member.userid}`" class="btn btn-warning">잠금 해제</router-link>
          </template>

          <template v-if="isAdmin">
            <router-link to="/member/list" class="btn btn-secondary">회원 목록으로</router-link>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'

const props = defineProps({
  member: {
    type: Object,
    required: true,
  },
  sessionUser: {
    type: Object,
    required: false,
    default: null
  },
  isAdmin: {
    type: Boolean,
    required: false,
    default: false
  }
})
</script>
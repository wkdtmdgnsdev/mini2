<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="/mini">MyWeb</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <template v-if="!member">
            <li class="nav-item">
              <a class="nav-link" href="/mini/member/loginForm">로그인</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/mini/member/registerForm">회원가입</a>
            </li>
          </template>

          <template v-else>
            <li class="nav-item">
              <a class="nav-link" :href="`/mini/member/detail?userid=${member.userid}`">내 정보</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/mini/member/logout">로그아웃</a>
            </li>
          </template>

          <li
            class="nav-item"
            v-if="isAdmin"
          >
            <a class="nav-link" href="/mini/member/list">회원목록</a>
          </li>

          <li class="nav-item">
            <a class="nav-link" href="/mini/board/list">게시판</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  member: {
    type: Object,
    default: null,
  },
  isAdmin: {
    type: Boolean,
    default: false,
  },
});
</script>
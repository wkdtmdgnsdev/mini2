<template>
  <div class="bg-light">
    <div class="container text-center mt-5">
      <h1 class="mb-4">👋 환영합니다!</h1>
      <p class="lead">아래 메뉴를 통해 원하는 기능을 이용해보세요.</p>

      <div class="row justify-content-center mt-4">
        <template v-if="!member">
          <CardBox
            title="🔐 로그인"
            text="회원 로그인 페이지로 이동합니다."
            link="/member/login"
            buttonClass="btn btn-primary"
          />
          <CardBox
            title="📝 회원가입"
            text="새 계정을 만들어보세요."
            link="/member/register"
            buttonClass="btn btn-success"
          />
        </template>

        <template v-else>
          <CardBox
            title="👤 내 정보"
            text="나의 회원 정보를 확인합니다."
            :link="`/member/detail/${member.userid}`"
            buttonClass="btn btn-info"
          />
          <CardBox
            title="🚪 로그아웃"
            text="로그아웃하고 메인 페이지로 돌아갑니다."
            link="/member/logout"
            buttonClass="btn btn-danger"
          />
        </template>

        <template v-if="isAdmin">
          <CardBox
            title="📋 회원목록"
            text="등록된 회원들을 조회할 수 있어요."
            link="/member/list"
            buttonClass="btn btn-warning"
          />
          <CardBox
            title="📝 퀴즈 만들기"
            text="새 퀴즈 문제를 만들어보세요."
            link="/quiz/register"
            buttonClass="btn btn-primary"
          />
        </template>

        <CardBox
          title="🗂 게시판"
          text="게시글 목록을 확인하거나 작성할 수 있어요."
          link="/board/list"
          buttonClass="btn btn-secondary"
        />

        <div class="col-md-4">
          <div class="card shadow-sm mb-4">
            <div class="card-body">
              <h5 class="card-title">🧠 퀴즈</h5>
              <p class="card-text">퀴즈를 풀고 지식을 확인해보세요!</p>
              <button @click="openQuizModal" class="btn btn-secondary">퀴즈 시작</button>
            </div>
          </div>
        </div>
      </div>

      <!-- 모달 -->
      <div
        class="modal fade"
        id="quizModal"
        tabindex="-1"
        aria-labelledby="quizModalLabel"
        aria-hidden="true"
        ref="quizModal"
      >
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content custom-modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="quizModalLabel">퀴즈 문제</h5>
              <button type="button" class="btn-close" @click="closeQuizModal"></button>
            </div>
            <div class="modal-body" id="quizContent">
              <p>여기에 퀴즈 내용이 들어갑니다.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <Footer />
</template>

<script setup>
import { ref } from 'vue';
import CardBox from '@/components/CardBox.vue';
import Footer from '@/components/Footer.vue';

const member = ref(null); // 로그인한 사용자 정보 예시 (null이면 비로그인)
const isAdmin = ref(false); // 관리자 여부
</script>

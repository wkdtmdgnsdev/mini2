<template>
  <div class="bg-light">
    <div class="container text-center mt-5">
      <h1 class="mb-4">👋 환영합니다!</h1>
      <p class="lead">아래 메뉴를 통해 원하는 기능을 이용해보세요.</p>

      <div class="row justify-content-center mt-4">
        <CardBox
          v-if="!userid"
          title="🔐 로그인"
          text="회원 로그인 페이지로 이동합니다."
          link="/member/login"
          buttonClass="btn btn-primary"
        />
        <CardBox
          v-if="!userid"
          title="📝 회원가입"
          text="새 계정을 만들어보세요."
          link="/member/register"
          buttonClass="btn btn-success"
        />
        <CardBox
          v-if="userid"
          title="👤 내 정보"
          text="나의 회원 정보를 확인합니다."
          :link="`/member/detail/${userid}`"
          buttonClass="btn btn-info"
        />
        <CardBox
          v-if="userid"
          title="🚪 로그아웃"
          text="로그아웃하고 메인 페이지로 돌아갑니다."
          buttonClass="btn btn-danger"
          @click="handleLogout"
        />
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
        <CardBox
          title="🗂 게시판"
          text="게시글 목록을 확인하거나 작성할 수 있어요."
          link="/board/list"
          buttonClass="btn btn-secondary"
        />
        <!-- 퀴즈 카드 -->
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

      <!-- 퀴즈 모달 -->
      <div
        class="modal fade"
        id="quizModal"
        tabindex="-1"
        aria-labelledby="quizModalLabel"
        aria-hidden="true"
        ref="quizModalRef"
      >
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content custom-modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="quizModalLabel">퀴즈 문제</h5>
              <button type="button" class="btn-close" @click="closeQuizModal"></button>
            </div>
            <div class="modal-body">
              <div v-if="loading">
                <p>문제를 불러오는 중...</p>
              </div>
              <div v-else>
                <h5 class="mb-3">{{ quiz?.question }}</h5>
                <div v-for="(opt, idx) in quiz?.options" :key="idx">
                  <button
                    class="btn btn-outline-primary w-100 my-2"
                    @click="checkAnswer(idx)"
                    :disabled="answerResult"
                  >
                    {{ idx + 1 }}. {{ opt }}
                  </button>
                </div>
                <div id="answerResult" class="mt-3">
                  <p
                    v-if="answerResult"
                    :class="{
                      'text-success': answerResult.includes('정답'),
                      'text-danger': answerResult.includes('틀렸')
                    }"
                  >
                    {{ answerResult }}
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <Footer />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick, onBeforeUnmount } from 'vue'
import axios from 'axios'
import CardBox from '@/components/CardBox.vue'
import Footer from '@/components/Footer.vue'
import { Modal } from 'bootstrap/dist/js/bootstrap.bundle.min'

// 로그인 상태
const userid = ref(localStorage.getItem('userid'))

function handleLogout() {
  localStorage.removeItem('userid')
  userid.value = null
  location.href = '/'
}

// 퀴즈 관련 상태
const quizModalRef = ref(null)
let quizModalInstance = null

const quiz = ref(null)
const answerResult = ref('')
const loading = ref(false)

// 모달 열기
const openQuizModal = async () => {
  await loadQuiz()
  nextTick(() => {
    if (!quizModalInstance) {
      quizModalInstance = new Modal(quizModalRef.value)
    }
    quizModalInstance.show()
  })
}

// 모달 닫기
const closeQuizModal = () => {
  if (quizModalInstance) quizModalInstance.hide()
}

// 퀴즈 불러오기 (axios 사용)
async function loadQuiz() {
  loading.value = true
  answerResult.value = ''
  try {
    const response = await axios.get('/api/quiz/random')
    console.log('받은 퀴즈:', response.data)
    quiz.value = response.data
  } catch (err) {
    console.error('퀴즈 요청 실패:', err)
    quiz.value = { question: '퀴즈를 불러오는 데 실패했습니다.', options: [], answer: -1 }
  } finally {
    loading.value = false
  }
}

// 보기 선택
function checkAnswer(index) {
  const correctIndex = parseInt(quiz.value.answer)
  if (index === correctIndex) {
    answerResult.value = '✅ 정답입니다! 🎉'
    setTimeout(loadQuiz, 2000)
  } else {
    answerResult.value = `❌ 틀렸어요! 정답: ${quiz.value.options[correctIndex]}`
    setTimeout(loadQuiz, 2000)
  }
}

// 키보드 입력 핸들러
function handleKeyDown(event) {
  const key = event.key
  if (/^[1-4]$/.test(key)) {
    const index = parseInt(key) - 1
    if (!answerResult.value && quiz.value?.options?.length > index) {
      checkAnswer(index)
    }
  }
}

// 이벤트 리스너 등록/해제
onMounted(() => {
  document.addEventListener('keydown', handleKeyDown)
})

onBeforeUnmount(() => {
  document.removeEventListener('keydown', handleKeyDown)
})
</script>

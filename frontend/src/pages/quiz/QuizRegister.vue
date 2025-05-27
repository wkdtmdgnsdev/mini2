<template>
  <div class="container mt-5">
    <h3>🧾 JSON 퀴즈 일괄 등록</h3>
    <p class="text-muted">여러 퀴즈를 JSON 형식으로 입력하면 한 번에 등록됩니다.</p>

    <form @submit.prevent="submitQuizzes">
      <textarea
        v-model="quizJson"
        class="form-control"
        rows="10"
        placeholder='[{"question":"", "options":["", "", "", ""], "answerIndex":1}]'
      ></textarea>
      <button type="submit" class="btn btn-primary mt-3">등록</button>
    </form>

    <div id="result" class="mt-3">
      <div v-if="message" :class="{'text-success': success, 'text-danger': !success}">
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const quizJson = ref('')
const message = ref('')
const success = ref(false)

const submitQuizzes = () => {
  try {
    const parsed = JSON.parse(quizJson.value)

    if (!Array.isArray(parsed)) {
      throw new Error('JSON 배열 형식이 아닙니다.')
    }

    // 간단한 구조 검증 (선택사항)
    parsed.forEach((quiz, i) => {
      if (
        typeof quiz.question !== 'string' ||
        !Array.isArray(quiz.options) ||
        typeof quiz.answerIndex !== 'number'
      ) {
        throw new Error(`${i + 1}번째 퀴즈 형식이 올바르지 않습니다.`)
      }
    })

    // TODO: 실제 API로 전송할 수 있음
    console.log('등록할 퀴즈:', parsed)

    message.value = `총 ${parsed.length}개의 퀴즈를 등록했습니다.`
    success.value = true
  } catch (err) {
    message.value = '❌ 오류: ' + err.message
    success.value = false
  }
}
</script>

<style scoped>
textarea {
  font-family: monospace;
}
</style>

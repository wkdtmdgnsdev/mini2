<template>
  <div class="container mt-5">
    <h2 class="text-center mb-4">📄 게시물 상세보기</h2>

    <div class="card shadow-sm mx-auto" style="max-width: 700px">
      <div class="card-header">
        <h4>
          게시물 제목: <span>{{ board.title }}</span>
        </h4>
      </div>
      <div class="card-body">
        <div class="mb-3">
          <strong>내용:</strong>
          <div class="mt-2 border rounded p-3 bg-light" style="white-space: pre-wrap">
            {{ board.content }}
          </div>
        </div>

        <div class="mb-3"><strong>작성자:</strong> {{ board.writer }}</div>
        <div class="mb-3"><strong>작성일:</strong> {{ board.reg_date }}</div>
        <div class="mb-3"><strong>조회수:</strong> {{ board.view_count }}</div>
      </div>

      <div class="card-footer text-end">
        <button class="btn btn-warning me-2" @click="openModal('update')">수정</button>
        <button class="btn btn-danger me-2" @click="openModal('delete')">삭제</button>
        <router-link to="/board/list" class="btn btn-secondary">목록으로</router-link>
      </div>
    </div>

    <!-- 비밀번호 입력 모달 -->
    <div class="modal fade" id="passwordModal" tabindex="-1" ref="passwordModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">비밀번호 입력</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body">
            <input
              v-model="password"
              type="password"
              class="form-control"
              placeholder="비밀번호 입력"
              required
            />
            <div v-if="passwordError" class="text-danger mt-2">{{ passwordError }}</div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
            <button type="button" class="btn btn-primary" @click="handleConfirmAction">확인</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
// import axios from 'axios'
import { useRoute, useRouter } from 'vue-router'
import { Modal } from 'bootstrap'

const route = useRoute()
const router = useRouter()

const board = ref({})
const password = ref('')
const passwordError = ref('')
const actionType = ref('') // update or delete
const passwordModal = ref(null)
let modalInstance = null

// const fetchBoard = async () => {
//   const { data } = await axios.get(`/api/board/detail/${route.params.bno}`)
//   board.value = data
// }

const openModal = (type) => {
  actionType.value = type
  password.value = ''
  passwordError.value = ''
  modalInstance = new Modal(passwordModal.value)
  modalInstance.show()
}

// const handleConfirmAction = async () => {
//   try {
//     const endpoint =
//       actionType.value === 'update'
//         ? `/api/board/check-update/${board.value.bno}`
//         : `/api/board/delete/${board.value.bno}`

//     const response = await axios.post(endpoint, { password: password.value })

//     if (actionType.value === 'update') {
//       router.push(`/board/update/${board.value.bno}`)
//     } else {
//       alert('삭제되었습니다.')
//       router.push('/board/list')
//     }

//     modalInstance.hide()
//   } catch (err) {
//     passwordError.value = '비밀번호가 틀렸습니다.'
//   }
// }

onMounted(() => {
  // fetchBoard()
})
</script>

<style scoped>
.modal-backdrop.show {
  opacity: 0.5;
}
</style>

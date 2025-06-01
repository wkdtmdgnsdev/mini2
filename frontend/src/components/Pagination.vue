<template>
  <nav aria-label="Page navigation" class="d-flex justify-content-center mt-3">
    <ul class="pagination">

      <!-- 이전 버튼 -->
      <li class="page-item" :class="{ disabled: !pageResponse.prev }">
        <button 
          class="page-link" 
          :disabled="!pageResponse.prev" 
          @click="movePage(pageResponse.startPage - 1)" 
          aria-label="Previous"
        >
          <span aria-hidden="true">&laquo;</span>
        </button>
      </li>

      <!-- 페이지 번호 -->
      <li 
        v-for="pageNo in pageNumbers" 
        :key="pageNo" 
        class="page-item" 
        :class="{ active: pageNo === pageResponse.pageNo }"
      >
        <button class="page-link" @click="movePage(pageNo)">
          {{ pageNo }}
        </button>
      </li>

      <!-- 다음 버튼 -->
      <li class="page-item" :class="{ disabled: !pageResponse.next }">
        <button 
          class="page-link" 
          :disabled="!pageResponse.next" 
          @click="movePage(pageResponse.endPage + 1)" 
          aria-label="Next"
        >
          <span aria-hidden="true">&raquo;</span>
        </button>
      </li>

    </ul>
  </nav>

  <!-- 현재 페이지 정보 -->
  <div class="text-center mt-3">
    <p class="text-muted">
      <span class="fw-bold">현재 페이지:</span> 
      <span class="fs-5 text-primary">{{ pageResponse.pageNo }}</span> / 
      <span class="fs-5 text-primary">{{ pageResponse.totalPage }}</span>
    </p>
  </div>
</template>

<script setup>
import { computed, defineEmits, defineProps } from 'vue';

const props = defineProps({
  pageResponse: {
    type: Object,
    required: true,
    // 예시: {
    //   prev: Boolean,
    //   next: Boolean,
    //   startPage: Number,
    //   endPage: Number,
    //   pageNo: Number,
    //   totalPage: Number
    // }
  }
});

const emit = defineEmits(['page-move']);

const pageNumbers = computed(() => {
  const pages = [];
  for(let i = props.pageResponse.startPage; i <= props.pageResponse.endPage; i++) {
    pages.push(i);
  }
  return pages;
});

function movePage(pageNo) {
  if (pageNo < 1 || pageNo > props.pageResponse.totalPage) return;
  emit('page-move', pageNo);
}
</script>

<style scoped>
.page-item.disabled > button {
  cursor: not-allowed;
}
</style>

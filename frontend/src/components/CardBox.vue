<template>
  <div class="col-md-4">
    <div class="card shadow-sm mb-4">
      <div class="card-body">
        <h5 class="card-title">{{ title }}</h5>
        <p class="card-text">{{ text }}</p>
        <router-link :to="link" :class="buttonClass">이동</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  title: String,
  text: String,
  link: {
    type: [String, Object],
    required: true,
  },
  buttonClass: {
    type: String,
    default: 'btn btn-primary',
  },
});
</script>

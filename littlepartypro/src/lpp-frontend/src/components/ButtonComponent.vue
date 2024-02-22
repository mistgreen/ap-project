<template>
  <a href="#"
     class="inline-flex items-center px-3 py-2 text-sm font-medium text-center
      text-white bg-lpp-third rounded-lg hover:bg-lpp-four focus:ring-4 focus:outline-none
      focus:ring-lpp-four" data-qa-button-component>
  {{text}}
  </a>
</template>

<script setup>
import {defineProps} from 'vue'

defineProps({
  text: String
})
</script>

<template>
  <div
      v-show="modalActive"
      class="absolute w-full bg-black bg-opacity-30 h-screen top-0 left-0 flex justify-center px-8">
    <div
        v-show="modalActive"
        class="p-4 bg-lpp-primary  self-start mt-32 max-w-screen-md">
      <i class="fa-solid fa-xmark hover:text-lpp-third cursor-pointer" @click="$emit('close-modal') "></i>
      <slot />
      <h1>Page Title {{pageTitle}}</h1>
      <h2>Page details</h2>

    </div>
  </div>
</template>

<script setup>
import  { defineProps, defineEmits } from 'vue';

defineEmits(['close-modal'])
defineProps({
  modalActive: {
    type: Boolean,
    default: false,
  },
  pageTitle: String

})


</script>


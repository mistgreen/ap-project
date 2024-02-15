<template>
  <div
      v-show="modalActive"
      class="absolute w-full bg-black bg-opacity-30 h-screen top-0 left-0 flex justify-center px-8">
    <div
        v-show="modalActive"
        class="p-4 bg-lpp-primary self-start mt-32 max-w-screen-md">
      <i class="fa-solid fa-xmark hover:text-lpp-third cursor-pointer" @click="$emit('close-modal') "></i>
      <slot />
      <h1> {{ vendor.title }}</h1>
      <img src="../assets/LittlePartyPro.png"/>
      <h2>{{ vendor.longDesc}}</h2>

        <ButtonComponent @click="goToSite(vendor)">
          View Website</ButtonComponent>


    </div>
  </div>


</template>

<script setup>
import  { defineProps, defineEmits } from 'vue';
import ButtonComponent from '@/components/ButtonComponent.vue'

defineEmits(['close-modal'])
defineProps({
  modalActive: {
    type: Boolean,
    default: false,
  },
  vendor: Object
})

function goToSite(vendor) {
  window.open(vendor.url)
}

</script>


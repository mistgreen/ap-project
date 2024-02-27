<template>
  <div
      v-show="modalActive"
      class="absolute w-full bg-black bg-opacity-30 h-screen top-0 left-0 flex justify-center px-8"
      data-qa-base-modal
  >
    <div class="p-4 bg-lpp-primary self-start mt-32 max-w-screen-md">
      <button type="button" class="fa-solid fa-xmark hover:text-lpp-third cursor-pointer" @click="$emit('close-modal')" data-qa-base-modal-button></button>
      <h1 data-qa-base-modal-title> {{ vendor.title }}</h1>
      <img :src="getImageUrl(vendor.id)" alt="vendor image" data-qa-base-modal-img/>
      <h2>{{ vendor.longDesc }}</h2>

      <ButtonComponent text="View Website" @click="goToSite(vendor)"/>


    </div>
  </div>


</template>

<script setup>
import {defineEmits, defineProps} from 'vue'
import ButtonComponent from '@/components/ButtonComponent.vue'

defineEmits(['close-modal'])
defineProps({
  modalActive: {
    type: Boolean,
    default: false,
  },
  vendor: Object,
})

function goToSite(vendor) {
  window.open(vendor.url)
}

function getImageUrl(vendor) {
  let imageUrl

  try {
    imageUrl = require(`../assets/Vendors/${vendor}.jpeg`)
  } catch (error) {
    console.error(`Image for vendor ${vendor} not found. Using default image.`)
    imageUrl = require('../assets/LittlePartyPro.png')
  }
  return imageUrl
}

</script>


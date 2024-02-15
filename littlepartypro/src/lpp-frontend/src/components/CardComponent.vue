<template>
  <div class="bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700" >
      <img class="rounded-t-lg" src="../assets/LittlePartyPro.png" alt="" />
    <div class="p-5">
        <h5 class="mb-2 font-bold text-xl sm:text-l tracking-tight font-Arimo text-gray-900 dark:text-white">{{ vendor.title.toUpperCase() }}</h5>
      <p class="mb-3 font-normal text-gray-700 text-Lato dark:text-gray-400">{{ vendor.shortDesc }}.</p>
    <div class="flex">
      <ButtonComponent class="self-end" @click="toggleModal()">
        View
        <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
          <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
        </svg>
      </ButtonComponent>
    </div>



    </div>
  </div>

  <BaseModal v-if="modalActive"
      :modalActive="modalActive"
      @close-modal="toggleModal"
             :vendor="vendor"
  >

  </BaseModal>
</template>

<script setup>
  import {defineProps, ref} from 'vue'
  import BaseModal from '@/components/BaseModal.vue'
  import ButtonComponent from '@/components/ButtonComponent.vue'
  const modalActive = ref(false);

  defineProps({
    vendor: Object
  });

  const toggleModal = () => {
    modalActive.value = !modalActive.value;
  };
</script>


<template>
  <div
      v-show="modalActive"

      class="fixed z-50 inset-0 flex items-center justify-center bg-black bg-opacity-50 w-full h-screen">
    <div
        v-show="modalActive"
        class="p-4 bg-lpp-primary self-start mt-32 max-w-screen-md">
      <i class="fa-solid fa-xmark hover:text-lpp-third cursor-pointer" @click="$emit('close-modal') "></i>
      <h3 class="text-lg font-medium text-gray-900" id="modal-title">{{ method === 'login' ? 'Login' : 'Register' }}</h3>
      <form>
        <label for="username">Username</label>
        <input type="text" id="username" v-model="username" :placeholder="'username'" required/>
        <label for="password">Password</label>
        <i class="fas" :class="[passwordFieldIcon]" @click="hidePassword = !hidePassword"></i>
        <input :type="passwordFieldType" id="password" v-model="password" :placeholder="'password'" required/>
      </form>

      <ButtonComponent text="Submit" @click="$emit('perform-action', method, username, password)"/>


      <div v-if="loginResponseStatus === 200">
        <p>{{ method === 'login' ? 'Login successful!' : 'Registration successful!' }}</p>
      </div>
      <div v-if="loginResponseStatus === 401 || loginResponseStatus === 500">
        <p class="text-red-400">{{ method === 'login' ? 'Login failed' : 'Registration failed' }}</p>
      </div>

    </div>
  </div>
<!--  class="absolute w-full bg-black bg-opacity-30 h-screen top-0 left-0 flex justify-center px-8"-->

</template>

<script setup>
import {defineProps, defineEmits, defineModel, computed, ref} from 'vue'
import ButtonComponent from '@/components/ButtonComponent.vue'
const username = defineModel('username')
const password = defineModel('password')
const hidePassword = ref(true);

const passwordFieldIcon = computed(() => hidePassword.value ? "fa-eye" : "fa-eye-slash");
const passwordFieldType = computed(() => hidePassword.value ? "password" : "text");

defineEmits(['close-modal'], 'perform-action')
defineProps({
  modalActive: {
    type: Boolean,
    default: false,
  },
  method: String,
  loginResponseStatus: Number,
  loginResponseMessage: String
})


</script>


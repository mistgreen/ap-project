<template>
  <div
      v-show="modalActive"

      class="fixed z-50 inset-0 flex items-center justify-center bg-black bg-opacity-50 w-full h-screen"
      data-qa-login-component>
    <div
        v-show="modalActive"
        class="p-4 bg-lpp-primary self-start mt-32 max-w-screen-md">
      <button type="button" class="fa-solid fa-xmark hover:text-lpp-third cursor-pointer" @click="$emit('close-modal') " data-qa-login-component-button></button>
      <h3 class="text-lg font-medium text-gray-900" id="modal-title" data-qa-login-component-login-message>{{ method === 'login' ? 'Login' : 'Register' }}</h3>
      <form>
        <label for="username">Username</label>
        <input type="text" id="username" v-model="username" :placeholder="'username'" required/>
        <label for="password">Password</label>
        <i class="fas" :class="[passwordFieldIcon]" @click="hidePassword = !hidePassword"></i>
        <input :type="passwordFieldType" id="password" v-model="password" :placeholder="'password'" required/>
      </form>

      <ButtonComponent text="Submit" @click="$emit('perform-action', method, username, password)" data-qa-login-component-submit-button/>


      <div v-if="loginResponseStatus === 200">
        <p data-qa-login-component-success-response-message>{{ method === 'login' ? 'Login successful!' : 'Registration successful!' }}</p>
      </div>
      <div v-if="loginResponseStatus === 401 || loginResponseStatus === 500">
        <p class="text-red-400" data-qa-login-component-failure-response-message>{{ method === 'login' ? 'Login failed' : 'Registration failed' }}</p>
      </div>
    </div>
  </div>

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


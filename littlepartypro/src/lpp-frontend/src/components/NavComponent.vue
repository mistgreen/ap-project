<template>
  <header class="sticky top-0 bg-lpp-primary shadow-lg" data-qa-nav-component>
    <nav class="gap-4 py-6">
      <div class="flex justify-end">
        <ButtonComponent text="Login" class="m-1 min-w-16 max-w-16 flex justify-center" @click="openLoginModal()" v-if="!loginSuccessful" data-qa-nav-component-login-button/>
        <ButtonComponent text="Register" class="m-1 min-w-16 max-w-16 flex justify-center" @click="openRegisterModal()" v-if="!loginSuccessful" data-qa-nav-component-register-button/>
        <ButtonComponent text="Logout" class="m-1 min-w-16 max-w-16 flex justify-center" @click="toggleLogin()" v-if="loginSuccessful" data-qa-nav-component-logout-button/>
      </div>
      <div>
        <div class="flex justify-center gap-3">
          <a href="http://localhost:3000" class="font-Bellaboo text-6xl md:text-8xl text-lpp-text-primary hover:text-lpp-third cursor-pointer" data-qa-nav-component-title>Little Party Pro</a>
        </div>
      </div>
      <div v-if="loggedInUsername !== undefined" data-qa-nav-component-logged-in-message>
        <p>Welcome, {{ loggedInUsername }}!</p>
      </div>
    </nav>
  </header>

  <LoginComponent v-if="modalActive"
                  :modalActive="modalActive"
                  @close-modal="toggleModal"
                  :method="modalMethod"
                  @perform-action="performAction"
                  :login-response-status="loginResponseStatus"
                  :login-response-message="loginResponseMessage"
  />
</template>

<script setup>


import ButtonComponent from '@/components/ButtonComponent.vue'
import LoginComponent from '@/components/LoginComponent.vue'
import {defineProps, ref, watchEffect} from 'vue'

const modalActive = ref(false)
const modalMethod = ref()

const loginResponseStatus = ref()
const loggedInUsername = ref()
const loginSuccessful = ref(false)
const loginResponseMessage = ref('')

defineProps({
  modalMethod: String,
  loginResponseStatus: Number,
  loggedInUsername: String,
  loginResponseMessage: String,
})
const toggleModal = () => {
  modalActive.value = false
}
const toggleLogin = () => {
  loginSuccessful.value = false
  loggedInUsername.value = undefined
}

const openLoginModal = () => {
  modalMethod.value = 'login'
  modalActive.value = true
}

const openRegisterModal = () => {
  modalMethod.value = 'register'
  modalActive.value = true
}

function performAction(endpoint, username, password) {
  // const pattern = /([A-Za-z0-9])\w+/;
  // searchQuery.value = {
  //   "searchQuery": searchString
  // };

  fetch(`api/auth/${endpoint}`, {
    method: 'POST',
    body: JSON.stringify({username, password}),
    headers: {
      'Content-Type': 'application/json',
    },
  }).then(response => {
    if (!response.ok) {
      loginResponseStatus.value = response.status
      throw new Error(`HTTP error! Status: ${response.status}`)
    }
    loggedInUsername.value = username
    loginSuccessful.value = true

    toggleModal()

    return response.text()
  }).catch(error => {
    console.error('Error:', error)
  })
}

watchEffect(() => {
  if (!modalActive.value) {
    loginResponseStatus.value = null
  }
})
</script>



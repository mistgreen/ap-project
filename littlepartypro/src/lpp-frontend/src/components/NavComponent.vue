<template>
  <header class="sticky top-0 bg-lpp-primary shadow-lg">
    <nav class="gap-4 py-6">

      <div class="flex justify-end">
        <ButtonComponent class="m-1 min-w-16 max-w-16 flex justify-center" @click="openLoginModal()" v-if="!loginSuccessful">
          Login
        </ButtonComponent>
        <ButtonComponent class="m-1 min-w-16 max-w-16 flex justify-center" @click="openRegisterModal()" v-if="!loginSuccessful">
          Register
        </ButtonComponent>
        <ButtonComponent class="m-1 min-w-16 max-w-16 flex justify-center" @click="toggleLogin()" v-if="loginSuccessful">
          Logout
        </ButtonComponent>
      </div>
      <div>
        <div class="flex justify-center gap-3">
          <a href="http://localhost:3000" class="font-Bellaboo text-6xl md:text-8xl text-lpp-text-primary hover:text-lpp-third cursor-pointer">Little Party Pro</a>
        </div>
      </div>
      <div v-if="loggedInUsername !== undefined">
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
  >

  </LoginComponent>
</template>

<script setup>


import ButtonComponent from '@/components/ButtonComponent.vue'
import LoginComponent from '@/components/LoginComponent.vue'
import {defineProps, ref, watchEffect} from 'vue'

const modalActive = ref(false)
const modalMethod = ref()
const queryTimeout = ref(null)
const loginResponseStatus = ref()
const loggedInUsername = ref()
const loginSuccessful = ref(false)
const loginResponseMessage = ref('')

defineProps({
  modalMethod: String,
  loginResponseStatus: Number,
  loggedInUsername: String,
  loginResponseMessage: String
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

function performAction(method, username, password) {
  // const pattern = /([A-Za-z0-9])\w+/;
  // searchQuery.value = {
  //   "searchQuery": searchString
  // };
  const user = {username, password}

  queryTimeout.value = setTimeout(() => {
    const endpoint = method === 'login' ? 'login' : 'register'
    fetch(`api/auth/${endpoint}`, {
      method: 'POST',
      body: JSON.stringify(user),
      headers: {
        'Content-Type': 'application/json',
      },
    }).then(response => {
      loginResponseStatus.value = response.status;
      console.log(loginResponseStatus.value)
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`)
      }
      return response.text()
    }).then(data => {
      loggedInUsername.value = username
      loginSuccessful.value = true
      setTimeout(() => {
        toggleModal()
      }, 2000)
      console.log(`${method} successful:` + data)
    }).catch(error => {
      console.error('Error:', error)
    })
  })
}

  watchEffect(() => {
    if (!modalActive.value) {
      loginResponseStatus.value = null
    }
  })
</script>



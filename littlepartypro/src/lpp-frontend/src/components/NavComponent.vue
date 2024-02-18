<template>
    <header class="sticky top-0 bg-lpp-primary shadow-lg">
      <nav class="gap-4 py-6">
        <RouterLink to="{name: 'home'}">
          <div class="flex justify-end">
            <ButtonComponent class="m-1 min-w-16 max-w-16 flex justify-center" @click="openLoginModal()">
              Login
            </ButtonComponent>
            <ButtonComponent class="m-1 min-w-16 max-w-16 flex justify-center" @click="openRegisterModal()">
              Register
            </ButtonComponent>
          </div>
          <div>
            <div class="flex justify-center gap-3">
              <a href="http://localhost:3000" class="font-Bellaboo text-6xl md:text-8xl text-lpp-text-primary hover:text-lpp-third cursor-pointer">Little Party Pro</a>
            </div>
          </div>
        </RouterLink>

<!--          <i class="fa-regular fa-user text-xl text-lpp-text-primary hover:text-lpp-third cursor-pointer"></i>-->


        <!--      <a href="http://localhost:3000" class="text-md hover:text-lpp-text-primary">Home</a>-->
        <!--      <p>Venues</p>-->
        <!--      <p>Suppliers</p>-->
        <!--      <p>Inspo</p>-->
      </nav>

    </header>

  <LoginComponent v-if="modalActive"
             :modalActive="modalActive"
             @close-modal="toggleModal"
                  :method="modalMethod"
                  @perform-action="performAction"
  >

  </LoginComponent>
</template>

<script setup>


import ButtonComponent from '@/components/ButtonComponent.vue'
import LoginComponent from '@/components/LoginComponent.vue'
import {defineProps, ref} from 'vue'

const modalActive = ref(false);
const modalMethod = ref();
const queryTimeout = ref(null);

defineProps({
  modalMethod: String
});
const toggleModal = () => {
  modalActive.value = false;
};

const openLoginModal = () => {
  modalMethod.value = 'login';
  modalActive.value = true;
};

const openRegisterModal = () => {
  modalMethod.value = 'register';
  modalActive.value = true;
};

function performAction(method, username, password) {
  // const pattern = /([A-Za-z0-9])\w+/;
  // searchQuery.value = {
  //   "searchQuery": searchString
  // };
  const user = {
    username: username,
    password: password
  }

  queryTimeout.value = setTimeout(() => {
    if (method === "login") {
      console.log(JSON.stringify(user));
      fetch(`api/auth/login`, {
        method: 'POST',
        body: JSON.stringify(user),
        headers: {
          'Content-Type': 'application/json'
        }
      }).then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.text();
      }).then(data => {
        console.log("login successful:" + data);
      }).catch(error => {
        console.error('Error:', error);
      });
    }
    else if(method === "register"){
      fetch(`api/auth/register`, {
        method: 'POST',
        body: JSON.stringify(user),
        headers: {
          'Content-Type': 'application/json'
        }
      }).then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      }).then(data => {
        console.log("registration successful:" + data);
      }).catch(error => {
        console.error('Error:', error);
      });
    }
  }, 300);
}
</script>



<template>
  <div class="max-w-2xl mx-auto" :class="{ 'z-0': modalActive }" data-qa-search-component>
    <form class="flex items-center" @submit.prevent="handleSubmit">
      <label for="simple-search" class="sr-only">Search Little Party Pro</label>
      <div class="relative w-full">
        <div class="flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none">
          <svg class="w-5 h-5 text-gray-500 dark:text-gray-400" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z" clip-rule="evenodd"></path>
          </svg>
        </div>
        <input
            v-model="searchString"
            type="text"
            id="simple-search"
            @input="lazySearch"
            class="border border-gray-300 text-gray-900 text-sm rounded-lg focus:outline-lpp-four focus:ring-lpp-four focus:border-lpp-third block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-lpp-third dark:focus:border-lpp-third"
            placeholder="Search Little Party Pro"
            required
        />
        <ul class="absolute text-black bg-white w-full shadow-md top-[66]" v-if="searchResult">
          <li v-for="vendor in searchResult" :key="vendor.id" class="py-2 cursor-pointer" @click="$emit('perform-search', searchString)"> {{vendor.title}}</li>
        </ul>
      </div>
      <button
          class="p-2.5 ml-2 text-sm font-medium text-white bg-lpp-third rounded-lg border border-lpp-third hover:bg-lpp-four hover:border-lpp-four focus:ring-4 focus:outline-none focus:ring-lpp-four dark:bg-lpp-four dark:hover:bg-lpp-four dark:focus:ring-lpp-four"
          @click="$emit('perform-search', searchString)">
        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
        </svg>
      </button>
    </form>
  </div>
</template>

<script setup>
import {defineEmits, ref, defineProps} from 'vue'

defineEmits('perform-search')

const searchString = ref("")
const searchResult = ref([]);
const queryTimeout = ref(null);

defineProps({
  modalActive: Boolean
});

function lazySearch() {
  const pattern = /([A-Za-z0-9])\w+/;
  clearTimeout(queryTimeout.value);

    queryTimeout.value = setTimeout(() => {
      if (pattern.test(searchString.value)) {
        fetch(`api/vendor/filter/search`, {
          method: 'POST',
          body: JSON.stringify({ "searchQuery": searchString.value }),
          headers: {
            'Content-Type': 'application/json'
          }
        }).then(response => {
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response.json();
        }).then(data => {
          searchResult.value = data.map(vendor => vendor);
        }).catch(error => {
          console.error('Error:', error);
        });
      }
    }, 300);
  }


</script>

<style scoped>

</style>
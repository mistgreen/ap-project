<template>
  <div class="grid items-stretch min-h-full grid-cols-4 rounded-lg bg-lpp-primary" data-qa-category-component>
    <div class="flex justify-center" v-for="category in categories" :key="category">
      <div class="flex flex-col justify-center max-w-sm p-4">
        <img class="h-12 w-12 sm:h-24 sm:w-24 md:h-24 md:w-24 hover:cursor-pointer" @click="$emit('category-filter',category)" :src="getImageUrl(category)" alt="Category Image" data-qa-category-component-image/>
        <p class="text-center" data-qa-category-component-category>{{ category }}</p>
      </div>
    </div>
  </div>

</template>

<script setup>
//
import {defineEmits, defineProps} from 'vue'
const categories = ["VENUE", "DECORATOR", "CATERER", "MISC"];

defineEmits('category-filter')

defineProps({
  vendors: []
})

function getImageUrl(category) {
  return require(`../assets/Icons/${category}.svg`);
}

</script>



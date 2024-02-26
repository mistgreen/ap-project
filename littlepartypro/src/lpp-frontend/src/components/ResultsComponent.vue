<template>
  <div class="px-4 pb-4" data-qa-results-component>
    <SearchComponent
        @perform-search="performSearch"
        @select-vendor="performSearch"
        data-qa-results-component-search-component/>
  </div>
  <CategoryComponent
    @category-filter="getVendorType"
    data-qa-results-component-category-component
  />
<div class="min-h-60">
  <div>
    <div v-if="!vendors.length" class="bg-lpp-primary text-center p-4" data-qa-results-component-vendor-no-results-message>
      <h1>Sorry, we couldn't find anything for {{searchQuery.searchQuery}}.</h1>
      <h1>Hint: You can search by town (location) or by the company name.</h1>
    </div>

    <div v-else>
        <div class="px-5" data-qa-results-component-vendor-results>
          <p data-qa-results-component-vendor-results-message>Showing {{vendors.length}} results.</p>
        </div>
      <div class="grid justify-items-center items-stretch grid-cols-1 sm:grid-cols-3 md:grid-cols-4 ">
        <div class="flex max-w-xs p-4" v-for="vendor in vendors" :key="vendor.id">
          <CardComponent :vendor="vendor" data-qa-results-component-vendor-results-card/>
        </div>
      </div>
    </div>
  </div>
</div>

</template>

<script setup>
import CategoryComponent from '@/components/CategoryComponent.vue'
import CardComponent from '@/components/CardComponent.vue'
import {onMounted, ref, } from 'vue'
import SearchComponent from '@/components/SearchComponent.vue'

const vendors = ref([]);
const filteredVendors = ref([]);
const selectedCategory = ref(null);
const searchQuery = ref({});

onMounted(() => {
  getAllVendors();
})

function getAllVendors() {
  fetch("api/vendor" )
  .then((response) => response.json())
  .then((data) => {
    vendors.value = data;
    filteredVendors.value = data;
  });
}

function getVendorType(category) {
  selectedCategory.value = category;
  fetch(`api/vendor/filter/vendorType/${category}`)
  .then((response) => {
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return response.json();
  })
  .then((data) => {
    vendors.value = data.map((vendor) => vendor);
  }).catch(error => {
    console.error('Error:', error);
  })
  ;
}

function performSearch(searchString) {
  const pattern = /([A-Za-z0-9])\w+/;
  searchQuery.value = {
    "searchQuery": searchString
  };

    if (pattern.test(searchString)) {
      fetch(`api/vendor/filter/search`, {
        method: 'POST',
        body: JSON.stringify(searchQuery.value),
        headers: {
          'Content-Type': 'application/json'
        }
      }).then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        console.log(response);
        return response.json();
      }).then(data => {
        vendors.value = data.map(vendor => vendor);
      }).catch(error => {
        console.error('Error:', error);
      });
    }
}


</script>

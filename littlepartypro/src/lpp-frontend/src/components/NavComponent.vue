<template>
    <header class="sticky top-0 bg-lpp-primary shadow-lg">
      <nav class="flex gap-4 py-6 justify-center">
        <RouterLink to="{name: 'home'}">
          <div>
            <div class="gap-3">
              <a href="http://localhost:3000" class="font-Bellaboo text-5xl text-lpp-text-primary hover:text-lpp-third cursor-pointer">Little Party Pro</a>
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
</template>

<script>

export default {
  name: 'NavComponent',
  setup(){
  },
 created(){
 },
  mounted() {
  }
}
</script>



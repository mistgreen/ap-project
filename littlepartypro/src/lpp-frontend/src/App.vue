<template>
  <div class=" bg-hero-pattern bg-lpp-primary">
    <div class="sticky flex flex-col font-Lato bg-lpp-primary">
      <div>
        <NavComponent/>
      </div>
      <HeaderComponent />
      <ResultsComponent />
    </div>
    <div>
      <FooterComponent/>
    </div>
  </div>

</template>

<script setup>
import NavComponent from '@/components/NavComponent.vue'
import HeaderComponent from '@/components/HeaderComponent.vue'
import ResultsComponent from '@/components/ResultsComponent.vue'
import FooterComponent from '@/components/FooterComponent.vue'



</script>

<style>

@font-face {
  font-family: "Bellaboo";
  src: url("./assets/bellaboo-1.ttf") format('truetype');
}
@font-face {
  font-family: "Lato";
  src: url("./assets/Lato-Regular.ttf") format('truetype');
}
@font-face {
  font-family: "Arimo";
  src: url("./assets/Arimo-VariableFont_wght.ttf") format('truetype');
}
</style>


<template>
  <div id="app">
    <Navbar />
    <main class="content-card container mt-4">
      <router-view />
    </main>
  </div>
</template>

<script>
import Navbar from './components/Navbar.vue'
export default { components: { Navbar } }
</script>

<style>
#app { margin: 0; padding: 0; }
</style>

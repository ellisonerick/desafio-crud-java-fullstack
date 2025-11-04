<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
      <div class="container">
        <a class="navbar-brand" href="#">CRUD de Pessoas</a>
        <div class="navbar-nav">
          <router-link to="/" class="nav-link">Listar</router-link>
          <router-link to="/cadastrar" class="nav-link">Cadastrar</router-link>
        </div>
      </div>
    </nav>

    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

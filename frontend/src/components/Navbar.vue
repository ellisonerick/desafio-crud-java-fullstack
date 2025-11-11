<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid d-flex justify-content-between align-items-center">
      <div class="d-flex align-items-center">
        <router-link class="navbar-brand fw-bold" to="/">Nexum</router-link>
        <span class="navbar-text text-white fs-5 ms-3">Cadastro de Pessoas</span>
      </div>

      <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/">Listar</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/cadastrar">Cadastrar</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped>
.navbar { box-shadow: 0 2px 6px rgba(0,0,0,0.3); }
</style>

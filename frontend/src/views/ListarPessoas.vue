<template>
  <div class="container mt-4">
    <h2 class="text-center mb-4">Lista de Pessoas</h2>

    <!-- Botão para cadastrar -->
    <div class="d-flex justify-content-end mb-3">
      <router-link to="/cadastrar" class="btn btn-success">
        + Nova Pessoa
      </router-link>
    </div>

    <!-- Tabela -->
    <table class="table table-striped table-bordered">
      <thead class="table-dark">
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>CPF/CNPJ</th>
          <th>Telefone</th>
          <th>E-mail</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="pessoa in pessoas" :key="pessoa.id">
          <td>{{ pessoa.id }}</td>
          <td>{{ pessoa.nome }}</td>
          <td>{{ pessoa.cpfCnpj }}</td>
          <td>{{ pessoa.telefone || '-' }}</td>
          <td>{{ pessoa.email || '-' }}</td>
          <td>
            <router-link
              :to="`/editar/${pessoa.id}`"
              class="btn btn-warning btn-sm me-2"
            >
              Editar
            </router-link>
            <button
              class="btn btn-danger btn-sm"
              @click="excluirPessoa(pessoa.id)"
            >
              Excluir
            </button>
          </td>
        </tr>
        <tr v-if="pessoas.length === 0">
          <td colspan="6" class="text-center">Nenhuma pessoa cadastrada.</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import api from '../services/api'

export default {
  name: 'ListarPessoas',
  data() {
    return {
      pessoas: []
    }
  },
  mounted() {
    this.carregarPessoas()
  },
  methods: {
    async carregarPessoas() {
      try {
        const response = await api.get('/pessoas')
        this.pessoas = response.data
      } catch (error) {
        console.error('Erro ao carregar pessoas:', error)
      }
    },
    async excluirPessoa(id) {
      if (confirm('Tem certeza que deseja excluir esta pessoa?')) {
        try {
          await api.delete(`/pessoas/${id}`)
          this.carregarPessoas()
        } catch (error) {
          console.error('Erro ao excluir pessoa:', error)
        }
      }
    }
  }
}
</script>

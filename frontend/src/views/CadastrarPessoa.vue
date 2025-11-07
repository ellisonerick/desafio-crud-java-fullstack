<template>
  <div class="container mt-4">
    <h2 class="text-center mb-4">Cadastrar Pessoa</h2>

    <AlertMessage
      v-if="alerta.message"
      :type="alerta.type"
      :message="alerta.message"
    />

    <form @submit.prevent="cadastrarPessoa" class="col-md-6 offset-md-3">
      <div class="mb-3">
        <label for="nome" class="form-label">Nome Completo</label>
        <input type="text" id="nome" v-model="pessoa.nome" class="form-control" required />
      </div>

      <div class="mb-3">
        <label for="cpfCnpj" class="form-label">CPF/CNPJ</label>
        <input type="text" id="cpfCnpj" v-model="pessoa.cpfCnpj" class="form-control" required />
      </div>

      <div class="mb-3">
        <label for="telefone" class="form-label">Telefone</label>
        <input type="text" id="telefone" v-model="pessoa.telefone" class="form-control" />
      </div>

      <div class="mb-3">
        <label for="email" class="form-label">E-mail</label>
        <input type="email" id="email" v-model="pessoa.email" class="form-control" />
      </div>

      <div class="d-flex justify-content-between">
        <router-link to="/" class="btn btn-secondary">Voltar</router-link>
        <button type="submit" class="btn btn-primary">Salvar</button>
      </div>
    </form>
  </div>
</template>

<script>
import api from '../services/api'
import AlertMessage from '../components/AlertMessage.vue'

export default {
  name: 'CadastrarPessoa',
  components: { AlertMessage },
  data() {
    return {
      pessoa: {
        nome: '',
        cpfCnpj: '',
        telefone: '',
        email: ''
      },
      alerta: {
        message: '',
        type: ''
      }
    }
  },
  methods: {
    async cadastrarPessoa() {
      try {
        await api.post('/pessoas', this.pessoa)
        this.alerta = { message: 'Pessoa cadastrada com sucesso!', type: 'success' }
        setTimeout(() => this.$router.push('/'), 1500)
      } catch (error) {
        console.error('Erro ao cadastrar pessoa:', error)
        this.alerta = { message: 'Erro ao cadastrar pessoa!', type: 'error' }
      }
    }
  }
}
</script>

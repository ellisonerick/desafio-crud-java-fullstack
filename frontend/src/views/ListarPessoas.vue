<template>
  <div class="container mt-4">
    <h2 class="text-center mb-4">Lista de Pessoas</h2>

    <!-- Alerta -->
    <AlertMessage
      v-if="alerta.message"
      :type="alerta.type"
      :message="alerta.message"
    />

    <!-- Botão para cadastrar -->
    <div class="d-flex justify-content-end mb-3">
      <router-link to="/cadastrar" class="btn btn-success">
        + Nova Pessoa
      </router-link>
    </div>

    <!-- Tabela -->
    <table class="table table-striped table-bordered align-middle">
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
              @click="abrirModal(pessoa)"
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

    <!-- Modal de confirmação -->
    <div
      class="modal fade"
      id="confirmarExclusaoModal"
      tabindex="-1"
      aria-labelledby="confirmarExclusaoLabel"
      aria-hidden="true"
      ref="modal"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header bg-danger text-white">
            <h5 class="modal-title" id="confirmarExclusaoLabel">
              Confirmar Exclusão
            </h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            Tem certeza que deseja excluir
            <strong>{{ pessoaSelecionada?.nome }}</strong>?
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Cancelar
            </button>
            <button
              type="button"
              class="btn btn-danger"
              @click="confirmarExclusao"
            >
              Confirmar
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../services/api'
import { Modal } from 'bootstrap'
import AlertMessage from '../components/AlertMessage.vue'

export default {
  name: 'ListarPessoas',
  components: { AlertMessage },
  data() {
    return {
      pessoas: [],
      pessoaSelecionada: null,
      modalInstance: null,
      alerta: {
        message: '',
        type: ''
      }
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
    abrirModal(pessoa) {
      this.pessoaSelecionada = pessoa
      const modalElement = this.$refs.modal
      this.modalInstance = new Modal(modalElement)
      this.modalInstance.show()
    },
    async confirmarExclusao() {
      try {
        await api.delete(`/pessoas/${this.pessoaSelecionada.id}`)
        this.modalInstance.hide()
        this.alerta = { message: 'Pessoa excluída com sucesso!', type: 'success' }
        this.carregarPessoas()
      } catch (error) {
        console.error('Erro ao excluir pessoa:', error)
        this.alerta = { message: 'Erro ao excluir pessoa!', type: 'error' }
      }
    }
  }
}
</script>

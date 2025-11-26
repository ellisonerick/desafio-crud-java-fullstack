<template>
  <div class="container mt-4">
    <h2 class="text-center mb-4">Editar Pessoa</h2>

    <AlertMessage
      v-if="alerta.message"
      :type="alerta.type"
      :message="alerta.message"
    />

    <form @submit.prevent="atualizarPessoa" class="col-md-6 offset-md-3">
      <div class="mb-3">
        <label for="nome" class="form-label">Nome Completo</label>
        <input
          type="text"
          id="nome"
          v-model="pessoa.nome"
          class="form-control"
          required
        />
      </div>

      <div class="mb-3">
        <label for="cpfCnpj" class="form-label">CPF/CNPJ</label>
        <input
          type="text"
          id="cpfCnpj"
          v-model="pessoa.cpfCnpj"
          class="form-control"
          @input="pessoa.cpfCnpj = formatarCpfCnpj(pessoa.cpfCnpj)"
          maxlength="18"
          required
        />
      </div>

      <div class="mb-3">
        <label for="telefone" class="form-label">Telefone</label>
        <input
          type="text"
          id="telefone"
          v-model="pessoa.telefone"
          class="form-control"
          @input="pessoa.telefone = formatarTelefone(pessoa.telefone)"
          maxlength="15"
        />
      </div>

      <div class="mb-3">
        <label for="email" class="form-label">E-mail</label>
        <input
          type="email"
          id="email"
          v-model="pessoa.email"
          class="form-control"
          placeholder="exemplo@email.com"
        />
      </div>

      <div class="d-flex justify-content-between">
        <router-link to="/" class="btn btn-secondary">Voltar</router-link>
        <button type="submit" class="btn btn-primary">Salvar Alterações</button>
      </div>
    </form>
  </div>
</template>

<script>
import api from "../services/api";
import AlertMessage from "../components/AlertMessage.vue";

export default {
  name: "EditarPessoa",
  components: { AlertMessage },

  data() {
    return {
      pessoa: {
        nome: "",
        cpfCnpj: "",
        telefone: "",
        email: "",
      },
      alerta: {
        message: "",
        type: "",
      },
    };
  },

  async mounted() {
    const id = this.$route.params.id;

    try {
      const response = await api.get(`/pessoas/${id}`);

      // Aplica máscaras automaticamente ao carregar os dados
      this.pessoa = {
        ...response.data,
        cpfCnpj: this.formatarCpfCnpj(response.data.cpfCnpj),
        telefone: this.formatarTelefone(response.data.telefone),
      };
    } catch (error) {
      console.error("Erro ao carregar pessoa:", error);
      this.alerta = {
        message: "Erro ao carregar dados da pessoa!",
        type: "error",
      };
    }
  },

  methods: {
    // -------------------------
    // MÁSCARA CPF / CNPJ
    // -------------------------
    formatarCpfCnpj(valor) {
      if (!valor) return "";
      valor = valor.replace(/\D/g, "");

      // CPF
      if (valor.length <= 11) {
        return valor
          .replace(/(\d{3})(\d)/, "$1.$2")
          .replace(/(\d{3})(\d)/, "$1.$2")
          .replace(/(\d{3})(\d{1,2})$/, "$1-$2");
      }

      // CNPJ
      return valor
        .replace(/^(\d{2})(\d)/, "$1.$2")
        .replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3")
        .replace(/\.(\d{3})(\d)/, ".$1/$2")
        .replace(/(\d{4})(\d)/, "$1-$2");
    },

    // -------------------------
    // MÁSCARA TELEFONE
    // -------------------------
    formatarTelefone(valor) {
      if (!valor) return "";
      valor = valor.replace(/\D/g, "");

      return valor
        .replace(/^(\d{2})(\d)/, "($1) $2")
        .replace(/(\d{5})(\d)/, "$1-$2")
        .substring(0, 15);
    },

    async atualizarPessoa() {
      const id = this.$route.params.id;

      try {
        await api.put(`/pessoas/${id}`, {
          ...this.pessoa,
          cpfCnpj: this.pessoa.cpfCnpj.replace(/\D/g, ""),
          telefone: this.pessoa.telefone.replace(/\D/g, ""),
        });

        this.alerta = {
          message: "Pessoa atualizada com sucesso!",
          type: "success",
        };

        setTimeout(() => this.$router.push("/"), 1500);
      } catch (error) {
        console.error("Erro ao atualizar pessoa:", error);
        this.alerta = {
          message: "Erro ao atualizar pessoa!",
          type: "error",
        };
      }
    },
  },
};
</script>

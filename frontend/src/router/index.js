import { createRouter, createWebHistory } from 'vue-router'
import ListarPessoas from '../views/ListarPessoas.vue'
import CadastrarPessoa from '../views/CadastrarPessoa.vue'
import EditarPessoa from '../views/EditarPessoa.vue'

const routes = [
  {
    path: '/',
    name: 'ListarPessoas',
    component: ListarPessoas
  },
  {
    path: '/cadastrar',
    name: 'CadastrarPessoa',
    component: CadastrarPessoa
  },
  {
    path: '/editar/:id',
    name: 'EditarPessoa',
    component: EditarPessoa,
    props: true
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router

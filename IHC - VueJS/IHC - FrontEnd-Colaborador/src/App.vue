<template>
  <div id="app">
    <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">COLABORADOR</a>
      </div>
    </nav>

    <div class="container">
      <ul>
        <li v-for="(erro, index) of errors" :key="index">
          campo <b>{{ erro.field }}</b> - {{ erro.defaultMessage }}
        </li>
      </ul>

      <form @submit.prevent="salvar">
        <label>CPF</label>
        <input type="number" placeholder="CPF" v-model="colaborador.cpf" />
        <label>Nome</label>
        <input type="text" placeholder="Nome" v-model="colaborador.nome" />
        <label>E-mail</label>
        <input type="text" placeholder="Email" v-model="colaborador.email" />
        <label>Perfil de Acesso</label>
        <input
          type="text"
          placeholder="Perfil"
          v-model="colaborador.perfilAcessoId"
        />
        <label>Cracha</label>
        <input
          type="number"
          placeholder="Cracha"
          v-model="colaborador.crachaId"
        />

        <button class="waves-effect waves-light btn-small">
          Salvar<i class="material-icons left">save</i>
        </button>
      </form>

      <table>
        <thead>
          <tr>
            <th>CPF</th>
            <th>NOME</th>
            <th>E-MAIL</th>
            <th>PERFIL DE ACESSO</th>
            <th>CRACHA</th>
            <th>OPÇÕES</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="colaborador of colaborador" :key="colaborador.id">
            <td>{{ colaborador.cpf }}</td>
            <td>{{ colaborador.nome }}</td>
            <td>{{ colaborador.email }}</td>
            <td>{{ colaborador.perfilAcessoId }}</td>
            <td>{{ colaborador.crachaId }}</td>
            <td>
              <button
                @click="editar(colaborador)"
                class="waves-effect btn-small blue darken-1"
              >
                <i class="material-icons">create</i>
              </button>
              <button
                @click="remover(colaborador)"
                class="waves-effect btn-small red darken-1"
              >
                <i class="material-icons">delete_sweep</i>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import Colaborador from "./services/colaborador";

export default {
  name: "app",
  data() {
    return {
      colaborador: {
        id: 0,
        cpf: 0,
        nome: "",
        email: "",
        perfilAcessoId: "",
        crachaId: 0,
      },
      colaborador: [],
      errors: [],
    };
  },
  mounted() {
    this.listar();
  },
  methods: {
    listar() {
      Colaborador.listar()
        .then((resposta) => {
          this.colaborador = resposta.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    salvar() {
      console.log(this.colaborador);
      if (!this.colaborador.id) {
        var colaborador2 = {
          ativo:"true",
          cpf: this.colaborador.cpf,
          email: this.colaborador.email,
          perfilAcessoId: this.colaborador.perfilAcessoId,
          nome: this.colaborador.nome,
          crachaId: this.colaborador.crachaId,
        };
        console.log(colaborador2);
        Colaborador.salvar(colaborador2)
          .then((resposta) => {
            this.colaborador = {};
            alert("Cadastrado com sucesso!");
            this.listar();
            this.errors = {};
          })
          .catch((e) => {
            this.errors = e.response.data.errors;
          });
      } else {
        Colaborador.atualizar(this.colaborador)
          .then((resposta) => {
            this.colaborador = {};
            this.errors = {};
            alert("Atualizado com sucesso!");
            this.listar();
          })
          .catch((e) => {
            this.errors = e.response.data.errors;
          });
      }
    },

    editar(colaborador) {
      this.colaborador = colaborador;
    },

    remover(colaborador) {
      this.colaborador = colaborador;
      if (confirm("Deseja excluir o colaborador?")) {
        Colaborador.apagar(this.colaborador.id)
          .then((resposta) => {
            this.listar();
            this.errors = {};
          })
          .catch((e) => {
            this.errors = e.response.data.errors;
          });
      }
    },
  },
};
</script>

<style></style>

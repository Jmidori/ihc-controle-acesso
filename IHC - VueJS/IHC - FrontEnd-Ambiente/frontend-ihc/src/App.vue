<template>
  <div id="app">
    <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">AMBIENTE</a>
      </div>
    </nav>

    <div class="container">
      <ul>
        <li v-for="(erro, index) of errors" :key="index">
          campo <b>{{ erro.field }}</b> - {{ erro.defaultMessage }}
        </li>
      </ul>

      <form @submit.prevent="salvar">
        <label>Nome</label>
        <input type="text" placeholder="Nome" v-model="ambiente.nome" />
        <label>Descrição</label>
        <input
          type="text"
          placeholder="Descrição"
          v-model="ambiente.descricao"
        />
        <label>Nivel de Acesso</label>
        <input
          type="number"
          placeholder="Nível"
          v-model="ambiente.nivelAcesso"
        />
        <label>Sentido</label>
        <input
          type="text"
          placeholder="Sentido"
          v-model="ambiente.sentidoFluxo"
        />

        <button class="waves-effect waves-light btn-small">
          Salvar<i class="material-icons left">save</i>
        </button>
      </form>

      <table>
        <thead>
          <tr>
            <th>NOME</th>
            <th>DESCRIÇÃO</th>
            <th>NIVEL DE ACESSO</th>
            <th>SENTIDO</th>
            <th>OPÇÕES</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="ambiente of ambiente" :key="ambiente.id">
            <td>{{ ambiente.nome }}</td>
            <td>{{ ambiente.descricao }}</td>
            <td>{{ ambiente.nivelAcesso }}</td>
            <td>{{ ambiente.sentidoFluxo }}</td>
            <td>
              <button
                @click="editar(ambiente)"
                class="waves-effect btn-small blue darken-1"
              >
                <i class="material-icons">create</i>
              </button>
              <button
                @click="remover(ambiente)"
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
import Ambiente from "./services/ambiente";

export default {
  name: "app",
  data() {
    return {
      ambiente: {
        id: 0,
        nome: "",
        descricao: "",
        nivelAcesso: 0,
        sentidoFluxo: "",
      },
      ambientes: [],
      errors: [],
    };
  },
  mounted() {
    this.listar();
  },
  methods: {
    listar() {
      Ambiente.listar()
        .then((resposta) => {
          this.ambiente = resposta.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    salvar() {
      console.log(this.ambiente);
      if (!this.ambiente.id) {
        var ambiente2 = {
          descricao: this.ambiente.descricao,
          nivelAcesso: this.ambiente.nivelAcesso,
          nome: this.ambiente.nome,
          sentidoFluxo: this.ambiente.sentidoFluxo,
        };
        console.log(ambiente2);
        Ambiente.salvar(ambiente2)
          .then((resposta) => {
            this.ambiente = {};
            alert("Cadastrado com sucesso!");
            this.listar();
            this.errors = {};
          })
          .catch((e) => {
            this.errors = e.response.data.errors;
          });
      } else {
        Ambiente.atualizar(this.ambiente)
          .then((resposta) => {
            this.ambiente = {};
            this.errors = {};
            alert("Atualizado com sucesso!");
            this.listar();
          })
          .catch((e) => {
            this.errors = e.response.data.errors;
          });
      }
    },

    editar(ambiente) {
      this.ambiente = ambiente;
    },

    remover(ambiente) {
      this.ambiente = ambiente;
      if (confirm("Deseja excluir o ambiente?")) {
        Ambiente.apagar(this.ambiente.id)
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
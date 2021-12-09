import { http } from "./config";

export default {
  salvar: (colaborador2) => {
    return http.post("/colaborador/novo", colaborador2);
  },

  listar: () => {
    return http.get("/colaborador");
  },

  atualizar: (colaborador) => {
    return http.put("/colaborador", colaborador);
  },

  apagar: (id) => {
    return http.delete("/colaborador/" + id);
  },
};

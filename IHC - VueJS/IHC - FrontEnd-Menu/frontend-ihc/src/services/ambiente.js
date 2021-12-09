import { http } from "./config";

export default {
  salvar: (ambiente2) => {
    return http.post("/ambiente/novo", ambiente2);
  },

  listar: () => {
    return http.get("/ambiente");
  },

  atualizar: (ambiente) => {
    return http.put("/ambiente", ambiente);
  },

  apagar: (id) => {
    return http.delete("/ambiente/" + id);
  },
};

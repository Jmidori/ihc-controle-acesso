package com.sga.backoffice.entities.enumerators;

public enum NIVEL_ACESSO {
    GERAL (1),
    RESTRITO (2),
    MUITO_RESTRITO (3);

    public final int codigo;

    NIVEL_ACESSO(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}

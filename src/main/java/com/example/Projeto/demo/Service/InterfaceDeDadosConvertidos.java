package com.example.Projeto.demo.Service;

import java.util.List;

public interface InterfaceDeDadosConvertidos {

    // Usando a classe generica <T>, nao precisamos atribuir nenhum tipo para a nossa funcao, por enquanto
    <T> T getDados(String json, Class<T> classe);

    <T> List<T> getList(String json, Class<T>classe);
}

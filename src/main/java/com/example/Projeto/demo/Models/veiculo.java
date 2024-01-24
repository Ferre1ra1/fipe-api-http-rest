package com.example.Projeto.demo.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
{

    "Valor": "R$ 108.412,00",
    "Marca": "Fiat",
    "Modelo": "500 ABARTH MULTIAIR 1.4 TB 16V 3p",
    "AnoModelo": 2015,

}
*/

@JsonIgnoreProperties(ignoreUnknown = true)
public record veiculo(@JsonAlias("Valor") String valor,
                      @JsonAlias("Marca") String marca,
                      @JsonAlias("Modelo") String modelo,
                      @JsonAlias("AnoModelo") int anoModelo
                      ) {
}

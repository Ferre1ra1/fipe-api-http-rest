package com.example.Projeto.demo.Principal;

import com.example.Projeto.demo.Models.Dados;
import com.example.Projeto.demo.Models.Modelos;
import com.example.Projeto.demo.Models.veiculo;
import com.example.Projeto.demo.Service.ConsumindoAPI;
import com.example.Projeto.demo.Service.ConversaoDeDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private Scanner leia= new Scanner(System.in);
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumindoAPI consumo = new ConsumindoAPI();

    private ConversaoDeDados conversor = new ConversaoDeDados();

    public void exibeMenu(){
        var menu = """
                  OPÇÕES
                  
                  - Carro
                  - Moto
                  - Caminhão
                        
                Escolha uma das opções: 
                """;


        /* Puxando a marca do carro */

        System.out.println(menu);
        var opcao = leia.nextLine();
        String endereco;
        if(opcao.toLowerCase().contains("carr")){
            endereco = URL_BASE + "carros/marcas";
        }else if (opcao.toLowerCase().contains("mot")){
            endereco = URL_BASE + "motos/marcas";
        }else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

         var json = consumo.getDados(endereco);
        System.out.println(json);
        var marcas = conversor.getList(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        /* Puxando os modelos do veiculo */

        System.out.println("\nInforme o codigo que desejar: ");
         var codigoMarca = leia.nextLine();
         endereco = endereco +"/"+codigoMarca+"/modelos";
         json = consumo.getDados(endereco);
         var modeloList = conversor.getDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modeloList.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);


        /*Puxando valores dos veiculos*/

        System.out.println("\n Digite o nome do veiculo: ");
        var nomeVeiculo = leia.nextLine();

        List<Dados> modeloFilter = modeloList.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase())) // Filtra o nome do veiculo de acordo com o nome que o user mandou no input (tudo em letra minuscula)
                .collect(Collectors.toList()); // Irá coletar os dados do filter e enviar como uma lista

        System.out.println("\nModelos filtrados\n");
        modeloFilter.forEach(System.out::println);


        /*Buscando valores e avaliacoes*/

        System.out.println("\nDigite o código do modelo para busca: ");
        var codigoModel = leia.nextLine(); // Puxando o código do modelo para localizar os valores e avaliacoes

        endereco = endereco + "/" + codigoModel + "/anos"; // puxando toda a url passada anteriormente + codigo mandado pelo user + o ano do modelo
        json = consumo.getDados(endereco); // obtendo os dados
        List<Dados> anos = conversor.getList(json, Dados.class); // convertendo eles para uma lista
        List<veiculo> veiculosList = new ArrayList<>();

        for(int i = 0; i < anos.size(); i++){
            var enderecoAnos = endereco + "/" + anos.get(i).codigo(); // Armazena o endereco da url + o código de cada indice vindo da api
            json = consumo.getDados(enderecoAnos); // Obtém os dados de endereco
            veiculo veiculo = conversor.getDados(json, veiculo.class); // converte os dados obtidos
            veiculosList.add(veiculo); // Adiciona os veiculos encontrados
        }

        System.out.println("\n Todos os veiculos filtrados: \n");
        veiculosList.forEach(System.out::println);


    }

}

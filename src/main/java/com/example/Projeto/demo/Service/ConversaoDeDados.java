package com.example.Projeto.demo.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConversaoDeDados implements InterfaceDeDadosConvertidos{

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T getDados(String json, Class<T> classe) {
        try{
            return mapper.readValue(json, classe);
        }catch(JsonProcessingException e ){
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> getList( String json, Class<T> classe) {

        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);

        try {
            return mapper.readValue(String.valueOf(json), lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

package br.com.upboxserver.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario {

    @Id
    private ObjectId id;

    private UUID uuid;
    private String nome;
    private String email;
    private String username;
    private String senha;
    private Set<BasicDBObject> arquivosCompartilhados = new HashSet<>();

    public Set<BasicDBObject> getArquivosCompartilhados() {
        return arquivosCompartilhados;
    }

    public void setArquivosCompartilhados(Set<BasicDBObject> arquivosCompartilhados) {
        this.arquivosCompartilhados = arquivosCompartilhados;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario criarId() {
        this.id = new ObjectId();
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}

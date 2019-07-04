package br.com.upboxserver.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private List<org.bson.Document> arquivosCompartilhados = new ArrayList<>();

    public List<org.bson.Document> getArquivosCompartilhados() {
        return arquivosCompartilhados;
    }

    public void setArquivosCompartilhados(List<org.bson.Document> arquivosCompartilhados) {
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

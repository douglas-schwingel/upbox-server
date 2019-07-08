package br.com.upboxserver.user.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
public class User {

    @Id
    private ObjectId id;

    private UUID uuid;
    private String name;
    private String email;
    private String username;
    private String password;
    private List<org.bson.Document> sharedWithMe = new ArrayList<>();
    private List<org.bson.Document> myShares = new ArrayList<>();

    public List<org.bson.Document> getMyShares() {
        return myShares;
    }

    public void setMyShares(List<org.bson.Document> myShares) {
        this.myShares = myShares;
    }

    public List<org.bson.Document> getSharedWithMe() {
        return sharedWithMe;
    }

    public void setSharedWithMe(List<org.bson.Document> sharedWithMe) {
        this.sharedWithMe = sharedWithMe;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}

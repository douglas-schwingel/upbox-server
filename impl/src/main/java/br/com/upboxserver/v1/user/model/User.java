package br.com.upboxserver.v1.user.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
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

}

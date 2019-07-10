package br.com.upboxserver.v1.user.model;

import br.com.upboxserver.v1.file.model.MyShare;
import br.com.upboxserver.v1.file.model.SharedWithMe;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ToString(onlyExplicitlyIncluded = true)
@Data
@Document
@ApiModel
public class User {

    @ToString.Include @EqualsAndHashCode.Exclude
    @ApiModelProperty(value = "_id", example = "5d238b32bae0507c65f6cca0", required = false)
    @Id
    private String id;

    @ApiModelProperty(value = "uuid", example = "f5abe5ea-a829-4fd6-ab3c-9cb0367513bd", required = true)
    private UUID uuid;

    @ToString.Include
    @ApiModelProperty(value = "name", example = "João da Silva", required = true)
    private String name;

    @ApiModelProperty(value = "email", example = "silvajoao@technocorp.com", required = true)
    private String email;

    @ToString.Include
    @ApiModelProperty(value = "username", example = "silvajoao", required = true)
    private String username;

    @ApiModelProperty(value = "password", example = "$2a$10$y2/1nGljMhYf0bIACzYV8OwikzOO.8XBS1uNpPbYlOXxnzPveDE4a", required = true)
    private String password;

    private List<SharedWithMe> sharedWithMe = new ArrayList<>();
    private List<MyShare> myShares = new ArrayList<>();

}

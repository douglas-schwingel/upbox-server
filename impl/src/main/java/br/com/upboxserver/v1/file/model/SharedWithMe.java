package br.com.upboxserver.v1.file.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SharedWithMe {

    @ApiModelProperty(value = "owner", example = "silvajoao", required = true)
    private String owner;

    @ApiModelProperty(value = "fileName", example = "example.txt", required = true)
    private String fileName;

}

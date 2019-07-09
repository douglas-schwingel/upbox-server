package br.com.upboxserver.v1.file.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class MyShare {

    @ApiModelProperty(value = "reciever", example = "dschwingel", required = true)
    private String reciever;

    @ApiModelProperty(value = "fileName", example = "example.txt", required = true)
    private String fileName;

}

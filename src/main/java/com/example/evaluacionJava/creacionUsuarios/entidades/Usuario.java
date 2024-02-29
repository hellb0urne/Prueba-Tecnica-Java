package com.example.evaluacionJava.creacionUsuarios.entidades;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@ApiModel(description = "Modelo de Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ApiModelProperty(example = "Roberto")
    private String name;
    @ApiModelProperty(example = "devwizard93@gmail.com")
    private String email;
    @ApiModelProperty(example = "ContraseñaSegura123")
    private String password;

    @ApiModelProperty(example = "[{\"number\":\"+56964460485\",\"3460000\":\"01\",\"countrycode\":\"+1\"}]")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "usuario_id")
    private List<Telefono> phones;

    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean isActive;



}


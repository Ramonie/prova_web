package com.example.prova_web.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date deleted;


    private String imageUri;

    @NotEmpty(message = "O nome da comida é obrigatório")
    private String nome;

    @NotEmpty(message = "A descrição da comida é obrigatória")
    private String descricao;

    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
    private double preco;

    @Min(value = 0, message = "A quantidade deve ser maior ou igual a zero")
    private int quantidade;

    public void Comida() {
    }

    public void Comida(long id, Date deleted, String imageUri, String nome, String descricao, double preco,
            int quantidade) {
        this.id = id;
        this.deleted = deleted;
        this.imageUri = imageUri;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}

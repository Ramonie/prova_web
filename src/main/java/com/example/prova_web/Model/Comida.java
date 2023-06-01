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
//1) Crie a classe do modelo
//conforme o tema escolhido, lembre-se que você precisa adicionar pelo menos 7 atributos (ID,
//Deleted, ImageUri). Adicione as restrições (validações) do modelo.
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

}

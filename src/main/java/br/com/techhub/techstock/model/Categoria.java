package br.com.techhub.techstock.model;

import com.fasterxml.jackson.annotation.JsonValue;

import br.com.techhub.techstock.controller.requests.CategoriaRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categoria")
@EqualsAndHashCode(of = {
    "id"
}, callSuper = true)
public class Categoria extends BaseModel {

    @Id
    @JsonValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 300, nullable = true)
    private String descricao;

    /**
     * @param id
     */
    public Categoria(Long id) {
        super();
        this.id = id;
    }

    /**
     * @param request
     */
    public Categoria(CategoriaRequest request) {
        super();
        this.id = request.getId();
        this.nome = request.getNome();
        this.descricao = request.getDescricao();
    }

}

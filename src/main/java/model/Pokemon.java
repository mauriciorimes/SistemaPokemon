package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mauri
 */

@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String nomePokemon;
    private String tipo;
    private String numeroPokedex;

    public Pokemon() {
    }

    public Pokemon(String nomePokemon, String tipo, String numeroPokedex) {
        this.nomePokemon = nomePokemon;
        this.tipo = tipo;
        this.numeroPokedex = numeroPokedex;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNomePokemon() {
        return nomePokemon;
    }

    public void setNomePokemon(String nomePokemon) {
        this.nomePokemon = nomePokemon;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumeroPokedex() {
        return numeroPokedex;
    }

    public void setNumeroPokedex(String numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

}

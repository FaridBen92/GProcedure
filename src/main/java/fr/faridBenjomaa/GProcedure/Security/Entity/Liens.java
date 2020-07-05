package fr.faridBenjomaa.GProcedure.Security.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Liens")
public class Liens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String lien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Liens liens = (Liens) o;
        return id == liens.id &&
                Objects.equals(lien, liens.lien) &&
                Objects.equals(titre, liens.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lien, titre);
    }

}

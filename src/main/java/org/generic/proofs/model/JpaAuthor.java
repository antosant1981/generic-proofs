package org.generic.proofs.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
public class JpaAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "authors")   // inverse side
    private Set<JpaBook> books = new HashSet<>();

    /*  Constructors  */
    public JpaAuthor() {}
    public JpaAuthor(String name)  { this.name = name; }
    public JpaAuthor(String name, Set<JpaBook> books) {
        this.name = name;
        this.books = books;
    }

    /*  Getters & setters  */
    public Long getId()          { return id; }
    public String getName()      { return name; }
    public void setName(String n){ this.name = n; }
    public Set<JpaBook> getBooks()           { return books; }
    public void setBooks(Set<JpaBook> b)     { this.books = b; }

    /* equals & hashCode based on id */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaAuthor)) return false;
        return id != null && id.equals(((JpaAuthor) o).id);
    }

    @Override
    public int hashCode() { return getClass().hashCode(); }
}

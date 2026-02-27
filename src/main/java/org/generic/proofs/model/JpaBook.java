package org.generic.proofs.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class JpaBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    // Many‑to‑many with Author via book_author
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<JpaAuthor> authors = new HashSet<>();

    /*  Constructors  */
    public JpaBook() {}
    public JpaBook(String title)     { this.title = title; }
    public JpaBook(String title, Set<JpaAuthor> authors) {
        this.title = title;
        this.authors = authors;
    }

    /*  Getters & setters  */
    public Long getId()           { return id; }
    public String getTitle()      { return title; }
    public void setTitle(String t){ this.title = t; }
    public Set<JpaAuthor> getAuthors()           { return authors; }
    public void setAuthors(Set<JpaAuthor> a)     { this.authors = a; }

    /* equals & hashCode based on id */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaBook)) return false;
        return id != null && id.equals(((JpaBook) o).id);
    }

    @Override
    public int hashCode() { return getClass().hashCode(); }
}

package org.generic.proofs.repository;

import org.generic.proofs.model.JpaAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends JpaRepository<JpaAuthor, String>, PagingAndSortingRepository<JpaAuthor, String>, JpaSpecificationExecutor<JpaAuthor> {}

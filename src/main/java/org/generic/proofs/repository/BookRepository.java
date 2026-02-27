package org.generic.proofs.repository;

import org.generic.proofs.model.JpaBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends JpaRepository<JpaBook, String>, PagingAndSortingRepository<JpaBook, String>, JpaSpecificationExecutor<JpaBook> {}

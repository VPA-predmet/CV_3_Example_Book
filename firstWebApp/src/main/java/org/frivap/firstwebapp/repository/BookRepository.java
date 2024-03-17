package org.frivap.firstwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.frivap.firstwebapp.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}


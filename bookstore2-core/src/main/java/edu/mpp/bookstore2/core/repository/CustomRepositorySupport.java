package edu.mpp.bookstore2.core.repository;

import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Getter
public abstract class CustomRepositorySupport {
    @PersistenceContext
    private EntityManager entityManager;
}

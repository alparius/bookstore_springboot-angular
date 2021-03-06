package edu.mpp.bookstore2.core.repository;

import edu.mpp.bookstore2.core.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@NoRepositoryBean
@Transactional
public interface MyJpaRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T,ID> {
}

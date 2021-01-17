package ro.ubb.lab6.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.ubb.lab6.core.model.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface BikeShopRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {

}

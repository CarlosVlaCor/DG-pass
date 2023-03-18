package com.dgpass.repository;

import com.dgpass.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long>{

    public Rol findById(long id);

}

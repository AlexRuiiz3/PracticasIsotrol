package com.isotrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isotrol.entity.Producto;

//@Repository Solo ponerlo cuando lo implementes a mano 
public interface ProductoDAO extends JpaRepository<Producto, Long> {

}

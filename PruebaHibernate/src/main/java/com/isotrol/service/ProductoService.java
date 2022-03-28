package com.isotrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isotrol.entity.Producto;
import com.isotrol.repository.ProductoDAO;

@Service
public class ProductoService {

	@Autowired 
	private ProductoDAO productoDao;
	
	public void guardarProducto(Producto producto) {
		productoDao.save(producto);
	}
	
	public Producto obtenerProducto(int id) {
		return productoDao.findById((long) id).orElseThrow();
	}
	
	public List<Producto> obtenerProductos() {
		return productoDao.findAll();
	}
}

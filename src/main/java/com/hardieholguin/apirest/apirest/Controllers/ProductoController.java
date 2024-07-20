package com.hardieholguin.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardieholguin.apirest.apirest.Repositories.ProductoRepository;
import com.hardieholguin.apirest.apirest.Entities.Producto;

@RestController
@RequestMapping("/productos")

public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerPorProductobyId(@PathVariable long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The ID does not exist" + id));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable long id, @RequestBody Producto producto_details) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The ID does not exist" + id));
        producto.setNombre(producto_details.getNombre());
        producto.setPrecio(producto_details.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The ID does not exist" + id));
        productoRepository.delete(producto);
        return "The id product: " + id + " has been deleted successfully";

    }

}

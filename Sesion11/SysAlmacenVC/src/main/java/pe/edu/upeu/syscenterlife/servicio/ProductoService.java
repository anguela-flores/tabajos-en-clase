package pe.edu.upeu.syscenterlife.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.syscenterlife.autocomp.ModeloDataAutocomplet;
import pe.edu.upeu.syscenterlife.modelo.Producto;
import pe.edu.upeu.syscenterlife.modelo.repositorio.ProductoRepository;
import pe.edu.upeu.syscenterlife.util.ErrorLogger;
import static pe.edu.upeu.syscenterlife.util.ErrorLogger.log;

@Service
public class ProductoService {
    
    ErrorLogger log=new ErrorLogger ("clienteService.class");

    @Autowired
    private ProductoRepository productoRepository;

    // Método para guardar un nuevo producto
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Método para listar todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Método para actualizar un producto existente
    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Método para eliminar un producto por su ID
    public void eliminarProductoPorId(Integer id) {
        productoRepository.deleteById(id);
    }

    // Método para buscar un producto por su ID
    public Producto buscarProductoPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    // Método para buscar productos por nombre utilizando una consulta SQL nativa
    public List<Producto> buscarProductosPorNombre(String nombre) {
        return productoRepository.findProductosByNombreNative(nombre);
    }

    public List<ModeloDataAutocomplet> listAutoCompletProducto(String nombre) {
        List<ModeloDataAutocomplet> listarProducto = new ArrayList<>();

        try {
            for (Producto producto : productoRepository.listAutoCompletProducto(nombre + "%")) {
                ModeloDataAutocomplet data = new ModeloDataAutocomplet();
                ModeloDataAutocomplet.TIPE_DISPLAY = "ID";
                data.setIdx(producto.getNombre());
                data.setNombreDysplay(String.valueOf(producto.getIdProducto()));
                data.setOtherData(producto.getPu() + ":" + producto.getStock());
                listarProducto.add(data);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "create", e);
        }

        return listarProducto;
    }

}

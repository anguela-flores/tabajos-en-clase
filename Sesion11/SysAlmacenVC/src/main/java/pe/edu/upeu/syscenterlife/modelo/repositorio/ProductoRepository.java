package pe.edu.upeu.syscenterlife.modelo.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.syscenterlife.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Método para encontrar productos por su nombre usando una consulta SQL nativa
    @Query(value = "SELECT * FROM Producto WHERE nombre = :nombre", nativeQuery = true)
    List<Producto> findProductosByNombreNative(@Param("nombre") String nombre);

    @Query(value = "SELECT p.* FROM Producto p WHERE p.nombre like :filter", nativeQuery = true)
    List<Producto> listAutoCompletProducto(@Param("filter") String filter);
}

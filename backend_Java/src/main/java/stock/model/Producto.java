package stock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data@NoArgsConstructor
@AllArgsConstructor

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precio;
    private int stockActual;
    private int stockMinimo;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}
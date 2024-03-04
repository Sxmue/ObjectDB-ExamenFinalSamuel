package Models;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Clase que representa un cliente en la base de datos
 */
@Data
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long totalSales;

    private String state;

    public Client() {
        totalSales = 0L;
        state = "active";
    }

    public Client( String name, Long totalSales, String state) {
        this.name = name;
        this.totalSales = totalSales;
        this.state = state;
    }
}

package dao;

import Models.Client;

/**
 * Interfaz del DAO de la entidad cliente
 */
public interface ClientDaoI {

    /** metodo para insertar un cliente en la bbdd */
    void insertarCliente(Client client);

    /** metodo para obtener un cliente de la bbdd */
    void getCliente(Long id);

    /** Metodo para mostrar los clientes que han comprado mas que la cantidad proporcionada*/
    void listarMejoresClientes(Long quantity);

    /** Muestra las estadisticas de todos los clientes */
    void estadisticas();


}
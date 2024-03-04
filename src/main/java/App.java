import Models.Client;
import dao.ClientDaoImpl;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        ClientDaoImpl clientDao = new ClientDaoImpl();

        //Empezamos creando los 5 clientes

        Client one = new Client("Pepe Rodriguez", 1399L,"activo");
        Client two = new Client("Samuel Leiva", 1250L,"inactivo");
        Client three = new Client("Jose Leiva",1500L, "activo");
        Client four = new Client("Roberto Carlos", 1200L, "activo");
        Client five = new Client("Lionel Messi", 2000L, "inactivo");

        ArrayList<Client> clients = new ArrayList<>();

        clients.add(one);

        clients.add(two);

        clients.add(three);

        clients.add(four);

        clients.add(five);


        //Vamos ahora a añadirlos a la Database

        for ( Client c : clients){

            clientDao.insertarCliente(c);

        }


        //Ahora que los tenemos insertados vamos a crear probar el metodo de coger clientes

        //Vamos a coger dos clientes

        System.out.println("Vamos a coger 2 clientes");

        clientDao.getCliente(1L);
        clientDao.getCliente(2L);

        //Vamos a listar ahora a los mejores clientes segun la cantidad que pongamos

        System.out.println("Vamos a coger los clientes que hayan hecho 1250 ventas o más!! de ahi parriba y ADEMAS esten activos");
        clientDao.listarMejoresClientes(1250L);


        //Vamos a probar ahora El metodo de las estadisticas

        System.out.println("Las estadísticas son: ");
        clientDao.estadisticas();

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.*;
import java.awt.Robot;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Farid Estepa
 */
public class Registro {

    public static void limpiarPantalla() {
        try {
            Robot robbie = new Robot();
            robbie.keyPress(17);
            robbie.keyPress(76);
            robbie.keyRelease(17);
            robbie.keyRelease(76);

            Thread.sleep(100);

            robbie.keyPress(17);
            robbie.keyPress(16);
            robbie.keyPress(82);
            robbie.keyRelease(17);
            robbie.keyRelease(16);
            robbie.keyRelease(82);
            Thread.sleep(100);
        } catch (Exception ex) {
        }

    }
    public static Sala elegirSala(Sala[] salas, int hora){
        for (int i = 0; i < salas.length; i++) {
            for (int j = 0; j < salas[i].getFunciones().size(); j++) {
                if(salas[i].getFunciones().get(j).getHora()!= hora){
                    return salas[i];
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        ArrayList<Usuario> arreglo_empleados = new ArrayList<>();
        ArrayList<Usuario> arreglo_clientes = new ArrayList<>();
        ArrayList<Pelicula> arreglo_peliculas = new ArrayList<>();
        Sala[] salas = new Sala[3];
        for (int i = 0; i < 3; i++) {
            salas[i] = new Sala(i+1);
        }
        Date fecha = new Date(2019,9,30);
        int hora = 8;

        String ruta_clientes = "clientes.txt";
        String ruta_empleados = "empleados.txt";
        String ruta_peliculas = "peliculas.txt";

        
        File archivo1 = new File(ruta_clientes);
        FileWriter w = new FileWriter(archivo1);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);

        Scanner lecture = new Scanner(archivo1);

        char abc[] = {'a', 'b', 'c', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] nombre = new char[8];
        int edad = 0, id = 0;
        boolean rol = true;
        String name = "";
        
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 8; i++) {
                int el = (int) (Math.random() * 12);
                nombre[i] = (char) abc[el];

                name = name + nombre[i];
                rol = Math.random() < 0.5;

                edad = (int) (Math.random() * 80);
                id = 10000000 + (int) (Math.random() * 9999999);

            }
            if (rol == true) {
                arreglo_empleados.add(new Empleado(name, edad, id, rol));
            } else {
                arreglo_clientes.add(new Cliente(name, edad, id, rol));
            }

            wr.write(nombre);
            wr.write(" " + edad + " " + id + " " + rol);
            wr.write('\n');
        }
        
        

        Scanner sc = new Scanner(System.in);
        boolean estado = true;
        do {
            archivo1 = new File(ruta_clientes);
            w = new FileWriter(archivo1);
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);

        lecture = new Scanner(archivo1);
            limpiarPantalla();
            System.out.println("/////////////////////////////////////////");
            System.out.println("              Bienvenido");
            System.out.println("¿Qué desea hacer?");
            System.out.println("1) Ingresar usuario");
            System.out.println("2) Registrar usuario");
            // System.out.println("3) Consultar usuario");
            System.out.println("4) Consultar pelicula");
            System.out.println("/////////////////////////////////////////");

            int seleccion = sc.nextInt();
            switch (seleccion) {
                case 1:
                    boolean valido = false;
                    boolean rolPrueba = false;
                    Thread.sleep(3000);
                    limpiarPantalla();
                    
                    System.out.println("Ingrese su documento");
                    int id_prueba = sc.nextInt();
                    for (int i = 0; i < arreglo_empleados.size(); i++) {
                        if(id_prueba == arreglo_empleados.get(i).getDocumento()){
                            valido = true;
                            rolPrueba = true;
                            break;
                        }
                    }
                    if(!valido){
                        for (int i = 0; i < arreglo_clientes.size(); i++) {
                            if(id_prueba == arreglo_clientes.get(i).getDocumento()){
                                valido = true;
                                rolPrueba = false;
                                break;
                            }
                        }
                        System.out.println("Usuario no encontrado");
                    }
                    if(valido){
                        if(rolPrueba){
                            System.out.println("Bienvenido, señor(a) empleado/a. Seleccione la acción a realizar");
                            System.out.println("1. Actualizar fecha");
                            System.out.println("2. Actualizar hora");
                            System.out.println("3. Actualizar peliculas");
                            System.out.println("4. Terminar sesión");
                            int selEmpleado = sc.nextInt();
                            switch(selEmpleado){
                                case 1:
                                    System.out.print("Día: ");
                                    int nuevoDia = sc.nextInt();
                                    System.out.print("Mes: ");
                                    int nuevoMes = sc.nextInt();
                                    System.out.print("Año: ");
                                    int nuevoAno = sc.nextInt();
                                    fecha = new Date(nuevoAno,nuevoMes,nuevoDia);
                                    break;
                                case 2:
                                    System.out.print("Hora: ");
                                    int nuevaHora = sc.nextInt();
                                    hora = nuevaHora;
                                    break;
                                case 3:
                                    System.out.println("Seleccione accion");
                                    System.out.println("1. Agregar pelicula");
                                    System.out.println("2. Agregar horario");
                                    int selPelicula = sc.nextInt();
                                    switch(selPelicula){
                                        case 1:
                                            System.out.print("Ingrese título: ");
                                            String titulo = sc.next();
                                            System.out.print("Ingrese duracion en horas (no superior a 3): ");
                                            int duracion = sc.nextInt();
                                            System.out.print("Ingrese edad mínima: ");
                                            int edadMinima = sc.nextInt();
                                            arreglo_peliculas.add(new Pelicula(titulo,duracion,edadMinima));
                                            System.out.println("Película agregada satisfactoriamente");
                                            System.out.println("1. Agregar otra película");
                                            System.out.println("2. Regresar");
                                            break;
                                        case 2:
                                            if(arreglo_peliculas.isEmpty()){
                                                System.out.println("No hay películas en cartelera");
                                            }else{
                                                System.out.println("Seleccione película");
                                                for (int i = 0; i < arreglo_peliculas.size(); i++) {
                                                    System.out.println((i+1)+". "+ arreglo_peliculas.get(i).getTitulo());
                                                }
                                                int indicePelicula = sc.nextInt()-1;
                                                System.out.println("Ingrese hora");
                                                int horaPelicula = sc.nextInt();
                                                arreglo_peliculas.get(indicePelicula).addFuncion(new Funcion(arreglo_peliculas.get(indicePelicula),fecha,horaPelicula,elegirSala(salas,horaPelicula)));
                                                System.out.println("Horario agregado satisfactoriamente");
                                                System.out.println("1. Agregar otro horario");
                                                System.out.println("2. Regresar");
                                            }
                                            break;
                                        default:
                                            System.out.println("Dato incorrecto");
                                    }
                                case 4:
                                    //aquello de los booleanos
                                    break;
                                default:
                                    System.out.println("Dato incorrecto");
                            }
                        }else{
                            System.out.println("Bienvenido. Seleccione su película");
                            for(int i = 0; i < arreglo_peliculas.size(); i++){
                                System.out.println((i+1) + ". " + arreglo_peliculas.get(i).getTitulo());
                            }
                            int selCliente = sc.nextInt();
                            
                        }
                    }
                    break;
                case 2:
                    limpiarPantalla();
                    System.out.println("Ingrese nombre");
                    String nombre_dinamico = sc.next();
                    System.out.println("Ingrese edad");
                    int edad_dinamico = sc.nextInt();
                    System.out.println("Ingrese documento");
                    int id_dinamico = sc.nextInt();
                    System.out.println("¿Es usted empleado? (si o no)");
                    String rol_dinamico = sc.next();

                    if (rol_dinamico.equals("si")) {
                        System.out.println("Ingrese clave (dada por la empresa)");
                        // La clave por defecto es 0000
                        String clave_dinamico = sc.next();
                        if (clave_dinamico.equals("0000")) {
                            w.write(nombre_dinamico);
                            w.write(" " + edad_dinamico + " " + id_dinamico + " " + "true");
                            w.write('\n');
                            arreglo_empleados.add(new Cliente(nombre_dinamico, edad_dinamico, id_dinamico, true));
                            System.out.println("Registrado con éxito");
                            Thread.sleep(3000);
                        limpiarPantalla();
                        } else {
                            System.out.println("Clave incorrecta");
                            Thread.sleep(3000);
                        limpiarPantalla();
                        }

                    } else {

                        w.write(nombre_dinamico);
                        w.write(" " + edad_dinamico + " " + id_dinamico + " " + "false");
                        w.write('\n');

                        arreglo_clientes.add(new Cliente(nombre_dinamico, edad_dinamico, id_dinamico, false));
                        System.out.println("Registrado con éxito");
                        Thread.sleep(3000);
                        limpiarPantalla();

                    }
                    w.close();
                    break;
                case 3:
                    Thread.sleep(3000);
                        limpiarPantalla();
            }
           

        } while (estado == true);

    }
}

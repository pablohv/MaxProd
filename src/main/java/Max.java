
import Vistas.Administrador;
import Vistas.Login;
import dao.AsignacionDaoImpl;
import dao.EquipoDaoImpl;
import dao.TiempoDao;
import dao.UsuarioDaoImpl;
import dto.Asignados;
import dto.Equipo;
import dto.Tecnico;
import dto.Tiempo;
import dto.Usuario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Max {

    public static void main(String[] args) {

        /* prueba login
            Usuario usuario = new Usuario("dpsi", "dpsi");
            UsuarioDaoImpl userDao = new UsuarioDaoImpl();
            boolean existe = userDao.ingresar(usuario);
            
            if(existe){
            System.out.println("perfecto");
            } else {
            System.out.println("imperfecto");
            }*/

 /* dar de alta equipo
            Equipo equipo = new Equipo(456, "Juan", "Rogel", "Izta", "TV", "Samsung", "f5gh", 456, "no enciende", "control");
            EquipoDaoImpl equipoDao = new EquipoDaoImpl();
            equipoDao.altaEquipo(equipo);
         */
 /* prueba para caraga de tecnicos
            AsignacionDaoImpl asignacionDao = new AsignacionDaoImpl();
            List<Tecnico> tecnicos = asignacionDao.listarTecnicos();
            for(Tecnico tec : tecnicos){
            System.out.println(tec.toString());
            }
         
        //carga de equipos 
        AsignacionDaoImpl asignacionDao = new AsignacionDaoImpl();
        List<Asignados> equipos = asignacionDao.listarEquipos();
        for (Asignados equi : equipos) {
            System.out.println(equi.toString());
        }
        /*
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");

        Date fechaInicial;
        fechaInicial = dateFormat.parse("2020-02-14 19:00:00");
        Date fechaFinal;
        fechaFinal = dateFormat.parse("2020-02-15 11:10:10");

        int diferencia = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 1000);

        int dias = 0;
        int horas = 0;
        int minutos = 0;
        if (diferencia > 86400) {
            dias = (int) Math.floor(diferencia / 86400);
            diferencia = diferencia - (dias * 86400);
        }
        if (diferencia > 3600) {
            horas = (int) Math.floor(diferencia / 3600);
            diferencia = diferencia - (horas * 3600);
        }
        if (diferencia > 60) {
            minutos = (int) Math.floor(diferencia / 60);
            diferencia = diferencia - (minutos * 60);
        }
        System.out.println("Hay " + dias + " dias, " + horas + " horas, " + minutos + " minutos y " + diferencia + " segundos de diferencia");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        Date date = new Date();
        String fecha = dateFormat.format(date);
        System.out.println(fecha);
         
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);

        int mes = 4;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date, date1;
        date = new Date();
        date1 = new Date();
        System.out.println("tu fecha 1:  " + dateFormat.format(date) + " Tu fecha 2 es: " + dateFormat.format(date1));

        /*
        TiempoDao tiempoDao = new TiempoDao();
        List<Tiempo> tiempos = tiempoDao.porRango(inicio, fin);
        int contador = 0;
        for (int i = 0; i < tiempos.size(); i++) {

            if (i == contador) {
                System.out.println(calcular(tiempos.get(i).getInicioDiag(), tiempos.get(i).getFinDiag()));
            }
            contador++;
        }
         */
        System.out.println(calcular("2000-11-14 14:56:00", "2020-04-24 14:56:00"));
        System.out.println(promedios(0, 5));
    }

    public static String promedios(int totalSegundos, int diagnostico) {

        int dias = 0;
        int horas = 0;
        int minutos = 0;

        int diferencia = totalSegundos / diagnostico;
        System.out.println("Promedio");

        if (diferencia > 86400) {
            dias = (int) Math.floor(diferencia / 86400);
            diferencia = diferencia - (dias * 86400);
        }
        if (diferencia > 3600) {
            horas = (int) Math.floor(diferencia / 3600);
            diferencia = diferencia - (horas * 3600);
        }
        if (diferencia > 60) {
            minutos = (int) Math.floor(diferencia / 60);
            diferencia = diferencia - (minutos * 60);
        }
        System.out.println("Hay " + dias + " dias, " + horas + " horas, " + minutos + " minutos y " + diferencia + " segundos de diferencia");
        String mostrar = "D " + dias + " H " + horas + " M" + minutos;
        return mostrar;
    }

    public static String calcular(String fechaIni, String fechaFi) {
        String diferencias = "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date fechaInicial;
            fechaInicial = dateFormat.parse(fechaIni);
            Date fechaFinal;
            fechaFinal = dateFormat.parse(fechaFi);

            int diferencia = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 1000);
            System.out.println("" + diferencia);

            int dias = 0;
            int horas = 0;
            int minutos = 0;
            if (diferencia > 86400) {
                dias = (int) Math.floor(diferencia / 86400);
                diferencia = diferencia - (dias * 86400);
            }
            if (diferencia > 3600) {
                horas = (int) Math.floor(diferencia / 3600);
                diferencia = diferencia - (horas * 3600);
            }
            if (diferencia > 60) {
                minutos = (int) Math.floor(diferencia / 60);
                diferencia = diferencia - (minutos * 60);
            }
            System.out.println("Hay " + dias + " dias, " + horas + " horas, " + minutos + " minutos y " + diferencia + " segundos de diferencia");
            diferencias = dias + " Dias " + horas + " Hr " + minutos + " Min";
        } catch (ParseException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return diferencias;
    }

    //devuelve los segundos
    public static int segundos(String fechaIni, String fechaFi) {
        int totalSegundos = 0;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date fechaInicial;
            fechaInicial = dateFormat.parse(fechaIni);
            Date fechaFinal;
            fechaFinal = dateFormat.parse(fechaFi);

            totalSegundos = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 1000);
            System.out.println("" + totalSegundos);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return totalSegundos;
    }

}

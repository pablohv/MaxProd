package Vistas;

import dao.AsignacionDaoImpl;
import dao.AutorizacionDao;
import dao.EquipoDaoImpl;
import dao.EstatusDao;
import dao.Reincidencia;
import dao.TiempoDao;
import dto.Autorizacion;
import dto.Diagnostico;
import dto.Equipo;
import dto.Prueba;
import dto.Reparacion;
import dto.Tiempo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Administrador extends javax.swing.JFrame {

    /**
     * Creates new form Alta
     */
    public Administrador() {
        initComponents();
        cargarComponentes();
        this.setLocationRelativeTo(null);

    }

    public void cargarComponentes() {
        parametro.addItem("Selecciona una opcion");
        parametro.addItem("Equipos en diagnostico");
        parametro.addItem("Equipos en reparacion");;
        parametro.addItem("Equipos en pruebas");

        seleccionaMes.addItem("Enero");
        seleccionaMes.addItem("Febrero");
        seleccionaMes.addItem("Marzo");
        seleccionaMes.addItem("Abril");
        seleccionaMes.addItem("Mayo");
        seleccionaMes.addItem("Junio");
        seleccionaMes.addItem("Julio");
        seleccionaMes.addItem("Agosto");
        seleccionaMes.addItem("Septiembre");
        seleccionaMes.addItem("Octubre");
        seleccionaMes.addItem("Noviembre");
        seleccionaMes.addItem("Diciembre");
    }

    public void cargarEstatusDiagnostico() {
        DefaultTableModel estado = new DefaultTableModel();

        EstatusDao estatus = new EstatusDao();
        List<Diagnostico> asig = estatus.equipoEnDiagnostico();

        estado.addColumn("Folio");
        estado.addColumn("T. Equipo");
        estado.addColumn("Marca");
        estado.addColumn("Falla");
        estado.addColumn("Tecnico");
        estado.addColumn("Inicio Diagnostico");
        this.jTable1.setModel(estado);

        Object[] o = new Object[6];
        for (Diagnostico asignados : asig) {
            o[0] = asignados.getFolio();
            o[1] = asignados.getTipoEquipo();
            o[2] = asignados.getMarca();
            o[3] = asignados.getFalla();
            o[4] = asignados.getNombre();
            o[5] = asignados.getInicioDiagnostico();
            estado.addRow(o);
        }
        this.jTable1.setModel(estado);
    }

    public void cargarEstatusReparacion() {
        DefaultTableModel estado = new DefaultTableModel();

        EstatusDao estatus = new EstatusDao();
        List<Reparacion> reparaciones = estatus.equipoEnReparacion();

        estado.addColumn("Folio");
        estado.addColumn("T. Equipo");
        estado.addColumn("Marca");
        estado.addColumn("Tecnico");
        estado.addColumn("Inicio Reparacion");
        estado.addColumn("Diagnostico");
        this.jTable1.setModel(estado);

        Object[] o = new Object[6];
        for (Reparacion estatusReparaciones : reparaciones) {
            o[0] = estatusReparaciones.getFolio();
            o[1] = estatusReparaciones.getTipoEquipo();
            o[2] = estatusReparaciones.getMarca();
            o[3] = estatusReparaciones.getNombre();
            o[4] = estatusReparaciones.getInicioReparacion();
            o[5] = estatusReparaciones.getDiagnostico();
            estado.addRow(o);
        }
        this.jTable1.setModel(estado);
    }

    public void cargarEstatusPruebas() {
        DefaultTableModel estado = new DefaultTableModel();

        EstatusDao estatus = new EstatusDao();
        List<Prueba> reparaciones = estatus.equipoEnPrueba();

        estado.addColumn("Folio");
        estado.addColumn("T. Equipo");
        estado.addColumn("Marca");
        estado.addColumn("Tecnico");
        estado.addColumn("Inicio Pruebas");
        estado.addColumn("Pruebas");
        this.jTable1.setModel(estado);

        Object[] o = new Object[6];
        for (Prueba estatusReparaciones : reparaciones) {
            o[0] = estatusReparaciones.getFolio();
            o[1] = estatusReparaciones.getTipoEquipo();
            o[2] = estatusReparaciones.getMarca();
            o[3] = estatusReparaciones.getNombre();
            o[4] = estatusReparaciones.getInicioPruebas();
            o[5] = estatusReparaciones.getPuebas();
            estado.addRow(o);
        }
        this.jTable1.setModel(estado);
    }

    public void cargarAutorizaciones() {
        DefaultTableModel autorizacion = new DefaultTableModel();

        autorizacion.addColumn("Folio");
        autorizacion.addColumn("Cliente");
        autorizacion.addColumn("Telefono");
        autorizacion.addColumn("Marca");
        autorizacion.addColumn("Falla");
        autorizacion.addColumn("Diagnostico");
        this.tablaAutorizaciones.setModel(autorizacion);

        AutorizacionDao autori = new AutorizacionDao();
        List<Autorizacion> cargaFinalistas = autori.diagnosticoTerminado();

        Object[] o = new Object[6];
        for (Autorizacion auto : cargaFinalistas) {
            o[0] = auto.getFolio();
            o[1] = auto.getNombre() + " " + auto.getApellido();
            o[2] = auto.getTelefono();
            o[3] = auto.getMarca() + " " + auto.getModelo();
            o[4] = auto.getFalla();
            o[5] = auto.getDiagnostico();
            autorizacion.addRow(o);
        }
        this.jTable1.setModel(autorizacion);
    }

    public void rendimientoMes() {
        DefaultTableModel rendi = new DefaultTableModel();

        rendi.addColumn("Folio - Tecnico");
        rendi.addColumn("Aparato - R");
        rendi.addColumn("Diagnostico");
        rendi.addColumn("Duracion");
        rendi.addColumn("Reparacion");
        rendi.addColumn("Duracion");
        rendi.addColumn("Pruebas");
        rendi.addColumn("Duracion");
        this.tablaRendimiento.setModel(rendi);

        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);

        int mes = seleccionaMes.getSelectedIndex() + 1;

        TiempoDao tiempoDao = new TiempoDao();
        List<Tiempo> tiempos = tiempoDao.porMes(anio, mes);

        String tecnicoUno = "Federico";
        String tecnicoDos = "Agustin";

        nombreTecnico1.setText(tecnicoUno);
        nombreTecnicoDos.setText(tecnicoDos);

        //variables para contar rendimiento
        int diagnosticoT1 = 0;
        int reparacionT1 = 0;
        int pruebasT1 = 0;

        int contadorDiagnosticoT1 = 0;
        int contadorRepT1 = 0;
        int contadorPruebasT1 = 0;

        int diagnosticoT2 = 0;
        int reparacionT2 = 0;
        int pruebasT2 = 0;

        int contadorDiagnosticoT2 = 0;
        int contadorRepT2 = 0;
        int contadorPruebasT2 = 0;

        Object[] o = new Object[8];
        for (int i = 0; i < tiempos.size(); i++) {
            o[0] = tiempos.get(i).getFolio() + " - " + tiempos.get(i).getTecnico();
            o[1] = tiempos.get(i).getTipoEquipo() + " - " + tiempos.get(i).getMarca() + " R=" + tiempos.get(i).getReincidencia();
            o[2] = tiempos.get(i).getInicioDiag() + " - " + tiempos.get(i).getFinDiag();

            if (tiempos.get(i).getFinDiag() == null) {
                if (tiempos.get(i).getReincidencia() == 0) {
                    o[3] = "En diagnostico";
                } else {
                    o[3] = "En diagnostico R";
                }
            } else {
                o[3] = calcular(tiempos.get(i).getInicioDiag(), tiempos.get(i).getFinDiag());
                if (tecnicoUno.equals(tiempos.get(i).getTecnico())) {
                    diagnosticoT1 += segundos(tiempos.get(i).getInicioDiag(), tiempos.get(i).getFinDiag());
                    contadorDiagnosticoT1++;
                } else {
                    diagnosticoT2 += segundos(tiempos.get(i).getInicioDiag(), tiempos.get(i).getFinDiag());
                    contadorDiagnosticoT2++;
                }
            }

            if (tiempos.get(i).getInicioRep() != null) {
                o[4] = tiempos.get(i).getInicioRep() + " - " + tiempos.get(i).getFinRep();
            } else {
                if (tiempos.get(i).getReparacion() == null) {
                    o[4] = "En proceso";
                } else {
                    if (tiempos.get(i).getReparacion().equals("NO")) {
                        o[4] = "No reparar";
                    } else if (tiempos.get(i).getReparacion().equals("SI") && tiempos.get(i).getReincidencia() == 0) {
                        o[4] = "En proceso";
                    } else {
                        o[4] = "En proceso R";
                    }
                }

            }

            if (tiempos.get(i).getFinRep() == null) {
                if (tiempos.get(i).getReparacion() == null) {
                    o[5] = "En proceso";
                } else {
                    if (tiempos.get(i).getReparacion().equals("NO")) {
                        o[5] = "No reparar";
                    } else if (tiempos.get(i).getReparacion().equals("SI") && tiempos.get(i).getReincidencia() == 0) {
                        o[5] = "En proceso";
                    } else {
                        o[5] = "En proceso R";
                    }
                }

            } else {
                o[5] = calcular(tiempos.get(i).getInicioRep(), tiempos.get(i).getFinRep());
                if (tecnicoUno.equals(tiempos.get(i).getTecnico())) {
                    reparacionT1 += segundos(tiempos.get(i).getInicioRep(), tiempos.get(i).getFinRep());
                    contadorRepT1++;
                } else {
                    reparacionT2 += segundos(tiempos.get(i).getInicioRep(), tiempos.get(i).getFinRep());
                    contadorRepT2++;
                }
            }

            if (tiempos.get(i).getInicioPru() != null) {
                o[6] = tiempos.get(i).getInicioPru() + " - " + tiempos.get(i).getFinPru();
            } else {
                if (tiempos.get(i).getReparacion() == null) {
                    o[6] = "En proceso";
                } else {
                    if (tiempos.get(i).getReparacion().equals("NO")) {
                        o[6] = "No reparar";
                    } else if (tiempos.get(i).getReparacion().equals("SI") && tiempos.get(i).getReincidencia() == 0) {
                        o[6] = "En proceso";
                    } else {
                        o[6] = "En proceso R";
                    }
                }

            }

            if (tiempos.get(i).getFinPru() == null) {
                if (tiempos.get(i).getReparacion() == null) {
                    o[7] = "En proceso";
                } else {
                    if (tiempos.get(i).getReparacion().equals("NO")) {
                        o[7] = "No reparar";
                    } else if (tiempos.get(i).getReparacion().equals("SI") && tiempos.get(i).getReincidencia() == 0) {
                        o[7] = "En proceso";
                    } else {
                        o[7] = "En proceso R";
                    }
                }

            } else {
                o[7] = calcular(tiempos.get(i).getInicioPru(), tiempos.get(i).getFinPru());
                if (tecnicoUno.equals(tiempos.get(i).getTecnico())) {
                    pruebasT1 += segundos(tiempos.get(i).getInicioPru(), tiempos.get(i).getFinPru());
                    contadorPruebasT1++;
                } else {
                    pruebasT2 += segundos(tiempos.get(i).getInicioPru(), tiempos.get(i).getFinPru());
                    contadorPruebasT2++;
                }
            }

            rendi.addRow(o);
        }
        this.tablaRendimiento.setModel(rendi);

        dt1.setText(promedios(diagnosticoT1, contadorDiagnosticoT1));
        dt2.setText(promedios(diagnosticoT2, contadorDiagnosticoT2));

        rt1.setText(promedios(reparacionT1, contadorRepT1));
        rt2.setText(promedios(reparacionT2, contadorRepT2));

        pt1.setText(promedios(pruebasT1, contadorPruebasT1));
        pt2.setText(promedios(pruebasT2, contadorPruebasT2));
    }

    public void rendimientoIntervalo(String inicio, String fin) {
        DefaultTableModel rendi = new DefaultTableModel();

        rendi.addColumn("Folio - Tecnico");
        rendi.addColumn("Aparato - R");
        rendi.addColumn("Diagnostico");
        rendi.addColumn("Duracion");
        rendi.addColumn("Reparacion");
        rendi.addColumn("Duracion");
        rendi.addColumn("Pruebas");
        rendi.addColumn("Duracion");
        this.tablaRendimiento.setModel(rendi);

        TiempoDao tiempoDao = new TiempoDao();
        List<Tiempo> tiempos = tiempoDao.porRango(inicio, fin);

        String tecnicoUno = "Federico";
        String tecnicoDos = "Agustin";

        nombreTecnico1.setText(tecnicoUno);
        nombreTecnicoDos.setText(tecnicoDos);

        //variables para contar rendimiento
        int diagnosticoT1 = 0;
        int reparacionT1 = 0;
        int pruebasT1 = 0;

        int contadorDiagnosticoT1 = 0;
        int contadorRepT1 = 0;
        int contadorPruebasT1 = 0;

        int diagnosticoT2 = 0;
        int reparacionT2 = 0;
        int pruebasT2 = 0;

        int contadorDiagnosticoT2 = 0;
        int contadorRepT2 = 0;
        int contadorPruebasT2 = 0;

        Object[] o = new Object[8];
        for (int i = 0; i < tiempos.size(); i++) {
            o[0] = tiempos.get(i).getFolio() + " - " + tiempos.get(i).getTecnico();
            o[1] = tiempos.get(i).getTipoEquipo() + " - " + tiempos.get(i).getMarca() + " R=" + tiempos.get(i).getReincidencia();
            o[2] = tiempos.get(i).getInicioDiag() + " - " + tiempos.get(i).getFinDiag();

            if (tiempos.get(i).getFinDiag() == null) {
                if (tiempos.get(i).getReincidencia() == 0) {
                    o[3] = "En diagnostico";
                } else {
                    o[3] = "En diagnostico R";
                }
            } else {
                o[3] = calcular(tiempos.get(i).getInicioDiag(), tiempos.get(i).getFinDiag());
                if (tecnicoUno.equals(tiempos.get(i).getTecnico())) {
                    diagnosticoT1 += segundos(tiempos.get(i).getInicioDiag(), tiempos.get(i).getFinDiag());
                    contadorDiagnosticoT1++;
                } else {
                    diagnosticoT2 += segundos(tiempos.get(i).getInicioDiag(), tiempos.get(i).getFinDiag());
                    contadorDiagnosticoT2++;
                }
            }

            if (tiempos.get(i).getInicioRep() != null) {
                o[4] = tiempos.get(i).getInicioRep() + " - " + tiempos.get(i).getFinRep();
            } else {
                if (tiempos.get(i).getReparacion() == null) {
                    o[4] = "En proceso";
                } else {
                    if (tiempos.get(i).getReparacion().equals("NO")) {
                        o[4] = "No reparar";
                    } else if (tiempos.get(i).getReparacion().equals("SI") && tiempos.get(i).getReincidencia() == 0) {
                        o[4] = "En proceso";
                    } else {
                        o[4] = "En proceso R";
                    }
                }

            }

            if (tiempos.get(i).getFinRep() == null) {
                if (tiempos.get(i).getReparacion() == null) {
                    o[5] = "En proceso";
                } else {
                    if (tiempos.get(i).getReparacion().equals("NO")) {
                        o[5] = "No reparar";
                    } else if (tiempos.get(i).getReparacion().equals("SI") && tiempos.get(i).getReincidencia() == 0) {
                        o[5] = "En proceso";
                    } else {
                        o[5] = "En proceso R";
                    }
                }

            } else {
                o[5] = calcular(tiempos.get(i).getInicioRep(), tiempos.get(i).getFinRep());
                if (tecnicoUno.equals(tiempos.get(i).getTecnico())) {
                    reparacionT1 += segundos(tiempos.get(i).getInicioRep(), tiempos.get(i).getFinRep());
                    contadorRepT1++;
                } else {
                    reparacionT2 += segundos(tiempos.get(i).getInicioRep(), tiempos.get(i).getFinRep());
                    contadorRepT2++;
                }
            }

            if (tiempos.get(i).getInicioPru() != null) {
                o[6] = tiempos.get(i).getInicioPru() + " - " + tiempos.get(i).getFinPru();
            } else {
                if (tiempos.get(i).getReparacion() == null) {
                    o[6] = "En proceso";
                } else {
                    if (tiempos.get(i).getReparacion().equals("NO")) {
                        o[6] = "No reparar";
                    } else if (tiempos.get(i).getReparacion().equals("SI") && tiempos.get(i).getReincidencia() == 0) {
                        o[6] = "En proceso";
                    } else {
                        o[6] = "En proceso R";
                    }
                }

            }

            if (tiempos.get(i).getFinPru() == null) {
                if (tiempos.get(i).getReparacion() == null) {
                    o[7] = "En proceso";
                } else {
                    if (tiempos.get(i).getReparacion().equals("NO")) {
                        o[7] = "No reparar";
                    } else if (tiempos.get(i).getReparacion().equals("SI") && tiempos.get(i).getReincidencia() == 0) {
                        o[7] = "En proceso";
                    } else {
                        o[7] = "En proceso R";
                    }
                }

            } else {
                o[7] = calcular(tiempos.get(i).getInicioPru(), tiempos.get(i).getFinPru());
                if (tecnicoUno.equals(tiempos.get(i).getTecnico())) {
                    pruebasT1 += segundos(tiempos.get(i).getInicioPru(), tiempos.get(i).getFinPru());
                    contadorPruebasT1++;
                } else {
                    pruebasT2 += segundos(tiempos.get(i).getInicioPru(), tiempos.get(i).getFinPru());
                    contadorPruebasT2++;
                }
            }

            rendi.addRow(o);
        }
        this.tablaRendimiento.setModel(rendi);

        dt1.setText(promedios(diagnosticoT1, contadorDiagnosticoT1));
        dt2.setText(promedios(diagnosticoT2, contadorDiagnosticoT2));

        rt1.setText(promedios(reparacionT1, contadorRepT1));
        rt2.setText(promedios(reparacionT2, contadorRepT2));

        pt1.setText(promedios(pruebasT1, contadorPruebasT1));
        pt2.setText(promedios(pruebasT2, contadorPruebasT2));

    }

    public int segundos(String fechaIni, String fechaFi) {
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
        String mostrar = dias + "d " + horas + "h " + minutos + "m";
        return mostrar;
    }

    public String calcular(String fechaIni, String fechaFi) {
        String diferencias = "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date fechaInicial;
            fechaInicial = dateFormat.parse(fechaIni);
            Date fechaFinal;
            fechaFinal = dateFormat.parse(fechaFi);

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
            //System.out.println("Hay " + dias + " dias, " + horas + " horas, " + minutos + " minutos y " + diferencia + " segundos de diferencia");
            diferencias = dias + " Dias " + horas + " Hr " + minutos + " Min";
        } catch (ParseException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return diferencias;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenu1 = new javax.swing.JMenu();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        folio = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        direccion = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        marca = new javax.swing.JTextField();
        modelo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        noSerie = new javax.swing.JTextField();
        tipoEquipo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        falla = new javax.swing.JTextField();
        accesorios = new javax.swing.JTextField();
        aceptar = new javax.swing.JButton();
        finalizar = new javax.swing.JButton();
        reincidencia = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        parametro = new javax.swing.JComboBox<>();
        visualizar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAutorizaciones = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        actualizarDiagnosticoTerminado = new javax.swing.JButton();
        siReparar = new javax.swing.JButton();
        noRepararEquipo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        seleccionaMes = new javax.swing.JComboBox<>();
        MesElegido = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        IntervaloElegido = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaRendimiento = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        dt1 = new javax.swing.JTextField();
        rt1 = new javax.swing.JTextField();
        pt1 = new javax.swing.JTextField();
        dt2 = new javax.swing.JTextField();
        rt2 = new javax.swing.JTextField();
        pt2 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        nombreTecnico1 = new javax.swing.JTextField();
        nombreTecnicoDos = new javax.swing.JTextField();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MaxProd");

        jLabel1.setText("Cliente");

        jLabel2.setText("Equipo");

        folio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folioActionPerformed(evt);
            }
        });

        jLabel4.setText("Folio");

        jLabel5.setText("Nombre");

        jLabel6.setText("Teléfono");

        telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoActionPerformed(evt);
            }
        });

        jLabel11.setText("Apellido");

        jLabel12.setText("Direccion");

        direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(telefono)
                        .addComponent(apellido, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                        .addComponent(nombre)
                        .addComponent(folio))
                    .addComponent(direccion, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(folio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel7.setText("Tipo Equipo");

        jLabel8.setText("Marca");

        jLabel9.setText("Modelo");

        jLabel10.setText("No de Serie");

        jLabel13.setText("Falla");

        jLabel14.setText("Accesorios");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14))
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipoEquipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(marca)
                    .addComponent(modelo)
                    .addComponent(noSerie)
                    .addComponent(falla)
                    .addComponent(accesorios))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tipoEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(noSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(falla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(accesorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        aceptar.setText("Elegir Tecnico");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        finalizar.setText("Finalizar");
        finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarActionPerformed(evt);
            }
        });

        reincidencia.setText("Reincidencia");
        reincidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reincidenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 441, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(156, 156, 156))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(reincidencia)
                .addGap(52, 52, 52)
                .addComponent(finalizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(aceptar)
                .addGap(88, 88, 88))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptar)
                    .addComponent(finalizar)
                    .addComponent(reincidencia))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Alta", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("Estatus");

        parametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parametroActionPerformed(evt);
            }
        });

        visualizar.setText("Visualizar");
        visualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(parametro, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(visualizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(parametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(visualizar))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Estatus", jPanel4);

        tablaAutorizaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaAutorizaciones);

        jLabel15.setText("Selecciona un equipo para reparar o terminar");

        actualizarDiagnosticoTerminado.setText("Actualizar tabla");
        actualizarDiagnosticoTerminado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarDiagnosticoTerminadoActionPerformed(evt);
            }
        });

        siReparar.setText("Reparar");
        siReparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siRepararActionPerformed(evt);
            }
        });

        noRepararEquipo.setText("No reparar");
        noRepararEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noRepararEquipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel15)
                .addGap(86, 86, 86)
                .addComponent(actualizarDiagnosticoTerminado)
                .addContainerGap(293, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(noRepararEquipo)
                        .addGap(18, 18, 18)
                        .addComponent(siReparar)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(actualizarDiagnosticoTerminado))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siReparar)
                    .addComponent(noRepararEquipo))
                .addGap(30, 30, 30))
        );

        jTabbedPane2.addTab("Autorizaciones", jPanel5);

        jLabel16.setText("Por mes");

        jLabel17.setText("Personalizado");

        MesElegido.setText("Seleccionar Mes");
        MesElegido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MesElegidoActionPerformed(evt);
            }
        });

        jLabel18.setText("Fecha inicio");

        jLabel19.setText("Fecha fin");

        IntervaloElegido.setText("Seleccionar intervalo");
        IntervaloElegido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IntervaloElegidoActionPerformed(evt);
            }
        });

        tablaRendimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tablaRendimiento);

        jLabel20.setText("Promedio:");

        jLabel21.setText("Tecnico");

        jLabel22.setText("Tecnico");

        dt1.setEditable(false);

        rt1.setEditable(false);
        rt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rt1ActionPerformed(evt);
            }
        });

        pt1.setEditable(false);
        pt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pt1ActionPerformed(evt);
            }
        });

        dt2.setEditable(false);

        rt2.setEditable(false);
        rt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rt2ActionPerformed(evt);
            }
        });

        pt2.setEditable(false);

        jLabel23.setText("Diagnostico");

        jLabel24.setText("Reparacion");

        jLabel25.setText("Pruebas");

        jLabel26.setText("Diagnostico");

        jLabel27.setText("Reparacion");

        jLabel28.setText("Pruebas");

        nombreTecnico1.setEditable(false);
        nombreTecnico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreTecnico1ActionPerformed(evt);
            }
        });

        nombreTecnicoDos.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(MesElegido)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16)
                                .addComponent(seleccionaMes, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(18, 18, 18)
                                        .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(IntervaloElegido)))
                        .addGap(69, 69, 69))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                    .addComponent(dt1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                    .addComponent(rt1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(pt1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel20))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(nombreTecnico1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(233, 233, 233)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel27))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(dt2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rt2)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(pt2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(nombreTecnicoDos, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(seleccionaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18))
                    .addComponent(fechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MesElegido)
                    .addComponent(IntervaloElegido))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(nombreTecnico1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(nombreTecnicoDos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Rendimiento", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.getAccessibleContext().setAccessibleName("tab1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoActionPerformed

    private void folioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_folioActionPerformed

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed

        ElegirTecnico elegido = new ElegirTecnico();
        elegido.setVisible(true);

    }//GEN-LAST:event_aceptarActionPerformed

    private void direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionActionPerformed

    private void finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarActionPerformed

        //instamciamos el modelo
        EquipoDaoImpl equipoDao = new EquipoDaoImpl();

        //agregamos los atributos al objeto
        Equipo equipo = new Equipo(Integer.parseInt(folio.getText()), nombre.getText(), apellido.getText(), telefono.getText(), direccion.getText(), tipoEquipo.getText(), marca.getText(), modelo.getText(), Integer.parseInt(noSerie.getText()), falla.getText(), accesorios.getText());

        //agregamos equipo a la base de datos
        equipoDao.altaEquipo(equipo);

        //agregamos el equipo y el tecnico a la asignacion
        AsignacionDaoImpl asignacionDao = new AsignacionDaoImpl();
        //instanciamos la pantalla de tecnico para obtner el valor del nombre
        ElegirTecnico ele = new ElegirTecnico();
        //pasamos el nombre y nos devuelve el id
        int nombretecnico = asignacionDao.buscarTecnico(ele.texto.toString());

        System.out.println(ele.texto.toString());
        System.out.println(nombretecnico);
        asignacionDao.asignarTecnico(nombretecnico, Integer.parseInt(folio.getText()));

        JOptionPane.showMessageDialog(this, "Datos agregados");

        folio.setText("");
        nombre.setText("");
        apellido.setText("");
        telefono.setText("");
        direccion.setText("");
        tipoEquipo.setText("");
        marca.setText("");
        modelo.setText("");
        noSerie.setText("");
        falla.setText("");
        accesorios.setText("");
    }//GEN-LAST:event_finalizarActionPerformed

    private void visualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizarActionPerformed

        switch (parametro.getItemAt(parametro.getSelectedIndex())) {
            case "Equipos en diagnostico":
                cargarEstatusDiagnostico();
                break;
            case "Equipos en reparacion":
                cargarEstatusReparacion();
                break;
            case "Equipos en pruebas":
                cargarEstatusPruebas();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Selecciona un componente");
                break;
        }

    }//GEN-LAST:event_visualizarActionPerformed

    private void parametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parametroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_parametroActionPerformed

    private void actualizarDiagnosticoTerminadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarDiagnosticoTerminadoActionPerformed

        cargarAutorizaciones();

    }//GEN-LAST:event_actualizarDiagnosticoTerminadoActionPerformed

    private void siRepararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siRepararActionPerformed

        int selecRow = tablaAutorizaciones.getSelectedRow();
        int folioDiagnostico = (int) tablaAutorizaciones.getValueAt(selecRow, 0);

        System.out.println(folioDiagnostico);
        AutorizacionDao autorizarReparacion = new AutorizacionDao();
        autorizarReparacion.aceptadoONo(folioDiagnostico, "SI");

        cargarAutorizaciones();

    }//GEN-LAST:event_siRepararActionPerformed

    private void noRepararEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noRepararEquipoActionPerformed

        int selecRow = tablaAutorizaciones.getSelectedRow();
        int folioDiagnostico = (int) tablaAutorizaciones.getValueAt(selecRow, 0);

        System.out.println(folioDiagnostico);
        AutorizacionDao autorizarReparacion = new AutorizacionDao();
        autorizarReparacion.aceptadoONo(folioDiagnostico, "NO");

        cargarAutorizaciones();

    }//GEN-LAST:event_noRepararEquipoActionPerformed

    private void nombreTecnico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreTecnico1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTecnico1ActionPerformed

    private void rt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rt2ActionPerformed

    private void pt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pt1ActionPerformed

    private void rt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rt1ActionPerformed

    private void IntervaloElegidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IntervaloElegidoActionPerformed

        if (fechaFin.getDate() != null && fechaInicio.getDate() != null) {
            if (fechaFin.getDate().getTime() < fechaInicio.getDate().getTime()) {
                JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser mayor a la fecha final");
                fechaInicio.setDateFormatString("");
                fechaFin.setDateFormatString("");
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date, date1;
                date = fechaInicio.getDate();
                date1 = fechaFin.getDate();
                System.out.println("tu fecha 1:  " + dateFormat.format(date) + " Tu fecha 2 es: " + dateFormat.format(date1));
                rendimientoIntervalo(dateFormat.format(date).toString(), dateFormat.format(date1).toString());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Te falta una fecha");
        }

    }//GEN-LAST:event_IntervaloElegidoActionPerformed

    private void MesElegidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MesElegidoActionPerformed

        //System.out.println(anio);
        //System.out.println(mes);
        rendimientoMes();
    }//GEN-LAST:event_MesElegidoActionPerformed

    private void reincidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reincidenciaActionPerformed

        if (!folio.getText().trim().equals("")) {

            Reincidencia reincide = new Reincidencia();
            int folioVerifica = Integer.parseInt(folio.getText());

            if (folio.getText().equals(Integer.toString(reincide.verificar(folioVerifica)))) {
                System.out.println(reincide.verificar(folioVerifica));
                reincide.reincidencia(folioVerifica);
                folio.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No existe este folio");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Agrega un folio");
        }


    }//GEN-LAST:event_reincidenciaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Administrador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IntervaloElegido;
    private javax.swing.JButton MesElegido;
    private javax.swing.JTextField accesorios;
    private javax.swing.JButton aceptar;
    private javax.swing.JButton actualizarDiagnosticoTerminado;
    private javax.swing.JTextField apellido;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField dt1;
    private javax.swing.JTextField dt2;
    private javax.swing.JTextField falla;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JButton finalizar;
    private javax.swing.JTextField folio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField modelo;
    private javax.swing.JButton noRepararEquipo;
    private javax.swing.JTextField noSerie;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField nombreTecnico1;
    private javax.swing.JTextField nombreTecnicoDos;
    private javax.swing.JComboBox<String> parametro;
    private javax.swing.JTextField pt1;
    private javax.swing.JTextField pt2;
    private javax.swing.JButton reincidencia;
    private javax.swing.JTextField rt1;
    private javax.swing.JTextField rt2;
    private javax.swing.JComboBox<String> seleccionaMes;
    private javax.swing.JButton siReparar;
    private javax.swing.JTable tablaAutorizaciones;
    private javax.swing.JTable tablaRendimiento;
    private javax.swing.JTextField telefono;
    private javax.swing.JTextField tipoEquipo;
    private javax.swing.JButton visualizar;
    // End of variables declaration//GEN-END:variables
}

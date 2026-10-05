package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Prototipo gráfico de SISNOM-Obra.
 * Solo contiene los nombres de los datos (etiquetas, campos y columnas).
 * Todavía no está conectado a los controladores.
 */
public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("SISNOM-Obra");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);

        JTabbedPane pestanas = new JTabbedPane();

        // RF-01: Obreros
        pestanas.addTab("Obreros", crearPanel(
                new String[]{},
                new String[]{"Nombre", "Documento de identidad", "Tarifa por hora"},
                "Registrar obrero",
                new String[]{"Nombre", "Documento", "Tipo", "Tarifa por hora"}));

        // RF-01: Capataces
        pestanas.addTab("Capataces", crearPanel(
                new String[]{},
                new String[]{"Nombre", "Documento de identidad"},
                "Registrar capataz",
                new String[]{"Nombre", "Documento", "Tipo"}));

        // RF-02: Obras
        pestanas.addTab("Obras", crearPanel(
                new String[]{"Capataz", "Obrero"},
                new String[]{"Nombre de la obra", "Ubicación"},
                "Registrar obra",
                new String[]{"Nombre", "Ubicación", "Capataces asignados", "Obreros asignados"}));

        // RF-03 / RF-04: Registro de horas
        pestanas.addTab("Registro de horas", crearPanel(
                new String[]{"Obrero", "Obra", "Capataz"},
                new String[]{"Fecha (AAAA-MM-DD)", "Hora inicio (HH:MM)", "Hora fin (HH:MM)"},
                "Registrar horas",
                new String[]{"Obrero", "Obra", "Capataz", "Fecha", "Hora inicio", "Hora fin"}));

        // Inasistencias
        pestanas.addTab("Inasistencias", crearPanel(
                new String[]{"Obrero"},
                new String[]{"Fecha (AAAA-MM-DD)", "Motivo"},
                "Registrar inasistencia",
                new String[]{"Obrero", "Fecha", "Motivo"}));

        // Préstamos
        pestanas.addTab("Préstamos", crearPanel(
                new String[]{"Obrero"},
                new String[]{"Monto inicial", "Fecha (AAAA-MM-DD)"},
                "Registrar préstamo",
                new String[]{"Obrero", "Monto inicial", "Saldo pendiente", "Fecha"}));

        // RF-07 / RF-08: Nómina
        pestanas.addTab("Nómina", crearPanel(
                new String[]{"Obrero"},
                new String[]{"Fecha inicio (AAAA-MM-DD)", "Fecha fin (AAAA-MM-DD)"},
                "Generar nómina",
                new String[]{"Obrero", "Periodo inicio", "Periodo fin", "Horas trabajadas",
                             "Pago bruto", "Desc. inasistencias", "Desc. préstamo", "Pago neto"}));

        add(pestanas);
    }

    /**
     * Arma una pestaña: formulario arriba (combos + campos de texto + botón)
     * y una tabla vacía abajo con los nombres de las columnas.
     */
    private JPanel crearPanel(String[] combos, String[] campos, String textoBoton, String[] columnas) {
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4, 4, 4, 4);
        g.anchor = GridBagConstraints.WEST;

        int fila = 0;
        for (String etiqueta : combos) {
            g.gridx = 0; g.gridy = fila;
            formulario.add(new JLabel(etiqueta + ":"), g);
            g.gridx = 1;
            formulario.add(new JComboBox<String>(), g);
            fila++;
        }
        for (String etiqueta : campos) {
            g.gridx = 0; g.gridy = fila;
            formulario.add(new JLabel(etiqueta + ":"), g);
            g.gridx = 1;
            formulario.add(new JTextField(20), g);
            fila++;
        }
        g.gridx = 1; g.gridy = fila;
        formulario.add(new JButton(textoBoton), g);

        JTable tabla = new JTable(new DefaultTableModel(columnas, 0));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(formulario, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}

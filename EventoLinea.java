import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Evento {
    String fecha;
    String descripcion;
    Image imagen;

    public Evento(String fecha, String descripcion, String rutaImagen) {
        this.fecha = fecha;
        this.descripcion = descripcion;

        if (rutaImagen != null && !rutaImagen.isEmpty()) {
            try {
               
                this.imagen = new ImageIcon(
                        getClass().getResource("/img/" + rutaImagen)
                ).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); 
            } catch (Exception e) {
                System.out.println("No se pudo cargar la imagen: " + rutaImagen);
                this.imagen = null;
            }
        } else {
            this.imagen = null;
        }
    }
}

public class EventoLinea extends JPanel {

    private List<Evento> eventos;

    public EventoLinea(List<Evento> eventos) {
        this.eventos = eventos;
        int ancho = eventos.size() * 280; 
        setPreferredSize(new Dimension(ancho, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int y = getHeight() / 2;
        g.setColor(Color.RED);
        g.drawLine(100, y, getPreferredSize().width - 70, y); 

        int anchoTotal = getPreferredSize().width;
        int separacion = (anchoTotal - 170) / (eventos.size() - 1); 
        int x = 120; 

        for (int i = 0; i < eventos.size(); i++) {
            Evento e = eventos.get(i);

          
            g.setColor(Color.ORANGE);
            g.fillOval(x - 10, y - 10, 20, 20);

          
            if (e.imagen != null) {
                g.drawImage(e.imagen, x - 40, y - 200, null);
            }

            
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            FontMetrics fm = g.getFontMetrics();
            int anchoTexto = fm.stringWidth(e.fecha);
            g.drawString(e.fecha, x - (anchoTexto / 2), y - 100);

            
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            int textoY = y + 40;
            for (String linea : dividirTexto(e.descripcion, 40)) {
                g.drawString(linea, x - 100, textoY); 
                textoY += 15;
            }

            x += separacion;
        }
    }

    private List<String> dividirTexto(String texto, int maxChar) {
        List<String> lineas = new ArrayList<>();
        String[] palabras = texto.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String palabra : palabras) {
            if (sb.length() + palabra.length() > maxChar) {
                lineas.add(sb.toString());
                sb = new StringBuilder();
            }
            sb.append(palabra).append(" ");
        }
        if (!sb.isEmpty()) {
            lineas.add(sb.toString());
        }
        return lineas;
    }

    public static void main(String[] args) {
        List<Evento> eventos = new ArrayList<>();
        eventos.add(new Evento(
                "1ª Generación (40–50)",
                "No existían sistemas operativos. Programas manuales con tarjetas perforadas y cables. Hardware enorme y exclusivo.",
                "Primera.png"
        ));
        eventos.add(new Evento(
                "2ª Generación (50–60)",
                "Surgieron los sistemas por lotes (Batch). Programas en lotes ejecutados sin interacción. Ejemplo: IBM 7094.",
                "cables.png"
        ));
        eventos.add(new Evento(
                "3ª Generación (60–70)",
                "Multiprogramación. Nacieron sistemas más estructurados como UNIX. Uso en universidades y empresas.",
                "Sistemas.png"
        ));
        eventos.add(new Evento(
                "4ª Generación (80–90)",
                "Expansión de las PCs. Sistemas como MS-DOS, MacOS y Windows. Introducción de interfaces gráficas (GUI).",
                "Pc.png"
        ));
        eventos.add(new Evento(
                "5ª Generación (2000–actualidad)",
                "Sistemas modernos: Windows, Linux, macOS, Android, iOS. Multitarea, multiusuario, redes, seguridad y movilidad.",
                "Sistemas op.png"
        ));

        JFrame ventana = new JFrame("Línea del Tiempo de los Sistemas Operativos");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(new JScrollPane(new EventoLinea(eventos)));
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}

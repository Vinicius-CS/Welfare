package TelasNP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;

public class Carregando extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private JLabel Fundo;
    private JProgressBar prbProgresso = null;
    private Timer timer = null;
    public Carregando() {
        super();
        setEnabled(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Carregando.class.getResource("/\u00CDcones/logomini.png")));
        initialize();
        setLocationRelativeTo(null);
    }
    private void initialize() {
         
        this.setSize(600, 450);
        this.setContentPane(getJContentPane());
        this.setTitle("Nutritional Welfare");
        // Vou mostrar 100 passos (cada passo deve durar 4000 / 100 = 40 ms então)
        // No último passo, só para facilitar, vou fechar esta janela, você pode fazer
        // outra coisa.
        timer = new Timer(25, new ActionListener() {
            private int step = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                prbProgresso.setValue(step);
                if (step == 101) {
                	Carregando.this.dispose();
                }
            }
        });
        timer.start();
    }
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getPrbProgresso());
            
            JLabel Título = DefaultComponentFactory.getInstance().createTitle("Carregando");
            Título.setHorizontalAlignment(SwingConstants.CENTER);
            Título.setFont(new Font("Arial", Font.BOLD, 24));
            Título.setForeground(Color.WHITE);
            Título.setBounds(0, 0, 584, 61);
            jContentPane.add(Título);
            Fundo = new JLabel();
            Fundo.setBounds(0, 0, 624, 460);
            Fundo.setIcon(new ImageIcon(Carregando.class.getResource("/\u00CDcones/Fundo.png")));
            Fundo.setHorizontalAlignment(SwingConstants.CENTER);
            jContentPane.add(Fundo);
        }
        return jContentPane;
    }
    private JProgressBar getPrbProgresso() {
        if (prbProgresso == null) {
            prbProgresso = new JProgressBar();
            prbProgresso.setStringPainted(true);
            prbProgresso.setForeground(new Color(0, 128, 0));
            prbProgresso.setBounds(10, 186, 564, 33);
        }
        return prbProgresso;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

            	Carregando thisClass = new Carregando();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
    }
}
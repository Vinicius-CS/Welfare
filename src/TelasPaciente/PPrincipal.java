package TelasPaciente;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTextPane;

public class PPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PPrincipal frame = new PPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PPrincipal.class.getResource("/\u00CDcones/LogoMenorAinda.png")));
		setTitle("Welfare - Paciente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 630, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane Desktop = new JDesktopPane();
		Desktop.setBounds(0, 21, 624, 460);
		contentPane.add(Desktop);
		
		JLabel T�tulo = DefaultComponentFactory.getInstance().createTitle("Welfare - Cr\u00E9ditos");
		T�tulo.setBounds(0, 0, 614, 61);
		Desktop.add(T�tulo);
		T�tulo.setForeground(Color.WHITE);
		T�tulo.setFont(new Font("Arial", Font.BOLD, 24));
		T�tulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextPane Sobre = new JTextPane();
		Sobre.setText("Este programa foi desenvolvido pela Oglle, somos uma empresa que desenvolve programas para simplificar a sua vida, acesse o nosso site e confira todos os nossos programas:\r\nwww.oglle.com.br");
		Sobre.setOpaque(false);
		Sobre.setForeground(Color.WHITE);
		Sobre.setFont(new Font("Arial", Font.PLAIN, 14));
		Sobre.setEditable(false);
		Sobre.setBounds(10, 72, 604, 61);
		Desktop.add(Sobre);
		
		JLabel Logo = DefaultComponentFactory.getInstance().createLabel("");
		Logo.setHorizontalAlignment(SwingConstants.CENTER);
		Logo.setIcon(new ImageIcon(PPrincipal.class.getResource("/\u00CDcones/LogoMenor.png")));
		Logo.setBounds(10, 144, 604, 305);
		Desktop.add(Logo);
		
		JLabel Fundo = DefaultComponentFactory.getInstance().createLabel("");
		Fundo.setBounds(0, 0, 624, 460);
		Desktop.add(Fundo);
		Fundo.setIcon(new ImageIcon(PPrincipal.class.getResource("/\u00CDcones/Fundo.png")));
		
		JMenuBar Menu = new JMenuBar();
		Menu.setBounds(0, 0, 624, 21);
		contentPane.add(Menu);
		Menu.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JMenu menupNutricionista = new JMenu("Nutricionista");
		Menu.add(menupNutricionista);
		
		JMenuItem mntmMapaDeMetas = new JMenuItem("Mapa de metas");
		mntmMapaDeMetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PMetas metas = new PMetas();
		        Desktop.add(metas);
		        metas.setVisible(true);
			}
		});
		menupNutricionista.add(mntmMapaDeMetas);
		
		JMenuItem menuConsultasAgendadas = new JMenuItem("Consultas agendadas");
		menuConsultasAgendadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PConsultasAgendadas consultasagendadas = new PConsultasAgendadas();
		        Desktop.add(consultasagendadas);
		        consultasagendadas.setVisible(true);
			}
		});
		menupNutricionista.add(menuConsultasAgendadas);
		
		JMenu menupOrienta��es = new JMenu("Orienta\u00E7\u00F5es");
		Menu.add(menupOrienta��es);
		
		JMenuItem menuOrienta��esNutricionais = new JMenuItem("Orienta\u00E7\u00F5es nutricionais");
		menuOrienta��esNutricionais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				POrienta��es orienta��es = new POrienta��es();
		        Desktop.add(orienta��es);
		        orienta��es.setVisible(true);
			}
		});
		menupOrienta��es.add(menuOrienta��esNutricionais);
		
		JMenuItem menuCard�pio = new JMenuItem("Card\u00E1pio");
		menuCard�pio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PCard�pio card�pio = new PCard�pio();
		        Desktop.add(card�pio);
		        card�pio.setVisible(true);
			}
		});
		menupOrienta��es.add(menuCard�pio);
		
		JMenu menupOutros = new JMenu("Outros");
		Menu.add(menupOutros);
		
		JMenuItem menuSobre = new JMenuItem("Sobre");
		menuSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PSobre sobre = new PSobre();
		        Desktop.add(sobre);
		        sobre.setVisible(true);
			}
		});
		menupOutros.add(menuSobre);
		
		JMenuItem menuSair = new JMenuItem("Sair");
		menuSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sair = JOptionPane.showConfirmDialog(null, "Deseja Sair?", "Aten��o", JOptionPane.YES_NO_OPTION);
		        if (sair == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
			}
		});
		
		JMenuItem menuConfigura��es = new JMenuItem("Configura\u00E7\u00F5es");
		menuConfigura��es.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PConfigura��es configura��es = new PConfigura��es();
		        Desktop.add(configura��es);
		        configura��es.setVisible(true);
			}
		});
		menupOutros.add(menuConfigura��es);
		menupOutros.add(menuSair);
	}
}

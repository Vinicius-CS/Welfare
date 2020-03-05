package TelasNutricionista;

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

public class NPrincipal extends JFrame {

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
					NPrincipal frame = new NPrincipal();
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
	public NPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NPrincipal.class.getResource("/\u00CDcones/LogoMenorAinda.png")));
		setTitle("Welfare - Nutricionista");
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
		
		JLabel Título = DefaultComponentFactory.getInstance().createTitle("Welfare - Cr\u00E9ditos");
		Título.setBounds(0, 0, 614, 61);
		Desktop.add(Título);
		Título.setForeground(Color.WHITE);
		Título.setFont(new Font("Arial", Font.BOLD, 24));
		Título.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel Logo = DefaultComponentFactory.getInstance().createLabel("");
		Logo.setHorizontalAlignment(SwingConstants.CENTER);
		Logo.setBounds(10, 144, 604, 305);
		Desktop.add(Logo);
		Logo.setIcon(new ImageIcon(NPrincipal.class.getResource("/\u00CDcones/LogoMenor.png")));
		
		JTextPane Sobre = new JTextPane();
		Sobre.setText("Este programa foi desenvolvido pela Oglle, somos uma empresa que desenvolve programas para simplificar a sua vida, acesse o nosso site e confira todos os nossos programas:\r\nwww.oglle.com.br");
		Sobre.setOpaque(false);
		Sobre.setForeground(Color.WHITE);
		Sobre.setFont(new Font("Arial", Font.PLAIN, 14));
		Sobre.setEditable(false);
		Sobre.setBounds(10, 72, 604, 61);
		Desktop.add(Sobre);
		
		JLabel Fundo = DefaultComponentFactory.getInstance().createLabel("");
		Fundo.setBounds(0, 0, 624, 460);
		Desktop.add(Fundo);
		Fundo.setIcon(new ImageIcon(NPrincipal.class.getResource("/\u00CDcones/Fundo.png")));
		
		JMenuBar Menu = new JMenuBar();
		Menu.setBounds(0, 0, 624, 21);
		contentPane.add(Menu);
		Menu.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JMenu menupCadastro = new JMenu("Cadastro");
		Menu.add(menupCadastro);
		
		JMenuItem menuPacientes = new JMenuItem("Pacientes");
		menuPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NCadastroPacientes pacientes = new NCadastroPacientes();
		        Desktop.add(pacientes);
		        pacientes.setVisible(true);
			}
		});
		menupCadastro.add(menuPacientes);
		
		JMenu menupConsultas = new JMenu("Consultas");
		Menu.add(menupConsultas);
		
		JMenuItem menuAgendarConsultas = new JMenuItem("Agendar consultas");
		menuAgendarConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NAgendarConsultas agendarconsultas = new NAgendarConsultas();
		        Desktop.add(agendarconsultas);
		        agendarconsultas.setVisible(true);
			}
		});
		
		menupConsultas.add(menuAgendarConsultas);
		
		JMenuItem menuConsultasAgendadas = new JMenuItem("Consultas agendadas");
		menuConsultasAgendadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NConsultasAgendadas consultasagendadas = new NConsultasAgendadas();
		        Desktop.add(consultasagendadas);
		        consultasagendadas.setVisible(true);
			}
		});
		menupConsultas.add(menuConsultasAgendadas);
		
		JMenu menupOrientacao = new JMenu("Orienta\u00E7\u00E3o");
		Menu.add(menupOrientacao);
		
		JMenuItem menuRegistrarOrientacao = new JMenuItem("Registrar orienta\u00E7\u00E3o");
		menuRegistrarOrientacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NOrientaçõesPaciente orientaçõespaciente = new NOrientaçõesPaciente();
		        Desktop.add(orientaçõespaciente);
		        orientaçõespaciente.setVisible(true);
			}
		});
		menupOrientacao.add(menuRegistrarOrientacao);
		
		JMenuItem menuOrientacao = new JMenuItem("Orienta\u00E7\u00E3o");
		menuOrientacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NOrientações orientações = new NOrientações();
		        Desktop.add(orientações);
		        orientações.setVisible(true);
			}
		});
		menupOrientacao.add(menuOrientacao);
		
		JMenu menupMetas = new JMenu("Metas");
		Menu.add(menupMetas);
		
		JMenuItem menuRegistrarMetas = new JMenuItem("Registrar mapa de metas");
		menuRegistrarMetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NRegistrarMetas registrarmetas = new NRegistrarMetas();
		        Desktop.add(registrarmetas);
		        registrarmetas.setVisible(true);
			}
		});
		menupMetas.add(menuRegistrarMetas);
		
		JMenuItem menuMetas = new JMenuItem("Mapa de metas");
		menuMetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NMetas metas = new NMetas();
		        Desktop.add(metas);
		        metas.setVisible(true);
			}
		});
		menupMetas.add(menuMetas);
		
		JMenu menupCardpio = new JMenu("Card\u00E1pio");
		Menu.add(menupCardpio);
		
		JMenuItem menuRegistrarCardápio = new JMenuItem("Registrar card\u00E1pio");
		menuRegistrarCardápio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NRegistrarCardápio registrarcardápio = new NRegistrarCardápio();
		        Desktop.add(registrarcardápio);
		        registrarcardápio.setVisible(true);
			}
		});
		menupCardpio.add(menuRegistrarCardápio);
		
		JMenuItem menuCardápio = new JMenuItem("Card\u00E1pio");
		menuCardápio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NCardápio cardápio = new NCardápio();
		        Desktop.add(cardápio);
		        cardápio.setVisible(true);
			}
		});
		menupCardpio.add(menuCardápio);
		
		JMenu menupOutros = new JMenu("Outros");
		Menu.add(menupOutros);
		
		JMenuItem menuSobre = new JMenuItem("Sobre");
		menuSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NSobre sobre = new NSobre();
		        Desktop.add(sobre);
		        sobre.setVisible(true);
			}
		});
		menupOutros.add(menuSobre);
		
		JMenuItem menuSair = new JMenuItem("Sair");
		menuSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sair = JOptionPane.showConfirmDialog(null, "Deseja Sair?", "Atenção", JOptionPane.YES_NO_OPTION);
		        if (sair == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
			}
		});
		
		JMenuItem menuConfigurações = new JMenuItem("Configura\u00E7\u00F5es");
		menuConfigurações.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NConfigurações configurações = new NConfigurações();
		        Desktop.add(configurações);
		        configurações.setVisible(true);
			}
		});
		menupOutros.add(menuConfigurações);
		menupOutros.add(menuSair);
	}
}

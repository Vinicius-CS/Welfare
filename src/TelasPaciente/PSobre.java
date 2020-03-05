package TelasPaciente;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import Conexão.ModuloConexao;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Dimension;
import javax.swing.JTextPane;

public class PSobre extends JInternalFrame {
	Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PSobre frame = new PSobre();
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
	public PSobre() {
		setMinimumSize(new Dimension(38, 35));
		setBorder(null);
		setBounds(0, -22, 624, 460);
		getContentPane().setLayout(null);
		
		conexao = ModuloConexao.conector();
		
		JLabel Título = DefaultComponentFactory.getInstance().createTitle("Welfare - Cr\u00E9ditos");
		Título.setFont(new Font("Arial", Font.BOLD, 24));
		Título.setForeground(Color.WHITE);
		Título.setHorizontalAlignment(SwingConstants.CENTER);
		Título.setBounds(0, 0, 614, 61);
		getContentPane().add(Título);
		
		JTextPane Sobre = new JTextPane();
		Sobre.setText("Este programa foi desenvolvido pela Oglle, somos uma empresa que desenvolve programas para simplificar a sua vida, acesse o nosso site e confira todos os nossos programas:\r\nwww.oglle.com.br");
		Sobre.setOpaque(false);
		Sobre.setForeground(Color.WHITE);
		Sobre.setFont(new Font("Arial", Font.PLAIN, 14));
		Sobre.setEditable(false);
		Sobre.setBounds(10, 72, 604, 61);
		getContentPane().add(Sobre);
		
		JLabel Logo = new JLabel("");
		Logo.setHorizontalAlignment(SwingConstants.CENTER);
		Logo.setIcon(new ImageIcon(PSobre.class.getResource("/\u00CDcones/LogoMenor.png")));
		Logo.setBounds(10, 144, 604, 305);
		getContentPane().add(Logo);

		JLabel Fundo = DefaultComponentFactory.getInstance().createLabel("");
		Fundo.setIcon(new ImageIcon(PSobre.class.getResource("/\u00CDcones/Fundo.png")));
		Fundo.setBounds(0, 0, 625, 458);
		getContentPane().add(Fundo);

	}
	
	PPrincipal principal = new PPrincipal();
}

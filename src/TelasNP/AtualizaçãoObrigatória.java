package TelasNP;

import java.awt.EventQueue;
import Conex�o.ModuloConexao;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JTextPane;

public class Atualiza��oObrigat�ria extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Atualiza��oObrigat�ria frame = new Atualiza��oObrigat�ria();
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
	public Atualiza��oObrigat�ria() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Atualiza��oObrigat�ria.class.getResource("/\u00CDcones/LogoMenorAinda.png")));
		setTitle("Welfare - Atualiza\u00E7\u00E3o");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 529, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Titulo = DefaultComponentFactory.getInstance().createTitle("Welfare - Atualiza\u00E7\u00E3o");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setFont(new Font("Arial", Font.BOLD, 23));
		Titulo.setForeground(Color.WHITE);
		Titulo.setBounds(0, 0, 523, 41);
		contentPane.add(Titulo);
		
		JLabel imgLogo = DefaultComponentFactory.getInstance().createLabel("");
		imgLogo.setIcon(new ImageIcon(Escolha.class.getResource("/\u00CDcones/LogoBoneco.png")));
		imgLogo.setBounds(25, 71, 150, 150);
		contentPane.add(imgLogo);
		
		JLabel lblStatus = DefaultComponentFactory.getInstance().createLabel("Status:");
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 11));
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setBounds(3, 264, 35, 14);
		contentPane.add(lblStatus);
		
		JLabel OF_ON = DefaultComponentFactory.getInstance().createLabel("Carregando");
		OF_ON.setFont(new Font("Arial", Font.PLAIN, 11));
		OF_ON.setForeground(Color.WHITE);
		OF_ON.setBounds(40, 264, 92, 14);
		contentPane.add(OF_ON);
		
		JButton btnNutricionista = new JButton("");
		btnNutricionista.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNutricionista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.oglle.com.br"));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});
		btnNutricionista.setIcon(new ImageIcon(Atualiza��oObrigat�ria.class.getResource("/\u00CDcones/Atualizar.png")));
		btnNutricionista.setBorderPainted(false);
		btnNutricionista.setContentAreaFilled(false);
		btnNutricionista.setBounds(264, 182, 130, 42);
		contentPane.add(btnNutricionista);
		
		JLabel txtNVers�o = DefaultComponentFactory.getInstance().createLabel("0.0.0.1");
		txtNVers�o.setForeground(Color.WHITE);
		txtNVers�o.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNVers�o.setBounds(484, 264, 39, 14);
		contentPane.add(txtNVers�o);
		
		JLabel txtVers�o = DefaultComponentFactory.getInstance().createLabel("Vers\u00E3o:");
		txtVers�o.setFont(new Font("Arial", Font.PLAIN, 11));
		txtVers�o.setForeground(Color.WHITE);
		txtVers�o.setBounds(442, 264, 39, 14);
		contentPane.add(txtVers�o);
		
		JTextPane Texto = new JTextPane();
		Texto.setForeground(Color.WHITE);
		Texto.setFont(new Font("Arial", Font.PLAIN, 14));
		Texto.setOpaque(false);
		Texto.setEditable(false);
		Texto.setText("Essa \u00E9 uma atualiza\u00E7\u00E3o obrigat\u00F3ria, voc\u00EA n\u00E3o poder\u00E1 utilizar esta vers\u00E3o, por favor, atualize agora mesmo clicando no bot\u00E3o abaixo, ou se preferir, voc\u00EA poder\u00E1 atualizar depois em nosso site:\r\nwww.oglle.com.br");
		Texto.setBounds(185, 52, 328, 108);
		contentPane.add(Texto);
		
		JLabel Fundo = DefaultComponentFactory.getInstance().createLabel("");
		Fundo.setIcon(new ImageIcon(Escolha.class.getResource("/\u00CDcones/Fundo.png")));
		Fundo.setBounds(0, 0, 523, 424);
		contentPane.add(Fundo);
        conexao = ModuloConexao.conector();

        if(conexao != null){
            OF_ON.setText("Conectado");
        }else{
            OF_ON.setText("Desconectado");
        }
	}
}
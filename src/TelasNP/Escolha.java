package TelasNP;

import java.awt.EventQueue;
import Conex�o.ModuloConexao;
import TelasNutricionista.NLogin;
import TelasPaciente.PLogin;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Escolha extends JFrame {

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
					Escolha frame = new Escolha();
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
	public Escolha() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Escolha.class.getResource("/\u00CDcones/LogoMenorAinda.png")));
		setTitle("Welfare");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 529, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Titulo = DefaultComponentFactory.getInstance().createTitle("Welfare");
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
				NLogin nlogin = new NLogin();
				nlogin.setVisible(true);
				dispose();
			}
		});
		btnNutricionista.setIcon(new ImageIcon(Escolha.class.getResource("/\u00CDcones/Nutricionista.png")));
		btnNutricionista.setBorderPainted(false);
		btnNutricionista.setContentAreaFilled(false);
		btnNutricionista.setBounds(208, 120, 130, 42);
		contentPane.add(btnNutricionista);
		
		JButton btnPaciente = new JButton("");
		btnPaciente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPaciente.setContentAreaFilled(false);
		btnPaciente.setBorderPainted(false);
		btnPaciente.setIcon(new ImageIcon(Escolha.class.getResource("/\u00CDcones/Paciente.png")));
		btnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PLogin plogin = new PLogin();
				plogin.setVisible(true);
				dispose();
			}
		});
		btnPaciente.setBounds(348, 120, 130, 42);
		contentPane.add(btnPaciente);
		
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
	
		String Atualiza��oObrigat�ria;
		private String getAtualiza��oObrigat�ria() {
			return Atualiza��oObrigat�ria;
		}

		private void setAtualiza��oObrigat�ria(String atualiza��oObrigat�ria) {
			Atualiza��oObrigat�ria = atualiza��oObrigat�ria;
		}

		String Vers�o;
		private String getVers�o() {
			return Vers�o;
		}

		private void setVers�o(String vers�o) {
			Vers�o = vers�o;
		}

		public void Atualiza��o() {
			String sql = "select Vers�o from atualiza��o";
			
			try {
				pst = conexao.prepareStatement(sql);
				rs = pst.executeQuery();
				
				if(rs.next()) {
					this.setVers�o(rs.getString("Vers�o"));
					Atualiza��o3();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		public void Atualiza��o2() {
			if(this.getVers�o().equals("0.0.0.1")) {
				
			}else{
				if(this.getAtualiza��oObrigat�ria().equals("1")) {
					
				}else {
					Atualiza��oObrigat�ria atualiza��oobrigat�ria = new Atualiza��oObrigat�ria();
					atualiza��oobrigat�ria.setVisible(true);
					dispose();
				}
				JOptionPane.showMessageDialog(null, "Existe uma nova vers�o dispon�vel, baixe em: www.vhasoftwares.ga", "Atualiza��o Dispon�vel", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		public void Atualiza��o3() {
			String sql = "select Atualiza��oObrigat�ria from atualiza��o";
			
			try {
				pst = conexao.prepareStatement(sql);
				rs = pst.executeQuery();
				
				if(rs.next()) {
					this.setAtualiza��oObrigat�ria(rs.getString("Atualiza��oObrigat�ria"));
					Atualiza��o2();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
}
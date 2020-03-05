package TelasNP;

import java.awt.EventQueue;
import Conexão.ModuloConexao;
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
		
		JLabel txtNVersão = DefaultComponentFactory.getInstance().createLabel("0.0.0.1");
		txtNVersão.setForeground(Color.WHITE);
		txtNVersão.setFont(new Font("Arial", Font.PLAIN, 11));
		txtNVersão.setBounds(484, 264, 39, 14);
		contentPane.add(txtNVersão);
		
		JLabel txtVersão = DefaultComponentFactory.getInstance().createLabel("Vers\u00E3o:");
		txtVersão.setFont(new Font("Arial", Font.PLAIN, 11));
		txtVersão.setForeground(Color.WHITE);
		txtVersão.setBounds(442, 264, 39, 14);
		contentPane.add(txtVersão);
		
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
	
		String AtualizaçãoObrigatória;
		private String getAtualizaçãoObrigatória() {
			return AtualizaçãoObrigatória;
		}

		private void setAtualizaçãoObrigatória(String atualizaçãoObrigatória) {
			AtualizaçãoObrigatória = atualizaçãoObrigatória;
		}

		String Versão;
		private String getVersão() {
			return Versão;
		}

		private void setVersão(String versão) {
			Versão = versão;
		}

		public void Atualização() {
			String sql = "select Versão from atualização";
			
			try {
				pst = conexao.prepareStatement(sql);
				rs = pst.executeQuery();
				
				if(rs.next()) {
					this.setVersão(rs.getString("Versão"));
					Atualização3();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		public void Atualização2() {
			if(this.getVersão().equals("0.0.0.1")) {
				
			}else{
				if(this.getAtualizaçãoObrigatória().equals("1")) {
					
				}else {
					AtualizaçãoObrigatória atualizaçãoobrigatória = new AtualizaçãoObrigatória();
					atualizaçãoobrigatória.setVisible(true);
					dispose();
				}
				JOptionPane.showMessageDialog(null, "Existe uma nova versão disponível, baixe em: www.vhasoftwares.ga", "Atualização Disponível", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		public void Atualização3() {
			String sql = "select AtualizaçãoObrigatória from atualização";
			
			try {
				pst = conexao.prepareStatement(sql);
				rs = pst.executeQuery();
				
				if(rs.next()) {
					this.setAtualizaçãoObrigatória(rs.getString("AtualizaçãoObrigatória"));
					Atualização2();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
}
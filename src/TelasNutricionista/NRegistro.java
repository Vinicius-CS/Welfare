package TelasNutricionista;

import java.awt.EventQueue;
import Conexão.ModuloConexao;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Cursor;

public class NRegistro extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
        
    @SuppressWarnings("deprecation")
	private void Registrar(){
        String sql = "insert into adm(Nome, EMail, Telefone, Senha) values(?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtEMail.getText());
            pst.setString(3, txtTelefone.getText());
            pst.setString(4, txtSenha.getText());
            
            if((txtNome.getText().isEmpty()) || (txtEMail.getText().isEmpty()) || (txtTelefone.getText().isEmpty()) || (txtSenha.getText().isEmpty())){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            }else{
            limparCampos();
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Registrado com sucesso!");
                
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Foi encontrado um erro:\n" + e);
        }
    }
    
    private void limparCampos(){
            txtNome.setText(null);
            txtEMail.setText(null);
            txtTelefone.setText(null);
            txtSenha.setText(null);
    }
    
	private JPanel contentPane;
	private JTextField txtEMail;
	private JPasswordField txtSenha;
	private JTextField txtNome;
	private JTextField txtTelefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NRegistro frame = new NRegistro();
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
	public NRegistro() {
			setIconImage(Toolkit.getDefaultToolkit().getImage(NRegistro.class.getResource("/\u00CDcones/LogoMenorAinda.png")));
			setTitle("Welfare - Registro Nutricionista");
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(400, 200, 529, 308);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel Titulo = DefaultComponentFactory.getInstance().createTitle("Welfare - Registro Nutricionista");
			Titulo.setHorizontalAlignment(SwingConstants.CENTER);
			Titulo.setFont(new Font("Arial", Font.BOLD, 24));
			Titulo.setForeground(Color.WHITE);
			Titulo.setBounds(42, 0, 481, 41);
			contentPane.add(Titulo);
			
			JLabel EMail = DefaultComponentFactory.getInstance().createLabel("E-Mail:");
			EMail.setForeground(Color.WHITE);
			EMail.setFont(new Font("Arial", Font.BOLD, 18));
			EMail.setBounds(202, 97, 92, 14);
			contentPane.add(EMail);
			
			txtEMail = new JTextField();
			txtEMail.setFont(new Font("Arial", Font.PLAIN, 12));
			txtEMail.setBounds(202, 112, 280, 19);
			contentPane.add(txtEMail);
			txtEMail.setColumns(10);
			
			JLabel Senha = DefaultComponentFactory.getInstance().createLabel("Senha:");
			Senha.setForeground(Color.WHITE);
			Senha.setFont(new Font("Arial", Font.BOLD, 18));
			Senha.setBounds(202, 187, 92, 14);
			contentPane.add(Senha);
			
			JLabel Telefone = DefaultComponentFactory.getInstance().createLabel("Telefone:");
			Telefone.setForeground(Color.WHITE);
			Telefone.setFont(new Font("Arial", Font.BOLD, 18));
			Telefone.setBounds(202, 142, 92, 14);
			contentPane.add(Telefone);
			
			JLabel lblNome = DefaultComponentFactory.getInstance().createLabel("Nome:");
			lblNome.setForeground(Color.WHITE);
			lblNome.setFont(new Font("Arial", Font.BOLD, 18));
			lblNome.setBounds(202, 52, 92, 14);
			contentPane.add(lblNome);
			
			txtSenha = new JPasswordField();
			txtSenha.setBounds(202, 202, 280, 19);
			contentPane.add(txtSenha);
			
			JButton btnRegistro = new JButton("");
			btnRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Registrar();
				}
			});
			
			txtNome = new JTextField();
			txtNome.setBounds(202, 67, 280, 19);
			contentPane.add(txtNome);
			txtNome.setColumns(10);
			
			txtTelefone = new JTextField();
			txtTelefone.setFont(new Font("Arial", Font.PLAIN, 12));
			txtTelefone.setColumns(10);
			txtTelefone.setBounds(202, 157, 280, 19);
			contentPane.add(txtTelefone);
			btnRegistro.setContentAreaFilled(false);
			btnRegistro.setBorder(null);
			btnRegistro.setBackground(Color.WHITE);
			btnRegistro.setBorderPainted(false);
			btnRegistro.setIcon(new ImageIcon(NLogin.class.getResource("/\u00CDcones/Registrar-se.png")));
			btnRegistro.setBounds(352, 226, 130, 42);
			contentPane.add(btnRegistro);
			
			JButton btnVoltar = new JButton("");
			btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnVoltar.setContentAreaFilled(false);
			btnVoltar.setBorderPainted(false);
			btnVoltar.setIcon(new ImageIcon(NRegistro.class.getResource("/\u00CDcones/VoltarMenor.png")));
			btnVoltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NLogin nlogin = new NLogin();
					nlogin.setVisible(true);
					dispose();
				}
			});
			btnVoltar.setBounds(3, 2, 40, 39);
			contentPane.add(btnVoltar);
			
			JLabel imgLogo = DefaultComponentFactory.getInstance().createLabel("");
			imgLogo.setIcon(new ImageIcon(NLogin.class.getResource("/\u00CDcones/LogoBoneco.png")));
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
			
			JLabel Fundo = DefaultComponentFactory.getInstance().createLabel("");
			Fundo.setIcon(new ImageIcon(NLogin.class.getResource("/\u00CDcones/Fundo.png")));
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
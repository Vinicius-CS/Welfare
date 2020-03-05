package TelasPaciente;

import java.awt.EventQueue;
import Conexão.ModuloConexao;
import TelasNP.Escolha;
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

public class PLogin extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private static String IDPAC;
    private static String EMAILPAC;
	
    public String getIDPAC() {
        return IDPAC;
    }
    
    public void setIDPAC(String IDPAC){
        PLogin.IDPAC = IDPAC;
    }
    
    public String getEMAILPAC() {
        return EMAILPAC;
    }
    
    public void setEMAILPAC(String EMAILPAC){
        PLogin.EMAILPAC = EMAILPAC;
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PLogin frame = new PLogin();
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
	public PLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PLogin.class.getResource("/\u00CDcones/LogoMenorAinda.png")));
		setTitle("Welfare - Login Paciente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 529, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Titulo = DefaultComponentFactory.getInstance().createTitle("Welfare - Login Paciente");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setFont(new Font("Arial", Font.BOLD, 23));
		Titulo.setForeground(Color.WHITE);
		Titulo.setBounds(42, 0, 481, 41);
		contentPane.add(Titulo);
		
		JLabel EMail = DefaultComponentFactory.getInstance().createLabel("E-Mail:");
		EMail.setForeground(Color.WHITE);
		EMail.setFont(new Font("Arial", Font.BOLD, 18));
		EMail.setBounds(202, 71, 92, 14);
		contentPane.add(EMail);
		
		txtEMail = new JTextField();
		txtEMail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEMail.setBounds(202, 89, 280, 35);
		contentPane.add(txtEMail);
		txtEMail.setColumns(10);
		
		JLabel Senha = DefaultComponentFactory.getInstance().createLabel("Senha:");
		Senha.setForeground(Color.WHITE);
		Senha.setFont(new Font("Arial", Font.BOLD, 18));
		Senha.setBounds(202, 139, 92, 14);
		contentPane.add(Senha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(202, 159, 280, 35);
		contentPane.add(txtSenha);
		
		JButton btnEntrar = new JButton("");
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logar();
			}
		});
		btnEntrar.setIcon(new ImageIcon(PLogin.class.getResource("/\u00CDcones/Entrar.png")));
		btnEntrar.setContentAreaFilled(false);
		btnEntrar.setBorderPainted(false);
		btnEntrar.setBorder(null);
		btnEntrar.setBackground(Color.WHITE);
		btnEntrar.setBounds(352, 207, 130, 42);
		contentPane.add(btnEntrar);
		
		JButton btnVoltar = new JButton("");
		btnVoltar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Escolha escolha = new Escolha();
				escolha.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBorderPainted(false);
		btnVoltar.setIcon(new ImageIcon(PLogin.class.getResource("/\u00CDcones/VoltarMenor.png")));
		btnVoltar.setBounds(2, 2, 40, 39);
		contentPane.add(btnVoltar);
		
		JLabel imgLogo = DefaultComponentFactory.getInstance().createLabel("");
		imgLogo.setIcon(new ImageIcon(PLogin.class.getResource("/\u00CDcones/LogoBoneco.png")));
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
		Fundo.setIcon(new ImageIcon(PLogin.class.getResource("/\u00CDcones/Fundo.png")));
		Fundo.setBounds(0, 0, 523, 424);
		contentPane.add(Fundo);
        conexao = ModuloConexao.conector();

        if(conexao != null){
            OF_ON.setText("Conectado");
        }else{
            OF_ON.setText("Desconectado");
        }
	}
	@SuppressWarnings("deprecation")
	public void Logar() {
        String sql = "select * from pacientes where EMail = ? and Senha = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtEMail.getText());
            pst.setString(2, txtSenha.getText());
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                String perfil = rs.getString(6);
                if(perfil.equals("Paciente")){
                PPrincipal principal = new PPrincipal();
                principal.setVisible(true);
                this.setIDPAC(rs.getString("ID"));
                this.setEMAILPAC(rs.getString("EMail"));
                this.dispose();
                conexao.close();
                }else{
                PPrincipal principal = new PPrincipal();
                principal.setVisible(true);
                this.setIDPAC(rs.getString("ID"));
                this.setEMAILPAC(rs.getString("EMail"));
                this.dispose();
                conexao.close();
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "E-mail ou senha inválido!");
            }
            
        } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "Foi encontrado um erro:\n" + e);
        }
    }
    
	private JTextField txtEMail;
	private JPasswordField txtSenha;
}
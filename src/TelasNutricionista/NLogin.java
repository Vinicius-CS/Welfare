package TelasNutricionista;

import java.awt.EventQueue;
import Conexão.ModuloConexao;
import TelasNP.Escolha;
import TelasNP.Licença;
import java.sql.*;
import java.text.SimpleDateFormat;
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

public class NLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private static String ID;
    private static String EMAILNUTRI;
    private static String DIAVENCIMENTO;
	
    public String getID() {
        return ID;
    }
    
    public void setID(String ID){
        NLogin.ID = ID;
    }
    
    public String getEMAILNUTRI() {
        return EMAILNUTRI;
    }
    
    public void setEMAILNUTRI(String EMAILNUTRI){
        NLogin.EMAILNUTRI = EMAILNUTRI;
    }
    
    public String getDIAVENCIMENTO() {
        return DIAVENCIMENTO;
    }
    
    public void setDIAVENCIMENTO(String DIAVENCIMENTO){
        NLogin.DIAVENCIMENTO = DIAVENCIMENTO;
    }
    
	@SuppressWarnings("deprecation")
	public void Logar() {
        String sql = "select * from adm where EMail = ? and Senha = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtEMail.getText());
            pst.setString(2, txtSenha.getText());
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                String perfil = rs.getString(6);
                if(perfil.equals("Administrador")){
                NPrincipal principal = new NPrincipal();
                principal.setVisible(true);
                this.setID(rs.getString("ID"));
                this.setEMAILNUTRI(rs.getString("EMail"));
                this.dispose();
                }else{
                NPrincipal principal = new NPrincipal();
                principal.setVisible(true);
                this.setID(rs.getString("ID"));
                this.setEMAILNUTRI(rs.getString("EMail"));
                this.dispose();
                //Date data = new Date(System.currentTimeMillis());
                //SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy");
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "E-mail ou senha inválido!");
            }
            
        } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "Foi encontrado um erro:\n" + e);
        }
    }
	
	public void Logar2() {
        String sql = "select day(DataVencimento) as DiaVencimento from adm where EMail = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtEMail.getText());
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                this.setDIAVENCIMENTO(rs.getString("DiaVencimento"));
                Date data = new Date(System.currentTimeMillis());
                SimpleDateFormat formatarDate = new SimpleDateFormat("dd");
                
                int DataAtual = Integer.parseInt(formatarDate.format(data));
                int DataVencimento = Integer.parseInt(getDIAVENCIMENTO());

                	int resultado = (DataVencimento - DataAtual);
                	System.out.println(resultado);
                		if(resultado <= 0) {
                			Licença licença = new Licença();
                			licença.setVisible(true);
            				dispose();
                		}else {
                			Logar();
                		}
                }
            conexao.close();
            }
        catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Foi encontrado um erro:\n" + e);
	}
}
                
    
	private JPanel contentPane;
	private JTextField txtEMail;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NLogin frame = new NLogin();
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
	public NLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NLogin.class.getResource("/\u00CDcones/LogoMenorAinda.png")));
		setTitle("Welfare - Login Nutricionista");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 529, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Titulo = DefaultComponentFactory.getInstance().createTitle("Welfare - Login Nutricionista");
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
				Logar2();
			}
		});
		btnEntrar.setIcon(new ImageIcon(NLogin.class.getResource("/\u00CDcones/Entrar.png")));
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
		btnVoltar.setIcon(new ImageIcon(NLogin.class.getResource("/\u00CDcones/VoltarMenor.png")));
		btnVoltar.setBounds(2, 2, 40, 39);
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
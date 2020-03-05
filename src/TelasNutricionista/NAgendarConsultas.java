package TelasNutricionista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import Conexão.ModuloConexao;
import net.proteanit.sql.DbUtils;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.Cursor;

public class NAgendarConsultas extends JInternalFrame {
	Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPaciente;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEMail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NAgendarConsultas frame = new NAgendarConsultas();
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
	public NAgendarConsultas() {
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameOpened(InternalFrameEvent arg0) {
				consultarauto();
			}
		});
		setMinimumSize(new Dimension(38, 35));
		setBorder(null);
		setBounds(0, -22, 624, 460);
		getContentPane().setLayout(null);
		conexao = ModuloConexao.conector();
		
		txtPaciente = new JTextField();
		txtPaciente.setSelectedTextColor(Color.BLACK);
		txtPaciente.setFont(new Font("Arial", Font.PLAIN, 18));
		txtPaciente.setBounds(169, 63, 386, 39);
		getContentPane().add(txtPaciente);
		txtPaciente.setColumns(10);
		
		JLabel BuscarPaciente = DefaultComponentFactory.getInstance().createLabel("Buscar Paciente:");
		BuscarPaciente.setForeground(Color.WHITE);
		BuscarPaciente.setFont(new Font("Arial", Font.BOLD, 18));
		BuscarPaciente.setBounds(10, 63, 149, 39);
		getContentPane().add(BuscarPaciente);
		
		JLabel Título = DefaultComponentFactory.getInstance().createTitle("Agendar Consultas");
		Título.setFont(new Font("Arial", Font.BOLD, 24));
		Título.setForeground(Color.WHITE);
		Título.setHorizontalAlignment(SwingConstants.CENTER);
		Título.setBounds(0, 0, 614, 57);
		getContentPane().add(Título);
		
		JLabel Nome = DefaultComponentFactory.getInstance().createLabel("Nome:");
		Nome.setFont(new Font("Arial", Font.BOLD, 18));
		Nome.setForeground(Color.WHITE);
		Nome.setBounds(35, 204, 59, 19);
		getContentPane().add(Nome);
		
		JLabel Telefone = DefaultComponentFactory.getInstance().createLabel("Telefone:");
		Telefone.setFont(new Font("Arial", Font.BOLD, 18));
		Telefone.setForeground(Color.WHITE);
		Telefone.setBounds(10, 234, 84, 19);
		getContentPane().add(Telefone);
		
		JLabel EMail = DefaultComponentFactory.getInstance().createLabel("E-Mail:");
		EMail.setFont(new Font("Arial", Font.BOLD, 18));
		EMail.setForeground(Color.WHITE);
		EMail.setBounds(34, 264, 60, 19);
		getContentPane().add(EMail);
		
		JLabel Dia = DefaultComponentFactory.getInstance().createLabel("Dia:");
		Dia.setFont(new Font("Arial", Font.BOLD, 18));
		Dia.setForeground(Color.WHITE);
		Dia.setBounds(59, 294, 35, 19);
		getContentPane().add(Dia);
		
		JLabel Hora = DefaultComponentFactory.getInstance().createLabel("Hora:");
		Hora.setFont(new Font("Arial", Font.BOLD, 18));
		Hora.setForeground(Color.WHITE);
		Hora.setBounds(330, 294, 48, 19);
		getContentPane().add(Hora);
		
		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setSelectedTextColor(Color.BLACK);
		txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNome.setForeground(new Color(0, 0, 0));
		txtNome.setBounds(100, 204, 505, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setEditable(false);
		txtTelefone.setSelectedTextColor(Color.BLACK);
		txtTelefone.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTelefone.setForeground(new Color(0, 0, 0));
		txtTelefone.setBounds(100, 235, 505, 19);
		getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);
		
		txtEMail = new JTextField();
		txtEMail.setEditable(false);
		txtEMail.setSelectedTextColor(Color.BLACK);
		txtEMail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEMail.setForeground(new Color(0, 0, 0));
		txtEMail.setBounds(100, 265, 505, 19);
		getContentPane().add(txtEMail);
		txtEMail.setColumns(10);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
		        consultarauto();
			}
		});
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setIcon(new ImageIcon(NAgendarConsultas.class.getResource("/\u00CDcones/PesquisarMenor.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnPesquisar.setBounds(565, 63, 40, 39);
		getContentPane().add(btnPesquisar);
		btnSalvar.setContentAreaFilled(false);
		btnSalvar.setBorderPainted(false);
		btnSalvar.setIcon(new ImageIcon(NAgendarConsultas.class.getResource("/\u00CDcones/Salvar.png")));
		btnSalvar.setBounds(282, 335, 66, 65);
		getContentPane().add(btnSalvar);
		
		tblPesquisa = new JTable();
		tblPesquisa.setAutoCreateRowSorter(true);
		tblPesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPesquisa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setar_campos();
			}
		});
		tblPesquisa.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Telefone", "EMail"
			}
		));
		tblPesquisa.setFont(new Font("Arial", Font.PLAIN, 12));
		tblPesquisa.setBounds(10, 113, 595, 80);
		getContentPane().add(tblPesquisa);
		
		txtIDP = new JTextField();
		txtIDP.setVisible(false);
		txtIDP.setBounds(0, 0, 34, 19);
		getContentPane().add(txtIDP);
		txtIDP.setColumns(10);
		
		txtDia = new JTextField();
		txtDia.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDia.setBounds(100, 294, 217, 19);
		getContentPane().add(txtDia);
		txtDia.setColumns(10);
		
		txtHora = new JTextField();
		txtHora.setFont(new Font("Arial", Font.PLAIN, 12));
		txtHora.setBounds(388, 294, 217, 19);
		getContentPane().add(txtHora);
		txtHora.setColumns(10);
		
		JLabel Fundo = DefaultComponentFactory.getInstance().createLabel("");
		Fundo.setIcon(new ImageIcon(NAgendarConsultas.class.getResource("/\u00CDcones/Fundo.png")));
		Fundo.setBounds(0, 0, 625, 458);
		getContentPane().add(Fundo);

	}
	
	NPrincipal principal = new NPrincipal();
	private JTable tblPesquisa;
	private JTextField txtIDP;
	private JTextField txtDia;
	private JTextField txtHora;
	private void consultar(){
        
        String sql = "select ID, Nome, Telefone, EMail from pacientes where IDADM = ? and Nome like ?";
        
        try {
            NLogin nutrilogin = new NLogin();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nutrilogin.getID());
            pst.setString(2, txtPaciente.getText() + "%");
            rs = pst.executeQuery();
            
            tblPesquisa.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Foi encontrado um erro:\n" + e);
        }
    }
	
	private void consultarauto(){
        
        String sql = "select ID, Nome, Telefone, EMail from pacientes where IDADM = ? and Nome like ?";
        
        try {
            NLogin nutrilogin = new NLogin();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nutrilogin.getID());
            pst.setString(2, txtPaciente.getText() + "%");
            rs = pst.executeQuery();
            
            tblPesquisa.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Foi encontrado um erro:\n" + e);
        }
    }
    
	private void adicionar(){
        String sql = "insert into consultas(Nome, Telefone, EMail, Dia, Hora, IDADM) values(?, ?, ?, ?, ?, ?)";
        try {
            NLogin login = new NLogin();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtTelefone.getText());
            pst.setString(3, txtEMail.getText());
            pst.setString(4, txtDia.getText());
            pst.setString(5, txtHora.getText());
            pst.setString(6, login.getID());
            
            if((txtNome.getText().isEmpty()) || (txtTelefone.getText().isEmpty()) || (txtEMail.getText().isEmpty()) || (txtDia.getText().isEmpty()) || (txtHora.getText().isEmpty())){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            }else{
            limparCampos();
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "A consulta foi agendada!");
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Foi encontrado um erro:\n" + e);
        }
    }
    
    public void setar_campos(){
        int setar = tblPesquisa.getSelectedRow();
        txtIDP.setText(tblPesquisa.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblPesquisa.getModel().getValueAt(setar, 1).toString());
        txtTelefone.setText(tblPesquisa.getModel().getValueAt(setar, 2).toString());
        txtEMail.setText(tblPesquisa.getModel().getValueAt(setar, 3).toString());
    }
    
    private void limparCampos(){
    		txtIDP.setText(null);
    		txtPaciente.setText(null);
            txtNome.setText(null);
            txtTelefone.setText(null);
            txtEMail.setText(null);
            txtDia.setText(null);
            txtHora.setText(null);
    }
}

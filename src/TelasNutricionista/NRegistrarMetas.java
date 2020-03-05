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
import Conex�o.ModuloConexao;
import net.proteanit.sql.DbUtils;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.Cursor;

public class NRegistrarMetas extends JInternalFrame {
	Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPaciente;
	private JTextField txtNome;
	private JTextField txtEMail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NRegistrarMetas frame = new NRegistrarMetas();
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
	public NRegistrarMetas() {
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameOpened(InternalFrameEvent e) {
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
		
		JLabel T�tulo = DefaultComponentFactory.getInstance().createTitle("Registrar Metas");
		T�tulo.setFont(new Font("Arial", Font.BOLD, 24));
		T�tulo.setForeground(Color.WHITE);
		T�tulo.setHorizontalAlignment(SwingConstants.CENTER);
		T�tulo.setBounds(0, 0, 614, 57);
		getContentPane().add(T�tulo);
		
		JLabel Nome = DefaultComponentFactory.getInstance().createLabel("Nome:");
		Nome.setFont(new Font("Arial", Font.BOLD, 18));
		Nome.setForeground(Color.WHITE);
		Nome.setBounds(31, 204, 59, 19);
		getContentPane().add(Nome);
		
		JLabel EMail = DefaultComponentFactory.getInstance().createLabel("E-Mail:");
		EMail.setFont(new Font("Arial", Font.BOLD, 18));
		EMail.setForeground(Color.WHITE);
		EMail.setBounds(30, 235, 60, 19);
		getContentPane().add(EMail);
		
		JLabel Exames = DefaultComponentFactory.getInstance().createLabel("Metas:");
		Exames.setFont(new Font("Arial", Font.BOLD, 18));
		Exames.setForeground(Color.WHITE);
		Exames.setBounds(31, 268, 59, 19);
		getContentPane().add(Exames);
		
		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setSelectedTextColor(Color.BLACK);
		txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNome.setForeground(new Color(0, 0, 0));
		txtNome.setBounds(100, 204, 505, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtEMail = new JTextField();
		txtEMail.setEditable(false);
		txtEMail.setSelectedTextColor(Color.BLACK);
		txtEMail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEMail.setForeground(new Color(0, 0, 0));
		txtEMail.setBounds(100, 235, 505, 19);
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
		btnSalvar.setBounds(24, 317, 66, 65);
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
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"ID", "Nome", "EMail"
			}
		));
		tblPesquisa.setFont(new Font("Arial", Font.PLAIN, 12));
		tblPesquisa.setBounds(10, 113, 595, 80);
		getContentPane().add(tblPesquisa);
		
		txtIDP = new JTextField();
		txtIDP.setVisible(false);
		
		txtMetas = new JTextField();
		txtMetas.setBounds(100, 268, 505, 165);
		getContentPane().add(txtMetas);
		txtMetas.setColumns(10);
		txtIDP.setBounds(0, 0, 34, 19);
		getContentPane().add(txtIDP);
		txtIDP.setColumns(10);
		
		JLabel Fundo = DefaultComponentFactory.getInstance().createLabel("");
		Fundo.setIcon(new ImageIcon(NAgendarConsultas.class.getResource("/\u00CDcones/Fundo.png")));
		Fundo.setBounds(0, 0, 624, 460);
		getContentPane().add(Fundo);

	}
	
	NPrincipal principal = new NPrincipal();
	private JTable tblPesquisa;
	private JTextField txtIDP;
	private JTextField txtMetas;
	private void consultar(){
        
        String sql = "select ID, Nome, EMail from pacientes where IDADM = ? and Nome like ?";
        
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
        
        String sql = "select ID, Nome, EMail from pacientes where IDADM = ? and Nome like ?";
        
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
        String sql = "insert into metas(Nome, EMail, Metas, IDADM) values(?, ?, ?, ?)";
        try {
            NLogin login = new NLogin();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtEMail.getText());
            pst.setString(3, txtMetas.getText());
            pst.setString(4, login.getID());
            
            if((txtNome.getText().isEmpty()) || (txtEMail.getText().isEmpty()) || (txtMetas.getText().isEmpty())){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            }else{
            limparCampos();
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "A meta foi enviada!");
                
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
        txtEMail.setText(tblPesquisa.getModel().getValueAt(setar, 2).toString());
        txtMetas.setText(tblPesquisa.getModel().getValueAt(setar, 3).toString());
    }
    
    private void limparCampos(){
    		txtIDP.setText(null);
    		txtPaciente.setText(null);
            txtNome.setText(null);
            txtEMail.setText(null);
            txtMetas.setText(null);
    }
}

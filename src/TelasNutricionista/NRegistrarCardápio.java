package TelasNutricionista;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import Conexão.ModuloConexao;
import net.proteanit.sql.DbUtils;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import java.awt.Cursor;

public class NRegistrarCardápio extends JInternalFrame {
	Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPaciente;
	private JTextField txtNome;
	private JTextField txtDia;
	private JTextField txtEMail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NRegistrarCardápio frame = new NRegistrarCardápio();
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
	public NRegistrarCardápio() {
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
		
		JLabel Título = DefaultComponentFactory.getInstance().createTitle("Registrar Card\u00E1pio");
		Título.setFont(new Font("Arial", Font.BOLD, 24));
		Título.setForeground(Color.WHITE);
		Título.setHorizontalAlignment(SwingConstants.CENTER);
		Título.setBounds(0, 0, 614, 57);
		getContentPane().add(Título);
		
		JLabel Nome = DefaultComponentFactory.getInstance().createLabel("Nome:");
		Nome.setFont(new Font("Arial", Font.BOLD, 18));
		Nome.setForeground(Color.WHITE);
		Nome.setBounds(31, 204, 59, 19);
		getContentPane().add(Nome);
		
		JLabel Dia = DefaultComponentFactory.getInstance().createLabel("Dia:");
		Dia.setFont(new Font("Arial", Font.BOLD, 18));
		Dia.setForeground(Color.WHITE);
		Dia.setBounds(55, 268, 35, 19);
		getContentPane().add(Dia);
		
		JLabel EMail = DefaultComponentFactory.getInstance().createLabel("E-Mail:");
		EMail.setFont(new Font("Arial", Font.BOLD, 18));
		EMail.setForeground(Color.WHITE);
		EMail.setBounds(30, 235, 60, 19);
		getContentPane().add(EMail);
		
		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setSelectedTextColor(Color.BLACK);
		txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNome.setForeground(new Color(0, 0, 0));
		txtNome.setBounds(100, 204, 505, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtDia = new JTextField();
		txtDia.setSelectedTextColor(Color.BLACK);
		txtDia.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDia.setForeground(new Color(0, 0, 0));
		txtDia.setBounds(100, 268, 505, 19);
		getContentPane().add(txtDia);
		txtDia.setColumns(10);
		
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
		btnPesquisar.setIcon(new ImageIcon(NOrientações.class.getResource("/\u00CDcones/PesquisarMenor.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnPesquisar.setBounds(565, 63, 40, 39);
		getContentPane().add(btnPesquisar);
		btnSalvar.setContentAreaFilled(false);
		btnSalvar.setBorderPainted(false);
		btnSalvar.setIcon(new ImageIcon(NRegistrarCardápio.class.getResource("/\u00CDcones/Salvar.png")));
		btnSalvar.setBounds(24, 289, 66, 65);
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
				"ID", "Nome", "EMail"
			}
		));
		tblPesquisa.setFont(new Font("Arial", Font.PLAIN, 12));
		tblPesquisa.setBounds(10, 113, 595, 80);
		getContentPane().add(tblPesquisa);
		
		txtIDP = new JTextField();
		txtIDP.setVisible(false);
		
		tblSemana = new JTable();
		tblSemana.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Domingo", "Segunda", "Ter\u00E7a", "Quarta", "Quinta", "Sexta", "S\u00E1bado"
			}
		));
		tblSemana.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSemana.setFont(new Font("Arial", Font.PLAIN, 12));
		tblSemana.setAutoCreateRowSorter(true);
		tblSemana.setBounds(100, 338, 505, 16);
		getContentPane().add(tblSemana);
		txtIDP.setBounds(0, 0, 34, 19);
		getContentPane().add(txtIDP);
		txtIDP.setColumns(10);
		
		JLabel txtSemana = DefaultComponentFactory.getInstance().createLabel("Alimentos do card\u00E1pio por semana:");
		txtSemana.setForeground(Color.WHITE);
		txtSemana.setFont(new Font("Arial", Font.BOLD, 18));
		txtSemana.setBounds(100, 298, 505, 19);
		getContentPane().add(txtSemana);
		
		JLabel txtSemana2 = DefaultComponentFactory.getInstance().createLabel(" Domingo    Segunda       Ter\u00E7a       Quarta        Quinta        Sexta        S\u00E1bado\r\n");
		txtSemana2.setHorizontalAlignment(SwingConstants.LEFT);
		txtSemana2.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSemana2.setForeground(Color.WHITE);
		txtSemana2.setBounds(100, 317, 505, 19);
		getContentPane().add(txtSemana2);
		
		JLabel Fundo = DefaultComponentFactory.getInstance().createLabel("");
		Fundo.setIcon(new ImageIcon(NOrientações.class.getResource("/\u00CDcones/Fundo.png")));
		Fundo.setBounds(0, 0, 625, 458);
		getContentPane().add(Fundo);

	}
	
	NPrincipal principal = new NPrincipal();
	private JTable tblPesquisa;
	private JTextField txtIDP;
	private JTable tblSemana;
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
        String sql = "insert into cardápio(Nome, EMail, Dia, Domingo, Segunda, Terça, Quarta, Quinta, Sexta, Sábado, IDADM) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       try {
           NLogin login = new NLogin();
           pst = conexao.prepareStatement(sql);
           pst.setString(1, txtNome.getText());
           pst.setString(2, txtEMail.getText());
           pst.setString(3, txtDia.getText());
           pst.setString(4, (String) tblSemana.getValueAt(0, 0));
           pst.setString(5, (String) tblSemana.getValueAt(0, 1));
           pst.setString(6, (String) tblSemana.getValueAt(0, 2));
           pst.setString(7, (String) tblSemana.getValueAt(0, 3));
           pst.setString(8, (String) tblSemana.getValueAt(0, 4));
           pst.setString(9, (String) tblSemana.getValueAt(0, 5));
           pst.setString(10, (String) tblSemana.getValueAt(0, 6));
           pst.setString(11, login.getID());
           
           
           if((txtNome.getText().isEmpty()) || (txtEMail.getText().isEmpty()) || (txtDia.getText().isEmpty())){
               JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
           }else{
           limparCampos();
           int adicionado = pst.executeUpdate();
           if(adicionado > 0){
               JOptionPane.showMessageDialog(null, "O cardápio foi registrado!");
               
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
        txtDia.setText(tblPesquisa.getModel().getValueAt(setar, 3).toString());
    }
    
    private void limparCampos(){
    		txtIDP.setText(null);
    		txtPaciente.setText(null);
            txtNome.setText(null);
            txtEMail.setText(null);
            txtDia.setText(null);
            ((DefaultTableModel) tblSemana.getModel()).setRowCount(0);
    }
}

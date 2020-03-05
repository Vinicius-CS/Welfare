package TelasPaciente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import Conexão.ModuloConexao;
import TelasNutricionista.NAgendarConsultas;
import TelasNutricionista.NPrincipal;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PConfigurações extends JInternalFrame {
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
					PConfigurações frame = new PConfigurações();
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
	public PConfigurações() {
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameOpened(InternalFrameEvent e) {
				consultar();
			}
		});
		setMinimumSize(new Dimension(38, 35));
		setBorder(null);
		setBounds(0, -22, 624, 460);
		getContentPane().setLayout(null);
		conexao = ModuloConexao.conector();
		
		JLabel Título = DefaultComponentFactory.getInstance().createTitle("Configura\u00E7\u00F5es");
		Título.setFont(new Font("Arial", Font.BOLD, 24));
		Título.setForeground(Color.WHITE);
		Título.setHorizontalAlignment(SwingConstants.CENTER);
		Título.setBounds(0, 0, 614, 57);
		getContentPane().add(Título);

		
		txtIDP = new JTextField();
		txtIDP.setVisible(false);
		txtIDP.setBounds(0, 0, 34, 19);
		getContentPane().add(txtIDP);
		txtIDP.setColumns(10);
		
		tblPesquisa = new JTable();
		tblPesquisa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setar_campos();
			}
		});
		tblPesquisa.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Telefone", "EMail", "Senha"
			}
		));
		tblPesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPesquisa.setFont(new Font("Arial", Font.PLAIN, 12));
		tblPesquisa.setAutoCreateRowSorter(true);
		tblPesquisa.setBounds(10, 69, 595, 16);
		getContentPane().add(tblPesquisa);
		
		JButton btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar();
				consultar();
			}
		});
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBorderPainted(false);
		btnEditar.setContentAreaFilled(false);
		btnEditar.setIcon(new ImageIcon(PConfigurações.class.getResource("/\u00CDcones/Editar.png")));
		btnEditar.setBounds(539, 105, 66, 65);
		getContentPane().add(btnEditar);
		
		JLabel Nome = DefaultComponentFactory.getInstance().createLabel("Nome:");
		Nome.setFont(new Font("Arial", Font.BOLD, 18));
		Nome.setForeground(Color.WHITE);
		Nome.setBounds(37, 98, 59, 19);
		getContentPane().add(Nome);
		
		JLabel Telefone = DefaultComponentFactory.getInstance().createLabel("Telefone:");
		Telefone.setFont(new Font("Arial", Font.BOLD, 18));
		Telefone.setForeground(Color.WHITE);
		Telefone.setBounds(10, 129, 86, 19);
		getContentPane().add(Telefone);
		
		JLabel Senha = DefaultComponentFactory.getInstance().createLabel("Senha:");
		Senha.setFont(new Font("Arial", Font.BOLD, 18));
		Senha.setForeground(Color.WHITE);
		Senha.setBounds(34, 159, 62, 19);
		getContentPane().add(Senha);
		
		txtNome = new JTextField();
		txtNome.setBounds(106, 98, 423, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(106, 129, 423, 19);
		getContentPane().add(txtTelefone);
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(106, 159, 423, 19);
		getContentPane().add(txtSenha);
		
		JLabel Fundo = DefaultComponentFactory.getInstance().createLabel("");
		Fundo.setIcon(new ImageIcon(NAgendarConsultas.class.getResource("/\u00CDcones/Fundo.png")));
		Fundo.setBounds(0, 0, 624, 460);
		getContentPane().add(Fundo);

	}
	
	NPrincipal principal = new NPrincipal();
	private JTextField txtIDP;
	private JTable tblPesquisa;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtSenha;
	
private void consultar(){
        
        String sql = "select ID, Nome, Telefone, EMail, Senha from pacientes where EMail = ?";
        
        try {
            PLogin login = new PLogin();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login.getEMAILPAC());
            rs = pst.executeQuery();
            
            tblPesquisa.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Foi encontrado um erro:\n" + e);
        }
    }
        
        private void alterar(){
        String sql = "update pacientes set Nome = ?, Telefone = ?, Senha = ? where ID = ?";
        try {
            PLogin login = new PLogin();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtTelefone.getText());
            pst.setString(3, txtSenha.getText());
            pst.setString(4, login.getIDPAC());
            
            if((txtNome.getText().isEmpty()) || (txtTelefone.getText().isEmpty()) || (txtSenha.getText().isEmpty())){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            }else{
                int adicionado = pst.executeUpdate();
            
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Suas configurações foram alteradas!");
                
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Foi encontrado um erro:\n" + e);
        
        }
    }
        
        public void setar_campos(){
        int setar = tblPesquisa.getSelectedRow();
        txtNome.setText(tblPesquisa.getModel().getValueAt(setar, 1).toString());
        txtTelefone.setText(tblPesquisa.getModel().getValueAt(setar, 2).toString());
        txtSenha.setText(tblPesquisa.getModel().getValueAt(setar, 4).toString());
    }
}

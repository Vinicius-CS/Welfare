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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.Cursor;

public class NConsultasAgendadas extends JInternalFrame {
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
					NConsultasAgendadas frame = new NConsultasAgendadas();
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
	public NConsultasAgendadas() {
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
		
		JLabel Título = DefaultComponentFactory.getInstance().createTitle("Consultas Agendadas");
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
		Hora.setBounds(331, 294, 48, 19);
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
		
		JButton btnEditar = new JButton("");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
		        consultarauto();
			}
		});
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setIcon(new ImageIcon(NConsultasAgendadas.class.getResource("/\u00CDcones/PesquisarMenor.png")));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnPesquisar.setBounds(565, 63, 40, 39);
		getContentPane().add(btnPesquisar);
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setIcon(new ImageIcon(NConsultasAgendadas.class.getResource("/\u00CDcones/Editar.png")));
		btnEditar.setBounds(255, 348, 66, 65);
		getContentPane().add(btnEditar);
		
		tblPesquisa = new JTable();
		tblPesquisa.setAutoCreateRowSorter(true);
		tblPesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPesquisa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setar_campos();
			}
		});
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remover();
				consultarauto();
			}
		});
		btnExcluir.setIcon(new ImageIcon(NConsultasAgendadas.class.getResource("/\u00CDcones/Excluir.png")));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(331, 348, 66, 65);
		getContentPane().add(btnExcluir);
		tblPesquisa.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Nome", "Telefone", "EMail", "Dia", "Hora"
			}
		));
		tblPesquisa.setFont(new Font("Arial", Font.PLAIN, 12));
		tblPesquisa.setBounds(10, 113, 595, 80);
		getContentPane().add(tblPesquisa);
		
		txtDia = new JTextField();
		txtDia.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDia.setBounds(100, 294, 217, 19);
		getContentPane().add(txtDia);
		txtDia.setColumns(10);
		
		txtHora = new JTextField();
		txtHora.setFont(new Font("Arial", Font.PLAIN, 12));
		txtHora.setBounds(388, 295, 217, 19);
		getContentPane().add(txtHora);
		txtHora.setColumns(10);
		
		JLabel Fundo = DefaultComponentFactory.getInstance().createLabel("");
		Fundo.setIcon(new ImageIcon(NConsultasAgendadas.class.getResource("/\u00CDcones/Fundo.png")));
		Fundo.setBounds(0, 0, 625, 458);
		getContentPane().add(Fundo);
		
		txtIDP = new JTextField();
		txtIDP.setFont(new Font("Arial", Font.PLAIN, 12));
		txtIDP.setVisible(false);
		txtIDP.setBounds(0, 0, 34, 19);
		getContentPane().add(txtIDP);
		txtIDP.setColumns(10);

	}
	
	NPrincipal principal = new NPrincipal();
	private JTable tblPesquisa;
	private JTextField txtIDP;
	private JTextField txtDia;
	private JTextField txtHora;
	
	private void consultar(){
        String sql = "select ID, Nome, Telefone, EMail, Dia, Hora from consultas where IDADM = ? and Nome like ? order by Dia desc";
        
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
        String sql = "select ID, Nome, Telefone, EMail, Dia, Hora from consultas where IDADM = ? and Nome like ? order by Dia desc";
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
	
	private void alterar(){
        String sql = "update consultas set Nome = ?, Telefone = ?, EMail = ?, Dia = ?, Hora = ?, IDADM = ? where ID = ?";
        try {
            NLogin login = new NLogin();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtTelefone.getText());
            pst.setString(3, txtEMail.getText());
            pst.setString(4, txtDia.getText());
            pst.setString(5, txtHora.getText());
            pst.setString(6, login.getID());
            pst.setString(7, txtIDP.getText());
            txtPaciente.setText(txtNome.getText());
            
            if((txtNome.getText().isEmpty()) || (txtTelefone.getText().isEmpty()) || (txtEMail.getText().isEmpty()) || (txtDia.getText().isEmpty()) || (txtHora.getText().isEmpty())){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            }else{
            
            limparCampos();
            int adicionado = pst.executeUpdate();
            
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "A consulta foi alterada!");
                
            }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Foi encontrado um erro:\n" + e);
        
        }
    }
    
	private void remover(){
        int confirmar = JOptionPane.showConfirmDialog(null, "Deseja apagar?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION){
            String sql = "delete from consultas where EMail = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtEMail.getText());
                int apagado = pst.executeUpdate();
            
            if(apagado > 0){
                JOptionPane.showMessageDialog(null, "A consulta foi apagada!");
                limparCampos();
                
            }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Foi encontrado um erro:\n" + e);
            }
        }
    }
    
    
    public void setar_campos(){
        int setar = tblPesquisa.getSelectedRow();
        txtIDP.setText(tblPesquisa.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblPesquisa.getModel().getValueAt(setar, 1).toString());
        txtTelefone.setText(tblPesquisa.getModel().getValueAt(setar, 2).toString());
        txtEMail.setText(tblPesquisa.getModel().getValueAt(setar, 3).toString());
        txtDia.setText(tblPesquisa.getModel().getValueAt(setar, 4).toString());
        txtHora.setText(tblPesquisa.getModel().getValueAt(setar, 5).toString());
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

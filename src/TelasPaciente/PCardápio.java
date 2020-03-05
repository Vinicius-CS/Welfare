package TelasPaciente;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import Conexão.ModuloConexao;
import TelasNutricionista.NOrientações;
import TelasNutricionista.NPrincipal;
import net.proteanit.sql.DbUtils;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PCardápio extends JInternalFrame {
	Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
					PCardápio frame = new PCardápio();
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
	public PCardápio() {
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameOpened(InternalFrameEvent e) {
				consultar();
				consultardias();
			}
		});
		setMinimumSize(new Dimension(38, 35));
		setBorder(null);
		setBounds(0, -22, 624, 460);
		getContentPane().setLayout(null);
		
		conexao = ModuloConexao.conector();
		
		JLabel Título = DefaultComponentFactory.getInstance().createTitle("Card\u00E1pio");
		Título.setFont(new Font("Arial", Font.BOLD, 24));
		Título.setForeground(Color.WHITE);
		Título.setHorizontalAlignment(SwingConstants.CENTER);
		Título.setBounds(0, 0, 614, 57);
		getContentPane().add(Título);
		
		JLabel Nome = DefaultComponentFactory.getInstance().createLabel("Nome:");
		Nome.setFont(new Font("Arial", Font.BOLD, 18));
		Nome.setForeground(Color.WHITE);
		Nome.setBounds(11, 159, 59, 19);
		getContentPane().add(Nome);
		
		JLabel Dia = DefaultComponentFactory.getInstance().createLabel("Dia:");
		Dia.setFont(new Font("Arial", Font.BOLD, 18));
		Dia.setForeground(Color.WHITE);
		Dia.setBounds(35, 219, 35, 19);
		getContentPane().add(Dia);
		
		JLabel EMail = DefaultComponentFactory.getInstance().createLabel("E-Mail:");
		EMail.setFont(new Font("Arial", Font.BOLD, 18));
		EMail.setForeground(Color.WHITE);
		EMail.setBounds(10, 189, 60, 19);
		getContentPane().add(EMail);
		
		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setSelectedTextColor(Color.BLACK);
		txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNome.setForeground(new Color(0, 0, 0));
		txtNome.setBounds(80, 159, 524, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtDia = new JTextField();
		txtDia.setEditable(false);
		txtDia.setSelectedTextColor(Color.BLACK);
		txtDia.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDia.setForeground(new Color(0, 0, 0));
		txtDia.setBounds(80, 219, 524, 19);
		getContentPane().add(txtDia);
		txtDia.setColumns(10);
		
		txtEMail = new JTextField();
		txtEMail.setEditable(false);
		txtEMail.setSelectedTextColor(Color.BLACK);
		txtEMail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEMail.setForeground(new Color(0, 0, 0));
		txtEMail.setBounds(80, 189, 524, 19);
		getContentPane().add(txtEMail);
		txtEMail.setColumns(10);
		
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
				"ID", "Nome", "EMail", "Dia"
			}
		));
		tblPesquisa.setFont(new Font("Arial", Font.PLAIN, 12));
		tblPesquisa.setBounds(10, 68, 595, 80);
		getContentPane().add(tblPesquisa);
		
		txtIDP = new JTextField();
		txtIDP.setVisible(false);
		
		tblSemana = new JTable();
		tblSemana.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblSemana.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSemana.setFont(new Font("Arial", Font.PLAIN, 12));
		tblSemana.setAutoCreateRowSorter(true);
		tblSemana.setBounds(80, 289, 524, 16);
		getContentPane().add(tblSemana);
		txtIDP.setBounds(0, 0, 34, 19);
		getContentPane().add(txtIDP);
		txtIDP.setColumns(10);
		
		JLabel txtSemana = DefaultComponentFactory.getInstance().createLabel("Alimentos do card\u00E1pio por semana:");
		txtSemana.setForeground(Color.WHITE);
		txtSemana.setFont(new Font("Arial", Font.BOLD, 18));
		txtSemana.setBounds(80, 249, 524, 19);
		getContentPane().add(txtSemana);
		
		JLabel txtSemana2 = DefaultComponentFactory.getInstance().createLabel("    Nome    Domingo  Segunda     Ter\u00E7a      Quarta      Quinta       Sexta      S\u00E1bado\r\n");
		txtSemana2.setHorizontalAlignment(SwingConstants.LEFT);
		txtSemana2.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSemana2.setForeground(Color.WHITE);
		txtSemana2.setBounds(80, 268, 524, 19);
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
        
        String sql = "select ID, Nome, EMail, Dia from cardápio where EMail = ?";
        
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
	
	private void consultardias(){
        String sql = "select Nome, Domingo, Segunda, Terça, Quarta, Quinta, Sexta, Sábado from cardápio where EMail = ?";
        
        try {
            PLogin login = new PLogin();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login.getEMAILPAC());
            rs = pst.executeQuery();
            
            tblSemana.setModel(DbUtils.resultSetToTableModel(rs));
            
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
}

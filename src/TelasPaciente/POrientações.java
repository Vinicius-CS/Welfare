package TelasPaciente;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import Conex�o.ModuloConexao;
import TelasNutricionista.NOrienta��es;
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

public class POrienta��es extends JInternalFrame {
	Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtOrienta��o;
	private JTextField txtEMail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POrienta��es frame = new POrienta��es();
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
	public POrienta��es() {
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
		
		JLabel T�tulo = DefaultComponentFactory.getInstance().createTitle("Orienta\u00E7\u00F5es");
		T�tulo.setFont(new Font("Arial", Font.BOLD, 24));
		T�tulo.setForeground(Color.WHITE);
		T�tulo.setHorizontalAlignment(SwingConstants.CENTER);
		T�tulo.setBounds(0, 0, 614, 57);
		getContentPane().add(T�tulo);
		
		JLabel Nome = DefaultComponentFactory.getInstance().createLabel("Nome:");
		Nome.setFont(new Font("Arial", Font.BOLD, 18));
		Nome.setForeground(Color.WHITE);
		Nome.setBounds(53, 155, 59, 19);
		getContentPane().add(Nome);
		
		JLabel Orienta��o = DefaultComponentFactory.getInstance().createLabel("Orienta\u00E7\u00E3o:");
		Orienta��o.setFont(new Font("Arial", Font.BOLD, 18));
		Orienta��o.setForeground(Color.WHITE);
		Orienta��o.setBounds(10, 215, 102, 19);
		getContentPane().add(Orienta��o);
		
		JLabel EMail = DefaultComponentFactory.getInstance().createLabel("E-Mail:");
		EMail.setFont(new Font("Arial", Font.BOLD, 18));
		EMail.setForeground(Color.WHITE);
		EMail.setBounds(52, 185, 60, 19);
		getContentPane().add(EMail);
		
		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setSelectedTextColor(Color.BLACK);
		txtNome.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNome.setForeground(new Color(0, 0, 0));
		txtNome.setBounds(122, 155, 483, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtOrienta��o = new JTextField();
		txtOrienta��o.setEditable(false);
		txtOrienta��o.setSelectedTextColor(Color.BLACK);
		txtOrienta��o.setFont(new Font("Arial", Font.PLAIN, 12));
		txtOrienta��o.setForeground(new Color(0, 0, 0));
		txtOrienta��o.setBounds(122, 215, 483, 218);
		getContentPane().add(txtOrienta��o);
		txtOrienta��o.setColumns(10);
		
		txtEMail = new JTextField();
		txtEMail.setEditable(false);
		txtEMail.setSelectedTextColor(Color.BLACK);
		txtEMail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEMail.setForeground(new Color(0, 0, 0));
		txtEMail.setBounds(122, 185, 483, 19);
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
				"ID", "Nome", "EMail", "Orienta��o"
			}
		));
		tblPesquisa.setFont(new Font("Arial", Font.PLAIN, 12));
		tblPesquisa.setBounds(10, 68, 595, 80);
		getContentPane().add(tblPesquisa);
		
		txtIDP = new JTextField();
		txtIDP.setVisible(false);
		txtIDP.setBounds(0, 0, 34, 19);
		getContentPane().add(txtIDP);
		txtIDP.setColumns(10);
		
		JLabel Fundo = DefaultComponentFactory.getInstance().createLabel("");
		Fundo.setIcon(new ImageIcon(NOrienta��es.class.getResource("/\u00CDcones/Fundo.png")));
		Fundo.setBounds(0, 0, 625, 458);
		getContentPane().add(Fundo);

	}
	
	NPrincipal principal = new NPrincipal();
	private JTable tblPesquisa;
	private JTextField txtIDP;
	private void consultar(){
        
        String sql = "select ID, Nome, EMail, Orienta��o from orienta��o where EMail = ?";
        
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
    
    public void setar_campos(){
        int setar = tblPesquisa.getSelectedRow();
        txtIDP.setText(tblPesquisa.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblPesquisa.getModel().getValueAt(setar, 1).toString());
        txtEMail.setText(tblPesquisa.getModel().getValueAt(setar, 2).toString());
        txtOrienta��o.setText(tblPesquisa.getModel().getValueAt(setar, 3).toString());
    }
}

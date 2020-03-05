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
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PConsultasAgendadas extends JInternalFrame {
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
					PConsultasAgendadas frame = new PConsultasAgendadas();
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
	public PConsultasAgendadas() {
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
		
		JLabel Título = DefaultComponentFactory.getInstance().createTitle("Consultas Agendadas");
		Título.setFont(new Font("Arial", Font.BOLD, 24));
		Título.setForeground(Color.WHITE);
		Título.setHorizontalAlignment(SwingConstants.CENTER);
		Título.setBounds(0, 0, 614, 57);
		getContentPane().add(Título);
		
		tblPesquisa = new JTable();
		tblPesquisa.setAutoCreateRowSorter(true);
		tblPesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPesquisa.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
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
		tblPesquisa.setBounds(10, 68, 595, 365);
		getContentPane().add(tblPesquisa);
		
		txtIDP = new JTextField();
		txtIDP.setVisible(false);
		txtIDP.setBounds(0, 0, 34, 19);
		getContentPane().add(txtIDP);
		txtIDP.setColumns(10);
		
		JLabel Fundo = DefaultComponentFactory.getInstance().createLabel("");
		Fundo.setIcon(new ImageIcon(NOrientações.class.getResource("/\u00CDcones/Fundo.png")));
		Fundo.setBounds(0, 0, 625, 458);
		getContentPane().add(Fundo);

	}
	
	NPrincipal principal = new NPrincipal();
	private JTable tblPesquisa;
	private JTextField txtIDP;
	private void consultar(){
        
        String sql = "select ID, Nome, Telefone, EMail, Dia, Hora from consultas where EMail = ? order by Dia desc";
        
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
}

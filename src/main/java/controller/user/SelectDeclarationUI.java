package controller.user;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Declaration;
import model.User;
import model.UserHolding;
import service.impl.DeclarationServiceImpl;
import service.impl.UserHoldingServiceImpl;
import util.IOTool;
import util.PublicKeyInfrastructure;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.PublicKey;

public class SelectDeclarationUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static UserHoldingServiceImpl userHoldingServiceImpl  = new UserHoldingServiceImpl();
	private static DeclarationServiceImpl declarationServiceImpl = new DeclarationServiceImpl(); 
	private JTable resultTable;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectDeclarationUI frame = new SelectDeclarationUI();
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
	public SelectDeclarationUI() {
		
		User user = (User)IOTool.readFile("user.txt");
		
		setTitle("查詢報關單");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 584, 275);
		contentPane.add(scrollPane);
		
		String[] columnNames = {"報關單號", "是否簽署", "進口商", "出口商", "應繳稅額"};
		tableModel = new DefaultTableModel(columnNames, 0);
		resultTable = new JTable(tableModel);
		scrollPane.setViewportView(resultTable);
		
		
		
		JButton backButton = new JButton("返回");
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserUI userUI = new UserUI();
				userUI.setVisible(true);
				dispose();
			}
		});
		backButton.setBounds(10, 328, 87, 23);
		contentPane.add(backButton);
		
		JButton selectButton = new JButton("查詢");
		selectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<UserHolding> userHoldingList = userHoldingServiceImpl.selectByUsername(user.getUsername());
		        if (!userHoldingList.isEmpty()) {
		            // 清空舊資料
		            tableModel.setRowCount(0);

		            for (UserHolding i : userHoldingList) {
		                Declaration temp = declarationServiceImpl.selectByDeclarationId(i.getDeclarationId());

		                String signStatus;
		                if (temp.getDigitalSignature() != null && temp.getPublicKey() != null) {
		                    // 驗證簽章
		                    boolean valid = false;
		                   
		                    try {
		                        String data = declarationServiceImpl.buildDataString(temp); // 和簽署時相同
		                        byte[] publicKeyBytes = java.util.Base64.getDecoder().decode(temp.getPublicKey());
	                            PublicKey officerPublicKey = PublicKeyInfrastructure.decodePublicKey(publicKeyBytes);
		                        valid = util.DigitalSignature.verify(
		                                data,
		                                temp.getDigitalSignature(),
		                                officerPublicKey
		                                
		                        );
		                    } catch (Exception ex) {
		                        ex.printStackTrace();
		                    }
		                    signStatus = valid ? "簽章正確 ✅" : "簽章無效 ❌";
		                } else {
		                    signStatus = "尚未簽署 ❌";
		                }

		                tableModel.addRow(new Object[]{
		                        temp.getDeclarationId(),
		                        signStatus,
		                        temp.getImporterName(),
		                        temp.getExporterName(),
		                        temp.getTaxFound()
		                });
		            }
		        } else {
		            JOptionPane.showMessageDialog(
		                    SelectDeclarationUI.this,
		                    "查無報關單",
		                    "查詢失敗",
		                    JOptionPane.ERROR_MESSAGE
		            );
		        }
			}
		});
		selectButton.setBounds(244, 301, 87, 23);
		contentPane.add(selectButton);

		
	}

}

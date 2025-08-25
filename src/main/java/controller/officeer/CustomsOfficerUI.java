package controller.officeer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.LoginUI;
import model.Declaration;
import service.impl.DeclarationServiceImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomsOfficerUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private DeclarationServiceImpl declarationServiceImpl = new DeclarationServiceImpl();
    private PrivateKey privateKey; // 從登入/註冊傳入
    private String publicKey;
    
    // 建構子接受私鑰
    public CustomsOfficerUI(PrivateKey privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        setTitle("海關人員 - 報關單管理");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 表格欄位名稱
        String[] columnNames = {"ID", "進口商", "出口商", "狀態", "簽章"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton signButton = new JButton("簽署");
        JButton rejectButton = new JButton("退回");
        JPanel buttonPanel = new JPanel();
        
        JButton backButton = new JButton("登出");
        backButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		LoginUI loginUI = new LoginUI();
        		loginUI.setVisible(true);
        		dispose();
        	}
        });
        buttonPanel.add(backButton);
        buttonPanel.add(signButton);
        buttonPanel.add(rejectButton);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // 載入資料
        loadDeclarations();

        // 簽署
        signButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "請先選擇一筆報關單！");
                return;
            }

            String declarationId = (String) tableModel.getValueAt(selectedRow, 0);
            Declaration declaration = declarationServiceImpl.selectByDeclarationId(declarationId);
            declaration.setStatus("已簽署");
            declaration.setPublicKey(publicKey);
            declarationServiceImpl.declarationSign(declaration, privateKey);
            declarationServiceImpl.update(declaration);                  

            JOptionPane.showMessageDialog(this, "報關單已簽署！");
            loadDeclarations();
        });

        // 退回
        rejectButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "請先選擇一筆報關單！");
                return;
            }

            String declarationId = (String) tableModel.getValueAt(selectedRow, 0);
            Declaration declaration = declarationServiceImpl.selectByDeclarationId(declarationId);
            declaration.setStatus("退回");
            declarationServiceImpl.update(declaration);

            JOptionPane.showMessageDialog(this, "報關單已退回！");
            loadDeclarations();
        });
    }

    // 載入所有報關單到 JTable
    private void loadDeclarations() {
        tableModel.setRowCount(0); // 清空
        List<Declaration> declarations = declarationServiceImpl.getAllDeclarations();
        for (Declaration d : declarations) {
            tableModel.addRow(new Object[]{
                    d.getDeclarationId(),
                    d.getImporterName(),
                    d.getExporterName(),
                    d.getStatus(),
                    d.getDigitalSignature() == null ? "" : d.getDigitalSignature()
            });
        }
    }
}

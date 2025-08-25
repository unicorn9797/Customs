package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.officeer.CustomsOfficerUI;

import controller.user.UserUI;
import model.User;
import service.impl.UserServiceImpl;
import util.IOTool;
import util.PublicKeyInfrastructure;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameText;
	private JTextField passwordText;
	private static UserServiceImpl userServiceImpl = new UserServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
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
	public LoginUI() {
		setTitle("報單簽章模擬系統");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("你家關稅20%");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 32));
		lblNewLabel.setBounds(100, 24, 203, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("報單簽章模擬系統");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 32));
		lblNewLabel_1.setBounds(71, 65, 267, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("帳號:");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(26, 146, 46, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("密碼:");
		lblNewLabel_2_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(26, 187, 46, 31);
		contentPane.add(lblNewLabel_2_1);
		
		usernameText = new JTextField();
		usernameText.setBounds(71, 155, 96, 21);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		passwordText = new JTextField();
		passwordText.setBounds(71, 196, 96, 21);
		contentPane.add(passwordText);
		passwordText.setColumns(10);
		
		JButton login = new JButton("登入");
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = usernameText.getText();
				String password = passwordText.getText();
				User temp = userServiceImpl.Login(username, password);
				if(temp == null)
					JOptionPane.showMessageDialog(LoginUI.this, "請檢察帳號密碼是否正確", "登入失敗", JOptionPane.WARNING_MESSAGE);
				else
				{
					JOptionPane.showMessageDialog(LoginUI.this, "登入成功", "", JOptionPane.INFORMATION_MESSAGE);
					if(temp.getRole().equals("申請者"))
					{
						IOTool.saveFile(temp, "user.txt");
						UserUI userUI = new UserUI();
						userUI.setVisible(true);
						dispose();
					}
					else if(temp.getRole().equals("海關人員"))
					{
						IOTool.saveFile(temp, "officeer.txt");
						byte[] privateKeyBytes = java.util.Base64.getDecoder().decode(temp.getPrivateKey());
						byte[] publicKeyBytes = java.util.Base64.getDecoder().decode(temp.getPublicKey());
						PrivateKey officerPrivateKey = null;
						//PublicKey officerPublicKey = null;
						try {
							officerPrivateKey = PublicKeyInfrastructure.decodePrivateKey(privateKeyBytes);
							//officerPublicKey = PublicKeyInfrastructure.decodePublicKey(publicKeyBytes);
						} catch (InvalidKeySpecException | NoSuchAlgorithmException e1) {							
							e1.printStackTrace();
						}
						CustomsOfficerUI officerUI = new CustomsOfficerUI(officerPrivateKey, temp.getPublicKey());
						officerUI.setVisible(true);
						dispose();
					}
				}
			}
		});
		login.setBounds(177, 212, 87, 23);
		contentPane.add(login);
		
		JButton btnNewButton = new JButton("註冊");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterUI registerUI = new RegisterUI();
				registerUI.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(282, 212, 87, 23);
		contentPane.add(btnNewButton);

	}
}

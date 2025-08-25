package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import service.impl.UserServiceImpl;
import util.PublicKeyInfrastructure;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.KeyPair;

import javax.swing.JComboBox;

public class RegisterUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameText;
	private JTextField usernameText;
	private JTextField passwordText;
	private UserServiceImpl userServiceImpl = new UserServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUI frame = new RegisterUI();
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
	public RegisterUI() {
		setTitle("註冊");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("姓名:");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel.setBounds(26, 44, 46, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("帳號:");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(26, 83, 46, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("密碼:");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(26, 119, 46, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("身分:");
		lblNewLabel_3.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(26, 165, 46, 15);
		contentPane.add(lblNewLabel_3);
		
		nameText = new JTextField();
		nameText.setBounds(70, 45, 96, 21);
		contentPane.add(nameText);
		nameText.setColumns(10);
		
		usernameText = new JTextField();
		usernameText.setBounds(70, 84, 96, 21);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		
		passwordText = new JTextField();
		passwordText.setBounds(70, 120, 96, 21);
		contentPane.add(passwordText);
		passwordText.setColumns(10);
		
		JComboBox<String> roleComboBox = new JComboBox<>(new String[] {"申請者","海關人員"});
		roleComboBox.setBounds(70, 165, 96, 23);
		contentPane.add(roleComboBox);
		
		JButton registerButton = new JButton("註冊");
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = nameText.getText();
				String username = usernameText.getText();
				String password = passwordText.getText();
				String role = (String)roleComboBox.getSelectedItem();
				User temp = new User(name,username,password,role);
				// 如果是海關人員，生成 PKI KeyPair
		        if ("海關人員".equals(role)) {
		            try {
		                KeyPair keyPair = PublicKeyInfrastructure.generateKeyPair();
		                String publicKeyStr = PublicKeyInfrastructure.encodeKey(keyPair.getPublic());
		                String privateKeyStr = PublicKeyInfrastructure.encodeKey(keyPair.getPrivate());

		                temp.setPublicKey(publicKeyStr);
		                temp.setPrivateKey(privateKeyStr); // 模擬存入 DB
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(RegisterUI.this, "生成金鑰失敗", "錯誤", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		        }
				
				if(userServiceImpl.addUser(temp))									
					JOptionPane.showMessageDialog(RegisterUI.this, "註冊成功", "", JOptionPane.INFORMATION_MESSAGE);
				else				
					JOptionPane.showMessageDialog(RegisterUI.this, "帳號重複，請換一個", "註冊失敗", JOptionPane.WARNING_MESSAGE);
			}
		});
		registerButton.setBounds(173, 187, 87, 23);
		contentPane.add(registerButton);
		
		JButton backButton = new JButton("返回");
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI loginUI = new LoginUI();
				loginUI.setVisible(true);
				dispose();
			}
		});
		backButton.setBounds(270, 187, 87, 23);
		contentPane.add(backButton);
		
		

	}

}

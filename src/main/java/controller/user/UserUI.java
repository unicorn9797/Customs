package controller.user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginUI;
import model.User;
import util.IOTool;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class UserUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserUI frame = new UserUI();
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
	public UserUI() {
		setTitle("報關服務");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		User user = (User)IOTool.readFile("user.txt");
		
		JButton addButton = new JButton("新增報關單");
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddDeclarationUI addDeclarationUI = new AddDeclarationUI();
				addDeclarationUI.setVisible(true);
				dispose();
			}
		});
		addButton.setBounds(160, 87, 123, 23);
		contentPane.add(addButton);
		
		JButton selectButton = new JButton("查詢報關單");
		selectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SelectDeclarationUI selectDeclarationUI = new SelectDeclarationUI();
				selectDeclarationUI.setVisible(true);
				dispose();
			}
		});
		selectButton.setBounds(160, 156, 123, 23);
		contentPane.add(selectButton);
		
		JLabel userHelloText = new JLabel("");
		userHelloText.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		userHelloText.setBounds(10, 22, 335, 23);
		contentPane.add(userHelloText);
		userHelloText.setText("申請者" + user.getName() + "您好，歡迎你使用本服務");
		
		JButton logoutButton = new JButton("登出");
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI loginUI = new LoginUI();
				loginUI.setVisible(true);
				dispose();
			}
		});
		logoutButton.setBounds(23, 217, 87, 23);
		contentPane.add(logoutButton);

	}

}

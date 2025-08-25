package controller.user;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import model.Declaration;
import model.Goods;
import model.Tax;
import model.Transport;
import model.User;
import model.UserHolding;
import service.impl.DeclarationServiceImpl;
import service.impl.GoodsServiceImpl;
import service.impl.TaxServiceImpl;
import service.impl.TransportServiceImpl;
import service.impl.UserHoldingServiceImpl;
import util.DataBaseConnectionPool;
import util.IOTool;
import util.IdGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class AddDeclarationUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField importerNameText;
	private JTextField exporterNameText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDeclarationUI frame = new AddDeclarationUI();
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
	
	private JPanel cardPanel;//宣告成成員變數，讓其他地方都能存取
	private JTextField itemNameText;
	private JTextField originCountryText;
	private JTextField quantityText;
	private JTextField unitPriceText;
	private JTextField grossWeightText;
	private JTextField netWeightText;
	private JTextField hsCodeText;
	private JTextField originOtherText;
	private JTable taxTable;
	private JComboBox<String> typeComboBox;
	private JComboBox<String> originCountryComboBox;
	private JComboBox<String> quantityUnitComboBox;
	private JComboBox<String> unitPriceUnitComboBox;
	private JComboBox<String> weightUnitComboBox;	
	private JComboBox<String> vesselOrFlightComboBox;
	private JComboBox<String> portOfLoadingComboBox;
	private JComboBox<String> portOfDischargeComboBox;
	private JDateChooser departureDateChooser;
	private JDateChooser arrivalDateChooser;
	private List<Goods> goodsList = new ArrayList<>();
	private List<Tax> taxList = new ArrayList<>();
	private JTextField totalPriceText;
	private static DeclarationServiceImpl declarationServiceImpl = new DeclarationServiceImpl();
	private static GoodsServiceImpl goodsServiceImpl = new GoodsServiceImpl();
	private static TaxServiceImpl taxServiceImpl = new TaxServiceImpl();
	private static TransportServiceImpl transportServiceImpl = new TransportServiceImpl();
	private static UserHoldingServiceImpl userHoldingServiceImpl = new UserHoldingServiceImpl();
			
	
	public AddDeclarationUI() {
		User user = (User)IOTool.readFile("user.txt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cardPanel = new JPanel();
		cardPanel.setBounds(0, 0, 517, 345);
		contentPane.add(cardPanel);
		cardPanel.setLayout(new CardLayout());
		
		JPanel informationCard = new JPanel();
		cardPanel.add(informationCard, "informationCard");
		informationCard.setLayout(null);
		
		JButton toGoodsButton = new JButton("下一步");
		toGoodsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCard("goodsCard");				
			}
		});
		toGoodsButton.setBounds(400, 301, 87, 23);
		informationCard.add(toGoodsButton);
		
		JLabel lblNewLabel = new JLabel("進口人:");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel.setBounds(119, 152, 64, 28);
		informationCard.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("出口人:");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(119, 217, 64, 28);
		informationCard.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("類型:");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(119, 87, 64, 28);
		informationCard.add(lblNewLabel_2);
		
		typeComboBox = new JComboBox<>(new String[] {"請選擇", "進口", "出口"});
		typeComboBox.setBounds(176, 89, 64, 23);
		informationCard.add(typeComboBox);
		
		
		importerNameText = new JTextField();
		importerNameText.setBounds(176, 160, 96, 21);
		informationCard.add(importerNameText);
		importerNameText.setColumns(10);
		
		exporterNameText = new JTextField();
		exporterNameText.setBounds(176, 225, 96, 21);
		informationCard.add(exporterNameText);
		exporterNameText.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("基本資料");
		lblNewLabel_3.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(223, 10, 87, 43);
		informationCard.add(lblNewLabel_3);
		
		JPanel goodsCard = new JPanel();
		cardPanel.add(goodsCard, "goodsCard");
		goodsCard.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("貨物資料");
		lblNewLabel_4.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(218, 10, 85, 30);
		goodsCard.add(lblNewLabel_4);
		
		JButton backToInformationButton = new JButton("上一步");
		backToInformationButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCard("informationCard");
			}
		});
		backToInformationButton.setBounds(322, 312, 87, 23);
		goodsCard.add(backToInformationButton);		
		
		
		JLabel lblNewLabel_8 = new JLabel("貨物名稱:");
		lblNewLabel_8.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(10, 64, 79, 28);
		goodsCard.add(lblNewLabel_8);
		
		itemNameText = new JTextField();
		itemNameText.setBounds(83, 72, 96, 21);
		goodsCard.add(itemNameText);
		itemNameText.setColumns(10);
		
		JLabel lblNewLabel_8_1 = new JLabel("HS Code:");
		lblNewLabel_8_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_1.setBounds(10, 117, 79, 28);
		goodsCard.add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_8_2 = new JLabel("原產地:");
		lblNewLabel_8_2.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_2.setBounds(10, 172, 79, 28);
		goodsCard.add(lblNewLabel_8_2);
		
		originCountryComboBox = new JComboBox<>();
		originCountryComboBox.setBounds(83, 180, 120, 21);
		
		// 加入常見國家選項
		String[] countries = {"台灣", "美國", "中國", "日本", "越南", "其他"};
		for (String c : countries) {
		    originCountryComboBox.addItem(c);
		}

		goodsCard.add(originCountryComboBox);
		
		// 如果選「其他」，才啟用輸入框
		originOtherText = new JTextField();
		originOtherText.setBounds(83, 210, 100, 21);
		originOtherText.setColumns(10);
		originOtherText.setEnabled(false); // 預設不可輸入
		goodsCard.add(originOtherText);

		originCountryComboBox.addActionListener(e -> {
		    String selected = (String) originCountryComboBox.getSelectedItem();
		    if ("其他".equals(selected)) {
		        originOtherText.setEnabled(true);
		        originOtherText.requestFocus();
		    } else {
		        originOtherText.setEnabled(false);
		        originOtherText.setText(""); // 清空其他輸入
		    }
		});
		
		JLabel lblNewLabel_8_2_1 = new JLabel("數量:");
		lblNewLabel_8_2_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_2_1.setBounds(10, 230, 79, 28);
		goodsCard.add(lblNewLabel_8_2_1);
		
		quantityText = new JTextField();
		quantityText.setBounds(83, 238, 96, 21);
		goodsCard.add(quantityText);
		quantityText.setColumns(10);
		
		
		
		JLabel lblNewLabel_8_2_1_1 = new JLabel("單價:");
		lblNewLabel_8_2_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_2_1_1.setBounds(266, 63, 52, 28);
		goodsCard.add(lblNewLabel_8_2_1_1);
		
		unitPriceText = new JTextField();
		unitPriceText.setBounds(314, 71, 96, 21);
		goodsCard.add(unitPriceText);
		unitPriceText.setColumns(10);
		
		JLabel lblNewLabel_8_2_1_1_1 = new JLabel("總價:");
		lblNewLabel_8_2_1_1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_2_1_1_1.setBounds(266, 116, 52, 28);
		goodsCard.add(lblNewLabel_8_2_1_1_1);
		
		JLabel lblNewLabel_8_2_1_1_2 = new JLabel("毛重:");
		lblNewLabel_8_2_1_1_2.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_2_1_1_2.setBounds(266, 171, 52, 28);
		goodsCard.add(lblNewLabel_8_2_1_1_2);
		
		grossWeightText = new JTextField();
		grossWeightText.setBounds(314, 179, 96, 21);
		goodsCard.add(grossWeightText);
		grossWeightText.setColumns(10);
		
		JLabel lblNewLabel_8_2_1_1_2_1 = new JLabel("淨重:");
		lblNewLabel_8_2_1_1_2_1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_2_1_1_2_1.setBounds(266, 229, 52, 28);
		goodsCard.add(lblNewLabel_8_2_1_1_2_1);
		
		netWeightText = new JTextField();
		netWeightText.setBounds(314, 237, 96, 21);
		goodsCard.add(netWeightText);
		netWeightText.setColumns(10);
		
		hsCodeText = new JTextField();
		hsCodeText.setBounds(83, 125, 96, 21);
		goodsCard.add(hsCodeText);
		hsCodeText.setEditable(false); // 使用者不能直接輸入
		goodsCard.add(hsCodeText);
		hsCodeText.setColumns(10);
		
		
		
		quantityUnitComboBox = new JComboBox<>(new String[] {"件", "箱", "公斤"});
		quantityUnitComboBox.setBounds(189, 237, 52, 23);
		goodsCard.add(quantityUnitComboBox);
		
		
		unitPriceUnitComboBox = new JComboBox<>(new String[] { "TWD/件", "TWD/kg"});
		unitPriceUnitComboBox.setBounds(420, 72, 73, 23);
		goodsCard.add(unitPriceUnitComboBox);
		
		weightUnitComboBox = new JComboBox<>(new String[] {"kg(公斤)", "t(公噸)"});
		weightUnitComboBox.setBounds(420, 179, 73, 23);
		goodsCard.add(weightUnitComboBox);
		
		JComboBox<String> comboBox = new JComboBox<>(new String[] {"kg(公斤)", "t(公噸)"});
		comboBox.setBounds(420, 237, 73, 23);
		goodsCard.add(comboBox);
		
		// 監聽貨物名稱文字變化，自動查 HSCode
		itemNameText.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) { updateHSCode(); }
		    @Override
		    public void removeUpdate(DocumentEvent e) { updateHSCode(); }
		    @Override
		    public void changedUpdate(DocumentEvent e) { updateHSCode(); }

		    private void updateHSCode() {
		        String goodsName = itemNameText.getText().trim();
		        if (!goodsName.isEmpty()) {
		            hsCodeText.setText(fetchHSCode(goodsName));
		        } else {
		            hsCodeText.setText("");
		        }
		    }
		});	
		
		JButton addToGoodsListButton = new JButton("新增至貨物清單");
		addToGoodsListButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Goods temp = getGoodsData();
				goodsList.add(temp);
				itemNameText.setText("");
				hsCodeText.setText("");
				unitPriceText.setText("");
				grossWeightText.setText("");
				netWeightText.setText("");	
				quantityText.setText("");
			}
		});
		
		
		
		
		
		addToGoodsListButton.setBounds(27, 282, 155, 23);
		goodsCard.add(addToGoodsListButton);
		
		JPanel taxCard = new JPanel();
		cardPanel.add(taxCard, "taxCard");
		taxCard.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("稅務資料");
		lblNewLabel_5.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(221, 10, 80, 28);
		taxCard.add(lblNewLabel_5);
		
		
		
		JButton backToGoodsButton = new JButton("上一步");
		backToGoodsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCard("goodsCard");
			}
		});
		backToGoodsButton.setBounds(331, 312, 87, 23);
		taxCard.add(backToGoodsButton);
		
		JButton toTransportButton = new JButton("下一步");
		toTransportButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCard("transportCard");
			}
		});
		toTransportButton.setBounds(420, 312, 87, 23);
		taxCard.add(toTransportButton);
		
		String[] taxTableColumns = {"貨物名稱", "HS Code", "關稅(%)", "增值稅(%)", "其他稅(%)", "總稅額(%)"};
		DefaultTableModel taxTableModel = new DefaultTableModel(taxTableColumns, 0);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 46, 507, 246);
		taxCard.add(scrollPane);		
		taxTable = new JTable(taxTableModel);
		scrollPane.setViewportView(taxTable);
		
		//稅務範例
//		Tax example = new Tax();
//		example.setItemName("範例貨物");
//		example.setHsCode("8888888");
//		example.setCustomsDuty(20.0);
//		example.setVat(5.0);
//		example.setOtherTax(0.0);
//		taxTableModel.addRow(
//				new Object[] {
//						example.getItemName(),
//						example.getHsCode(),
//						example.getCustomsDuty(),
//						example.getVat(),
//						example.getOtherTax(),
//						example.getTotalTax()
//				});
		//List<Tax> taxList = new ArrayList<Tax>();
//		for(Goods i : goodsList)
//		{
//			Tax temp = taxOfGoods(i);
//			taxList.add(temp);
//			taxTableModel.addRow(new Object[] {
//					temp.getItemName(),
//					temp.getHsCode(),
//					temp.getCustomsDuty(),
//					temp.getVat(),
//					temp.getOtherTax(),
//					temp.getTotalTax()
//			});
//		}
		JButton toTaxButton = new JButton("下一步");//goodsCard的按鈕但因為要更新TaxCard所以放在Tax這邊
		toTaxButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCard("taxCard");
				for(Goods i : goodsList)
					{
						Tax temp = taxOfGoods(i);
						taxList.add(temp);
						taxTableModel.addRow(new Object[] {
								temp.getItemName(),
								temp.getHsCode(),
								temp.getCustomsDuty(),
								temp.getVat(),
								temp.getOtherTax(),
								temp.getTotalTax()
						});
					}
			}
		});
		toTaxButton.setBounds(420, 312, 87, 23);
		goodsCard.add(toTaxButton);
		
		totalPriceText = new JTextField();
		totalPriceText.setBounds(313, 125, 96, 21);
		goodsCard.add(totalPriceText);
		totalPriceText.setColumns(10);
		
		JLabel lblNewLabel_8_2_1_1_3 = new JLabel("NTD");
		lblNewLabel_8_2_1_1_3.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_2_1_1_3.setBounds(420, 117, 52, 28);
		goodsCard.add(lblNewLabel_8_2_1_1_3);
		
		
		// 為單價和數量欄位添加 DocumentListener
		DocumentListener priceListener = new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) { updateTotalPrice(); }
		    @Override
		    public void removeUpdate(DocumentEvent e) { updateTotalPrice(); }
		    @Override
		    public void changedUpdate(DocumentEvent e) { updateTotalPrice(); }
		};

		unitPriceText.getDocument().addDocumentListener(priceListener);
		quantityText.getDocument().addDocumentListener(priceListener);
		
		
		
		JPanel transportCard = new JPanel();
		cardPanel.add(transportCard, "transportCard");
		transportCard.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("運輸資料");
		lblNewLabel_6.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(216, 20, 90, 21);
		transportCard.add(lblNewLabel_6);
		
		JButton backToTaxButton = new JButton("上一步");
		backToTaxButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCard("taxCard");
			}
		});
		backToTaxButton.setBounds(333, 312, 87, 23);
		transportCard.add(backToTaxButton);
		
		
		
		JLabel lblNewLabel_8_3 = new JLabel("船名/航班公司:");
		lblNewLabel_8_3.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_3.setBounds(23, 83, 115, 28);
		transportCard.add(lblNewLabel_8_3);
		
		String[] companies = {
			    "Evergreen (長榮海運)", "Yang Ming (陽明海運)", "Wan Hai (萬海航運)",
			    "Maersk (快桅航運)", "COSCO (中遠海運)", "CMA CGM (達飛通運)",
			    "China Airlines (華航)", "EVA Air (長榮航空)",
			    "Cathay Pacific (國泰航空)", "Singapore Airlines (新加坡航空)",
			    "Emirates (阿聯酋航空)", "Lufthansa (漢莎航空)"
			};
			vesselOrFlightComboBox = new JComboBox<>(companies);
			vesselOrFlightComboBox.setEditable(true); // 允許使用者手動輸入
			vesselOrFlightComboBox.setBounds(140, 88, 228, 23);
			transportCard.add(vesselOrFlightComboBox);
		
		JLabel lblNewLabel_8_4 = new JLabel("裝貨港/機場:");
		lblNewLabel_8_4.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_4.setBounds(23, 150, 115, 28);
		transportCard.add(lblNewLabel_8_4);
		
		String[] portsLoading = {
			    "Kaohsiung Port (高雄港)", "Keelung Port (基隆港)", "Taichung Port (台中港)",
			    "Taipei Songshan Airport (松山機場)", "Taoyuan Intl Airport (桃園機場)", "Kaohsiung Intl Airport (小港機場)",
			    "HKG - Hong Kong Intl (香港國際機場)", "LAX - Los Angeles Intl (洛杉磯國際機場)",
			    "FRA - Frankfurt Intl (法蘭克福國際機場)", "JFK - New York JFK Intl (紐約 約翰甘迺迪機場)",
			    "NRT - Tokyo Narita (東京成田機場)", "SIN - Singapore Changi (新加坡樟宜機場)",
			    "Shanghai Port (上海港)", "Hong Kong Port (香港港)", "Busan Port (釜山港)",
			    "Los Angeles Port(洛杉磯港)", "Rotterdam Port (鹿特丹港)", "Singapore Port (新加坡港)"
			};
			portOfLoadingComboBox = new JComboBox<>(portsLoading);
			portOfLoadingComboBox.setEditable(true);
			portOfLoadingComboBox.setBounds(140, 155, 333, 23);
			transportCard.add(portOfLoadingComboBox);
		
		JLabel lblNewLabel_8_5 = new JLabel("卸貨港/機場:");
		lblNewLabel_8_5.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_5.setBounds(23, 224, 115, 28);
		transportCard.add(lblNewLabel_8_5);
		
		String[] portsDischarge = {
			    "Kaohsiung Port (高雄港)", "Keelung Port (基隆港)", "Taichung Port (台中港)",
			    "Taipei Songshan Airport (松山機場)", "Taoyuan Intl Airport (桃園機場)", "Kaohsiung Intl Airport (小港機場)",
			    "HKG - Hong Kong Intl (香港國際機場)", "LAX - Los Angeles Intl (洛杉磯國際機場)",
			    "FRA - Frankfurt Intl (法蘭克福國際機場)", "JFK - New York JFK Intl (紐約 約翰甘迺迪機場)",
			    "NRT - Tokyo Narita (東京成田機場)", "SIN - Singapore Changi (新加坡樟宜機場)",
			    "Shanghai Port (上海港)", "Hong Kong Port (香港港)", "Busan Port (釜山港)",
			    "Los Angeles Port(洛杉磯港)", "Rotterdam Port (鹿特丹港)", "Singapore Port (新加坡港)"
			};
			portOfDischargeComboBox = new JComboBox<>(portsDischarge);
			portOfDischargeComboBox.setEditable(true);
			portOfDischargeComboBox.setBounds(140, 229, 333, 23);
			transportCard.add(portOfDischargeComboBox);
		
		JLabel lblNewLabel_8_6 = new JLabel("起運日期");
		lblNewLabel_8_6.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_6.setBounds(23, 276, 79, 28);
		transportCard.add(lblNewLabel_8_6);
		
		departureDateChooser = new JDateChooser();
		departureDateChooser.setDateFormatString("yyyy-MM-dd"); // 顯示格式
		departureDateChooser.setBounds(106, 281, 120, 23);
		transportCard.add(departureDateChooser);

		
		JLabel lblNewLabel_8_7 = new JLabel("到港日期");
		lblNewLabel_8_7.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lblNewLabel_8_7.setBounds(265, 276, 79, 28);
		transportCard.add(lblNewLabel_8_7);
		
		arrivalDateChooser = new JDateChooser();
		arrivalDateChooser.setDateFormatString("yyyy-MM-dd");
		arrivalDateChooser.setBounds(348, 281, 120, 23);
		transportCard.add(arrivalDateChooser);
		
		JPanel briefCard = new JPanel();
		cardPanel.add(briefCard, "briefCard");
		briefCard.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("報關單確認");
		lblNewLabel_7.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(210, 10, 109, 22);
		briefCard.add(lblNewLabel_7);
		
		JButton backToTransportButton = new JButton("上一步");
		backToTransportButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCard("transportCard");
			}
		});
		backToTransportButton.setBounds(332, 312, 87, 23);
		briefCard.add(backToTransportButton);
		
		JButton submitButton = new JButton("送出");
		submitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Declaration declarationTemp = getDeclarationData();
				Transport transportTemp = getTransportData();
				declarationTemp.setDeclarationId(IdGenerator.generateDeclarationId());
				transportTemp.setDeclarationId(declarationTemp.getDeclarationId());				
				Double taxFound = 0.0;
				for(int i = 0 ; i < taxList.size(); i++ )
				{					
					taxList.get(i).setDeclarationId(declarationTemp.getDeclarationId());	
					taxServiceImpl.addTax(taxList.get(i));
					taxFound += goodsList.get(i).getTotalPrice()*taxList.get(i).getTotalTax()/100;
				}
				declarationTemp.setTaxFound(taxFound);
				
				declarationServiceImpl.addDeclaration(declarationTemp);
				goodsServiceImpl.addGoodsToDeclaration(declarationTemp.getDeclarationId(), goodsList);
				transportServiceImpl.addTransportToDeclaration(declarationTemp.getDeclarationId(), transportTemp);
				userHoldingServiceImpl.addUserHolding(new UserHolding(user.getUsername(), declarationTemp.getDeclarationId(), user.getRole()));
				JOptionPane.showMessageDialog(AddDeclarationUI.this, "請等待海關人員審核", "送出成功", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		submitButton.setBounds(429, 312, 87, 23);
		briefCard.add(submitButton);
		
		JTextArea briefArea = new JTextArea();
		briefArea.setBounds(0, 38, 507, 239);
		briefCard.add(briefArea);		
		
		JButton printButton = new JButton("列印");
		printButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try
				{
					briefArea.print();
				}catch(PrinterException ex)
				{
					ex.printStackTrace();
				}
			}
		});
		printButton.setBounds(420, 279, 87, 23);
		briefCard.add(printButton);
		
		JButton toBriefButton = new JButton("下一步");////這個是transportCard的按鈕但要更新brief資料資料所以移到這裡
		toBriefButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showCard("briefCard");
				briefArea.setText(getBriefString());
			}
		});
		toBriefButton.setBounds(430, 312, 87, 23);
		transportCard.add(toBriefButton);
		
		JButton backToUserUIButton = new JButton("返回");
		backToUserUIButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserUI userUI = new UserUI();
				userUI.setVisible(true);
				dispose();
			}
		});
		backToUserUIButton.setBounds(0, 355, 112, 33);
		contentPane.add(backToUserUIButton);

	}
	//methods////////////////////////////////////////////////////////////////////////////
	private void showCard(String name)
	{
		CardLayout cardLayout = (CardLayout)cardPanel.getLayout();
		cardLayout.show(cardPanel, name);
	}
	
	// 新增 fetchHSCode 方法
	private String fetchHSCode(String goodsName) {
	    String hsCode = null;
	    String sql = "SELECT hs_code FROM hs_code WHERE description_zh LIKE ? OR description_en LIKE ? LIMIT 1";
	    try (Connection connection = DataBaseConnectionPool.getDataBaseConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql))
	    {
	        preparedStatement.setString(1, "%" + goodsName + "%");
	        preparedStatement.setString(2, "%" + goodsName + "%");
	        try (ResultSet resultSet = preparedStatement.executeQuery())
	        {
	            if (resultSet.next())
	            {
	                hsCode = resultSet.getString("hs_code");
	            }
	        }
	    } catch (Exception ex)
	    {
	        ex.printStackTrace();
	    }
	    return hsCode != null ? hsCode : "未知";
	}
	
	// 建立一個方法計算總價
	private void updateTotalPrice() {
	    try {
	        String unitPriceStr = unitPriceText.getText().trim();
	        String quantityStr = quantityText.getText().trim();

	        if (!unitPriceStr.isEmpty() && !quantityStr.isEmpty()) {
	            double unitPrice = Double.parseDouble(unitPriceStr);
	            double quantity = Double.parseDouble(quantityStr);
	            double total = unitPrice * quantity;
	            totalPriceText.setText(String.format("%.2f", total));
	        } else {
	            totalPriceText.setText("");
	        }
	    } catch (NumberFormatException e) {
	        totalPriceText.setText(""); // 如果輸入不是數字就清空
	    }
	}
	
	private Tax taxOfGoods(Goods goods)
	{
		Tax temp = null;
		String sql = "SELECT * FROM hs_code WHERE hs_code = ?";
	    try (Connection connection = DataBaseConnectionPool.getDataBaseConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql))
	    {
	        preparedStatement.setString(1, goods.getHsCode());
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery())
	        {
	            if (resultSet.next())
	            {
	                temp = new Tax();
	                temp.setHsCode(goods.getHsCode());
	                temp.setDeclarationType((String)typeComboBox.getSelectedItem());
	                temp.setCustomsDuty(resultSet.getDouble("duty_rate"));
	                temp.setVat(resultSet.getDouble("vat_rate"));
	                temp.setItemName(goods.getItemName());
	                temp.setOtherTax(0.0);
	                temp.setTotalTax(temp.getTotalTax());
	            }
	        }
	    } catch (Exception ex)
	    {
	        ex.printStackTrace();
	    }
	    return temp;
	}
	
	private Declaration getDeclarationData()
	{		
		Declaration temp = new Declaration();
		temp.setDeclarationDate(LocalDateTime.now());
		String type = (String)typeComboBox.getSelectedItem();
		temp.setDeclarationType(type);		
		temp.setImporterName(importerNameText.getText());
		temp.setExporterName(exporterNameText.getText());
		
		return temp;
	}
	
	private Goods getGoodsData()
	{
		Goods temp = new Goods();
		temp.setItemName(itemNameText.getText());
		temp.setHsCode(hsCodeText.getText());
		String originCountry;
		if ("其他".equals(originCountryComboBox.getSelectedItem())) {
		    originCountry = originOtherText.getText().trim();
		} else {
		    originCountry = (String) originCountryComboBox.getSelectedItem();
		}
		temp.setOriginCountry(originCountry);
		temp.setQuantity(Integer.parseInt(quantityText.getText().trim()));
		temp.setUnitPrice(Double.parseDouble(unitPriceText.getText().trim()));
		temp.setTotalPrice(temp.getTotalPrice());
		temp.setGrossWeight(Double.parseDouble(grossWeightText.getText().trim()));
		temp.setNetWeight(Double.parseDouble(netWeightText.getText().trim()));
		temp.setQuantityUnit((String)quantityUnitComboBox.getSelectedItem());
		temp.setUnitPriceUnit((String)unitPriceUnitComboBox.getSelectedItem());
		temp.setWeightUnit((String)weightUnitComboBox.getSelectedItem());
		return temp;
	}
	
	private Transport getTransportData()
	{
		Transport temp = new Transport();
		temp.setVesselOrFlight((String)vesselOrFlightComboBox.getSelectedItem());
		temp.setPortOfLoading((String)portOfLoadingComboBox.getSelectedItem());
		temp.setPortOfDischarge((String)portOfDischargeComboBox.getSelectedItem());
		Date selectedDate = departureDateChooser.getDate();
		if (selectedDate != null) {
		    LocalDateTime departureDate = selectedDate.toInstant()
		            .atZone(ZoneId.systemDefault())
		            .toLocalDateTime();
		    temp.setDepartureDate(departureDate);
		}
		Date selectedDate2 = arrivalDateChooser.getDate();
		if (selectedDate2 != null) {
		    LocalDateTime arrivalDate = selectedDate2.toInstant()
		            .atZone(ZoneId.systemDefault())
		            .toLocalDateTime();
		    temp.setArrivalDate(arrivalDate);
		}
		return temp;		
	}
	
	private String getBriefString()
	{
		String brief = "";
		Declaration declarationTemp = getDeclarationData();
		Transport transportTemp = getTransportData();
		String goodsNameString = "";
		Double taxFound = 0.0;
		for(int i = 0; i < goodsList.size(); i++)
		{
			goodsNameString += goodsList.get(i).getItemName() + "\n";
			taxFound += goodsList.get(i).getTotalPrice()*taxList.get(i).getTotalTax()/100;
		}
		
		brief = "類型:" + declarationTemp.getDeclarationType() + "\n進口人:" + declarationTemp.getImporterName() + "\n出口人:" + declarationTemp.getExporterName() + "\n報關日期:" + declarationTemp.getDeclarationDate() 
				+"\n貨物品項:\n" + goodsNameString + "船名/航班公司:" + transportTemp.getVesselOrFlight() + "\n裝貨港/機場" + transportTemp.getPortOfLoading() +"\n卸貨港/機場" + transportTemp.getPortOfDischarge()
				+"\n起運日期:" + transportTemp.getDepartureDate() + "\n到港日期:" + transportTemp.getArrivalDate() + "\n應交稅額:" + String.format("%.2f", taxFound) + "NTD";
		return brief;
	}
	
	
}

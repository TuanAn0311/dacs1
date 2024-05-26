package SignUp_SignIn;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Connection.ConnectJDBC;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class Update extends JFrame {
	Vector<Vector<String>> vData = new Vector<Vector<String>>();
	Vector<String> vTitle = new Vector<String>();
	JScrollPane tableResult;
	DefaultTableModel Model;
	int selectedrow = 0;

	Statement stm;
	ResultSet rst;
	ConnectJDBC jdbc = new ConnectJDBC();
	Connection conn = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField RSusername;
	private JTextField RSpass;
	private JTextField RSemail;
	private JTextField RSphone;


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update frame = new Update(null);
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
	public Update(String REusernam) {
		
		try {

			conn = ConnectJDBC.getConnection();

			stm = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Thông tin về "+REusernam);
		URL urll = SignIn.class.getResource("infor.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urll);
		this.setIconImage(img);
		
		RSusername = new JTextField();
		RSusername.setBounds(203, 122, 214, 41);
		contentPane.add(RSusername);
		RSusername.setColumns(10);
		RSusername.setEditable(false);
		
		RSpass = new JTextField();
		RSpass.setColumns(10);
		RSpass.setBounds(203, 173, 214, 41);
		contentPane.add(RSpass);
		
		RSemail = new JTextField();
		RSemail.setColumns(10);
		RSemail.setBounds(203, 225, 214, 41);
		contentPane.add(RSemail);
		
		RSphone = new JTextField();
		RSphone.setColumns(10);
		RSphone.setBounds(203, 276, 214, 41);
		contentPane.add(RSphone);
		

		JLabel lblNewLabel = new JLabel("Thông Tin Tài Khoản Của Bạn");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(55, 35, 362, 77);
		contentPane.add(lblNewLabel);

		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(55, 122, 111, 41);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(55, 173, 111, 41);
		contentPane.add(lblPassword);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(55, 225, 111, 41);
		contentPane.add(lblEmail);

		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhoneNumber.setBounds(55, 276, 141, 41);
		contentPane.add(lblPhoneNumber);
		
		JButton btUpdate = new JButton("Update");
		btUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update(REusernam);
			}
		});
		btUpdate.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btUpdate.setBounds(43, 357, 174, 47);
		contentPane.add(btUpdate);
				
				JButton btnDelete = new JButton("Delete ");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						delete(REusernam);
					}
				});
				btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 24));
				btnDelete.setBounds(243, 357, 174, 47);
				contentPane.add(btnDelete);
		
				JLabel background = new JLabel("");
				background.setBounds(0, 0, 452, 461);
				contentPane.add(background);
				background.setIcon(
						new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("backgroundMK.jpg"))));
		setLocationRelativeTo(null);

		ConnectJDBC jdbc = new ConnectJDBC();
		Connection conn = null;

		setVisible(true);
		conn = jdbc.getConnection();
		String sql = "";
		
			sql = "SELECT username, pass, email, SDT FROM Users WHERE username =  '" + REusernam +"'";
		

		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rst = pstm.executeQuery();

			if (rst.next()) {
				RSusername.setText(rst.getString(1));
				RSpass.setText(rst.getString(2));
				RSemail.setText(rst.getString(3));
				RSphone.setText(rst.getString(4));
			}
			this.stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void update(String REusername) {
			ConnectJDBC jdbc = new ConnectJDBC();
			Connection conn = jdbc.getConnection();
			PreparedStatement pstm;
			try {

				String username = RSemail.getText();
				String pass = RSpass.getText();

				String email = RSemail.getText();
				int SDT = Integer.parseInt(RSphone.getText());
					

				String sql = "update Users set pass = '" + pass + "', email = '" + email
						+ "', SDT = " + SDT + " Where username = '" + REusername + "'";
				
				 pstm = conn.prepareStatement(sql);
				// Cập nhật vào csdl
				this.stm.executeUpdate(sql);
				// Cập nhật giao diện của sổ chính
				JOptionPane.showMessageDialog(null, "Cập nhật thành công! ");
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Cập nhật KHÔNG thành công! ");
			}
		
	}
	
	public void delete(String REusernam) {
			ConnectJDBC jdbc = new ConnectJDBC();
			Connection conn = jdbc.getConnection();
			PreparedStatement pstm;
			try {

				String sql = "DELETE FROM Users WHERE username = '" + REusernam   + "'";
				 pstm = conn.prepareStatement(sql);
				stm.executeUpdate(sql);
				
				JOptionPane.showMessageDialog(null, "Xóa thành công! ");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Xóa KHÔNG thành công! ");
			}
		

	}
}
package SignUp_SignIn;

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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.ConnectJDBC;

public class Infor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JLabel RSusername;
	public JLabel RSpass;
	public JLabel RSemail;
	public JLabel RSphone;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Infor frame = new Infor(null);
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
	public Infor(String inf) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Thông tin về "+inf);
		URL urll = SignIn.class.getResource("infor.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urll);
		this.setIconImage(img);
		

		JLabel lblNewLabel = new JLabel("Thông Tin Tài Khoản Của Bạn");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(55, 35, 362, 77);
		contentPane.add(lblNewLabel);

		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(55, 156, 111, 41);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(55, 207, 111, 41);
		contentPane.add(lblPassword);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(55, 259, 111, 41);
		contentPane.add(lblEmail);

		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhoneNumber.setBounds(55, 310, 141, 41);
		contentPane.add(lblPhoneNumber);

		RSusername = new JLabel("");
		RSusername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		RSusername.setBounds(206, 156, 211, 41);
		contentPane.add(RSusername);

		RSpass = new JLabel("");
		RSpass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		RSpass.setBounds(206, 207, 211, 41);
		contentPane.add(RSpass);

		RSemail = new JLabel("");
		RSemail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		RSemail.setBounds(206, 259, 211, 41);
		contentPane.add(RSemail);

		RSphone = new JLabel("");
		RSphone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		RSphone.setBounds(206, 310, 211, 41);
		contentPane.add(RSphone);

		JLabel background = new JLabel("");
		background.setBounds(0, 0, 452, 461);
		contentPane.add(background);
		background.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("backgroundMK.jpg"))));

		JButton btnNewButton = new JButton("Đăng Nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn signIn = new SignIn();
				signIn.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBounds(139, 380, 160, 46);
		contentPane.add(btnNewButton);
		setLocationRelativeTo(null);

		ConnectJDBC jdbc = new ConnectJDBC();
		Connection conn = null;

		setVisible(true);
		conn = jdbc.getConnection();
		String sql = "";
		if (inf.matches("[0-9]+")) {
			sql = "SELECT username, pass, email, SDT FROM Users WHERE SDT = ?";
		} else {
			sql = "SELECT username, pass, email, SDT FROM Users WHERE email = ?";
		}

		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, inf);
			ResultSet rst = pstm.executeQuery();

			if (rst.next()) {
				RSusername.setText(rst.getString(1));
				RSpass.setText(rst.getString(2));
				RSemail.setText(rst.getString(3));
				RSphone.setText(rst.getString(4));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không có tài khoản liên quan với ");
		}

	}
	

}
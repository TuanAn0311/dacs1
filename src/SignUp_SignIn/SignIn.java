package SignUp_SignIn;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.ConnectJDBC;
import View.KhachHang;
import View.trangChu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SignIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String SDT = null;
	private JPanel contentPane;
	private JTextField text_user;
	private JPasswordField passwordField;
	private boolean isPasswordVisible;
	private Image background_SU;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
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
	public SignIn() {

		URL urll = SignIn.class.getResource("iconAdmin.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urll);
		this.setIconImage(img);
		setTitle("ADMIN");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 473, 553);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel Eye = new JLabel("");
		Eye.setForeground(Color.BLACK);
		Eye.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("iconNotEye.png"))));
		Eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				isPasswordVisible = !isPasswordVisible;
				if (isPasswordVisible) {
					passwordField.setEchoChar((char) 0);// chuyển thành ký tự 
					Eye.setIcon(new ImageIcon(
							Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("inconEye.png"))));
				} else {
					passwordField.setEchoChar('*');//Ẩn kí tự thành dấu *
					Eye.setIcon(new ImageIcon(
							Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("iconNotEye.png"))));
				}
			}
		});
		Eye.setBounds(394, 292, 31, 32);
		panel.add(Eye);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(155, 23, 160, 152);
		panel.add(lblNewLabel);

		lblNewLabel.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("iconAdmin.png"))));

		JLabel lblNewLabel_1 = new JLabel("ADMIN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(74, 177, 317, 43);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("UserName: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 244, 109, 32);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("PassWord:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(10, 298, 109, 32);
		panel.add(lblNewLabel_2_1);

		text_user = new JTextField();
		text_user.setForeground(Color.BLACK);
		text_user.setFont(new Font("Tahoma", Font.PLAIN, 20));
		text_user.setBounds(129, 243, 262, 32);
		panel.add(text_user);
		text_user.setColumns(10);

		JLabel quenMK = new JLabel("Quên mật khẩu ?");
		quenMK.setCursor(new Cursor(HAND_CURSOR));
		quenMK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				quenMK q = new quenMK();
				q.setVisible(true);
				setVisible(false);
			}
		});
		quenMK.setFont(new Font("Tahoma", Font.ITALIC, 15));
		quenMK.setBounds(159, 337, 134, 19);
		panel.add(quenMK);

		JButton bt_SignIn = new JButton("Đăng Nhập");
		bt_SignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enteredUser = text_user.getText(); // Lấy giá trị từ JTextField
				String enteredPassword = new String(passwordField.getPassword()); // Lấy giá trị từ JPasswordField

				ConnectJDBC jdbc = new ConnectJDBC();
				Connection conn = null;
				if (enteredUser.equals("") || enteredPassword.equals("")) {
					JOptionPane.showMessageDialog(null, "Thiếu thông tin đăng nhập!");
				} else {
					try {
						conn = jdbc.getConnection();
						String sql = "SELECT * FROM Users WHERE username = ? AND pass = ?";
						PreparedStatement pstm = conn.prepareStatement(sql);
						pstm.setString(1, enteredUser);
						pstm.setString(2, enteredPassword);
						ResultSet rs = pstm.executeQuery();

						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
							trangChu view = new trangChu();
							view.setVisible(true);
							view.setLocationRelativeTo(null);
							setVisible(false);
						} else if (enteredUser.isEmpty() || enteredPassword.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập tên tài khoản và mật khẩu");
						} else {
							JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng");
						}

						pstm.close();
						conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		bt_SignIn.setBackground(SystemColor.activeCaption);
		bt_SignIn.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_SignIn.setBounds(146, 372, 160, 32);
		panel.add(bt_SignIn);

		JButton bt_SignUp = new JButton("Tạo Tài Khoản");
		bt_SignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp su = new SignUp();
				su.setVisible(true);
				setVisible(false);
			}
		});
		bt_SignUp.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_SignUp.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		bt_SignUp.setBounds(123, 440, 207, 43);
		panel.add(bt_SignUp);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setToolTipText("Enter password");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(129, 292, 262, 32);
		panel.add(passwordField);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(0, 0, 463, 553);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("backgroundMK.jpg"))));

		setLocationRelativeTo(null);
	}

	
}

package SignUp_SignIn;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.ConnectJDBC;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class quenMK extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtEmailorPhone;
	public String inf;
	private JLabel thongTin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					quenMK frame = new quenMK();
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
	public quenMK() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setTitle("Quên Mật Khẩu");
		URL urll = SignIn.class.getResource("quenMK.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urll);
		this.setIconImage(img);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		thongTin = new JLabel("\r\nTôi sẽ giúp bạn lấy thông tin!");
		thongTin.setFont(new Font("Tahoma", Font.BOLD, 25));
		thongTin.setBounds(47, 29, 375, 85);
		contentPane.add(thongTin);

		JLabel lblNewLabel_1 = new JLabel(" Nhập địa chỉ email hoặc số điện thoại của bạn tại đây");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(47, 313, 383, 39);
		contentPane.add(lblNewLabel_1);

		txtEmailorPhone = new JTextField();
		txtEmailorPhone.setForeground(Color.BLACK);
		txtEmailorPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmailorPhone.setBounds(33, 349, 397, 51);
		contentPane.add(txtEmailorPhone);
		txtEmailorPhone.setColumns(10);

		JButton btnNewButton = new JButton("Tiếp Tục");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtEmailorPhone.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng cung cấp email hoặc số điện thoại!");
				} else {

					ConnectJDBC jdbc = new ConnectJDBC();
					Connection conn = null;

					conn = jdbc.getConnection();
					String sql = "";
					boolean hasData = false;

					if (txtEmailorPhone.getText().matches("[0-9]+")) {
						sql = "SELECT username, pass, email, SDT FROM Users WHERE SDT = ?";
					} else {
						sql = "SELECT username, pass, email, SDT FROM Users WHERE email = ?";
					}
					PreparedStatement pstm;
					try {
						pstm = conn.prepareStatement(sql);
						pstm.setString(1, txtEmailorPhone.getText());
						ResultSet rst = pstm.executeQuery();
						if (rst.next()) {
							Infor inf = new Infor(txtEmailorPhone());
							inf.setVisible(true);
							setVisible(false);
							hasData = true;
						}

						if (!hasData) {
							if (txtEmailorPhone.getText().matches("[0-9]+")) {
								JOptionPane.showMessageDialog(null,
										"Không có tài khoản liên quan đến số điện thoại " + txtEmailorPhone.getText());
							} else {
								JOptionPane.showMessageDialog(null,
										"Không có tài khoản liên quan đến Email " + txtEmailorPhone.getText());

							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});

		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(95, 421, 273, 39);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Quay Lại");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignIn s = new SignIn();
				s.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(172, 488, 116, 39);
		contentPane.add(btnNewButton_1);

		JLabel cute = new JLabel("");
		cute.setBounds(81, 118, 301, 146);
		contentPane.add(cute);
		cute.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("inconcute.png"))));

		JLabel background = new JLabel("");
		background.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignIn signIn = new SignIn();
				setVisible(false);
				signIn.setVisible(true);
			}
		});
		background.setBounds(0, 0, 457, 576);
		contentPane.add(background);
		background.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignIn.class.getResource("backgroundMK.jpg"))));

		setLocationRelativeTo(null);

	}

	public String txtEmailorPhone() {
		String txt = txtEmailorPhone.getText();
		return txt;
	}
}

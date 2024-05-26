package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.ConnectJDBC;
import SignUp_SignIn.*;


import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Toolkit;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class trangChu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	Statement stm;
	ResultSet rst;
	ConnectJDBC jdbc = new ConnectJDBC();
	Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trangChu frame = new trangChu();
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
	public trangChu() {
		
		URL url = KhachHang.class.getResource("chaiRuou.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage(img);
		this.setTitle("Trang chủ");
		try {

			conn = ConnectJDBC.getConnection();

			stm = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btDatHangNhanh = new JButton("Đặt Hàng Nhanh");
		btDatHangNhanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lenDon ld = new lenDon();
				ld.setVisible(true);
				setVisible(false);
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 79, 997, 7);
		contentPane.add(separator);
		
		JButton btQLTK = new JButton("Quản Lý Tài Khoản");
		btQLTK.addActionListener(new ActionListener() {
			private JLabel RSusername;
			private JLabel RSpass;
			private JLabel RSemail;
			private JLabel RSphone;

			public void actionPerformed(ActionEvent e) {
				String tk = JOptionPane.showInputDialog("vui lòng nhập tài khoản bạn muốn quản lý:");
				ConnectJDBC jdbc = new ConnectJDBC();
				Connection conn = null;

				setVisible(true);
				conn = jdbc.getConnection();
				
				String	sql = "SELECT * FROM Users WHERE username = ?";
					
				try {
					PreparedStatement pstm = conn.prepareStatement(sql);
					pstm.setString(1, tk);
					ResultSet rst = pstm.executeQuery();
					
					
						if (rst.next()) {
							String mk = JOptionPane.showInputDialog("vui lòng nhập Mật Khẩu của bạn: ");
							String	sqll = "Select username, pass, email, SDT FROM Users WHERE pass = ? and username = ?";
							
							PreparedStatement pstmm = conn.prepareStatement(sqll);
							pstmm.setString(1, mk);
							pstmm.setString(2, tk);
							ResultSet rstt = pstmm.executeQuery();
							
							if(rstt.next()) {
								Update ud = new Update(tk);
								ud.setVisible(true);
								
							}else {
								JOptionPane.showConfirmDialog(null, "Mật khẩu không đúng");
							}
							
						}else {
							JOptionPane.showMessageDialog(null, "Không có tài khoản liên quan ");
						}		
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
		});
		btQLTK.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btQLTK.setBounds(47, 331, 336, 76);
		contentPane.add(btQLTK);
		btDatHangNhanh.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btDatHangNhanh.setBounds(47, 240, 336, 69);
		contentPane.add(btDatHangNhanh);
		
		JPanel panel_img = new JPanel();
		panel_img.setBounds(440, 191, 485, 325);
		contentPane.add(panel_img);
		
		
		
		JLabel lblNewLabel = new JLabel("Quay về");
		lblNewLabel.setCursor(new Cursor(HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showOptionDialog(null, "Bạn muốn tiếp tục?", "Xác nhận",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (result == JOptionPane.YES_OPTION) {
					SignIn sIn = new SignIn();
					sIn.setVisible(true);
					setVisible(false);
				}
			}
				
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 21, 154, 48);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(trangChu.class.getResource("back.jpg"))));
		
		JButton bt_khoHang = new JButton("Kho Hàng");
		bt_khoHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhoHang kh = new KhoHang();
				kh.setVisible(true);
				setVisible(false);
			}
		});
		bt_khoHang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bt_khoHang.setBounds(480, 106, 143, 60);
		contentPane.add(bt_khoHang);
		
		JButton btnNewButton_4_2 = new JButton("<html>Nhà Cung<br>&#160 &#160 Cấp</html>");
		btnNewButton_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_4_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_4_2.setBounds(776, 106, 143, 60);
		contentPane.add(btnNewButton_4_2);
		
		JButton bt_sanPham = new JButton("Sản Phẩm");
		bt_sanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SanPham sp = new SanPham();
				sp.setVisible(true);
				setVisible(false);
			}
		});
		bt_sanPham.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bt_sanPham.setBounds(633, 106, 133, 60);
		contentPane.add(bt_sanPham);
		
		JButton bt_donHang = new JButton("Đơn Hàng");
		bt_donHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DonDatHang ddh = new DonDatHang();
				ddh.setVisible(true);
				setVisible(false);
			}
		});
		bt_donHang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bt_donHang.setBounds(327, 106, 143, 60);
		contentPane.add(bt_donHang);
		
		JButton bt_nhanVien = new JButton("Nhân Viên");
		bt_nhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NhanVien nv = new NhanVien();
				nv.setVisible(true);
				setVisible(false);
			}
		});
		bt_nhanVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bt_nhanVien.setBounds(174, 106, 143, 60);
		contentPane.add(bt_nhanVien);
		
		JButton bt_khachHang = new JButton("Khách Hàng");
		bt_khachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHang kh = new KhachHang();
				kh.setVisible(true);
			}
		});
		bt_khachHang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bt_khachHang.setBounds(21, 106, 143, 60);
		contentPane.add(bt_khachHang);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(new Color(192, 192, 192));
		lblNewLabel_2.setBounds(10, 96, 919, 82);
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(Color.decode("#696969"));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Hệ Thống Quản Lý Kho Rượu");
		lblNewLabel_1.setBounds(307, 3, 640, 76);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);
		panel_img.setLayout(null);
		
		ImageTransitionExample newimg = new ImageTransitionExample();
		newimg.setBounds(0, 0, 485, 324);
		panel_img.add(newimg);
		newimg.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(36, 228, 361, 93);
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(Color.decode("#F95858"));
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Rượu - Sự tinh tế trong từng hương vị!");
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 22));
		lblNewLabel_4.setBounds(27, 429, 391, 54);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblBackGround = new JLabel("");
		lblBackGround.setBackground(UIManager.getColor("CheckBox.focus"));
		lblBackGround.setBounds(0, 0, 935, 516);
		contentPane.add(lblBackGround);
	}
}

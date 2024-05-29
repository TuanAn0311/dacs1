package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.ConnectJDBC;
import SignUp_SignIn.Infor;
import SignUp_SignIn.*;
import SignUp_SignIn.quenMK;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout.Group;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import javax.swing.SwingConstants;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class lenDon extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField textField_MaSanPham;
	public JTextField textField_MaDonHang;
	public JTextField textField_MaKH;
	public JTextField textField_MaKhoHang;
	public JTextField textField_MaNV;
	public JTextField textField_TrangThai;
	public Component frame;
	public JMenu dataTable;

	Vector<Vector<String>> vData = new Vector<Vector<String>>();
	Vector<String> vTitle = new Vector<String>();
	DefaultTableModel Model;
	int selectedrow = 0;

	Statement stm;
	ResultSet rst;
	ConnectJDBC jdbc = new ConnectJDBC();
	Connection conn = null;
	private JTextField textField_SoLuong;
	

	public lenDon() {

		try {

			conn = ConnectJDBC.getConnection();

			stm = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		setFont(new Font("Dialog", Font.ITALIC, 18));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 575);
		setTitle("Lên Đơn");
		setForeground(Color.BLACK);

		URL urll = lenDon.class.getResource("hoaDon.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urll);
		this.setIconImage(img);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnNewMenuu = new JMenu("Quản Lý");
		mnNewMenuu.setForeground(Color.BLACK);
		mnNewMenuu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNewMenuu);
		
		JMenuItem mnkhachHang = new JMenuItem("khách hàng");
		mnkhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHang kh = new KhachHang();
				kh.setVisible(true);
				setVisible(false);
			}
		});
		mnkhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenuu.add(mnkhachHang);
		
		JMenuItem mntmSnPhm = new JMenuItem("Sản Phẩm");
		mntmSnPhm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SanPham sp = new SanPham();
				sp.setVisible(true);
				setVisible(false);
			}
		});
		mntmSnPhm.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenuu.add(mntmSnPhm);
		
		JMenuItem mnDonDatHang = new JMenuItem("Đơn Đặt Hàng");
		mnDonDatHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lenDon ddh = new lenDon();
				ddh.setVisible(true);
				setVisible(false);
			}
		});
		mnDonDatHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenuu.add(mnDonDatHang);
		
		JMenuItem mntmNhCungCp = new JMenuItem("Nhà Cung Cấp");
		mntmNhCungCp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setVisible(true);
				setVisible(false);
			}
		});
		mntmNhCungCp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenuu.add(mntmNhCungCp);
		
		JMenuItem mntmKhoHng = new JMenuItem("Kho Hàng");
		mntmKhoHng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhoHang kho = new KhoHang();
				kho.setVisible(true);
				setVisible(false);
			}
		});
		mntmKhoHng.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenuu.add(mntmKhoHng);
		
		JMenuItem mntmNhnVin = new JMenuItem("Nhân Viên");
		mntmNhnVin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NhanVien nv = new NhanVien();
				nv.setVisible(true);
				setVisible(false);
			}
		});
		mntmNhnVin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenuu.add(mntmNhnVin);
		
		JMenuItem mntmExit = new JMenuItem("exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trangChu s = new trangChu();
				s.setVisible(true);
				setVisible(false);
			}
		});
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenuu.add(mntmExit);

		JMenu mnNewMenu = new JMenu("Manager");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.setForeground(new Color(0, 0, 0));

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Users");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mk = JOptionPane.showInputDialog("Nhập mật khẩu");
				if (mk.equals("123456")) {
					Users u = new Users();
					u.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Sai mật khẩu");
				}
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton QL = new JButton("");
		QL.setBounds(0, 10, 46, 32);
		QL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showOptionDialog(null, "Bạn muốn tiếp tục?", "Xác nhận",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (result == JOptionPane.YES_OPTION) {
					trangChu s = new trangChu();
					s.setVisible(true);
					setVisible(false);
				}
			}
		});
		contentPane.setLayout(null);
		
		textField_SoLuong = new JTextField();
		textField_SoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_SoLuong.setColumns(10);
		textField_SoLuong.setBounds(145, 172, 214, 32);
		contentPane.add(textField_SoLuong);
		contentPane.add(QL);
		QL.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconback.png"))));

		JButton bt_insert = new JButton("Insert");
		bt_insert.setBounds(10, 446, 157, 51);
		bt_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaSanPham.setEditable(true);
				if(kiemTraMaKhachHang(textField_MaKH.getText())) {
					insert();
					hoaDon hoadon = new hoaDon(textField_MaKH.getText(),
							textField_MaSanPham.getText(),
							textField_MaNV.getText(),
							textField_SoLuong.getText(),
							textField_MaKhoHang.getText());
					hoadon.setVisible(true);
					setVisible(false);
					//(String makh, String masp, String manv, String soluong, String makho)
				}else {
					JOptionPane.showMessageDialog(null, "Khách hàng không tồn tại");
				}
				reload();

			}

		});

		bt_insert.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconInsert.png"))));
		contentPane.add(bt_insert);
		bt_insert.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lbl_MaSanPham = new JLabel("Mã sản phẩm  :");
		lbl_MaSanPham.setBounds(10, 117, 137, 32);
		lbl_MaSanPham.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl_MaSanPham);

		JLabel lbl_maDonHang = new JLabel("Mã Đơn Hàng  :");
		lbl_maDonHang.setBounds(10, 64, 137, 32);
		lbl_maDonHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl_maDonHang);

		JLabel lbl_MaKH = new JLabel("Mã khách hàng  :");
		lbl_MaKH.setBounds(10, 225, 137, 32);
		lbl_MaKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl_MaKH);

		textField_MaSanPham = new JTextField();
		textField_MaSanPham.setBounds(145, 118, 214, 32);
		textField_MaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaSanPham.setColumns(10);
		contentPane.add(textField_MaSanPham);

		textField_MaDonHang = new JTextField();
		textField_MaDonHang.setBounds(145, 65, 214, 32);
		textField_MaDonHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaDonHang.setColumns(10);
		contentPane.add(textField_MaDonHang);

		textField_MaKH = new JTextField();
		textField_MaKH.setBounds(145, 226, 214, 32);
		textField_MaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaKH.setColumns(10);
		contentPane.add(textField_MaKH);

		JButton bt_clear = new JButton("Clear");
		bt_clear.setBounds(189, 446, 157, 51);
		bt_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaDonHang.setEditable(true);
				textField_MaDonHang.setText("");
				textField_TrangThai.setText("");
				textField_MaKH.setText("");
				textField_MaKhoHang.setText("");
				textField_MaNV.setText("");
			}
		});
		bt_clear.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconClear.png"))));

		bt_clear.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(bt_clear);

		ButtonGroup btg = new ButtonGroup();

		reload();
		Model = new DefaultTableModel(vData, vTitle);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 52, 361, 2);
		contentPane.add(separator_1);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 434, 361, 2);
		contentPane.add(separator_1_1);
		
		textField_MaKhoHang = new JTextField();
		textField_MaKhoHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaKhoHang.setColumns(10);
		textField_MaKhoHang.setBounds(145, 284, 214, 32);
		contentPane.add(textField_MaKhoHang);
		
		JLabel lblGi_MaKhoHang = new JLabel("Mã kho hàng :");
		lblGi_MaKhoHang.setForeground(Color.BLACK);
		lblGi_MaKhoHang.setBackground(new Color(255, 255, 255));
		lblGi_MaKhoHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGi_MaKhoHang.setBounds(10, 283, 125, 32);
		contentPane.add(lblGi_MaKhoHang);
		
		JLabel lbl_MaNV = new JLabel("Mã nhân viên :");
		lbl_MaNV.setForeground(Color.BLACK);
		lbl_MaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_MaNV.setBackground(Color.WHITE);
		lbl_MaNV.setBounds(10, 342, 137, 32);
		contentPane.add(lbl_MaNV);
		
		textField_MaNV = new JTextField();
		textField_MaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaNV.setColumns(10);
		textField_MaNV.setBounds(145, 343, 214, 32);
		contentPane.add(textField_MaNV);
						
		textField_TrangThai = new JTextField();
		textField_TrangThai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_TrangThai.setColumns(10);
		textField_TrangThai.setBounds(145, 392, 214, 32);
		contentPane.add(textField_TrangThai);
		
		JLabel lbl_TrangThai = new JLabel("Trạng thái :");
		lbl_TrangThai.setForeground(Color.BLACK);
		lbl_TrangThai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_TrangThai.setBackground(Color.WHITE);
		lbl_TrangThai.setBounds(10, 392, 107, 32);
		contentPane.add(lbl_TrangThai);
		
		JLabel lbl_MaKH_1 = new JLabel("Số lượng: ");
		lbl_MaKH_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_MaKH_1.setBounds(10, 172, 137, 32);
		contentPane.add(lbl_MaKH_1);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 371, 505);
		contentPane.add(background);
		background.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("background.jpg"))));

		setLocationRelativeTo(null); // hiển thị cửa sổ jframe ra ngay giữa màn hình
	}
	public static void main(String[] args) {
		lenDon ld = new lenDon();
		ld.setVisible(true);
	}

	private boolean kiemTraMaKhachHang(String kh) {
		String sql = "Select tenkh from khachHang where makh='"+textField_MaKH.getText()+"'";
		PreparedStatement prt;
		try {
			prt = conn.prepareStatement(sql);
			ResultSet rst = prt.executeQuery();
		if(rst.next()) {
			return true;
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public void reload() {
		try {
			vTitle.clear();
			vData.clear();

			ResultSet rst = stm.executeQuery("Select * From donHang");

			ResultSetMetaData rstmeta = rst.getMetaData();
			int num_column = rstmeta.getColumnCount();

			for (int i = 1; i <= num_column; i++) {

				vTitle.add(rstmeta.getColumnLabel(i));
			}

			while (rst.next()) {
				Vector<String> row = new Vector<String>(num_column);
				for (int i = 1; i <= num_column; i++) {
					row.add(rst.getString(i));
				}
				vData.add(row);
			}
			rst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	public void insert() {
		if (textField_MaSanPham.getText().equals("")
				|| textField_MaDonHang.getText().equals("") 
				|| textField_MaKH.getText().equals("")
				|| textField_MaKhoHang.getText().equals("")
				|| textField_MaNV.getText().equals("")
				|| textField_TrangThai.getText().equals("")){
			// Tạo nội dung lỗi
				JOptionPane.showConfirmDialog(null, "Vui lòng điền đầy đủ thông tin !");

		}else {
		
			try {
				String madh = textField_MaDonHang.getText();
				String masp = textField_MaSanPham.getText();	
				String makh = textField_MaKH.getText();
				String maKhoHang = textField_MaKhoHang.getText();
				String manv = textField_MaNV.getText();	
				String tt = textField_TrangThai.getText();

				
				
				String sql = "insert into donhang(madh,masp,maKH,maKhoHang,maNV,trangThai) Values('" + madh + "','" 
						+ masp + "','"
						+ makh + "','"
						+ maKhoHang + "','"
						+ manv + "','"
						+ tt + "')";

				// Cập nhật vào csdl
				this.stm.executeUpdate(sql);
				// Cập nhật giao diện của sổ chính
				this.reload();
				this.Model.fireTableDataChanged();
				JOptionPane.showMessageDialog(null, "Nhập thành công!");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Mã đơn hàng đã tồn tại!");
				ex.printStackTrace();
			}
		}
				
	}
	private void clearFields() {
		textField_MaDonHang.setEditable(true);
		textField_MaDonHang.setText("");
		textField_TrangThai.setText("");
		textField_MaKH.setText("");
		textField_MaKhoHang.setText("");
		textField_MaNV.setText("");
		textField_MaSanPham.setText("");
	}
	


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}


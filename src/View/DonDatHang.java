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

public class DonDatHang extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;

	public JTextField Search_MaDH;
	public JTable table;
	public JTextField textField_MaSanPham;
	public JTextField textField_MaDonHang;
	public JTextField textField_MaKH;
	public JTextField textField_MaKhoHang;
	public JTextField textField_MaNV;
	public JTextField textField_TrangThai;
	public Component frame;

	public JLabel lblNote;
	public JLabel note;
	public JMenu dataTable;

	Vector<Vector<String>> vData = new Vector<Vector<String>>();
	Vector<String> vTitle = new Vector<String>();
	JScrollPane tableResult;
	DefaultTableModel Model;
	int selectedrow = 0;

	Statement stm;
	ResultSet rst;
	ConnectJDBC jdbc = new ConnectJDBC();
	Connection conn = null;
	

	public DonDatHang() {

		try {

			conn = ConnectJDBC.getConnection();

			stm = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		setFont(new Font("Dialog", Font.ITALIC, 18));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1147, 671);
		setTitle("Đơn Hàng");
		setForeground(Color.BLACK);

		URL urll = KhoHang.class.getResource("datHang.png");
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
				DonDatHang ddh = new DonDatHang();
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
					SignIn s = new SignIn();
					s.setVisible(true);
					setVisible(false);
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(QL);
		QL.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconback.png"))));

		JLabel lblNewLabel = new JLabel("Tìm Kiếm Theo Mã KH:");
		lblNewLabel.setBounds(292, 22, 226, 32);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNewLabel);

		Search_MaDH = new JTextField();
		Search_MaDH.setBounds(465, 23, 205, 32);
		Search_MaDH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(Search_MaDH);
		Search_MaDH.setColumns(10);

		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.setBounds(680, 21, 106, 34);
		btnNewButton.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconSearch.png"))));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaSanPham.setEditable(true);

				String maDHSearch = Search_MaDH.getText();
				if (maDHSearch.isEmpty()) {
					String tx = JOptionPane
							.showInputDialog("Vui lòng nhập mã Đơn Hàng(maĐH) vào đây để truy xuất thông tin");
					Search_MaDH.setText(tx);
				} else {
					String sql = "Select * From donHang where maDH = '" + maDHSearch + "'";
					try {
						PreparedStatement pst = conn.prepareStatement(sql);
						ResultSet rst = pst.executeQuery();
						if (rst.next()) {
							
							textField_MaDonHang.setText(rst.getString("madh"));
							textField_MaSanPham.setText(rst.getString("masp"));
							textField_MaDonHang.setText(rst.getString("tenKH"));
							textField_MaDonHang.setText(rst.getString("tenKH"));
							textField_MaKH.setText(rst.getString("tuoi"));

							pst.close();
						} else {
							JOptionPane.showMessageDialog(null,
									"Không có thông tin về Mã Khách Hàng: '" + Search_MaDH.getText() + "'");
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(btnNewButton);

		JButton bt_update = new JButton("Update");
		bt_update.setBounds(189, 529, 170, 51);
		bt_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaSanPham.setEditable(true);
				update();
				clearFields();

			}
		});

		bt_update.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconUpdate.png"))));
		contentPane.add(bt_update);
		bt_update.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton bt_delete = new JButton("Delete");
		bt_delete.setBounds(10, 529, 157, 51);
		bt_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaSanPham.setEditable(true);

				delete();
				clearFields();
			}
		});

		bt_delete.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconDelete.png"))));
		contentPane.add(bt_delete);
		bt_delete.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton bt_insert = new JButton("Insert");
		bt_insert.setBounds(10, 446, 157, 51);
		bt_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaSanPham.setEditable(true);
				insert();
				reload();

			}

		});

		bt_insert.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconInsert.png"))));
		contentPane.add(bt_insert);
		bt_insert.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lbl_MaSanPham = new JLabel("Mã sản phẩm  :");
		lbl_MaSanPham.setBounds(10, 138, 137, 32);
		lbl_MaSanPham.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl_MaSanPham);

		JLabel lbl_maDonHang = new JLabel("Mã Đơn Hàng  :");
		lbl_maDonHang.setBounds(10, 75, 137, 32);
		lbl_maDonHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl_maDonHang);

		JLabel lbl_MaKH = new JLabel("Mã khách hàng  :");
		lbl_MaKH.setBounds(10, 194, 137, 32);
		lbl_MaKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl_MaKH);

		textField_MaSanPham = new JTextField();
		textField_MaSanPham.setBounds(145, 139, 214, 32);
		textField_MaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaSanPham.setColumns(10);
		contentPane.add(textField_MaSanPham);

		textField_MaDonHang = new JTextField();
		textField_MaDonHang.setBounds(145, 76, 214, 32);
		textField_MaDonHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaDonHang.setColumns(10);
		contentPane.add(textField_MaDonHang);

		textField_MaKH = new JTextField();
		textField_MaKH.setBounds(145, 195, 214, 32);
		textField_MaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaKH.setColumns(10);
		contentPane.add(textField_MaKH);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(369, 543, 742, 32);
		verticalBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(verticalBox);
		
				note = new JLabel("<html>Mã đơn hàng phải có định dạng <b>dhxx</b> (x: số nguyên) !</html>");
				verticalBox.add(note);
				note.setForeground(Color.RED);
				note.setFont(new Font("Tahoma", Font.ITALIC, 15));

		lblNote = new JLabel("NOTE");
		lblNote.setBounds(379, 520, 74, 24);
		lblNote.setHorizontalAlignment(SwingConstants.LEFT);
		lblNote.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconNote.png"))));
		lblNote.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		contentPane.add(lblNote);

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
		table = new JTable(Model);
		table.addMouseListener(this);
		tableResult = new JScrollPane(table);
		tableResult.setBounds(369, 63, 742, 447);
		contentPane.add(tableResult);

		tableResult.setViewportView(table);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 63, 361, 2);
		contentPane.add(separator_1);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 434, 361, 2);
		contentPane.add(separator_1_1);
		
		textField_MaKhoHang = new JTextField();
		textField_MaKhoHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaKhoHang.setColumns(10);
		textField_MaKhoHang.setBounds(145, 255, 214, 32);
		contentPane.add(textField_MaKhoHang);
		
		JLabel lblGi_MaKhoHang = new JLabel("Mã kho hàng :");
		lblGi_MaKhoHang.setForeground(Color.BLACK);
		lblGi_MaKhoHang.setBackground(new Color(255, 255, 255));
		lblGi_MaKhoHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGi_MaKhoHang.setBounds(10, 254, 125, 32);
		contentPane.add(lblGi_MaKhoHang);
		
		JLabel lbl_MaNV = new JLabel("Mã nhân viên :");
		lbl_MaNV.setForeground(Color.BLACK);
		lbl_MaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_MaNV.setBackground(Color.WHITE);
		lbl_MaNV.setBounds(10, 320, 137, 32);
		contentPane.add(lbl_MaNV);
		
		textField_MaNV = new JTextField();
		textField_MaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaNV.setColumns(10);
		textField_MaNV.setBounds(145, 321, 214, 32);
		contentPane.add(textField_MaNV);
						
		textField_TrangThai = new JTextField();
		textField_TrangThai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_TrangThai.setColumns(10);
		textField_TrangThai.setBounds(145, 381, 214, 32);
		contentPane.add(textField_TrangThai);
		
		JLabel lbl_TrangThai = new JLabel("Trạng thái :");
		lbl_TrangThai.setForeground(Color.BLACK);
		lbl_TrangThai.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_TrangThai.setBackground(Color.WHITE);
		lbl_TrangThai.setBounds(22, 380, 107, 32);
		contentPane.add(lbl_TrangThai);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 1133, 601);
		contentPane.add(background);
		background.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("background.jpg"))));

		setLocationRelativeTo(null); // hiển thị cửa sổ jframe ra ngay giữa màn hình
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

	public void delete() {
		if ( textField_MaSanPham.getText().equals("")
				|| textField_MaDonHang.getText().equals("") 
				|| textField_MaKH.getText().equals("")
				|| textField_MaKhoHang.getText().equals("")
				|| textField_MaNV.getText().equals("")
				|| textField_TrangThai.getText().equals("")){
				

			// Tạo nội dung lỗi

			note.setText("Vui lòng chọn thông tin đơn hàng muốn xóa !");
			note.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin đơn hàng muốn xóa !");
			// Hiển thị lỗi
			note.setVisible(true);
		} else {
			try {

				String sql = "DELETE FROM donHang WHERE maDH = '" + textField_MaDonHang.getText() + "'";
				stm.executeUpdate(sql);

				vData.remove(selectedrow);
				this.reload();
				Model.fireTableDataChanged();
				JOptionPane.showMessageDialog(null, "Xóa thành công! ");
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			note.setText("Vui lòng điền đầy đủ thông tin !");
			note.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin !");
			// Hiển thị lỗi
			note.setVisible(true);

		
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
				clearFields();
				JOptionPane.showMessageDialog(null, "Nhập thành công!");
				note.setText("...");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Mã đơn hàng đã tồn tại!");
				note.setText("Mã đơn hàng đã tồn tại!");
				ex.printStackTrace();
			}
		}
				
	}

	public void update() {
		if (textField_MaSanPham.getText().equals("")
				|| textField_MaDonHang.getText().equals("") 
				|| textField_MaKH.getText().equals("")
				|| textField_MaKhoHang.getText().equals("")
				|| textField_MaNV.getText().equals("")
				|| textField_TrangThai.getText().equals("")){
			// Tạo nội dung lỗi
			note.setText("Vui lòng điền đầy đủ thông tin !");
			note.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin !");
			// Hiển thị lỗi
			note.setVisible(true);

		}else {
			try {
				String madh = textField_MaDonHang.getText();
				String masp = textField_MaSanPham.getText();	
				String makh = textField_MaKH.getText();
				String maKhoHang = textField_MaKhoHang.getText();
				String manv = textField_MaNV.getText();	
				String tt = textField_TrangThai.getText();

				

				String sql = "update donHang set maSP = '" + masp + "', maKH = '" + makh
						+ "', maKhoHang = '" + maKhoHang +"', maNV='"+ manv +"', trangThai='"+ tt + "' Where maDH = '" + madh +"'";

				// Cập nhật vào csdl
				this.stm.executeUpdate(sql);
				// Cập nhật giao diện của sổ chính
				this.reload();
				this.Model.fireTableDataChanged();
				JOptionPane.showMessageDialog(null, "Cập nhật thành công! ");
				note.setText("");
			} catch (Exception e) {
				e.printStackTrace();
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DonDatHang frame = new DonDatHang();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int selectedrow = table.getSelectedRow();
		textField_MaDonHang.setText(Model.getValueAt(selectedrow, 0).toString());
		textField_MaDonHang.setEditable(false);
		textField_MaSanPham.setText(Model.getValueAt(selectedrow, 1).toString());
		textField_MaKH.setText(Model.getValueAt(selectedrow, 2).toString());
		textField_MaKhoHang.setText(Model.getValueAt(selectedrow, 3).toString());
		textField_MaNV.setText(Model.getValueAt(selectedrow, 4).toString());
		textField_TrangThai.setText(Model.getValueAt(selectedrow, 5).toString());

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
	
}


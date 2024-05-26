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

public class SanPham extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;

	public JTextField Search_TimKiem;
	public JTable table;
	public JTextField textField_MaSanPham;
	public JTextField textField_TenSanPham;
	public JTextField textField_TonKho;
	public JTextField textField_Gia;
	public JTextField textField_NhaCungCap;
	public Component frame;

	public JLabel lbl_Note;
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
	
	

	public SanPham() {

		try {

			conn = ConnectJDBC.getConnection();

			stm = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		setFont(new Font("Dialog", Font.ITALIC, 18));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 581);
		setTitle("Sản Phẩm");
		setForeground(Color.BLACK);

		URL urll = KhoHang.class.getResource("sanPham.png");
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

		JLabel lblNewLabel_TimKiem = new JLabel("Tìm Kiếm Theo Mã KH:");
		lblNewLabel_TimKiem.setBounds(127, 20, 181, 32);
		lblNewLabel_TimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNewLabel_TimKiem);

		Search_TimKiem = new JTextField();
		Search_TimKiem.setBounds(318, 21, 205, 32);
		Search_TimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(Search_TimKiem);
		Search_TimKiem.setColumns(10);

		JButton NewButton_TimKiem = new JButton("Tìm");
		NewButton_TimKiem.setBounds(533, 19, 106, 34);
		NewButton_TimKiem.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconSearch.png"))));
		NewButton_TimKiem.setFont(new Font("Tahoma", Font.BOLD, 20));
		NewButton_TimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaSanPham.setEditable(true);

				String maSp = Search_TimKiem.getText();
				if (maSp.isEmpty()) {
					String tx = JOptionPane
							.showInputDialog("Vui lòng nhập mã khách hàng(Mã Sản Phẩm) vào đây để truy xuất thông tin");
					Search_TimKiem.setText(tx);
				} else {
					String sql = "Select * From Sanpham where masp = " + maSp + "";
					try {
						PreparedStatement pst = conn.prepareStatement(sql);
						ResultSet rst = pst.executeQuery();
						if (rst.next()) {
							textField_MaSanPham.setText(rst.getString("maSp"));
							textField_TenSanPham.setText(rst.getString("tenSP"));
							textField_TonKho.setText(rst.getString("tonKho"));
							textField_Gia.setText(rst.getString("gia"));
							textField_NhaCungCap.setText(rst.getString("maCT"));

							pst.close();
						} else {
							JOptionPane.showMessageDialog(null,
									"Không có thông tin về Mã Sản Phẩm: '" + Search_TimKiem.getText() + "'");
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(NewButton_TimKiem);

		JButton bt_Update = new JButton("Update");
		bt_Update.setBounds(189, 450, 170, 51);
		bt_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaSanPham.setEditable(true);

				update();
				clearFields();

			}
		});

		bt_Update.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconUpdate.png"))));
		contentPane.add(bt_Update);
		bt_Update.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton bt_Delete = new JButton("Delete");
		bt_Delete.setBounds(10, 450, 157, 51);
		bt_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaSanPham.setEditable(true);

				delete();
				clearFields();
			}
		});

		bt_Delete.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconDelete.png"))));
		contentPane.add(bt_Delete);
		bt_Delete.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton bt_Insert = new JButton("Insert");
		bt_Insert.setBounds(10, 367, 157, 51);
		bt_Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaSanPham.setEditable(true);
				insert();
				reload();

			}

		});

		bt_Insert.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconInsert.png"))));
		contentPane.add(bt_Insert);
		bt_Insert.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lbl_MaSanPham = 	new JLabel("Mã sản phẩm :");
		lbl_MaSanPham.setBounds(10, 79, 129, 32);
		lbl_MaSanPham.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl_MaSanPham);

		JLabel lbl_TenSanPham = new JLabel("Tên sản phẩm :");
		lbl_TenSanPham.setBounds(10, 140, 129, 32);
		lbl_TenSanPham.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl_TenSanPham);

		JLabel lbl_TonKho = new JLabel("Tồn Kho :");
		lbl_TonKho.setBounds(10, 194, 107, 32);
		lbl_TonKho.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl_TonKho);

		textField_MaSanPham = new JTextField();
		textField_MaSanPham.setBounds(127, 82, 232, 32);
		textField_MaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaSanPham.setColumns(10);
		contentPane.add(textField_MaSanPham);

		textField_TenSanPham = new JTextField();
		textField_TenSanPham.setBounds(127, 135, 232, 32);
		textField_TenSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_TenSanPham.setColumns(10);
		contentPane.add(textField_TenSanPham);

		textField_TonKho = new JTextField();
		textField_TonKho.setBounds(127, 189, 232, 32);
		textField_TonKho.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_TonKho.setColumns(10);
		contentPane.add(textField_TonKho);

		Box verticalBox_Luuy = Box.createVerticalBox();
		verticalBox_Luuy.setBounds(369, 464, 524, 32);
		verticalBox_Luuy.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(verticalBox_Luuy);

		lbl_Note = new JLabel("NOTE");
		lbl_Note.setBounds(379, 441, 74, 24);
		lbl_Note.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Note.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconNote.png"))));
		lbl_Note.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		contentPane.add(lbl_Note);

		JButton bt_Clear = new JButton("Clear");
		bt_Clear.setBounds(189, 367, 157, 51);
		bt_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaSanPham.setEditable(true);
				textField_MaSanPham.setText("");
				textField_TenSanPham.setText("");
				textField_TonKho.setText("");
				textField_Gia.setText("");
				textField_NhaCungCap.setText("");
			}
		});
		bt_Clear.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconClear.png"))));

		bt_Clear.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(bt_Clear);

		note = new JLabel("<html>Mã Sản Phẩm phải có định dạng <b>SPxxx</b> (x: số nguyên) !</html>");
		note.setBounds(379, 464, 514, 37);
		contentPane.add(note);
		note.setForeground(Color.RED);
		note.setFont(new Font("Tahoma", Font.ITALIC, 15));

		ButtonGroup btg = new ButtonGroup();

		reload();
		Model = new DefaultTableModel(vData, vTitle);
		table = new JTable(Model);
		table.addMouseListener(this);
		tableResult = new JScrollPane(table);
		tableResult.setBounds(369, 63, 540, 377);
		contentPane.add(tableResult);

		tableResult.setViewportView(table);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 63, 361, 2);
		contentPane.add(separator_1);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 355, 361, 2);
		contentPane.add(separator_1_1);
		
		textField_Gia = new JTextField();
		textField_Gia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_Gia.setColumns(10);
		textField_Gia.setBounds(127, 244, 232, 32);
		contentPane.add(textField_Gia);
		
		JLabel lbl_gia = new JLabel(" Giá :");
		lbl_gia.setForeground(Color.BLACK);
		lbl_gia.setBackground(new Color(255, 255, 255));
		lbl_gia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_gia.setBounds(10, 243, 107, 32);
		contentPane.add(lbl_gia);
			
		JLabel lbl_NhaCungCap = new JLabel("Nhà cung cấp :");
		lbl_NhaCungCap.setForeground(Color.BLACK);
		lbl_NhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_NhaCungCap.setBackground(Color.WHITE);
		lbl_NhaCungCap.setBounds(10, 297, 129, 32);
		contentPane.add(lbl_NhaCungCap);
		
		textField_NhaCungCap = new JTextField();
		textField_NhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_NhaCungCap.setColumns(10);
		textField_NhaCungCap.setBounds(127, 298, 232, 32);
		contentPane.add(textField_NhaCungCap);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 909, 511);
		contentPane.add(background);
		background.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("background.jpg"))));

		setLocationRelativeTo(null); // hiển thị cửa sổ jframe ra ngay giữa màn hình
	}

	public void reload() {
		try {
			vTitle.clear();
			vData.clear();

			ResultSet rst = stm.executeQuery("Select * From sanpham");

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
		if (textField_TenSanPham.getText().equals("") || textField_MaSanPham.getText().equals("")
				|| textField_TonKho.getText().equals("") || textField_TonKho.getText().equals("")||textField_Gia.getText().equals("")) {
				

			// Tạo nội dung lỗi

			note.setText("Vui lòng chọn thông tin khách hàng muốn xóa !");
			note.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin khách hàng muốn xóa !");
			// Hiển thị lỗi
			note.setVisible(true);
		} else {
			try {

				String sql = "DELETE FROM sanpham WHERE masp = '" + textField_MaSanPham.getText() + "'";
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
		if (textField_TenSanPham.getText().equals("") || textField_MaSanPham.getText().equals("")
				|| textField_TonKho.getText().equals("") || textField_TonKho.getText().equals("")||textField_Gia.getText().equals("")) {
			// Tạo nội dung lỗi
			note.setText("Vui lòng điền đầy đủ thông tin !");
			note.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin !");
			// Hiển thị lỗi
			note.setVisible(true);
		} else {
			try {
				String tensp = textField_TenSanPham.getText();
				String masp = textField_MaSanPham.getText();
				float gia = Float.parseFloat(textField_Gia.getText());
				String ncc = textField_NhaCungCap.getText();
				float tonkho = Integer.parseInt(textField_TonKho.getText());
				
				
				String sql = "insert into sanpham(masp,tensp,tonkho,gia,mact) Values('" + masp + "',N'" + tensp+"',"+tonkho+","+gia+",'"+ncc
						+ "')";

				// Cập nhật vào csdl
				this.stm.executeUpdate(sql);
				// Cập nhật giao diện của sổ chính
				this.reload();
				this.Model.fireTableDataChanged();
				clearFields();
				JOptionPane.showMessageDialog(null, "Nhập thành công!");
				note.setText("...");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Mã sản phẩm đã tồn tại!");
				ex.printStackTrace();
			}
		}
	}

	public void update() {
		if (textField_TenSanPham.getText().equals("") || textField_MaSanPham.getText().equals("")
				|| textField_TonKho.getText().equals("") || textField_TonKho.getText().equals("")||textField_Gia.getText().equals("")) {
				

			// Tạo nội dung lỗi

			note.setText("Vui lòng chọn thông tin sản phẩm! ");
			note.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, "Chưa chọn thông tin sản phẩm cần thay đổi !");
			// Hiển thị lỗi
			note.setVisible(true);
		
		} else {

			try {
				String tensp = textField_TenSanPham.getText();
				String masp = textField_MaSanPham.getText();
				float gia = Float.parseFloat(textField_Gia.getText());
				String ncc = textField_NhaCungCap.getText();
				float tonkho = Integer.parseInt(textField_TonKho.getText());
				

				String sql = "update sanPham set tensp = N'" + tensp + "', Gia = " + gia +",tonkho ="+tonkho
						+ ", mact = '" + ncc + "' Where masp = '" + masp+"'";

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
		textField_MaSanPham.setText("");
		textField_TenSanPham.setText("");
		textField_TonKho.setText("");
		textField_NhaCungCap.setText("");
		textField_Gia.setText("");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SanPham frame = new SanPham();
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
		textField_MaSanPham.setText(Model.getValueAt(selectedrow, 0).toString());
		textField_MaSanPham.setEditable(false);
		textField_TenSanPham.setText(Model.getValueAt(selectedrow, 1).toString());
		textField_TonKho.setText(Model.getValueAt(selectedrow, 2).toString());
		textField_Gia.setText(Model.getValueAt(selectedrow, 3).toString() );
		textField_NhaCungCap.setText(Model.getValueAt(selectedrow, 4).toString());

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
		}
	}


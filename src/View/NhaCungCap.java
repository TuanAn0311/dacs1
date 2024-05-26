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

public class NhaCungCap extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;

	public JTextField Search_MaCT;
	public JTable table;
	public JTextField textField_MaCongTy;
	public JTextField textField_TenCongTy;
	public JTextField textField_SoDienThoai;
	public JTextField textField_DiaChi;
	public JTextField textField_Email;
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
	

	public NhaCungCap() {

		try {

			conn = ConnectJDBC.getConnection();

			stm = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		setFont(new Font("Dialog", Font.ITALIC, 18));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 581);
		setTitle("Nhà Cung Cấp");
		setForeground(Color.BLACK);

		URL urll = KhoHang.class.getResource("nhaCungcap.png");
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
					trangChu s = new trangChu();
					s.setVisible(true);
					setVisible(false);
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(QL);
		QL.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconback.png"))));

		JLabel lblNewLabel = new JLabel("Tìm Kiếm Theo Mã Công Ty  :");
		lblNewLabel.setBounds(191, 20, 226, 32);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNewLabel);

		Search_MaCT = new JTextField();
		Search_MaCT.setBounds(441, 21, 205, 32);
		Search_MaCT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(Search_MaCT);
		Search_MaCT.setColumns(10);

		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.setBounds(686, 17, 106, 34);
		btnNewButton.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconSearch.png"))));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaCongTy.setEditable(true);

				String maCTSearch = Search_MaCT.getText();
				if (maCTSearch.isEmpty()) {
					String tx = JOptionPane
							.showInputDialog("Vui lòng nhập 'Mã Công Ty' vào đây để truy xuất thông tin");
					Search_MaCT.setText(tx);
				} else {
					String sql = "Select * From NhaCungCap where maCT = " + maCTSearch + "";
					try {
						PreparedStatement pst = conn.prepareStatement(sql);
						ResultSet rst = pst.executeQuery();
						if (rst.next()) {
							textField_MaCongTy.setText(rst.getString("maCT"));
							textField_TenCongTy.setText(rst.getString("tenCT"));
							textField_DiaChi.setText(rst.getString("DiaChi"));
							textField_SoDienThoai.setText(rst.getString("SDT"));
							textField_Email.setText(rst.getString("Email"));

							pst.close();
						} else {
							JOptionPane.showMessageDialog(null,
									"Không có thông tin về Mã Công Ty: '" + Search_MaCT.getText() + "'");
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(btnNewButton);

		JButton bt_update = new JButton("Update");
		bt_update.setBounds(189, 450, 170, 51);
		bt_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaCongTy.setEditable(true);

				update();
				clearFields();

			}
		});

		bt_update.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconUpdate.png"))));
		contentPane.add(bt_update);
		bt_update.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton bt_delete = new JButton("Delete");
		bt_delete.setBounds(10, 450, 157, 51);
		bt_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaCongTy.setEditable(true);

				delete();
				clearFields();
			}
		});

		bt_delete.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconDelete.png"))));
		contentPane.add(bt_delete);
		bt_delete.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton bt_insert = new JButton("Insert");
		bt_insert.setBounds(10, 367, 157, 51);
		bt_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaCongTy.setEditable(true);
				insert();
				reload();

			}

		});

		bt_insert.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconInsert.png"))));
		contentPane.add(bt_insert);
		bt_insert.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lbl_MaCongTy = new JLabel("Mã công ty :");
		lbl_MaCongTy.setBounds(10, 79, 107, 32);
		lbl_MaCongTy.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl_MaCongTy);

		JLabel lbl_TenCongTy = new JLabel("Tên công ty :");
		lbl_TenCongTy.setBounds(10, 134, 107, 32);
		lbl_TenCongTy.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl_TenCongTy);

		JLabel lbl_TonKho = new JLabel("Số điện thoại :");
		lbl_TonKho.setBounds(10, 243, 122, 32);
		lbl_TonKho.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl_TonKho);

		textField_MaCongTy = new JTextField();
		textField_MaCongTy.setBounds(127, 82, 232, 32);
		textField_MaCongTy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaCongTy.setColumns(10);
		contentPane.add(textField_MaCongTy);

		textField_TenCongTy = new JTextField();
		textField_TenCongTy.setBounds(127, 135, 232, 32);
		textField_TenCongTy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_TenCongTy.setColumns(10);
		contentPane.add(textField_TenCongTy);
		
		textField_DiaChi = new JTextField();
		textField_DiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_DiaChi.setColumns(10);
		textField_DiaChi.setBounds(127, 190, 232, 32);
		contentPane.add(textField_DiaChi);
		
		textField_Email = new JTextField();
		textField_Email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_Email.setColumns(10);
		textField_Email.setBounds(127, 297, 232, 32);
		contentPane.add(textField_Email);

		textField_SoDienThoai = new JTextField();
		textField_SoDienThoai.setBounds(127, 244, 232, 32);
		textField_SoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_SoDienThoai.setColumns(10);
		contentPane.add(textField_SoDienThoai);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(369, 464, 524, 32);
		verticalBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(verticalBox);

		lblNote = new JLabel("NOTE");
		lblNote.setBounds(379, 441, 74, 24);
		lblNote.setHorizontalAlignment(SwingConstants.LEFT);
		lblNote.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconNote.png"))));
		lblNote.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		contentPane.add(lblNote);

		JButton bt_clear = new JButton("Clear");
		bt_clear.setBounds(189, 367, 157, 51);
		bt_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_MaCongTy.setEditable(true);
				textField_MaCongTy.setText("");
				textField_TenCongTy.setText("");
				textField_DiaChi.setText("");
				textField_SoDienThoai.setText("");
				textField_Email.setText("");
			}
		});
		bt_clear.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconClear.png"))));

		bt_clear.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(bt_clear);

		note = new JLabel("<html>Mã Công Ty có định dạng <b>CTxxx</b> (x: số nguyên) !</html>");
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
		
		JLabel lblGi_Email = new JLabel("Email :");
		lblGi_Email.setForeground(Color.BLACK);
		lblGi_Email.setBackground(new Color(255, 255, 255));
		lblGi_Email.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGi_Email.setBounds(14, 296, 107, 32);
		contentPane.add(lblGi_Email);
				
				JLabel lbl_DiaChi = new JLabel("Địa chỉ :");
				lbl_DiaChi.setForeground(Color.BLACK);
				lbl_DiaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
				lbl_DiaChi.setBackground(Color.WHITE);
				lbl_DiaChi.setBounds(10, 189, 107, 32);
				contentPane.add(lbl_DiaChi);
				
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

			ResultSet rst = stm.executeQuery("Select * From NhaCungCap");

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
		if ( textField_MaCongTy.getText().equals("")
				|| textField_TenCongTy.getText().equals("") 
				|| textField_DiaChi.getText().equals("")
				|| textField_Email.getText().equals("")
				|| textField_SoDienThoai.getText().equals("")){
				

			// Tạo nội dung lỗi

			note.setText("Vui lòng chọn thông tin Công Ty muốn xóa !");
			note.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin Công Ty muốn xóa !");
			// Hiển thị lỗi
			note.setVisible(true);
		} else {
			try {

				String sql = "DELETE FROM nhaCungCap WHERE maCT = '" + textField_MaCongTy.getText() + "'";
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
		if ( textField_MaCongTy.getText().equals("")
				|| textField_TenCongTy.getText().equals("") 
				|| textField_DiaChi.getText().equals("")
				|| textField_Email.getText().equals("")
				|| textField_SoDienThoai.getText().equals("")){
			// Tạo nội dung lỗi
			note.setText("Vui lòng điền đầy đủ thông tin !");
			note.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin !");
			// Hiển thị lỗi
			note.setVisible(true);

		} else if (!textField_TenCongTy.getText().matches("^[a-zA-Z \\p{L}]+$")) {// \\p{L} phù hợp với bất kỳ ký tự chữ cái
																				// nào trong bất kỳ ngôn ngữ nào.
			JOptionPane.showMessageDialog(null, "Tên khách hàng không đúng!");
			note.setText("Tên khách hàng không đúng!");
		} else if (!textField_SoDienThoai.getText().matches("^[0-9]{10}$")) {// xác định các trường hợp xãy ra.
																					// tùy chọn.
			JOptionPane.showMessageDialog(null, "số điện thoại không đúng! ");
			note.setText("số điện thoại không đúng!");
		} else {
			try {
				String maCT = textField_MaCongTy.getText();
				int SDT = Integer.parseInt(textField_SoDienThoai.getText());

				String tenCT = textField_TenCongTy.getText();
				String diaChi = textField_DiaChi.getText();
				String email = textField_Email.getText();

				
				
				String sql = "insert into nhaCungCap(maCT,tenCT,diaChi,SDT,email) Values('" + maCT 
						+ "',N'" + tenCT 
						+ "',N'" + diaChi 
						+ "'," + SDT 
						+ ",N'" + email
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
				ex.printStackTrace();
			}
		}
	}

	public void update() {
		if (textField_MaCongTy.getText().equals("")
				|| textField_TenCongTy.getText().equals("") 
				|| textField_DiaChi.getText().equals("")
				|| textField_Email.getText().equals("")
				|| textField_SoDienThoai.getText().equals("")){
				

			// Tạo nội dung lỗi

			note.setText("Vui lòng chọn thông tin Công Ty! ");
			note.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, "Chưa chọn thông tin Công Ty cần thay đổi !");
			// Hiển thị lỗi
			note.setVisible(true);
		
		} else if (!textField_SoDienThoai.getText().matches("^[0-9]{10}$")) {// xác định các trường hợp xãy ra.
			JOptionPane.showMessageDialog(null, "số điện thoại không đúng! ");
			note.setText("số điện thoại không đúng!");
		} else {
			try {
				String maCT = textField_MaCongTy.getText();
				int SDT = Integer.parseInt(textField_SoDienThoai.getText());

				String tenCT = textField_TenCongTy.getText();
				String diaChi = textField_DiaChi.getText();
				String email = textField_Email.getText();
				

				String sql = "update nhaCungCap set tenCT = N'" + tenCT 
						+ "', diaChi = N'" +diaChi
						+ "', email = N'" + email
						+ "', Sdt = " + SDT 
						+ " Where MaCT = '" + maCT +"'";

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
		textField_MaCongTy.setText("");
		textField_TenCongTy.setText("");
		textField_SoDienThoai.setText("");
		textField_Email.setText("");
		textField_DiaChi.setText("");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhaCungCap frame = new NhaCungCap();
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
		textField_MaCongTy.setText(Model.getValueAt(selectedrow, 0).toString());
		textField_MaCongTy.setEditable(false);
		textField_TenCongTy.setText(Model.getValueAt(selectedrow, 1).toString());
		textField_DiaChi.setText(Model.getValueAt(selectedrow, 2).toString());
		textField_SoDienThoai.setText(Model.getValueAt(selectedrow, 3).toString());
		textField_Email.setText(Model.getValueAt(selectedrow, 4).toString());

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


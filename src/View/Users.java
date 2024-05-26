package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Connection.ConnectJDBC;
import SignUp_SignIn.SignIn;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;

public class Users extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	
	public JTextField Search_username;
	private JTable table;
	public JTextField textField_pass;
	public JTextField textField_email;
	public JTextField textField_username;
	public JTextField textField_SDT;
	
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

	public Users() {

		try {

			conn = ConnectJDBC.getConnection();

			stm = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		setFont(new Font("Dialog", Font.ITALIC, 18));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 545);
		setTitle("Admin");
		setForeground(Color.BLACK);

		URL urll = KhachHang.class.getResource("iconKH.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urll);
		this.setIconImage(img);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JButton QL = new JButton("");
		QL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showOptionDialog(null, "Bạn muốn tiếp tục?", "Xác nhận",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (result == JOptionPane.YES_OPTION) {
					KhachHang k = new KhachHang();
					k.setVisible(true);
					setVisible(false);
				}
			}
		});
		QL.setBounds(0, 10, 46, 32);
		contentPane.add(QL);
		QL.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhachHang.class.getResource("iconback.png"))));

		JLabel lblNewLabel = new JLabel("UserName:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(167, 20, 126, 32);
		contentPane.add(lblNewLabel);

		Search_username= new JTextField();
		Search_username.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Search_username.setBounds(303, 21, 205, 32);
		contentPane.add(Search_username);
		Search_username.setColumns(10);

		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhachHang.class.getResource("iconSearch.png"))));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_username.setEditable(true);

				
				String unSearch = Search_username.getText();
				if (unSearch.isEmpty()) {
					String tx = JOptionPane
							.showInputDialog("Vui lòng nhập username vào đây để truy xuất thông tin");
					Search_username.setText(tx);
				} else {
					String sql = "Select * From Users where username = '" + unSearch + "'";
					try {
						PreparedStatement pst = conn.prepareStatement(sql);
						ResultSet rst = pst.executeQuery();
						if (rst.next()) {
							textField_username.setText(rst.getString("username"));
							textField_pass.setText(rst.getString("pass"));
							textField_email.setText(rst.getString("email"));
							textField_SDT.setText(rst.getString("SDT"));

							pst.close();
						} else {
							JOptionPane.showConfirmDialog(null, "Erroll in 'Tìm' ");
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(533, 19, 106, 34);
		contentPane.add(btnNewButton);

		JButton bt_update = new JButton("Update");
		bt_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_username.setEditable(true);

				update();
				clearFields();

			}
		});

		bt_update.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhachHang.class.getResource("iconUpdate.png"))));
		bt_update.setBounds(187, 369, 170, 51);
		contentPane.add(bt_update);
		bt_update.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton bt_delete = new JButton("Delete");
		bt_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_username.setEditable(true);

				delete();
				clearFields();
			}
		});

		bt_delete.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhachHang.class.getResource("iconDelete.png"))));

		bt_delete.setBounds(45, 445, 283, 51);
		contentPane.add(bt_delete);
		bt_delete.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblMakh = new JLabel("username:");
		lblMakh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMakh.setBounds(10, 79, 80, 32);
		contentPane.add(lblMakh);

		JLabel lblTenkh = new JLabel("pass");
		lblTenkh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenkh.setBounds(10, 145, 53, 32);
		contentPane.add(lblTenkh);

		JLabel lblTuoi = new JLabel("email");
		lblTuoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTuoi.setBounds(10, 213, 53, 32);
		contentPane.add(lblTuoi);

		JLabel lblDiachi = new JLabel("SDT");
		lblDiachi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDiachi.setBounds(10, 279, 53, 32);
		contentPane.add(lblDiachi);

		textField_username = new JTextField();
		textField_username.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_username.setColumns(10);
		textField_username.setBounds(91, 82, 268, 32);
		contentPane.add(textField_username);

		textField_pass = new JTextField();
		textField_pass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_pass.setColumns(10);
		textField_pass.setBounds(91, 148, 268, 32);
		contentPane.add(textField_pass);

		textField_email = new JTextField();
		textField_email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_email.setColumns(10);
		textField_email.setBounds(91, 216, 268, 32);
		contentPane.add(textField_email);

		textField_SDT = new JTextField();
		textField_SDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_SDT.setColumns(10);
		textField_SDT.setBounds(91, 282, 268, 32);
		contentPane.add(textField_SDT);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		verticalBox.setBounds(369, 464, 524, 32);
		contentPane.add(verticalBox);

		lblNote = new JLabel("NOTE");
		lblNote.setHorizontalAlignment(SwingConstants.LEFT);
		lblNote.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhachHang.class.getResource("iconNote.png"))));
		lblNote.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		lblNote.setBounds(379, 441, 64, 24);
		contentPane.add(lblNote);

		JButton bt_clear = new JButton("Clear");
		bt_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_SDT.setText("");
				textField_username.setEditable(true);
				textField_pass.setText("");
				textField_email.setText("");
				textField_username.setText("");
			}
		});
		bt_clear.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhachHang.class.getResource("iconClear.png"))));

		bt_clear.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_clear.setBounds(20, 369, 157, 51);
		contentPane.add(bt_clear);

		note = new JLabel("....");
		note.setBounds(379, 464, 514, 37);
		contentPane.add(note);
		note.setForeground(Color.RED);
		note.setFont(new Font("Tahoma", Font.ITALIC, 15));

		

		reload();
		Model = new DefaultTableModel(vData, vTitle);
		table = new JTable(Model);
		table.addMouseListener((MouseListener) this);
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

		JLabel background = new JLabel("");
		background.setBounds(0, 0, 909, 511);
		contentPane.add(background);
		background.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhachHang.class.getResource("bkusers.jpg"))));

		
	
		setLocationRelativeTo(null); // hiển thị cửa sổ jframe ra ngay giữa màn hình
	}

	public void reload() {
		try {
			vTitle.clear();
			vData.clear();

			ResultSet rst = stm.executeQuery("Select * From Users");

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
		if (textField_SDT.getText().equals("") || textField_username.getText().equals("")
				|| textField_email.getText().equals("") || textField_pass.getText().equals("")) {

			// Tạo nội dung lỗi

			note.setText("Vui lòng chọn thông tin người dùng muốn xóa !");
			note.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin người dùng muốn xóa !");
			// Hiển thị lỗi
			note.setVisible(true);
		} else {
			try {
				Vector st = (Vector) vData.elementAt(selectedrow);

				String sql = "DELETE FROM Users WHERE username = '" + st.elementAt(0) + "'";
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
		if (textField_SDT.getText().equals("") || textField_username.getText().equals("")
				|| textField_email.getText().equals("") || textField_pass.getText().equals("")) {

			// Tạo nội dung lỗi

			note.setText("Vui lòng điền đầy đủ thông tin !");
			note.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin !");
			// Hiển thị lỗi
			note.setVisible(true);

		} else {
			try {
				String username = textField_username.getText();
				String pass = textField_pass.getText();

				String email = textField_email.getText();
				int SDT = Integer.parseInt(textField_SDT.getText());
				
				String sql = "insert into Users(username,pass,email,SDT) Values(N'" + username + "',N'" + pass
						+ "',N'" + email + "'," + SDT + ")";

				// Cập nhật vào csdl
				this.stm.executeUpdate(sql);
				// Cập nhật giao diện của sổ chính
				this.reload();
				this.Model.fireTableDataChanged();
				clearFields();
				JOptionPane.showMessageDialog(null, "Chào mừng đến với dịch vụ của chúng tôi!");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void update() {
		if (textField_SDT.getText().equals("") || textField_username.getText().equals("")
				|| textField_email.getText().equals("") || textField_pass.getText().equals("")) {

			// Tạo nội dung lỗi

			note.setText("Thiếu thông tin khách hàng!");
			note.setForeground(Color.RED);
			JOptionPane.showMessageDialog(null, "Chưa chọn thông tin khách hàng cần thay đổi !");
			// Hiển thị lỗi
			note.setVisible(true);
		} else {

			try {

				String username = textField_username.getText();
				String pass = textField_pass.getText();

				String email = textField_email.getText();
				int SDT = Integer.parseInt(textField_SDT.getText());
				

				String sql = "update Users set pass = N'" + pass + "', email = '" + email
						+ "', SDT = " + SDT + " Where username = '" + username+ "'";

				// Cập nhật vào csdl
				this.stm.executeUpdate(sql);
				// Cập nhật giao diện của sổ chính
				this.reload();
				this.Model.fireTableDataChanged();
				JOptionPane.showMessageDialog(null, "Cập nhật thành công! ");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void clearFields() {
		textField_username.setText("");
		textField_pass.setText("");
		textField_email.setText("");
		textField_SDT.setText("");
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Users frame = new Users();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void mouseClicked(MouseEvent e) {
		int selectedrow = table.getSelectedRow();
		textField_username.setText(Model.getValueAt(selectedrow, 0).toString());
		textField_username.setEditable(false);
		textField_pass.setText(Model.getValueAt(selectedrow, 1).toString());
		textField_email.setText(Model.getValueAt(selectedrow, 2).toString());
		textField_SDT.setText(Model.getValueAt(selectedrow, 3).toString());

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}


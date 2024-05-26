package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.ConnectJDBC;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class hoaDon extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String mact;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hoaDon frame = new hoaDon(null,null,null,null,null);
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
	public hoaDon(String makh, String masp, String manv, String soluong, String makho) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoá Đơn Thanh Toán");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(77, 10, 309, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Khách Hàng :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(10, 62, 131, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên rượu :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1_1.setBounds(10, 104, 131, 32);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Giá(VNĐ):");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1_2.setBounds(10, 188, 131, 32);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Số lượng :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1_3.setBounds(10, 237, 131, 32);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Thành Tiền :");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1_4.setBounds(10, 279, 131, 32);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Địa chỉ cửa hàng: ");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1_5.setBounds(10, 373, 189, 32);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Nhân viên bán hàng:");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1_6.setBounds(10, 420, 189, 32);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("Thông tin thêm");
		lblNewLabel_1_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.ITALIC, 17));
		lblNewLabel_1_7.setBounds(168, 331, 131, 32);
		contentPane.add(lblNewLabel_1_7);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 347, 161, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(296, 347, 161, 2);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Xuất Xứ :");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1_2_1.setBounds(10, 146, 131, 32);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lbKhachHang = new JLabel("");
		lbKhachHang.setFont(new Font("Tahoma", Font.BOLD, 19));
		lbKhachHang.setBounds(151, 62, 306, 32);
		contentPane.add(lbKhachHang);
		
		JLabel lbtenRuou = new JLabel("");
		lbtenRuou.setFont(new Font("Tahoma", Font.BOLD, 19));
		lbtenRuou.setBounds(120, 104, 337, 32);
		contentPane.add(lbtenRuou);
		
		JLabel lbXuatXu = new JLabel("");
		lbXuatXu.setFont(new Font("Tahoma", Font.BOLD, 19));
		lbXuatXu.setBounds(120, 146, 337, 32);
		contentPane.add(lbXuatXu);
		
		JLabel lbGia = new JLabel("");
		lbGia.setFont(new Font("Tahoma", Font.BOLD, 19));
		lbGia.setBounds(130, 188, 327, 32);
		contentPane.add(lbGia);
		
		JLabel lbSoLuong = new JLabel("");
		lbSoLuong.setFont(new Font("Tahoma", Font.BOLD, 19));
		lbSoLuong.setBounds(120, 237, 337, 32);
		contentPane.add(lbSoLuong);
		
		JLabel lbThanhTien = new JLabel("");
		lbThanhTien.setFont(new Font("Tahoma", Font.BOLD, 19));
		lbThanhTien.setBounds(130, 279, 309, 32);
		contentPane.add(lbThanhTien);
		
		JLabel lbDiaChiCuaHang = new JLabel("");
		lbDiaChiCuaHang.setFont(new Font("Tahoma", Font.BOLD, 19));
		lbDiaChiCuaHang.setBounds(209, 373, 189, 32);
		contentPane.add(lbDiaChiCuaHang);
		
		JLabel lbNhanVien = new JLabel("");
		lbNhanVien.setFont(new Font("Tahoma", Font.BOLD, 19));
		lbNhanVien.setBounds(209, 420, 189, 32);
		contentPane.add(lbNhanVien);
		
		JButton btnNewButton = new JButton("Xác Nhận");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton.setBounds(138, 506, 161, 32);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("cảm ơn và hẹn gặp lại quý khách !");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_2.setBounds(120, 462, 256, 25);
		contentPane.add(lblNewLabel_2);
		
		
		ConnectJDBC jdbc = new ConnectJDBC();
		Connection conn = null;

		conn = jdbc.getConnection();
		String sql="select tenkh from khachHang where makh ='"+makh+"'";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rst = pst.executeQuery();
			if(rst.next()) {
				lbKhachHang.setText(rst.getString("tenkh"));
			}
			rst.close();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql1="select tensp,gia,mact from sanPham where masp='"+masp+"'";
		try {
			PreparedStatement pst1 = conn.prepareStatement(sql1);
			ResultSet rst1 = pst1.executeQuery();
			if(rst1.next()) {
				lbtenRuou.setText(rst1.getString("tensp"));
				lbGia.setText(rst1.getString("Gia"));
				mact = rst1.getString("mact");
			}
			rst1.close();
			pst1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql3="select diaChiKhoHang from khoHang where makhohang='"+makho+"'";
		try {
			PreparedStatement pst3 = conn.prepareStatement(sql3);
			ResultSet rst3 = pst3.executeQuery();
			if(rst3.next()) {
				lbDiaChiCuaHang.setText(rst3.getString("diaChiKhoHang"));
			}
			rst3.close();
			pst3.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql4="select tennv from nhanVien where manv='"+manv+"'";
		try {
			PreparedStatement pst4 = conn.prepareStatement(sql4);
			ResultSet rst4 = pst4.executeQuery();
			if(rst4.next()) {
				lbNhanVien.setText(rst4.getString("tennv"));
			}
			rst4.close();
			pst4.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql5="select diachi from nhaCungCap where mact ='"+ mact +"'";
		try {
			PreparedStatement pst5 = conn.prepareStatement(sql5);
			ResultSet rst5 = pst5.executeQuery();
			if(rst5.next()) {
				lbXuatXu.setText(rst5.getString("diachi"));
			}
			rst5.close();
			pst5.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Float thanhTien = Float.parseFloat(soluong) *Float.parseFloat(lbGia.getText());
		lbThanhTien.setText(String.valueOf(thanhTien));
		lbSoLuong.setText(soluong);
	}
}

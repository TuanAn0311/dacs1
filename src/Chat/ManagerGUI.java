package Chat;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import View.KhoHang;
import View.trangChu;

public class ManagerGUI extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblNewLabel;
    private JTextField textField;
    private JTabbedPane tabbedPane;
    private JButton btnNewButton;
    // _________________________________________
    ManagerGUI thisManager;
    ServerSocket socket = null;
    BufferedReader br = null;
    Thread t;
    private JLabel lblNewLabel_2;
    private JButton btnNewButton_2;

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    ManagerGUI frame = new ManagerGUI();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    public ManagerGUI() {
	initComponents();
	thisManager = this;
    }

    private void initComponents() {
	setTitle("Tổng Đài");
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 835, 637);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getLblNewLabel());
	contentPane.add(getTextField());
	contentPane.add(getTabbedPane());
	contentPane.add(getBtnNewButton());
	setLocationRelativeTo(null);
	contentPane.add(getBtnNewButton_2());
    }

    public JLabel getLblNewLabel() {
	if (lblNewLabel == null) {
	    lblNewLabel = new JLabel("Manager Port");
	    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
	    lblNewLabel.setBounds(161, 22, 148, 53);
	}
	return lblNewLabel;
    }

    public JTextField getTextField() {
	if (textField == null) {
	    textField = new JTextField();
	    textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
	    textField.setBounds(290, 27, 144, 46);
	    textField.setColumns(10);
	}
	return textField;
    }

    public JTabbedPane getTabbedPane() {
	if (tabbedPane == null) {
	    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	    tabbedPane.setFont(new Font("Sylfaen", Font.PLAIN, 20));
	    tabbedPane.setBorder(null);
	    tabbedPane.setBounds(12, 73, 805, 521);
	    tabbedPane.addTab(null, null, getLblNewLabel_2(), null);
	}
	return tabbedPane;
    }

    @Override
    public void run() {
	while (true)

	    try {
		// Chấp nhận kết nối từ Client
		Socket staffSocket = socket.accept();
		if (staffSocket != null) {
		    // Lấy tên của nhân viên vừa nhắn tin cho Server
		    // Có nhi�?u cách xử lý, đây là cách của mình
		    br = new BufferedReader(new InputStreamReader(staffSocket.getInputStream()));
		    String staffName = br.readLine();
		    staffName = staffName.substring(0, staffName.indexOf(":"));

		    // Tạo ChatPanel và show nó vào cái TabbedPane, khá là đơn giản
		    ChatPanel chatPanel = new ChatPanel(staffSocket, "Manager", staffName);
		    tabbedPane.add(staffName, chatPanel);
		    chatPanel.updateUI();

		    // Chạy Thread ChatPanel để kiểm tra các tin nhắn đến và đi (�?ã giải thích ở
		    // phần 1)
		    Thread t = new Thread(chatPanel);
		    t.start();
		}

		// Không cần thiết cho lắm
		Thread.sleep(1000);
	    } catch (Exception e) {
		// Do not change this because it spawn try-catch many time while running thread!
	    }
    }

    public JButton getBtnNewButton() {
	if (btnNewButton == null) {
	    btnNewButton = new JButton("START SERVER");
	    btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
	    btnNewButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		    // Cổng mặc định là 8, bạn có thể đổi thành số bạn thích
		    int port = 8;
		    try {
			// Kiểm tra dữ liệu nhập vào
			port = Integer.parseInt(textField.getText());
		    } catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,
			                "Can't start at this port, program will use the default port=8\nDetails: " + e,
			                "Error while read Port", JOptionPane.ERROR_MESSAGE);
		    }
		    try {
			// Hiểu nôm na là chạy Server tại port này
			socket = new ServerSocket(port);
			JOptionPane.showMessageDialog(contentPane, "Server is running at port: " + port, "Started server",
			                JOptionPane.INFORMATION_MESSAGE);

		    } catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Details: " + e, "Start server error",
			                JOptionPane.ERROR_MESSAGE);
		    }

		    // Chạy Server (class hiên tại) để kiểm tra các luồng kết nối vào server sau này
		    // Ở trên mình đã gán thisManager=this (tức class hiện tại)
		    Thread t = new Thread(thisManager);
		    t.start();
		}
	    });
	    btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
	    btnNewButton.setBounds(446, 25, 161, 47);
	}
	return btnNewButton;
    }

    public JLabel getLblNewLabel_2() {
	if (lblNewLabel_2 == null) {
	    lblNewLabel_2 = new JLabel("Waitting for client");
	    lblNewLabel_2.setBackground(Color.WHITE);
	    lblNewLabel_2.setForeground(Color.RED);
	    lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 28));
	    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	}
	return lblNewLabel_2;
    }
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");
			btnNewButton_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhoHang.class.getResource("iconback.png"))));
			btnNewButton_2.addActionListener(new ActionListener() {
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
			btnNewButton_2.setBounds(12, 10, 51, 46);
		}
		return btnNewButton_2;
	}
	
}
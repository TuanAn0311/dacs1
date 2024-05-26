package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageTransitionExample extends JPanel {
    private Image[] images; // Mảng chứa các ảnh
    private int currentImageIndex; // Chỉ số của ảnh hiện tại

    public ImageTransitionExample() {
        // Khởi tạo mảng ảnh và chỉ số ảnh hiện tại
    	 images = new Image[5];
         images[0] = new ImageIcon("D:\\JAVA\\eclipse-workspace\\dacs1\\img\\anh1.jpg").getImage();
         images[1] = new ImageIcon("D:\\JAVA\\eclipse-workspace\\dacs1\\img\\anh2.jpg").getImage();
         images[2] = new ImageIcon("D:\\JAVA\\eclipse-workspace\\dacs1\\img\\anh3.jpg").getImage();
         images[3] = new ImageIcon("D:\\JAVA\\eclipse-workspace\\dacs1\\img\\anh4.jpg").getImage();
         images[4] = new ImageIcon("D:\\JAVA\\eclipse-workspace\\dacs1\\img\\anh5.jpg").getImage();
        currentImageIndex = 0;

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentImageIndex = (currentImageIndex + 1) % images.length; // Di chuyển tới ảnh tiếp theo
                repaint(); // Vẽ lại giao diện
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Vẽ ảnh hiện tại trên JPanel
        if (images[currentImageIndex] != null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int imageWidth = images[currentImageIndex].getWidth(this);
            int imageHeight = images[currentImageIndex].getHeight(this);

            // Tính toán tỷ lệ co dãn ảnh
            double scaleX = (double) panelWidth / imageWidth;
            double scaleY = (double) panelHeight / imageHeight;
            double scale = Math.min(scaleX, scaleY);

            // Tính toán kích thước mới của ảnh
            int newWidth = (int) (imageWidth * scale);
            int newHeight = (int) (imageHeight * scale);

            // Vẽ ảnh đã co dãn lên JPanel
            int x = (panelWidth - newWidth) / 2;
            int y = (panelHeight - newHeight) / 2;
            g.drawImage(images[currentImageIndex], x, y, newWidth, newHeight, this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600); // Kích thước JPanel
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Existing JFrame");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel existingJPanel = new JPanel();
                frame.getContentPane().add(existingJPanel);

                ImageTransitionExample transitionExample = new ImageTransitionExample();
                existingJPanel.add(transitionExample);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
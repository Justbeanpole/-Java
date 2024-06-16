import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCV_Training {
    static Container contentPane;
    static JFrame frame;
    static String fileName;
    static int inH, inW, outH, outW;
    static Mat inImg, outImg, originalImg;
    static MyPanel panel;

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    static class MyPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            double RGB[];
            int R, G, B;
            for (int i=0; i < outH; i++) {
                for (int k=0; k < outW; k++) {
                    RGB = outImg.get(k,i);
                    if (outImg.channels() == 3) { // 칼라 영상
                        B = (int)RGB[0];
                        G = (int)RGB[1];
                        R = (int)RGB[2];
                    } else { // 그레이스케일 또는 흑백 영상
                        R = G = B = (int)RGB[0];
                    }
                    g.setColor(new Color(R, G, B));
                    g.drawRect(i, k, 1, 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        frame = new JFrame("OpenCV Training");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());
        panel = new MyPanel();
        frame.add(panel, BorderLayout.CENTER);
        contentPane = frame.getContentPane();
        addMenu();

        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    static void display() {
        Graphics g = contentPane.getGraphics();
        contentPane.paintAll(g);
        frame.setPreferredSize(new Dimension(outH + 15, outW + 63));
        frame.pack();
    }

    static void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu parentFile = new JMenu("파일");
        JMenu parentAngle = new JMenu("각도 조정");
        JMenu parentEffect = new JMenu("사진 효과");

        menuBar.add(parentFile);
        menuBar.add(parentAngle);
        menuBar.add(parentEffect);

        JMenuItem openFile = new JMenuItem("파일 열기");
        JMenuItem rotate90 = new JMenuItem("90도 돌리기");
        JMenuItem rotate180 = new JMenuItem("180도 돌리기");
        JMenuItem rotate270 = new JMenuItem("270도 돌리기");
        JMenuItem grayScale = new JMenuItem("회색 화면");
        JMenuItem blackWhite = new JMenuItem("흑백 화면");
        JMenuItem canny = new JMenuItem("경계선 추출");
        JMenuItem back = new JMenuItem("원본 보기");
        parentFile.add(openFile);
        parentAngle.add(rotate90);
        parentAngle.add(rotate180);
        parentAngle.add(rotate270);
        parentEffect.add(grayScale);
        parentEffect.add(blackWhite);
        parentEffect.add(canny);
        parentEffect.add(back);


        openFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileDialog fd = new FileDialog(frame, "파일 열기", FileDialog.LOAD);
                fd.setVisible(true);
                fileName = fd.getDirectory() + fd.getFile();
                inImg = Imgcodecs.imread(fileName);
                inH = inImg.cols();
                inW = inImg.rows();

                outImg = inImg.clone();
                originalImg = inImg.clone();
                outH = outImg.cols();
                outW = outImg.rows();
                display();
            }
        });
        rotate90.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outH = inW; // 높이와 폭이 바뀜
                outW = inH;
                Core.rotate (inImg, outImg, Core.ROTATE_90_CLOCKWISE);
                display();
            }
        });
        rotate180.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outH = inH;
                outW = inW;
                Core.rotate (inImg, outImg, Core.ROTATE_180);
                display();
            }
        });
        rotate270.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outH = inW; // 높이와 폭이 바뀜
                outW = inH;
                Core.rotate (inImg, outImg, Core.ROTATE_90_COUNTERCLOCKWISE);
                display();
            }
        });
        grayScale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outH = inH;
                outW = inW;
                Imgproc.cvtColor(inImg, outImg, Imgproc.COLOR_RGB2GRAY);
                display();
            }
        });
        blackWhite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outH = inH;
                outW = inW;
                Imgproc.cvtColor(inImg, outImg, Imgproc.COLOR_RGB2GRAY);
                Imgproc.threshold(outImg, outImg , 127 , 255, Imgproc.THRESH_BINARY);
                display();
            }
        });
        canny.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outH = inH;
                outW = inW;
                Imgproc.Canny(inImg, outImg, 100, 300);
                display();
            }
        });
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (originalImg != null) {
                    outImg = originalImg.clone();
                    outH = outImg.cols();
                    outW = outImg.rows();
                    display();
                }
            }
        });
    }
}



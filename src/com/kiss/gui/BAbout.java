package com.kiss.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BAbout extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JButton btnOK;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblngNguynTrng;
	private JLabel lblNguynHiuThnh;
	private JLabel lblInstructor;
	private JLabel lblMrNguynNht;
	
	


	/** Returns an ImageIcon, or null if the path was invalid. this is for set image for a component*/
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = BMain.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

	/**
	 * Create the dialog.
	 */
	public BAbout() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("About Banana Crypter");
		setResizable(false);
		setBounds(100, 100, 353, 309);
		getContentPane().setLayout(null);
		
		
		this.panel = new JPanel(){
			/**
			 * 
			 */		
			private static final long serialVersionUID = 1L;
			ImageIcon icon = BAbout.createImageIcon("/com/kiss/gui/about.jpg");
			public void paintComponent(Graphics g){
				if (icon == null)
					return;
		        Dimension d = getSize();
		        g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
		        setOpaque(false);
		        super.paintComponent(g);
		    }  
		};
		this.panel.setForeground(Color.YELLOW);
		this.panel.setBackground(new Color(255, 255, 204));
		this.panel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		this.panel.setBounds(0, 0, 347, 281);
		getContentPane().add(this.panel);
		
		this.lblNewLabel = new JLabel("Developer Group:");
		this.lblNewLabel.setBounds(17, 11, 205, 25);
		this.lblNewLabel.setForeground(new Color(0, 255, 255));
		this.lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 21));
		
		this.lblNewLabel_2 = new JLabel("Bùi Tiến Đạt");
		this.lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.lblNewLabel_2.setBounds(60, 47, 196, 25);
		this.lblNewLabel_2.setForeground(Color.YELLOW);
		//this.lblNewLabel_5.setIcon(ChatWindow.createImageIcon("/icon/banana-man1.gif"));
		
		this.btnOK = new JButton("OK");
		this.btnOK.setBounds(249, 219, 67, 33);
		this.btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		this.lblNewLabel_6 = new JLabel("Please e-mail to chjcken123@gmail.com ");
		this.lblNewLabel_6.setBounds(17, 220, 179, 13);
		this.lblNewLabel_6.setForeground(Color.BLUE);
		this.lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		this.lblNewLabel_7 = new JLabel("if you have any question.");
		this.lblNewLabel_7.setBounds(17, 239, 115, 13);
		this.lblNewLabel_7.setForeground(Color.BLUE);
		this.lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		this.lblNewLabel_8 = new JLabel("Banana Inc Copyright 2014");
		this.lblNewLabel_8.setBounds(119, 263, 114, 11);
		this.lblNewLabel_8.setForeground(Color.WHITE);
		this.lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 9));
		this.panel.setLayout(null);
		this.panel.add(this.lblNewLabel_2);
		this.panel.add(this.lblNewLabel);
		this.panel.add(this.btnOK);
		this.panel.add(this.lblNewLabel_6);
		this.panel.add(this.lblNewLabel_7);
		this.panel.add(this.lblNewLabel_8);
		
		this.lblngNguynTrng = new JLabel("Đặng Nguyễn Trường Huy");
		this.lblngNguynTrng.setForeground(Color.YELLOW);
		this.lblngNguynTrng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.lblngNguynTrng.setBounds(60, 87, 231, 25);
		this.panel.add(this.lblngNguynTrng);
		
		this.lblNguynHiuThnh = new JLabel("Nguyễn Hiếu Thịnh");
		this.lblNguynHiuThnh.setForeground(Color.YELLOW);
		this.lblNguynHiuThnh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.lblNguynHiuThnh.setBounds(60, 123, 196, 25);
		this.panel.add(this.lblNguynHiuThnh);
		
		this.lblInstructor = new JLabel("Instructor:");
		this.lblInstructor.setForeground(Color.CYAN);
		this.lblInstructor.setFont(new Font("Trebuchet MS", Font.BOLD, 21));
		this.lblInstructor.setBounds(17, 166, 115, 25);
		this.panel.add(this.lblInstructor);
		
		this.lblMrNguynNht = new JLabel("Mr. Nguyễn Nhật Nam");
		this.lblMrNguynNht.setForeground(Color.GREEN);
		this.lblMrNguynNht.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.lblMrNguynNht.setBounds(141, 166, 196, 25);
		this.panel.add(this.lblMrNguynNht);

	}
}

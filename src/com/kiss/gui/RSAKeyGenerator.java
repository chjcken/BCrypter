package com.kiss.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.kiss.rsa.RSA;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class RSAKeyGenerator extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblKeyLength;
	private JTextField txtKeyLength;
	private JLabel lblSaveTo;
	private JTextField txtFolder;
	private JButton btnBrowse;
	private JButton btnGenerate;
	private JFileChooser fc;
	private final String FILE_SEPARATOR = System.getProperty("file.separator");
	private final String DESKTOP_PATH = System.getProperty("user.home") + FILE_SEPARATOR + "Desktop";

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			RSAKeyGenerator dialog = new RSAKeyGenerator();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public RSAKeyGenerator() {
		this.fc = new JFileChooser(DESKTOP_PATH);
		this.fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("RSA Key Generator");
		setResizable(false);
		setBounds(100, 100, 292, 259);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		this.lblKeyLength = new JLabel("Key Length");
		this.lblKeyLength.setBounds(10, 35, 64, 21);
		contentPanel.add(this.lblKeyLength);
		
		this.txtKeyLength = new JTextField();
		this.txtKeyLength.setText("1024");
		this.txtKeyLength.setBounds(89, 35, 38, 20);
		contentPanel.add(this.txtKeyLength);
		this.txtKeyLength.setColumns(10);
		
		this.lblSaveTo = new JLabel("Save to");
		this.lblSaveTo.setBounds(10, 80, 55, 21);
		contentPanel.add(this.lblSaveTo);
		
		this.txtFolder = new JTextField();
		this.txtFolder.setBounds(10, 104, 167, 20);
		contentPanel.add(this.txtFolder);
		this.txtFolder.setColumns(10);
		this.txtFolder.setText(DESKTOP_PATH);
		
		this.btnBrowse = new JButton("Browse...");
		this.btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resultCode = fc.showDialog(RSAKeyGenerator.this, "OK");
				if (resultCode == JFileChooser.APPROVE_OPTION){
					txtFolder.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		this.btnBrowse.setBounds(186, 101, 90, 23);
		contentPanel.add(this.btnBrowse);
		
		this.btnGenerate = new JButton("Generate");
		this.btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!checkValidInput())
						return;
					RSA.getInstance().generateKeyPair(Integer.parseInt(txtKeyLength.getText()), txtFolder.getText() + FILE_SEPARATOR);
					JOptionPane.showConfirmDialog(RSAKeyGenerator.this, 
							"public.key and private.key file are generated successfully at:\n" + txtFolder.getText(),"Success", JOptionPane.PLAIN_MESSAGE);
				} catch (NoSuchAlgorithmException | IOException e1) {
					JOptionPane.showConfirmDialog(RSAKeyGenerator.this, "Error while generating RSA key: " + e1.getMessage(),
							"Error", JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		this.btnGenerate.setBounds(89, 168, 98, 26);
		contentPanel.add(this.btnGenerate);
	}
	
	private boolean checkValidInput(){
		int length;
		try {
			length = Integer.parseInt(txtKeyLength.getText());
		} catch (Exception e) {
			length = -1;
		}
		if (length < 128 || length > 16384){
			JOptionPane.showConfirmDialog(this, "Key length must be a number between 512 and 1024!",
					"Invalid key length", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		File folder = new File(txtFolder.getText());
		if (txtFolder.getText().isEmpty() || !folder.exists()){
			JOptionPane.showConfirmDialog(this, "Invalid save folder!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		return true;
	}
}

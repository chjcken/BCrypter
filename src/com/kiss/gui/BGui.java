package com.kiss.gui;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.kiss.des.DES;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class BGui {

	private JFrame frame;
	private JTabbedPane tabbedPane;
	private JMenuBar menuBar;
	private JMenu mnProgram;
	private JMenuItem mntmExit;
	private JPanel panelDES;
	private JPanel panelRSA;
	private JPanel panelSomeAlg;
	private JTextField txtDESInput;
	private JButton btnDESInput;
	private JLabel lblInput;
	private JTextField txtDESOutput;
	private JLabel lblFileOutput;
	private JButton btnDESOutput;
	private JButton btnDESEncrypt;
	private JButton btnDESDecrypt;
	
	private JFileChooser fc;
	private JLabel lblStatus;
	private JLabel lblSecretKey;
	private JTextField txtKey;
	private JButton btnDESKeyGenerate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BGui window = new BGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BGui() {
		initialize();
		this.lblStatus.setText("");
		this.fc = new JFileChooser(System.getProperty("user.home") + "\\Desktop"){ //create file chooser dialog with default path is desktop
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void approveSelection() {
	            File f = getSelectedFile();
	            if (f.exists() && getDialogType() == SAVE_DIALOG) {
	                int result = JOptionPane.showConfirmDialog(this, f.getName() +
	                        " already exists.\nDo you want to overwrite it?", "Existing file",
	                        JOptionPane.YES_NO_OPTION);
	                switch (result) {
		                case JOptionPane.YES_OPTION:
		                    super.approveSelection();
		                    return;
		                
		                default:
		                    return;
	                }
	            }
	            super.approveSelection();
	        }
	    };
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 450, 372);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.getContentPane().setLayout(null);
		this.frame.setResizable(false);
		
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.tabbedPane.setBounds(0, 0, 444, 297);
		this.frame.getContentPane().add(this.tabbedPane);
		
		this.panelDES = new JPanel();
		this.tabbedPane.addTab("DES", null, this.panelDES, null);
		this.panelDES.setLayout(null);
		
		this.txtDESInput = new JTextField();
		this.txtDESInput.setBounds(12, 90, 302, 20);
		this.panelDES.add(this.txtDESInput);
		this.txtDESInput.setColumns(10);
		
		this.btnDESInput = new JButton("Browse...");
		this.btnDESInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setSelectedFile(new File(""));
				int returnValue = fc.showOpenDialog(panelDES);
				if (returnValue == JFileChooser.APPROVE_OPTION){
					txtDESInput.setText(fc.getSelectedFile().getPath());
				}
				
			}
		});
		this.btnDESInput.setBounds(332, 89, 89, 23);
		this.panelDES.add(this.btnDESInput);
		
		this.lblInput = new JLabel("File Input");
		this.lblInput.setBounds(12, 73, 136, 14);
		this.panelDES.add(this.lblInput);
		
		this.txtDESOutput = new JTextField();
		this.txtDESOutput.setColumns(10);
		this.txtDESOutput.setBounds(12, 149, 302, 20);
		this.panelDES.add(this.txtDESOutput);
		
		this.lblFileOutput = new JLabel("File Output");
		this.lblFileOutput.setBounds(12, 132, 70, 14);
		this.panelDES.add(this.lblFileOutput);
		
		this.btnDESOutput = new JButton("Browse...");
		this.btnDESOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setSelectedFile(new File(""));
				int returnValue = fc.showSaveDialog(panelDES);
				if (returnValue == JFileChooser.APPROVE_OPTION){
					txtDESOutput.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		this.btnDESOutput.setBounds(332, 148, 89, 23);
		this.panelDES.add(this.btnDESOutput);
		
		this.btnDESEncrypt = new JButton("Encrypt");
		this.btnDESEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDESEncrypt_onClick();
			}
		});
		this.btnDESEncrypt.setBounds(192, 210, 77, 23);
		this.panelDES.add(this.btnDESEncrypt);
		
		this.btnDESDecrypt = new JButton("Decrypt");
		this.btnDESDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDESDecrypt_onClick();
			}
		});
		this.btnDESDecrypt.setBounds(296, 210, 78, 23);
		this.panelDES.add(this.btnDESDecrypt);
		
		this.lblSecretKey = new JLabel("Secret Key");
		this.lblSecretKey.setBounds(12, 12, 136, 14);
		this.panelDES.add(this.lblSecretKey);
		
		this.txtKey = new JTextField();
		this.txtKey.setText("this is secret key");
		this.txtKey.setColumns(10);
		this.txtKey.setBounds(12, 29, 302, 20);
		this.panelDES.add(this.txtKey);
		
		this.btnDESKeyGenerate = new JButton("Generate");
		this.btnDESKeyGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = btnDESGenKey_onClick();
				txtKey.setText(key);
				DES.getInstance().init(key);
			}
		});
		this.btnDESKeyGenerate.setBounds(332, 28, 89, 23);
		this.panelDES.add(this.btnDESKeyGenerate);
		
		this.panelRSA = new JPanel();
		this.tabbedPane.addTab("RSA", null, this.panelRSA, null);
		
		this.panelSomeAlg = new JPanel();
		this.tabbedPane.addTab("3rd Alg", null, this.panelSomeAlg, null);
		
		this.lblStatus = new JLabel("status");
		this.lblStatus.setBounds(10, 303, 315, 16);
		this.frame.getContentPane().add(this.lblStatus);
		
		this.menuBar = new JMenuBar();
		this.frame.setJMenuBar(this.menuBar);
		
		this.mnProgram = new JMenu("Program");
		this.menuBar.add(this.mnProgram);
		
		this.mntmExit = new JMenuItem("Exit");
		this.mnProgram.add(this.mntmExit);
	}
	
	private boolean isInputValid(){
		if(txtKey.getText().isEmpty()){
			JOptionPane.showConfirmDialog(panelDES, "Please specify key!", "Error",
                    JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Key is empty");
			return false;
		}
		
		if (txtDESInput.getText().isEmpty() || txtDESOutput.getText().isEmpty()){
			JOptionPane.showConfirmDialog(panelDES, "Input file is empty!", "Error",
                    JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: No input file!");
			return false;
		}
		
		if (txtDESOutput.getText().isEmpty()){
			JOptionPane.showConfirmDialog(panelDES, "Output is empty!", "Error",
                    JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: No output!");
			return false;
		}
		
		if (!new File(txtDESInput.getText()).exists()){
			JOptionPane.showConfirmDialog(panelDES, "Input file is invalid or unexist!", "Error",
                    JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Input file is invalid or unexist!");
			return false;
		}
		String outputDir = txtDESOutput.getText().substring(0, txtDESOutput.getText().lastIndexOf(System.getProperty("file.separator")));
		if (!new File(outputDir).isDirectory()){
			JOptionPane.showConfirmDialog(panelDES, "Output file is invalid!", "Error",
                    JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Output file is invalid!");
			return false;
		}
			
		return true;
	}
	
	private void btnDESEncrypt_onClick(){
		if (!isInputValid())
			return;
		try {
			DES.getInstance().encrypt(new FileInputStream(new File(txtDESInput.getText())), new FileOutputStream(new File(txtDESOutput.getText())));

			lblStatus.setText("Encryption successful!");
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(panelDES, "Un error has occured!", "Error",
                    JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error!");
			e.printStackTrace();
		}
	}
	
	private void btnDESDecrypt_onClick(){
		if (!isInputValid())
			return;
		try {
			DES.getInstance().decrypt(new FileInputStream(new File(txtDESInput.getText())), new FileOutputStream(new File(txtDESOutput.getText())));
			lblStatus.setText("Decryption successful!");
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(panelDES, "Un error has occured.\nMaybe secret key mismatch!", "Error",
                    JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error while decrypting!");
			e.printStackTrace();
		}
	}
	
	private String btnDESGenKey_onClick(){
		Random ran = new Random();
		int keyLength = ran.nextInt(10) + 20;
		StringBuilder result = new StringBuilder();
		
		for (int i=0; i <= keyLength; i++) {
		  char data = (char) (ran.nextInt(94) + 32);
		  result.append(data);
		}
		
		return result.toString();
	}
}

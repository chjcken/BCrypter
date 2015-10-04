package com.kiss.gui;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
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

import com.kiss.aes.AES;
import com.kiss.des.DES;
import com.kiss.md5.MD5;
import com.kiss.rsa.RSA;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class BMain {

	private JFrame frmBcrypter;
	private JTabbedPane tabbedPane;
	private JMenuBar menuBar;
	private JMenu mnProgram;
	private JMenuItem mntmExit;
	private JPanel panelDES;
	private JPanel panelRSA;
	private JPanel panelAES;
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
	private JTextField txtDESKey;
	private JButton btnDESKeyGenerate;
	private JButton btnRSAEncrypt;
	private JButton btnRSADecrypt;
	private JTabbedPane tabbedPane_1;
	private JPanel panelRSACryption;
	private JPanel panelRSASignature;
	private JTextField txtRSAKey;
	private JButton btnRSAKeyChoose;
	private JLabel lblRsaKeyLocation;
	private JPanel panelRSAKeyGen;
	private JLabel label;
	private JTextField txtRSACryptionInput;
	private JLabel label_1;
	private JTextField txtRSACryptionOutput;
	private JButton btnRSACryptionOutput;
	private JButton btnRSACryptionInput;
	private JLabel lblKeyLengthIn;
	private JTextField txtRSAKeyGenKeyLength;
	private JLabel label_3;
	private JTextField txtRSAKeyGenKeyFolder;
	private JButton btnRSAKeyGenFolder;
	private JButton btnRSAKeyGenerate;

	private final String FILE_SEPARATOR = System.getProperty("file.separator");
	private final String DESKTOP_PATH = System.getProperty("user.home") + FILE_SEPARATOR + "Desktop";
	
	private JTabbedPane tabbedPane_2;
	private JPanel panelRSASignatureSign;
	private JPanel panelRSASignatureVerify;
	private JLabel lblYourRsaPrivate;
	private JTextField txtRSASignatureKey;
	private JButton btnRSASignatureKey;
	private JTextField txtRSASignatureInput;
	private JLabel lblYourFileInput;
	private JButton button_1;
	private JTextField txtRSASignatureOutput;
	private JLabel lblSignatureFileOutput;
	private JButton button_2;
	private JButton btnRSASign;
	private JLabel lblSignatureFileInput;
	private JTextField txtRSASignatureVerifyOriginInput;
	private JTextField txtRSASignatureVerifyInput;
	private JLabel lblOriginalFileInput;
	private JButton button;
	private JButton button_3;
	private JButton btnRSAVerify;
	private JLabel label_2;
	private JTextField txtAESKey;
	private JButton button_4;
	private JTextField txtAESInput;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField txtAESOutput;
	private JButton button_5;
	private JButton button_6;
	private JButton btnAESEncrypt;
	private JButton btnAESDecrypt;
	private JPanel panelMD5;
	private JTabbedPane tabbedPane_3;
	private JPanel panelMD5GenMD5;
	private JPanel panelMD5Compare;
	private JLabel lblFileLocation;
	private JTextField txtMD5File;
	private JButton button_7;
	private JButton btnMdHashing;
	private JLabel lblMdChecksumResult;
	private JTextField txtMD5HashResult;
	private JButton btnCopy;
	private JLabel label_6;
	private JTextField txtMD5CompareFile1;
	private JButton button_8;
	private JLabel lblCompareWith;
	private JRadioButton rdbtnAnotherFile;
	private JRadioButton rdbtnMdCode;
	private JTextField txtMD5CompareFile2;
	private JButton button_9;
	private JTextField txtMD5CompareString2;
	private JButton btnCompare;
	private JTextArea txtMD5CompareResult;
	
	private ButtonGroup md5Group;
	private JMenu mnAbout;
	private JMenuItem mntmAboutBcrypter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BMain window = new BMain();
					window.frmBcrypter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BMain() {
		initialize();
		this.lblStatus.setText("");
		this.fc = new JFileChooser(DESKTOP_PATH) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void approveSelection() {
				File f = getSelectedFile();
				if (f.exists() && getDialogType() == SAVE_DIALOG) {
					int result = JOptionPane
							.showConfirmDialog(
									this,
									f.getName()
											+ " already exists.\nDo you want to overwrite it?",
									"Existing file", JOptionPane.YES_NO_OPTION);
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
		this.frmBcrypter = new JFrame();
		this.frmBcrypter.setTitle("BCrypter");
		this.frmBcrypter.setBounds(100, 100, 450, 382);
		this.frmBcrypter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmBcrypter.setLocationRelativeTo(null);
		this.frmBcrypter.getContentPane().setLayout(null);
		this.frmBcrypter.setResizable(false);

		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.tabbedPane.setBounds(0, 0, 444, 301);
		this.frmBcrypter.getContentPane().add(this.tabbedPane);

		this.panelDES = new JPanel();
		this.tabbedPane.addTab("DES", null, this.panelDES, null);
		this.panelDES.setLayout(null);

		this.txtDESInput = new JTextField();
		this.txtDESInput.setBounds(12, 90, 302, 20);
		this.panelDES.add(this.txtDESInput);
		this.txtDESInput.setColumns(10);

		this.btnDESInput = new JButton("Browse...");
		this.btnDESInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fc.setSelectedFile(new File(""));
				int returnValue = fc.showOpenDialog(panelDES);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					String filePath = fc.getSelectedFile().getPath();
					txtDESInput.setText(filePath);
					String fileOut = filePath + ".out";
					txtDESOutput.setText(fileOut);
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
				
				fc.setSelectedFile(new File("output"));
				int returnValue = fc.showSaveDialog(panelDES);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
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

		this.txtDESKey = new JTextField();
		this.txtDESKey.setText("this is des secret key");
		this.txtDESKey.setColumns(10);
		this.txtDESKey.setBounds(12, 29, 302, 20);
		this.panelDES.add(this.txtDESKey);

		this.btnDESKeyGenerate = new JButton("Generate");
		this.btnDESKeyGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = btnDESGenKey_onClick();
				txtDESKey.setText(key);
			}
		});
		this.btnDESKeyGenerate.setBounds(332, 28, 89, 23);
		this.panelDES.add(this.btnDESKeyGenerate);
		
				this.panelAES = new JPanel();
				this.tabbedPane.addTab("AES", null, this.panelAES, null);
				this.panelAES.setLayout(null);
				
				this.label_2 = new JLabel("Secret Key");
				this.label_2.setBounds(12, 12, 136, 14);
				this.panelAES.add(this.label_2);
				
				this.txtAESKey = new JTextField();
				this.txtAESKey.setText("this is aes secret key");
				this.txtAESKey.setColumns(10);
				this.txtAESKey.setBounds(12, 29, 302, 20);
				this.panelAES.add(this.txtAESKey);
				
				this.button_4 = new JButton("Generate");
				this.button_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String key = btnDESGenKey_onClick();
						txtAESKey.setText(key);
						
					}
				});
				this.button_4.setBounds(332, 28, 89, 23);
				this.panelAES.add(this.button_4);
				
				this.txtAESInput = new JTextField();
				this.txtAESInput.setColumns(10);
				this.txtAESInput.setBounds(12, 90, 302, 20);
				this.panelAES.add(this.txtAESInput);
				
				this.label_4 = new JLabel("File Input");
				this.label_4.setBounds(12, 73, 136, 14);
				this.panelAES.add(this.label_4);
				
				this.label_5 = new JLabel("File Output");
				this.label_5.setBounds(12, 132, 70, 14);
				this.panelAES.add(this.label_5);
				
				this.txtAESOutput = new JTextField();
				this.txtAESOutput.setColumns(10);
				this.txtAESOutput.setBounds(12, 149, 302, 20);
				this.panelAES.add(this.txtAESOutput);
				
				this.button_5 = new JButton("Browse...");
				this.button_5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						fc.setSelectedFile(new File("output"));
						int returnValue = fc.showSaveDialog(panelAES);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							txtAESOutput.setText(fc.getSelectedFile().getPath());
						}
						
					}
				});
				this.button_5.setBounds(332, 148, 89, 23);
				this.panelAES.add(this.button_5);
				
				this.button_6 = new JButton("Browse...");
				this.button_6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						fc.setSelectedFile(new File(""));
						int returnValue = fc.showOpenDialog(panelAES);
						if (returnValue == JFileChooser.APPROVE_OPTION) {
							String filePath = fc.getSelectedFile().getPath();
							txtAESInput.setText(filePath);
							String fileOut = filePath + ".out";
							txtAESOutput.setText(fileOut);
						}
						
					}
				});
				this.button_6.setBounds(332, 89, 89, 23);
				this.panelAES.add(this.button_6);
				
				this.btnAESEncrypt = new JButton("Encrypt");
				this.btnAESEncrypt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						btnAESEncypt_onClick();
						
					}
				});
				this.btnAESEncrypt.setBounds(192, 210, 77, 23);
				this.panelAES.add(this.btnAESEncrypt);
				
				this.btnAESDecrypt = new JButton("Decrypt");
				this.btnAESDecrypt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						btnAESDecypt_onClick();
						
					}
				});
				this.btnAESDecrypt.setBounds(296, 210, 78, 23);
				this.panelAES.add(this.btnAESDecrypt);

		this.panelRSA = new JPanel();
		this.tabbedPane.addTab("RSA", null, this.panelRSA, null);
		this.panelRSA.setLayout(null);

		this.tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		this.tabbedPane_1.setBounds(0, 0, 439, 273);
		this.panelRSA.add(this.tabbedPane_1);

		this.panelRSACryption = new JPanel();
		this.tabbedPane_1.addTab("RSA Cryption", null, this.panelRSACryption,
				null);
		this.panelRSACryption.setLayout(null);

		this.txtRSAKey = new JTextField();
		this.txtRSAKey.setBounds(12, 29, 302, 20);
		this.txtRSAKey.setColumns(10);
		this.panelRSACryption.add(this.txtRSAKey);

		this.btnRSAKeyChoose = new JButton("Browse...");
		this.btnRSAKeyChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setSelectedFile(new File("key.key"));
				int resultCode = fc.showOpenDialog(panelRSACryption);
				if (resultCode == JFileChooser.APPROVE_OPTION){
					txtRSAKey.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		this.btnRSAKeyChoose.setBounds(332, 26, 89, 23);
		this.panelRSACryption.add(this.btnRSAKeyChoose);

		this.lblRsaKeyLocation = new JLabel("RSA Key Location");
		this.lblRsaKeyLocation.setBounds(12, 12, 302, 14);
		this.panelRSACryption.add(this.lblRsaKeyLocation);

		this.txtRSACryptionOutput = new JTextField();
		this.txtRSACryptionOutput.setBounds(12, 154, 302, 20);
		this.panelRSACryption.add(this.txtRSACryptionOutput);
		this.txtRSACryptionOutput.setColumns(10);

		this.txtRSACryptionInput = new JTextField();
		this.txtRSACryptionInput.setBounds(12, 91, 302, 20);
		this.panelRSACryption.add(this.txtRSACryptionInput);
		this.txtRSACryptionInput.setColumns(10);

		this.label = new JLabel("File Input");
		this.label.setBounds(12, 75, 136, 14);
		this.panelRSACryption.add(this.label);

		this.label_1 = new JLabel("File Output");
		this.label_1.setBounds(12, 138, 70, 14);
		this.panelRSACryption.add(this.label_1);

		this.btnRSACryptionOutput = new JButton("Browse...");
		this.btnRSACryptionOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setSelectedFile(new File("output"));
				int returnValue = fc.showSaveDialog(panelRSACryption);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					txtRSACryptionOutput.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		this.btnRSACryptionOutput.setBounds(332, 153, 89, 23);
		this.panelRSACryption.add(this.btnRSACryptionOutput);

		this.btnRSACryptionInput = new JButton("Browse...");
		this.btnRSACryptionInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setSelectedFile(new File(""));
				int returnValue = fc.showOpenDialog(panelRSACryption);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					String filePath = fc.getSelectedFile().getPath();
					txtRSACryptionInput.setText(filePath);
					String fileOut = filePath + ".out";
					txtRSACryptionOutput.setText(fileOut);
				}
			}
		});
		this.btnRSACryptionInput.setBounds(332, 90, 89, 23);
		this.panelRSACryption.add(this.btnRSACryptionInput);
		
				this.btnRSAEncrypt = new JButton("Encrypt");
				this.btnRSAEncrypt.setBounds(190, 210, 77, 23);
				this.panelRSACryption.add(this.btnRSAEncrypt);
				
						this.btnRSADecrypt = new JButton("Decrypt");
						this.btnRSADecrypt.setBounds(302, 210, 78, 23);
						this.panelRSACryption.add(this.btnRSADecrypt);
						this.btnRSADecrypt.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								btnRSADecrypt_onClick();

																																																			}
																																																		});
				this.btnRSAEncrypt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnRSAEncrypt_onClick();
					}
				});

		this.panelRSASignature = new JPanel();
		this.tabbedPane_1.addTab("RSA Signature", null, this.panelRSASignature,
				null);
		this.panelRSASignature.setLayout(null);
		
		this.tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		this.tabbedPane_2.setBounds(0, 69, 434, 176);
		this.panelRSASignature.add(this.tabbedPane_2);
		
		this.panelRSASignatureSign = new JPanel();
		this.tabbedPane_2.addTab("Sign Document", null, this.panelRSASignatureSign, null);
		this.panelRSASignatureSign.setLayout(null);
		
		this.txtRSASignatureInput = new JTextField();
		this.txtRSASignatureInput.setColumns(10);
		this.txtRSASignatureInput.setBounds(12, 28, 302, 20);
		this.panelRSASignatureSign.add(this.txtRSASignatureInput);
		
		this.lblYourFileInput = new JLabel("Your File Input");
		this.lblYourFileInput.setBounds(12, 12, 136, 14);
		this.panelRSASignatureSign.add(this.lblYourFileInput);
		
		this.button_1 = new JButton("Browse...");
		this.button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setSelectedFile(new File(""));
				int returnValue = fc.showOpenDialog(panelRSASignatureSign);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					String filePath = fc.getSelectedFile().getPath();
					txtRSASignatureInput.setText(filePath);
					String fileOut = filePath + ".signed";
					txtRSASignatureOutput.setText(fileOut);
				}
			}
		});
		this.button_1.setBounds(332, 27, 89, 23);
		this.panelRSASignatureSign.add(this.button_1);
		
		this.txtRSASignatureOutput = new JTextField();
		this.txtRSASignatureOutput.setColumns(10);
		this.txtRSASignatureOutput.setBounds(12, 87, 302, 20);
		this.panelRSASignatureSign.add(this.txtRSASignatureOutput);
		
		this.lblSignatureFileOutput = new JLabel("Signature File Output");
		this.lblSignatureFileOutput.setBounds(12, 71, 186, 14);
		this.panelRSASignatureSign.add(this.lblSignatureFileOutput);
		
		this.button_2 = new JButton("Browse...");
		this.button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setSelectedFile(new File("output"));
				int returnValue = fc.showSaveDialog(panelRSASignatureSign);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					txtRSASignatureOutput.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		this.button_2.setBounds(332, 86, 89, 23);
		this.panelRSASignatureSign.add(this.button_2);
		
		this.btnRSASign = new JButton("Sign");
		this.btnRSASign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnRSASign_onClick();
				
			}
		});
		this.btnRSASign.setBounds(164, 121, 77, 23);
		this.panelRSASignatureSign.add(this.btnRSASign);
		
		this.panelRSASignatureVerify = new JPanel();
		this.tabbedPane_2.addTab("Verify Signature", null, this.panelRSASignatureVerify, null);
		this.panelRSASignatureVerify.setLayout(null);
		
		this.lblSignatureFileInput = new JLabel("Original File Input");
		this.lblSignatureFileInput.setBounds(12, 12, 136, 14);
		this.panelRSASignatureVerify.add(this.lblSignatureFileInput);
		
		this.txtRSASignatureVerifyOriginInput = new JTextField();
		this.txtRSASignatureVerifyOriginInput.setColumns(10);
		this.txtRSASignatureVerifyOriginInput.setBounds(12, 28, 302, 20);
		this.panelRSASignatureVerify.add(this.txtRSASignatureVerifyOriginInput);
		
		this.txtRSASignatureVerifyInput = new JTextField();
		this.txtRSASignatureVerifyInput.setColumns(10);
		this.txtRSASignatureVerifyInput.setBounds(12, 87, 302, 20);
		this.panelRSASignatureVerify.add(this.txtRSASignatureVerifyInput);
		
		this.lblOriginalFileInput = new JLabel("Signature File Input");
		this.lblOriginalFileInput.setBounds(12, 71, 186, 14);
		this.panelRSASignatureVerify.add(this.lblOriginalFileInput);
		
		this.button = new JButton("Browse...");
		this.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setSelectedFile(new File(""));
				int returnValue = fc.showOpenDialog(panelRSA);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					String filePath = fc.getSelectedFile().getPath();
					txtRSASignatureVerifyInput.setText(filePath);
				}
			}
		});
		this.button.setBounds(332, 86, 89, 23);
		this.panelRSASignatureVerify.add(this.button);
		
		this.button_3 = new JButton("Browse...");
		this.button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setSelectedFile(new File(""));
				int returnValue = fc.showOpenDialog(panelRSA);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					String filePath = fc.getSelectedFile().getPath();
					txtRSASignatureVerifyOriginInput.setText(filePath);
					String fileOut = filePath + ".signed";
					txtRSASignatureVerifyInput.setText(fileOut);
				}
			}
		});
		this.button_3.setBounds(332, 27, 89, 23);
		this.panelRSASignatureVerify.add(this.button_3);
		
		this.btnRSAVerify = new JButton("Verify");
		this.btnRSAVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btnRSAVerify_onClick();
			}
		});
		this.btnRSAVerify.setBounds(164, 121, 77, 23);
		this.panelRSASignatureVerify.add(this.btnRSAVerify);
		
		this.lblYourRsaPrivate = new JLabel("RSA Key Location");
		this.lblYourRsaPrivate.setBounds(12, 12, 302, 14);
		this.panelRSASignature.add(this.lblYourRsaPrivate);
		
		this.txtRSASignatureKey = new JTextField();
		this.txtRSASignatureKey.setBounds(12, 29, 302, 20);
		this.panelRSASignature.add(this.txtRSASignatureKey);
		this.txtRSASignatureKey.setColumns(10);
		
		this.btnRSASignatureKey = new JButton("Browse...");
		this.btnRSASignatureKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setSelectedFile(new File("key.key"));
				int resultCode = fc.showOpenDialog(panelRSASignature);
				if (resultCode == JFileChooser.APPROVE_OPTION){
					txtRSASignatureKey.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		this.btnRSASignatureKey.setBounds(332, 26, 89, 23);
		this.panelRSASignature.add(this.btnRSASignatureKey);

		this.panelRSAKeyGen = new JPanel();
		this.tabbedPane_1.addTab("RSA Key Generator", null,
				this.panelRSAKeyGen, null);
		this.panelRSAKeyGen.setLayout(null);

		this.lblKeyLengthIn = new JLabel(
				"Key Length in bit (range: 512 - 16384)");
		this.lblKeyLengthIn.setBounds(39, 12, 224, 21);
		this.panelRSAKeyGen.add(this.lblKeyLengthIn);

		this.txtRSAKeyGenKeyLength = new JTextField();
		this.txtRSAKeyGenKeyLength.setText("1024");
		this.txtRSAKeyGenKeyLength.setColumns(10);
		this.txtRSAKeyGenKeyLength.setBounds(39, 38, 224, 20);
		this.panelRSAKeyGen.add(this.txtRSAKeyGenKeyLength);

		this.label_3 = new JLabel("Save to");
		this.label_3.setBounds(39, 82, 55, 21);
		this.panelRSAKeyGen.add(this.label_3);

		this.txtRSAKeyGenKeyFolder = new JTextField();
		this.txtRSAKeyGenKeyFolder.setText(DESKTOP_PATH);
		this.txtRSAKeyGenKeyFolder.setColumns(10);
		this.txtRSAKeyGenKeyFolder.setBounds(39, 106, 224, 20);
		this.panelRSAKeyGen.add(this.txtRSAKeyGenKeyFolder);

		this.btnRSAKeyGenFolder = new JButton("Browse...");
		this.btnRSAKeyGenFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int resultCode = fc.showDialog(panelRSAKeyGen, "OK");
				if (resultCode == JFileChooser.APPROVE_OPTION) {
					txtRSAKeyGenKeyFolder.setText(fc.getSelectedFile()
							.getPath());
				}
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			}
		});
		this.btnRSAKeyGenFolder.setBounds(275, 105, 90, 23);
		this.panelRSAKeyGen.add(this.btnRSAKeyGenFolder);

		this.btnRSAKeyGenerate = new JButton("Generate");
		this.btnRSAKeyGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!isRSAKeyGenInputValid())
						return;
					RSA.getInstance().generateKeyPair(
							Integer.parseInt(txtRSAKeyGenKeyLength.getText()),
							txtRSAKeyGenKeyFolder.getText() + FILE_SEPARATOR);
					JOptionPane.showConfirmDialog(panelRSAKeyGen,
							"public.key and private.key file are generated successfully at:\n"
									+ txtRSAKeyGenKeyFolder.getText(),
							"Success", JOptionPane.PLAIN_MESSAGE);
				} catch (NoSuchAlgorithmException | IOException e1) {
					JOptionPane.showConfirmDialog(
							panelRSAKeyGen,
							"Error while generating RSA key: "
									+ e1.getMessage(), "Error",
							JOptionPane.PLAIN_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		this.btnRSAKeyGenerate.setBounds(155, 167, 98, 26);
		this.panelRSAKeyGen.add(this.btnRSAKeyGenerate);
		
		this.panelMD5 = new JPanel();
		this.tabbedPane.addTab("MD5", null, this.panelMD5, null);
		this.panelMD5.setLayout(null);
		
		this.tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		this.tabbedPane_3.setBounds(0, 0, 439, 273);
		this.panelMD5.add(this.tabbedPane_3);
		
		this.panelMD5GenMD5 = new JPanel();
		this.tabbedPane_3.addTab("MD5 From File", null, this.panelMD5GenMD5, null);
		this.panelMD5GenMD5.setLayout(null);
		
		this.lblFileLocation = new JLabel("Input File Location");
		this.lblFileLocation.setBounds(12, 32, 302, 14);
		this.panelMD5GenMD5.add(this.lblFileLocation);
		
		this.txtMD5File = new JTextField();
		this.txtMD5File.setColumns(10);
		this.txtMD5File.setBounds(12, 49, 302, 20);
		this.panelMD5GenMD5.add(this.txtMD5File);
		
		this.button_7 = new JButton("Browse...");
		this.button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.setSelectedFile(new File(""));
				int resultCode = fc.showOpenDialog(panelMD5);
				if (resultCode == JFileChooser.APPROVE_OPTION){
					txtMD5File.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		this.button_7.setBounds(332, 46, 89, 23);
		this.panelMD5GenMD5.add(this.button_7);
		
		this.btnMdHashing = new JButton("MD5 Hashing");
		this.btnMdHashing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCheckSum_onClick();
			}
		});
		this.btnMdHashing.setBounds(143, 97, 113, 26);
		this.panelMD5GenMD5.add(this.btnMdHashing);
		
		this.lblMdChecksumResult = new JLabel("MD5 Checksum Result");
		this.lblMdChecksumResult.setBounds(12, 169, 302, 14);
		this.panelMD5GenMD5.add(this.lblMdChecksumResult);
		
		this.txtMD5HashResult = new JTextField();
		this.txtMD5HashResult.setEditable(false);
		this.txtMD5HashResult.setColumns(10);
		this.txtMD5HashResult.setBounds(12, 186, 302, 20);
		this.panelMD5GenMD5.add(this.txtMD5HashResult);
		
		this.btnCopy = new JButton("Copy");
		this.btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String get= txtMD5HashResult.getText();
				StringSelection selec= new StringSelection(get);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(selec, selec);
				lblStatus.setText("Text copied to clipboard.");
			}
		});
		this.btnCopy.setToolTipText("Copy to clipboard");
		this.btnCopy.setBounds(332, 183, 89, 23);
		this.panelMD5GenMD5.add(this.btnCopy);
		
		this.panelMD5Compare = new JPanel();
		this.tabbedPane_3.addTab("Compare", null, this.panelMD5Compare, null);
		this.panelMD5Compare.setLayout(null);
		
		this.label_6 = new JLabel("Input File Location");
		this.label_6.setBounds(12, 12, 302, 14);
		this.panelMD5Compare.add(this.label_6);
		
		this.txtMD5CompareFile1 = new JTextField();
		this.txtMD5CompareFile1.setColumns(10);
		this.txtMD5CompareFile1.setBounds(12, 29, 320, 20);
		this.panelMD5Compare.add(this.txtMD5CompareFile1);
		
		this.button_8 = new JButton("Browse...");
		this.button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setSelectedFile(new File(""));
				int resultCode = fc.showOpenDialog(panelMD5);
				if (resultCode == JFileChooser.APPROVE_OPTION){
					txtMD5CompareFile1.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		this.button_8.setBounds(336, 26, 89, 23);
		this.panelMD5Compare.add(this.button_8);
		
		this.lblCompareWith = new JLabel("Compare with...");
		this.lblCompareWith.setBounds(12, 78, 302, 14);
		this.panelMD5Compare.add(this.lblCompareWith);
		
		this.md5Group = new ButtonGroup();
		
		this.rdbtnAnotherFile = new JRadioButton("Another File");
		this.rdbtnAnotherFile.setBounds(8, 107, 92, 24);
		this.rdbtnAnotherFile.setSelected(true);
		this.panelMD5Compare.add(this.rdbtnAnotherFile);
		this.md5Group.add(rdbtnAnotherFile);
		
		this.rdbtnMdCode = new JRadioButton("MD5 code");
		this.rdbtnMdCode.setBounds(8, 130, 92, 24);
		this.panelMD5Compare.add(this.rdbtnMdCode);
		this.md5Group.add(rdbtnMdCode);
		
		this.txtMD5CompareFile2 = new JTextField();
		this.txtMD5CompareFile2.setColumns(10);
		this.txtMD5CompareFile2.setBounds(107, 111, 225, 20);
		this.panelMD5Compare.add(this.txtMD5CompareFile2);
		
		this.button_9 = new JButton("Browse...");
		this.button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setSelectedFile(new File(""));
				int resultCode = fc.showOpenDialog(panelMD5);
				if (resultCode == JFileChooser.APPROVE_OPTION){
					txtMD5CompareFile2.setText(fc.getSelectedFile().getPath());
				}
			}
		});
		this.button_9.setBounds(336, 108, 89, 23);
		this.panelMD5Compare.add(this.button_9);
		
		this.txtMD5CompareString2 = new JTextField();
		this.txtMD5CompareString2.setColumns(10);
		this.txtMD5CompareString2.setBounds(107, 134, 225, 20);
		this.panelMD5Compare.add(this.txtMD5CompareString2);
		
		this.btnCompare = new JButton("Compare");
		this.btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMD5Compare_onClick();
			}
		});
		this.btnCompare.setBounds(155, 162, 98, 26);
		this.panelMD5Compare.add(this.btnCompare);
		
		this.txtMD5CompareResult = new JTextArea();
		this.txtMD5CompareResult.setEditable(false);
		this.txtMD5CompareResult.setBounds(11, 198, 410, 38);
		this.panelMD5Compare.add(this.txtMD5CompareResult);

		this.lblStatus = new JLabel("status");
		this.lblStatus.setBounds(10, 313, 315, 16);
		this.frmBcrypter.getContentPane().add(this.lblStatus);

		this.menuBar = new JMenuBar();
		this.frmBcrypter.setJMenuBar(this.menuBar);

		this.mnProgram = new JMenu("Program");
		this.menuBar.add(this.mnProgram);

		this.mntmExit = new JMenuItem("Exit");
		this.mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.mnProgram.add(this.mntmExit);
		
		this.mnAbout = new JMenu("About");
		this.menuBar.add(this.mnAbout);
		
		this.mntmAboutBcrypter = new JMenuItem("About BCrypter");
		this.mntmAboutBcrypter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BAbout dialog = new BAbout();				
				dialog.setLocationRelativeTo(BMain.this.frmBcrypter);		
				dialog.setModal(true);
				dialog.setVisible(true);
			}
		});
		this.mnAbout.add(this.mntmAboutBcrypter);
	}



	private boolean isDESInputValid() {
		if (txtDESKey.getText().length() < 8) {
			JOptionPane.showConfirmDialog(panelDES,
					"Secret Key must be at least 8 character!", "Error",
					JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Key is invalid!");
			return false;
		}

		if (!new File(txtDESInput.getText()).exists()) {
			JOptionPane.showConfirmDialog(panelDES, "Input file is invalid!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Input file is invalid!");
			return false;
		}
		String outputDir = txtDESOutput.getText().substring(
				0,
				txtDESOutput.getText().lastIndexOf(FILE_SEPARATOR));
		if (!new File(outputDir).isDirectory()) {
			JOptionPane.showConfirmDialog(panelDES, "Output file is invalid!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Output file is invalid!");
			return false;
		}

		return true;
	}

	private void btnDESEncrypt_onClick() {
		if (!isDESInputValid())
			return;
		try {
			DES.getInstance().init(txtDESKey.getText());
			DES.getInstance().encrypt(
					new FileInputStream(new File(txtDESInput.getText())),
					new FileOutputStream(new File(txtDESOutput.getText())));

			lblStatus.setText("Encryption successful!");
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(panelDES, "Un error has occured!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error!");
			e.printStackTrace();
		}
	}

	private void btnDESDecrypt_onClick() {
		if (!isDESInputValid())
			return;
		try {
			DES.getInstance().init(txtDESKey.getText());
			DES.getInstance().decrypt(
					new FileInputStream(new File(txtDESInput.getText())),
					new FileOutputStream(new File(txtDESOutput.getText())));
			lblStatus.setText("Decryption successful!");
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(panelDES,
					"Un error has occured.\nMaybe secret key mismatch!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error while decrypting!");
			e.printStackTrace();
		}
	}

	private String btnDESGenKey_onClick() {
		Random ran = new Random();
		int keyLength = ran.nextInt(10) + 20;
		StringBuilder result = new StringBuilder();

		for (int i = 0; i <= keyLength; i++) {
			char data = (char) (ran.nextInt(94) + 32);
			result.append(data);
		}

		return result.toString();
	}

	private boolean isRSACryptionInputValid() {
		File keyFile = new File(txtRSAKey.getText());
		File inputFile = new File(txtRSACryptionInput.getText());
		
		if (!keyFile.exists()) {
			JOptionPane.showConfirmDialog(panelRSA, "RSA key is invalid!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Invalid Key");
			return false;
		}

		if (!inputFile.exists()) {
			JOptionPane.showConfirmDialog(panelRSA,
					"Input file is invalid or unexist!", "Error",
					JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Input file is invalid or unexist!");
			return false;
		}
		String outputDir = txtRSACryptionOutput.getText().substring(
				0,
				txtRSACryptionOutput.getText().lastIndexOf(
						System.getProperty("file.separator")));
		if (!new File(outputDir).isDirectory()) {
			JOptionPane.showConfirmDialog(panelRSA, "Output file is invalid!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Output file is invalid!");
			return false;
		}
		
		if (keyFile.length() < inputFile.length()){
			JOptionPane.showConfirmDialog(panelRSA, "Input file must not be larger than key!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Input file is too large!");
			return false;
		}

		return true;
	}

	private void btnRSAEncrypt_onClick() {
		if (isRSACryptionInputValid()) {
			Key key;
			try {
				key = RSA.getInstance().readPublicKey(txtRSAKey.getText());
			} catch (IOException | InvalidKeySpecException
					| NoSuchAlgorithmException e) {
				JOptionPane.showConfirmDialog(panelRSA,
						"Your key is not a RSA key", "Error",
						JOptionPane.PLAIN_MESSAGE);
				lblStatus.setText("Error: Invalid RSA key!");
				e.printStackTrace();
				return;
			}

			try {
				RSA.getInstance().encrypt(key,
						new FileInputStream(txtRSACryptionInput.getText()),
						new FileOutputStream(txtRSACryptionOutput.getText()));

				// if (rdbtnForCrypting.isSelected())
				lblStatus.setText("Encryption successful!");
				// else
				// lblStatus.setText("Signing document successful!");
			} catch (InvalidKeyException | IOException e) {
				JOptionPane.showConfirmDialog(panelRSA,
						"Un error has occured: " + e.getMessage(), "Error",
						JOptionPane.PLAIN_MESSAGE);
				lblStatus.setText("Error while encrypting!");
				e.printStackTrace();
			}
		}
	}

	private void btnRSADecrypt_onClick() {
		if (isRSACryptionInputValid()) {
			Key key;
			try {
				key = RSA.getInstance().readPrivateKey(txtRSAKey.getText());

			} catch (IOException | InvalidKeySpecException
					| NoSuchAlgorithmException e) {
				JOptionPane.showConfirmDialog(panelRSA,
						"Your RSA key is invalid", "Error",
						JOptionPane.PLAIN_MESSAGE);
				lblStatus.setText("Error: Invalid RSA key!");
				e.printStackTrace();
				return;
			}

			try {
				RSA.getInstance().decrypt(key,
						new FileInputStream(txtRSACryptionInput.getText()),
						new FileOutputStream(txtRSACryptionOutput.getText()));
				// if (rdbtnForCrypting.isSelected())
				lblStatus.setText("Decryption successful!");
				// else
				// lblStatus.setText("Verify signature successful!");
			} catch (InvalidKeyException | IOException e) {
				JOptionPane.showConfirmDialog(panelRSA,
						"Un error has occured: " + e.getMessage(), "Error",
						JOptionPane.PLAIN_MESSAGE);
				lblStatus.setText("Error while decrypting!");
				e.printStackTrace();
			}
		}

	}

	private boolean isRSAKeyGenInputValid() {
		int length;
		try {
			length = Integer.parseInt(txtRSAKeyGenKeyLength.getText());
		} catch (Exception e) {
			length = -1;
		}
		if (length < 512 || length > 16384) {
			JOptionPane.showConfirmDialog(panelRSA,
					"Key length must be a number between 512 and 16384!",
					"Invalid key length", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Key length is out of range!");
			return false;
		}
		File folder = new File(txtRSAKeyGenKeyFolder.getText());
		if (!folder.exists()) {
			JOptionPane.showConfirmDialog(panelRSA,
					"Invalid save folder!", "Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Invalid save folder");
			return false;
		}
		return true;
	}
	
	private boolean isRSASignInputValid(){
		if (!new File(txtRSASignatureKey.getText()).exists()){
			JOptionPane.showConfirmDialog(panelRSA,
					"Your RSA key is invalid",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Invalid key");
			return false;
		}
			
		if (!new File(txtRSASignatureInput.getText()).exists()) {
			JOptionPane.showConfirmDialog(panelRSA,
					"Input file is invalid or unexist!", "Error",
					JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Input file is invalid or unexist!");
			return false;
		}
		String outputDir = txtRSASignatureOutput.getText().substring(
				0,
				txtRSASignatureOutput.getText().lastIndexOf(
						System.getProperty("file.separator")));
		if (!new File(outputDir).isDirectory()) {
			JOptionPane.showConfirmDialog(panelRSA, "Output file is invalid!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Output file is invalid!");
			return false;
		}
		
		return true;
	}

	private void btnRSASign_onClick() {
		if (isRSASignInputValid()){
			Key key;
			try {
				key = RSA.getInstance().readPrivateKey(txtRSASignatureKey.getText());
			} catch (IOException | InvalidKeySpecException
					| NoSuchAlgorithmException e) {
				JOptionPane.showConfirmDialog(panelRSA,
						"Your key is not a RSA private key", "Error",
						JOptionPane.PLAIN_MESSAGE);
				lblStatus.setText("Error: Invalid RSA key!");
				e.printStackTrace();
				return;
			}

			try {
				RSA.getInstance().sign(key,
						new FileInputStream(txtRSASignatureInput.getText()),
						new FileOutputStream(txtRSASignatureOutput.getText()));

				lblStatus.setText("Signing successful!");
			} catch (InvalidKeyException | IOException e) {
				JOptionPane.showConfirmDialog(panelRSA,
						"Un error has occured: " + e.getMessage(), "Error",
						JOptionPane.PLAIN_MESSAGE);
				lblStatus.setText("Error while signing!");
				e.printStackTrace();
			}
		}		
	}
	
	private boolean isRSAVerifyInputValid(){
		if (!new File(txtRSASignatureKey.getText()).exists()){
			JOptionPane.showConfirmDialog(panelRSA,
					"Your RSA key is invalid",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Invalid key");
			return false;
		}
		
		if (!new File(txtRSASignatureVerifyOriginInput.getText()).exists()){
			JOptionPane.showConfirmDialog(panelRSA,
					"Your origin file is unexist!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: File not found");
			return false;
		}
		
		if (!new File(txtRSASignatureVerifyInput.getText()).exists()){
			JOptionPane.showConfirmDialog(panelRSA,
					"Your signature file is unexist!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: File not found");
			return false;
		}
		
		
		return true;
	}

	private void btnRSAVerify_onClick() {
		if (isRSAVerifyInputValid()){
			Key key;
			try {
				key = RSA.getInstance().readPublicKey(txtRSASignatureKey.getText());
			} catch (IOException | InvalidKeySpecException
					| NoSuchAlgorithmException e) {
				JOptionPane.showConfirmDialog(panelRSA,
						"Your key is not a RSA public key", "Error",
						JOptionPane.PLAIN_MESSAGE);
				lblStatus.setText("Error: Invalid RSA key!");
				e.printStackTrace();
				return;
			}
			
			try {
				boolean match = RSA.getInstance().verify(key,
						new FileInputStream(txtRSASignatureVerifyInput.getText()),
						new FileInputStream(txtRSASignatureVerifyOriginInput.getText()));
				if (match){
					JOptionPane.showConfirmDialog(panelRSA,
							"Origin file and signature match!", "Info",
							JOptionPane.PLAIN_MESSAGE);
					lblStatus.setText("Verify OK!");
				}
				else{
					JOptionPane.showConfirmDialog(panelRSA,
							"Origin file and signature mismatch!!!\n" +
							"Maybe some files are damaged or the signature doesn't belong to the original file.", "Info",
							JOptionPane.PLAIN_MESSAGE);
					lblStatus.setText("Verify Mismatch!!!");
				}
				
			} catch (InvalidKeyException | IOException e) {
				JOptionPane.showConfirmDialog(panelRSA,
						"Un error has occured: " + e.getMessage(), "Error",
						JOptionPane.PLAIN_MESSAGE);
				lblStatus.setText("Error while signing!");
				e.printStackTrace();
			}
		}
	}
	
	private boolean isAESInputValid(){
//		if (txtAESKey.getText().length() < 8) {
//			JOptionPane.showConfirmDialog(panelDES,
//					"Secret Key must be at least 8 character!", "Error",
//					JOptionPane.PLAIN_MESSAGE);
//			lblStatus.setText("Error: Key is invalid!");
//			return false;
//		}

		if (!new File(txtAESInput.getText()).exists()) {
			JOptionPane.showConfirmDialog(panelAES, "Input file is invalid!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Input file is invalid!");
			return false;
		}
		String outputDir = txtAESOutput.getText().substring(
				0,
				txtAESOutput.getText().lastIndexOf(FILE_SEPARATOR));
		if (!new File(outputDir).isDirectory()) {
			JOptionPane.showConfirmDialog(panelAES, "Output file is invalid!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Output file is invalid!");
			return false;
		}
		
		return true;
	}
	
	private void btnAESEncypt_onClick(){
		if (!isAESInputValid())
			return;
		try {
			AES.getInstance().init(txtAESKey.getText());
			AES.getInstance().encrypt(
					new FileInputStream(new File(txtAESInput.getText())),
					new FileOutputStream(new File(txtAESOutput.getText())));

			lblStatus.setText("Encryption successful!");
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(panelAES, "Un error has occured: " + e.getMessage(),
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error!");
			e.printStackTrace();
		}
	}
	
	private void btnAESDecypt_onClick(){
		if (!isAESInputValid())
			return;
		try {
			AES.getInstance().init(txtAESKey.getText());
			AES.getInstance().decrypt(
					new FileInputStream(new File(txtAESInput.getText())),
					new FileOutputStream(new File(txtAESOutput.getText())));

			lblStatus.setText("Decryption successful!");
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(panelAES, "Un error has occured: " + e.getMessage(),
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error!");
			e.printStackTrace();
		}
	}
	
	

	private void btnCheckSum_onClick() {
		
		File file = new File(txtMD5File.getText());
		if (!file.exists()){
			JOptionPane.showConfirmDialog(panelMD5, "Input file invalid!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Input file invalid!");
			return;
		}
		
		try {
			String result = MD5.getInstance().md5InHex(new FileInputStream(file));
			txtMD5HashResult.setText(result);
			lblStatus.setText("MD5 Hashing done!");
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(panelMD5, "Un error has occured: " + e.getMessage(),
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error!");
			e.printStackTrace();
		}
		
	}
	
	private boolean isMD5CompareInputValid(){
		if (!new File(txtMD5CompareFile1.getText()).exists()) {
			JOptionPane.showConfirmDialog(panelMD5, "Input file is invalid!",
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error: Input file is invalid!");
			return false;
		}
		
		if (rdbtnAnotherFile.isSelected()){
			if (!new File(txtMD5CompareFile2.getText()).exists()) {
				JOptionPane.showConfirmDialog(panelMD5, "Input file 2 is invalid!",
						"Error", JOptionPane.PLAIN_MESSAGE);
				lblStatus.setText("Error: Input file 2 is invalid!");
				return false;
			}
		}
		else {
			if (txtMD5CompareString2.getText().length() != 32){
				JOptionPane.showConfirmDialog(panelMD5, "MD5 code is invalid!",
						"Error", JOptionPane.PLAIN_MESSAGE);
				lblStatus.setText("Error: MD5 code is invalid!");
				return false;
			}				
		}
		
		return true;
	}

	private void btnMD5Compare_onClick() {
		if (!isMD5CompareInputValid())
			return;
		
		try {
			String originCode = MD5.getInstance().md5InHex(new FileInputStream(txtMD5CompareFile1.getText()));
			String secondCode;
			if (rdbtnAnotherFile.isSelected()){
				secondCode = MD5.getInstance().md5InHex(new FileInputStream(txtMD5CompareFile2.getText()));
			}
			else{
				secondCode = txtMD5CompareString2.getText();
			}
			
			txtMD5CompareResult.setText(String.format("Checksum 1: %s\nChecksum 2: %s", originCode, secondCode));
			
			JOptionPane.showConfirmDialog(panelMD5, originCode.equals(secondCode)? "MATCH!" : "MISMATCH",
					"Compare Result", JOptionPane.PLAIN_MESSAGE);
			
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(panelMD5, "Un error has occured: " + e.getMessage(),
					"Error", JOptionPane.PLAIN_MESSAGE);
			lblStatus.setText("Error!");
		}
	}
}

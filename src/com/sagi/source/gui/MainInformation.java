package com.sagi.source.gui;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sagi.source.generate.GenerateCss;
import com.sagi.source.generate.GenerateHtml;

/**
 * This class creates a windows for generating HTML from the template
 *
 * @author SAGI Code
 */
public class MainInformation {

	private JFrame frmPageMaker;
	private JTextField bgmain;
	private JTextField tcmain;
	private JTextField tcstrip;
	private JTextField bgstrip;
	private JTextField cp;
	private JTextField cn;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInformation window = new MainInformation();
					window.frmPageMaker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainInformation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPageMaker = new JFrame();
		frmPageMaker.setResizable(false);
		frmPageMaker.setTitle("Page Maker | Indev | Alpha 3.0.0");
		frmPageMaker.setBounds(100, 100, 487, 466);
		frmPageMaker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPageMaker.getContentPane().setLayout(null);
		
		Button button = new Button("?");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "This will chage the sites title and header to the website name/company name and the copy right to the one you filled in", "Main info", JOptionPane.QUESTION_MESSAGE);
			}
		});
		button.setBounds(434, 36, 30, 22);
		frmPageMaker.getContentPane().add(button);
		
		Button mchelp = new Button("?");
		mchelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "This background color and text color are the default settings for the sites theme", "Main Colors", JOptionPane.QUESTION_MESSAGE);
			}
		});
		mchelp.setBounds(159, 36, 30, 22);
		frmPageMaker.getContentPane().add(mchelp);
		
		Button schelp = new Button("?");
		schelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "These are the colors for the header and the footer", "Strip colors", JOptionPane.QUESTION_MESSAGE);
			}
		});
		schelp.setBounds(160, 221, 30, 22);
		frmPageMaker.getContentPane().add(schelp);
		
		bgmain = new JTextField();
		bgmain.setBounds(10, 91, 180, 20);
		frmPageMaker.getContentPane().add(bgmain);
		bgmain.setColumns(10);
		
		JLabel lblBackgroundColor = new JLabel("Background Color");
		lblBackgroundColor.setBounds(10, 66, 180, 14);
		frmPageMaker.getContentPane().add(lblBackgroundColor);
		
		tcmain = new JTextField();
		tcmain.setColumns(10);
		tcmain.setBounds(10, 147, 180, 20);
		frmPageMaker.getContentPane().add(tcmain);
		
		JLabel lblTextColor = new JLabel("Text Color");
		lblTextColor.setBounds(10, 122, 180, 14);
		frmPageMaker.getContentPane().add(lblTextColor);
		
		JLabel lblMainColors = new JLabel("Main Colors");
		lblMainColors.setBounds(10, 36, 180, 14);
		frmPageMaker.getContentPane().add(lblMainColors);
		
		tcstrip = new JTextField();
		tcstrip.setColumns(10);
		tcstrip.setBounds(10, 332, 180, 20);
		frmPageMaker.getContentPane().add(tcstrip);
		
		JLabel label = new JLabel("Text Color");
		label.setBounds(10, 307, 180, 14);
		frmPageMaker.getContentPane().add(label);
		
		bgstrip = new JTextField();
		bgstrip.setColumns(10);
		bgstrip.setBounds(10, 276, 180, 20);
		frmPageMaker.getContentPane().add(bgstrip);
		
		JLabel label_1 = new JLabel("Background Color");
		label_1.setBounds(10, 251, 180, 14);
		frmPageMaker.getContentPane().add(label_1);
		
		JLabel lblStripColors = new JLabel("Strip Colors");
		lblStripColors.setBounds(10, 221, 180, 14);
		frmPageMaker.getContentPane().add(lblStripColors);
		
		cp = new JTextField();
		cp.setColumns(10);
		cp.setBounds(284, 147, 180, 20);
		frmPageMaker.getContentPane().add(cp);
		
		JLabel lblCopyRightName = new JLabel("Copy Right name");
		lblCopyRightName.setBounds(284, 122, 180, 14);
		frmPageMaker.getContentPane().add(lblCopyRightName);
		
		cn = new JTextField();
		cn.setColumns(10);
		cn.setBounds(284, 91, 180, 20);
		frmPageMaker.getContentPane().add(cn);
		
		JLabel lblCompanyNamewebsiteName = new JLabel("Company name/Website name");
		lblCompanyNamewebsiteName.setBounds(284, 66, 180, 14);
		frmPageMaker.getContentPane().add(lblCompanyNamewebsiteName);
		
		JLabel mihelp = new JLabel("Main Information");
		mihelp.setBounds(284, 36, 180, 14);
		frmPageMaker.getContentPane().add(mihelp);
		
		Button generate = new Button("Generate files");
		generate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				GenerateHtml.generateHtml(cn.getText(), cp.getText());
				GenerateCss.generateCss(bgmain.getText(), tcmain.getText(), bgstrip.getText(), tcstrip.getText());
				
				cn.setText("");
				cp.setText("");
				bgmain.setText("");
				tcmain.setText("");
				bgstrip.setText("");
				tcstrip.setText("");
				
			}
		});
		generate.setBounds(347, 389, 117, 28);
		frmPageMaker.getContentPane().add(generate);
		
		JLabel lblNoticeThisIs = new JLabel("NOTICE! This is still the Alpha version of");
		lblNoticeThisIs.setBounds(217, 210, 247, 14);
		frmPageMaker.getContentPane().add(lblNoticeThisIs);
		
		JLabel lblPagemakerThisVersion = new JLabel("page-maker! This version still have some");
		lblPagemakerThisVersion.setBounds(217, 221, 247, 14);
		frmPageMaker.getContentPane().add(lblPagemakerThisVersion);
		
		JLabel lblBugs = new JLabel("bugs!");
		lblBugs.setBounds(217, 235, 244, 14);
		frmPageMaker.getContentPane().add(lblBugs);
		
		Button editor = new Button("Edit Existing Files");
		editor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPageMaker.dispose();
				Editor.main();
			}
		});
		editor.setBounds(347, 345, 117, 28);
		frmPageMaker.getContentPane().add(editor);
	}
}

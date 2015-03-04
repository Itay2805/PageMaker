package com.sagi.source.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.sagi.source.API.Packages;
import com.sagi.source.generate.Edit;

import javax.swing.DefaultComboBoxModel;

/**
 * This class creates a windows for editing the HTML
 *
 * @author SAGI Code
 */
public class Editor {
	
	private JFrame frmPageMaker;
	
	private static final JComboBox<String> cssclass = new JComboBox<String>();
	private static final JComboBox<String> type = new JComboBox<String>();
	private static final JComboBox<String> packages = new JComboBox<String>();

	
	private static 	URL website = null;
	private static ReadableByteChannel rbc = null;
	
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor window = new Editor();
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
	public Editor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		frmPageMaker = new JFrame();
		frmPageMaker.setResizable(false);
		frmPageMaker.setTitle("Page Maker |  Alpha 3.0.0");
		frmPageMaker.setBounds(100, 100, 517, 364);
		frmPageMaker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPageMaker.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 481, 303);
		frmPageMaker.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Elements", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblText = new JLabel("Text");
		lblText.setBounds(10, 11, 46, 14);
		panel.add(lblText);
		
		final JTextField textField = new JTextField();
		textField.setBounds(10, 25, 456, 20);
		panel.add(textField);
		textField.setColumns(10);
		type.setModel(new DefaultComboBoxModel<String>(new String[] {"--Choose--", "Paragraph - <p>", "Header 1 - <h1>", "Header 2 - <h2>"}));
		type.setBounds(10, 83, 167, 20);
		panel.add(type);
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(10, 58, 46, 14);
		panel.add(lblType);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(199, 70, 13, 194);
		separator.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator);
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setBounds(10, 114, 46, 14);
		panel.add(lblClass);
		
		final JCheckBox italic = new JCheckBox("Italic");
		italic.setBounds(10, 166, 97, 23);
		panel.add(italic);
		
		final JCheckBox bold = new JCheckBox("Bold");
		bold.setBounds(10, 193, 97, 23);
		panel.add(bold);
		
		cssclass.setBounds(10, 139, 167, 20);
		panel.add(cssclass);
		cssclass.setModel(new DefaultComboBoxModel<String>(new String[] {"--Choose--", "largeText (style.css)", "strip (style.css)"}));
		
		Packages.loadFiles();
		
		JButton add = new JButton("Edit");
		add.setBounds(377, 241, 89, 23);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean hasClass = true;
				String text = textField.getText();
				String typeSelected = type.getSelectedItem().toString();
				String classSelected = cssclass.getSelectedItem().toString();
				if(text.equals("")) {
					JOptionPane.showMessageDialog(null, "No Text where inputed!", "Editor", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(italic.isSelected()) {
					text = "<em>" + text + "</em>";
				}
				if(bold.isSelected()) {
					text = "<strong>" + text + "</strong>";
				}
				if(!typeSelected.equals("--Choose--")) {
					if(typeSelected.equals("Paragraph - <p>")) {
						text = "<p 0001>" + text + "</p>";
					}else if(typeSelected.equals("Header 1 - <h1>")) {
						text = "<h1 0001>" + text + "</h1>";
					}else if(typeSelected.equals("Header 2 - <h2>")) {
						text = "<h2 0001>" + text + "</h2>";
					}else {
						String input = text;
						text = Packages.types.get(typeSelected.toString());
						text = text.replace("0000", input);
						text = text.replace(":", "=");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Type have to be choosen!", "Editor", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!classSelected.equals("--Choose--") && hasClass) {
					if(classSelected.equals("largeText (style.css)")) {
						text = text.replace("0001", "class=\"largeText\"");
					}else if(classSelected.equals("strip (style.css)")) {
						text = text.replace("0001", "class=\"strip\"");
					}else {
						text = text.replace("0001", "class=\"" + Packages.classes.get(classSelected) + "\"");
					}
				}else {
					text = text.replace(" 0001", "");
				}
				if(text.contains("0002")) {
					String input = JOptionPane.showInputDialog("Please Enter link/file in the system");
					text = text.replace("0002", input);
					System.out.println(text);
				}
				textField.setText("");
				type.setSelectedItem("--Choose--");
				cssclass.setSelectedItem("--Choose--");
				italic.setSelected(false);
				bold.setSelected(false);
				if(Edit.editHtml(text)) {
					JOptionPane.showMessageDialog(null, "The files were seccefully edited!", "Editor", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "The style.css file or index.html were not found!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(add);
		
		JButton btnCreateNewPage = new JButton("Go to generator");
		btnCreateNewPage.setBounds(241, 241, 126, 23);
		btnCreateNewPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPageMaker.dispose();
				MainInformation.main();
			}
		});
		panel.add(btnCreateNewPage);
				
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Install", null, panel_1, null);
		panel_1.setLayout(null);
						
		JLabel lblPleaseChooseA = new JLabel("Please choose a package to install");
		lblPleaseChooseA.setBounds(10, 11, 220, 14);
		panel_1.add(lblPleaseChooseA);
		
		packages.addItem("--Choose--");
		packages.setBounds(10, 36, 166, 20);
		panel_1.add(packages);
		
		Packages.getPackages();
		
		JButton btnInstall = new JButton("Install");
		btnInstall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String packageChoose = packages.getSelectedItem().toString();
				if(!packageChoose.equals("--Choose--")) {
					Packages.install(Packages.packages.get(packageChoose));
					
				}else {
					
				}
			}
		});
		btnInstall.setBounds(149, 241, 89, 23);
		panel_1.add(btnInstall);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(248, 11, 13, 253);
		panel_1.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(261, 11, 205, 253);
		panel_1.add(scrollPane);
		
		JTextPane txtpnPackageIsA = new JTextPane();
		scrollPane.setViewportView(txtpnPackageIsA);
		txtpnPackageIsA.setText("Package is a special addon or plugin which adds new content to the builder, like new classes or types. To get packages you can to visit www.SAGICode.com and download free packages. After you install the package you need to put it in the 'resources' folder. after it just restart the builder and click the install button! The install button will extract, copy and link the files to the page. after it you can to use it with no problem!\r\n\r\nNOTICE!\r\nThe current API version is 1.0.0, If you get a package which needs API which is newer than this one then the package might not work!");
		
		final JPanel panel_2 = new JPanel();
		tabbedPane.addTab("JQuery", null, panel_2, null);
		panel_2.setLayout(null);
		
		final JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setBounds(10, 241, 327, 23);
		panel_2.add(progressBar_1);
		
		final JLabel label = new JLabel("Not downloading...");
		label.setBounds(10, 216, 456, 14);
		panel_2.add(label);
		
		final Thread download = new Thread("download") {
			public void run() {
				try {
					label.setText("Preparing Download...");
					System.out.println(label.getText());
					progressBar_1.setValue(0);
					
					website = new URL("http://192.168.100.29:8080/jquery-1.11.2.min.js");
					
					progressBar_1.setValue(20);
					label.setText("Downloading jquery-1.11.2.min.js...");
					System.out.println(label.getText());
					
					rbc = Channels.newChannel(website.openStream());

					progressBar_1.setValue(46);
					label.setText("Saving file to GeneratedPages/js/jquery-1.11.2.min.js");
					System.out.println(label.getText());
					
					FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\generatedPages\\js\\jquery-1.11.2.min.js");
					fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
					fos.close();
					
					label.setText("JQuery were download seccefully!");
					System.out.println(label.getText());
					progressBar_1.setValue(100);
				} catch (Exception e) {
					if(e.getMessage().equals("Connection refused: connect")) {
						label.setText("Could not connect to the server! -> " + e.getMessage());
						JOptionPane.showMessageDialog(null, "Could not connect to the server!\n\r Server might be down(Domain: www.SAGICode.com) \n\r Please contact to SAGI to get more info about this problem!", "Error", JOptionPane.ERROR_MESSAGE);
					}else if(e.getMessage().contains("http://192.168.1.12:8080/")){
						label.setText("File was not found on the server! -> " + e.getMessage());
						JOptionPane.showMessageDialog(null, "Could not find the file on the server! \n\r Please contact SAGI(www.SAGICode.com) if you get this problem again!", "Error", JOptionPane.ERROR_MESSAGE);
					}else if(e.getMessage().equals("Connection timed out: connect")){
						label.setText("Could not connect to the server -> " + e.getMessage());
						JOptionPane.showMessageDialog(null, "Could not connect to the server! \n\r It can be caused by slow intenet or server down!\n\r Please contact SAGI(www.SAGICode.com) if you get this problem again!", "Error", JOptionPane.ERROR_MESSAGE);						
					}
					e.printStackTrace();
				}
			}
		};
		
		JButton button = new JButton("Download");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				download.start();
			}
		});
		button.setBounds(347, 241, 119, 23);
		panel_2.add(button);
		
		JLabel lblJqueryIsA = new JLabel("jQuery is a fast, small, and feature-rich JavaScript library. It makes ");
		lblJqueryIsA.setBounds(10, 11, 456, 14);
		panel_2.add(lblJqueryIsA);
		
		JLabel lblNewLabel_1 = new JLabel("things like HTML document traversal and manipulation, event handling, ");
		lblNewLabel_1.setBounds(10, 28, 456, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblAnimationAndAjax = new JLabel("animation, and Ajax much simpler with an easy-to-use API that works ");
		lblAnimationAndAjax.setBounds(10, 44, 456, 14);
		panel_2.add(lblAnimationAndAjax);
		
		JLabel lblAcrossAMultitude = new JLabel("across a multitude of browsers.");
		lblAcrossAMultitude.setBounds(10, 62, 456, 14);
		panel_2.add(lblAcrossAMultitude);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 87, 456, 118);
		panel_2.add(scrollPane_1);
		
		JTree tree_1 = new JTree();
		tree_1.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("JQuery") {
				private static final long serialVersionUID = 1L;

				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("js");
						node_1.add(new DefaultMutableTreeNode("jquery-1.11.2.min.js (93.6 Kb)"));
					add(node_1);
				}
			}
		));
		scrollPane_1.setViewportView(tree_1);
		
	}
	
	public static void addClass(String name) {
		cssclass.addItem(name);
	}
	
	public static void addType(String name) {
		type.addItem(name);
	}
	
	public static void addPackage(String name) {
		packages.addItem(name);
	}
}

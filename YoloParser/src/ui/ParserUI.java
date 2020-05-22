package ui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.YoloParser;
import model.RealObject;
import model.RealObjectPack;

import java.awt.Component;

public class ParserUI {
	private JFrame framePrincipal;
	private JPanel panelPrincipal;
	private JTextArea textArea;
	private ArrayList<JLabel> labelListCode;
	private ArrayList<JLabel> labelListStack;
	private JScrollPane scrollPane;
	private JTextArea textArea2;
	private JScrollPane scrollPane2;
	private JButton btnSelectFile;
	private ScheduledExecutorService exec;
	private boolean automaticMode;
	private boolean singleObjectsMode = true;
	
	public ParserUI() {
		framePrincipal = new JFrame();
		framePrincipal.setResizable(false);
		framePrincipal.setTitle("YoloParser");
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.setBounds(50, 50, 820, 554);
		panelPrincipal = new JPanel();
		framePrincipal.getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JButton btnSintaxeAceita = new JButton("Single Objects");
		btnSintaxeAceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (automaticMode) {
					singleObjectsMode = true;
				} else {
					processSingleObjectsOutput();
				}
			}
		});
		btnSintaxeAceita.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSintaxeAceita.setBounds(435, 479, 121, 23);
		panelPrincipal.add(btnSintaxeAceita);
		
		JLabel lblHistrico = new JLabel("Input");
		lblHistrico.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHistrico.setBounds(10, 31, 73, 14);
		panelPrincipal.add(lblHistrico);
				
		textArea = new JTextArea();
		textArea.setBounds(10, 239, 572, 218);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 56, 353, 401);
		panelPrincipal.add(scrollPane);
		
		labelListCode = new ArrayList<JLabel>();
		labelListStack = new ArrayList<JLabel>();
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Desenvolvido por Vinícius Scheffel\nvbscheffel@inf.ufrgs.br\nAbril, 2017");
			}
		});
		label.setBounds(822, 11, 24, 24);
		panelPrincipal.add(label);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOutput.setBounds(435, 32, 73, 14);
		panelPrincipal.add(lblOutput);
		
		textArea2 = new JTextArea();
		textArea2.setEditable(false);
		textArea2.setBounds(10, 239, 572, 600);
		scrollPane2 = new JScrollPane(textArea2);
		scrollPane2.setBounds(435, 56, 353, 401);
		panelPrincipal.add(scrollPane2);
		
		JButton btnQuantity = new JButton("Quantity");
		btnQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (automaticMode) {
					singleObjectsMode = false;
				} else {
					processQuantityObjectsOutput();
				}
			}
		});
		btnQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnQuantity.setBounds(667, 479, 121, 23);
		panelPrincipal.add(btnQuantity);
		
		btnSelectFile = new JButton("Select File");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectFile();
			}
		});
		btnSelectFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSelectFile.setBounds(116, 480, 121, 23);
		panelPrincipal.add(btnSelectFile);
	}
	
	public void showUI(){
		this.framePrincipal.setVisible(true);
	}
		
	private void processSingleObjectsOutput() {
		ArrayList<RealObject> objects = (ArrayList<RealObject>) YoloParser.parse(textArea.getText());
		
		for (RealObject object : objects) {
			System.out.println("Result:" + object);
		}
		
		String outputString = "";
		for (final RealObject object : objects) {
			outputString = outputString + "Object Name: " + object.getName() + "\n" +
										  "Object Probability: " + object.getProbability() + "%" + "\n\n";
		}
		
		textArea2.setText(outputString);
	}
	
	private void processQuantityObjectsOutput() {
		ArrayList<RealObject> objects = (ArrayList<RealObject>) YoloParser.parse(textArea.getText());
		HashMap objectsHash = new HashMap<String, RealObjectPack>();

		for (final RealObject object : objects) {
			RealObjectPack objectPack = (RealObjectPack) objectsHash.get(object.getName());
			
			if (objectPack == null) {
				objectsHash.put(object.getName(), new RealObjectPack(object));
				continue;
			}
			
			objectPack.addOne();
		}
		
		String outputString = "";
		for (final Object objectName : objectsHash.keySet()) {
			RealObjectPack pack = (RealObjectPack) objectsHash.get(objectName);
			outputString = outputString + "Object Name: " + pack.getObject().getName() + "\n" +
					  "Quantity: " + pack.getQuantity() + "\n\n";
		}
		
		textArea2.setText(outputString);
	}
	
	private void selectFile() {
		JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	            "Text Files(*.txt)", "txt");
	    fileChooser.setFileFilter(filter);
	    fileChooser.setCurrentDirectory(new File(System
	            .getProperty("user.home")));
	    int result = fileChooser.showOpenDialog(framePrincipal);
	    if (result == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        BufferedReader br = null;
	        try {
	            br = new BufferedReader(new FileReader(selectedFile));
	            StringBuilder sb = new StringBuilder();
	            String line = br.readLine();

	            while (line != null) {
	                sb.append(line);
	                sb.append("\n");
	                line = br.readLine();
	            }
	            String all = sb.toString();
	            textArea.setText(all);
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally {
	            try {
	                br.close();
	            } catch (IOException ex) {
	                System.out.println("Problem reading file");
	            }
	        }
	        automaticModeEnabled();
		    startUpdateInputFileJob(selectedFile);
	    }
	}
	
	private void automaticModeEnabled() {
		this.automaticMode = true;
		this.textArea.setEditable(false);
	}
	
	private void startUpdateInputFileJob(final File selectedFile) {
		System.out.println("\n\n\n\n EXECUTING JOB \n\n\n\n\n");
		exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
			  BufferedReader br = null;
		        try {
		            br = new BufferedReader(new FileReader(selectedFile));
		            StringBuilder sb = new StringBuilder();
		            String line = br.readLine();

		            while (line != null) {
		                sb.append(line);
		                sb.append("\n");
		                line = br.readLine();
		            }
		            String all = sb.toString();
		            textArea.setText(all);
		        }catch(Exception e){
		            e.printStackTrace();
		        }finally {
		            try {
		                br.close();
		            } catch (IOException ex) {
		                System.out.println("Problem reading file");
		            }
		        }
		        
		        if (singleObjectsMode) {
		        	processSingleObjectsOutput();
		        } else {
		        	processQuantityObjectsOutput();
		        }
		  }
		}, 0, 1, TimeUnit.SECONDS);
	}
}
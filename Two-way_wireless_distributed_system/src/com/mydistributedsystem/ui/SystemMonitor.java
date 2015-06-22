package com.mydistributedsystem.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.mydistributedsystem.message.JDMessageType;
import com.mydistributedsystem.nodes.GatewayNode;
import com.mydistributedsystem.nodes.Node;
import com.mydistributedsystem.nodes.NodeManager;
import com.mydistributedsystem.nodes.NodeStatus;

public class SystemMonitor extends JFrame implements Observer {

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JPanel interfacePanel;
	private JTable table;
	private JLabel msgLabel;
	private JButton btn;
	private String changableJob ;
	private int selectedNodeId;
	DefaultTableModel tableModel ;

    String[] choices = { "JobMean", "JobAccumulate", "JobHalt"};

    final JComboBox<String> cb = new JComboBox<String>(choices);
	private static SystemMonitor monitor = null;

	
	public static SystemMonitor getMonitor(){
			if( monitor == null) monitor = new SystemMonitor();
			
		return monitor;
	}
	
	public void prepareGUI(){
		mainFrame = new JFrame("Gateway Monitor");
		mainFrame.setSize(700,600);
		mainFrame.setLayout(null);
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		headerLabel = new JLabel("Gateway Monitor", JLabel.CENTER);
		statusLabel = new JLabel("Status", JLabel.CENTER);
		
		statusLabel.setSize(350, 100);
		Object columnNames[] = {"GID", "ID", "NodeName","Currnt Job", "Status"}; 
		table = new JTable();
		tableModel = new DefaultTableModel(columnNames, 0);
		this.makeRowData();
		table.setModel(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setSize(400, 150);
		
//		Object rowData[][] = { {"1","2","3","4"} , {"5", "6", "7", "8"} };
//		Object columnNames[] = {"ID", "NodeName","Currnt Job", "Status"}; 
//		table = new JTable(rowData, columnNames);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (table.getSelectedRow() > -1) {
		            // print first column value from selected row
		            System.out.println(table.getValueAt(table.getSelectedRow(), 1).toString());
		            selectedNodeId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 1).toString());
		        }
		    }
		});
		msgLabel = new JLabel("Welcome to Gateway Monitoring System", JLabel.CENTER);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		controlPanel.setBounds(new Rectangle(430,600));
		controlPanel.add(scrollPane);
		
		interfacePanel = new JPanel();
		interfacePanel.setLayout(new FlowLayout());
		interfacePanel.setBounds(440, 2, 180, 100);
		  cb.setVisible(true);
		  cb.addActionListener(
	                new ActionListener(){
	                    public void actionPerformed(ActionEvent e){
	                        JComboBox combo = (JComboBox)e.getSource();
	                        changableJob = (String)combo.getSelectedItem();
	                        System.out.println("[Combobox]:"+changableJob);
	                    }
	                }            
	        );
		    interfacePanel.add(cb);

		    btn = new JButton("OK");
		    btn.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					if(selectedNodeId > 0){
						NodeManager.getNodeManager().getNode(selectedNodeId).setJobName(changableJob);
						NodeManager.getNodeManager().getNode(selectedNodeId).setJobChanged(true);
					}

                    System.out.println("[Button]:"+changableJob);
               
				}
		    	
		    });
		    interfacePanel.add(btn);
	//	mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(interfacePanel);
	//	mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}
	
	public Object[][] makeRowData(){
		Object rowData[][] = new String[JDMessageType.MaxNode][];
		for (int i = 2; i < JDMessageType.MaxNode; i++){
		Node n = NodeManager.getNodeManager().getNode(i);
		rowData[i] = new String[4];
			if(n != null){
					
				rowData[i][0] = n.getId()+"";
				rowData[i][1] = "Name-"+n.getId();
				rowData[i][2] = n.getJobName()+"";
				

				String status = "";
				if(n.getStatus() == NodeStatus.disconnected){
				status = "Disconnected";
				}else if(n.getStatus() == NodeStatus.connected){
					status = "Connected";
				}else if(n.getStatus() == NodeStatus.run){
					status = "Run";
				}else if(n.getStatus() == NodeStatus.ready){
					status = "Ready";
				}else {
					status = "Undefined";
				}
				tableModel.addRow(new Object[]{n.getGid()+"",n.getId()+"", "Android-"+n.getId(), n.getJobName()+"" ,status});

			}
			else{
				rowData[i][0] = "";
				rowData[i][1] = "Name-";
				rowData[i][2] = "";
				rowData[i][3] = "";
			}
			
		}
		
		return rowData;
	}
	
	
	public void showMonitor(){
		
	}
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Update "+o+", "+arg);
		int originalCount = tableModel.getRowCount();
		for(int i = 0; i < originalCount; i++){
			tableModel.removeRow(0);
		}
		makeRowData();
//		Node node = (Node) arg;

		
	}

}

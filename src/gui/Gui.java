package gui;

import java.io.*;
import find.Find;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultEditorKit;
import javax.swing.JSeparator;



public class Gui implements ActionListener{
	
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menuFile, menuEdit, menuSearch;
	private JMenuItem newFile, openFile, saveFile, saveAsFile , exit, cut, copy, paste, undo, redo, selectAll, search;
	private JTextArea textArea;
	private File file;
	private JSeparator separator;
	private JSeparator separator_1;

	
	
	
	
	public static void main(String args[])
	{
		Gui g= new Gui();
		g.go();
		
	}
	
	public void go()
	{
		frame = new JFrame();
		frame.setSize(500, 400);
		frame.setTitle("SimpleTextEditor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		
		 menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menuFile = new JMenu("File");
		menuEdit = new JMenu("Edit");
		menuSearch = new JMenu("Search");
		
		
		//openFile, saveFile, saveAsFile , Exit, cut, copy, paste, undo, redo, selectAll;
		newFile = new JMenuItem("New");
		newFile.addActionListener(new NewFileListner());
		
		openFile = new JMenuItem("Open");
		openFile.addActionListener(new OpenFileListner());
		
		saveFile = new JMenuItem("Save");
		saveFile.addActionListener(new SaveFileListner());
		
		saveAsFile = new JMenuItem("Save As...");
		saveAsFile.addActionListener(new SaveAsFileListner());
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		
		cut = new JMenuItem(new DefaultEditorKit.CutAction());
		
	
		copy = new JMenuItem(new DefaultEditorKit.CopyAction());
		
		paste = new JMenuItem(new DefaultEditorKit.PasteAction());
		
		
		undo = new JMenuItem("Undo");
		undo.addActionListener(this);
		
		redo = new JMenuItem("Redo");
		redo.addActionListener(this);
		
		selectAll = new JMenuItem("Select All");
		selectAll.addActionListener(this);
		
		search = new JMenuItem("Search...");
		search.addActionListener(new SearchFileListener());
		
		
		menuFile.add(newFile);
		menuFile.add(openFile);
		menuFile.add(saveFile);
		menuFile.add(saveAsFile);
		menuFile.add(exit);
		
		menuEdit.add(undo);
		menuEdit.add(redo);
		
		separator = new JSeparator();
		menuEdit.add(separator);
		menuEdit.add(cut);
		menuEdit.add(copy);
		menuEdit.add(paste);
		
		separator_1 = new JSeparator();
		menuEdit.add(separator_1);
		menuEdit.add(selectAll);
		
		menuSearch.add(search);
	
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuSearch);
	
		frame.setJMenuBar(menuBar);
		
		frame.getContentPane().setLayout(new BorderLayout());
		
		textArea=new JTextArea("",0,0);
		textArea.setLineWrap(true);
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		frame.getContentPane().add(scroller);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==selectAll)
		{
			textArea.selectAll();
		}
		
		else if(e.getSource()==exit)
		{
			frame.dispose();
		}
	}
	
	
	public class NewFileListner implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			textArea.setText("");
			file=null;
		}
		
	
		
	}
	
	public class OpenFileListner implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			JFileChooser fileOpen = new JFileChooser();
			int option=fileOpen.showOpenDialog(frame);
			
			if(option==JFileChooser.APPROVE_OPTION)
			{
				
			loadFile(fileOpen.getSelectedFile());
			file=fileOpen.getSelectedFile();
			
			}
		}
		
		
	}

	public class SaveFileListner implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(file!=null)
			{
				writeFile(file);
			}
			else
			{
				SaveAsFileListner save = new SaveAsFileListner();
				save.actionPerformed(e);
			}
		}

		
	}
	
	public class SaveAsFileListner implements ActionListener
	{
	
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			JFileChooser fileSave = new JFileChooser();
			int option=fileSave.showSaveDialog(frame);
			
			if(option==JFileChooser.APPROVE_OPTION)
			{
				writeFile(fileSave.getSelectedFile());
			    file=fileSave.getSelectedFile();	
			}
			
		}
	}
	
	public class SearchFileListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			new Find(textArea);
		}
		
	}
	public void loadFile(File file)
	{
		
	try
	{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		textArea.setText("");
		
		String line = null;
		
		while( (line=reader.readLine()) !=null)
		{
	      
			textArea.append(line);
			textArea.append("\n");
			
		}
		reader.close();
	}
	
	catch (Exception e) 
	{
		// TODO: handle exception
		e.printStackTrace();
		
	}	
	
	
	}
	
	
	public void writeFile(File file)
	{
		
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(textArea.getText());
			writer.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		
	}
	
}
package gui;

import find.Find;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class Gui implements ActionListener {
	private JMenuBar menuBar;
	private JMenu menuFile, menuEdit, menuSearch;
	private JMenuItem newFile, openFile, saveFile, saveAsFile , exit, cut, copy, paste, undo, redo, selectAll, search;
	private JTextArea textArea;
	
	
	
	
	
	public static void main(String args[])
	{
		Gui g= new Gui();
		g.go();
		
	}
	
	public void go()
	{
		JFrame frame = new JFrame();
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
		newFile.addActionListener(this);
		
		openFile = new JMenuItem("Open");
		openFile.addActionListener(this);
		
		saveFile = new JMenuItem("Save");
		saveFile.addActionListener(this);
		
		saveAsFile = new JMenuItem("Save As...");
		saveAsFile.addActionListener(this);
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		
		cut = new JMenuItem("Cut");
		cut.addActionListener(this);
		
		copy = new JMenuItem("Copy");
		copy.addActionListener(this);
		
		paste = new JMenuItem("Paste");
		paste.addActionListener(this);
		
		undo = new JMenuItem("Undo");
		undo.addActionListener(this);
		
		redo = new JMenuItem("Redo");
		redo.addActionListener(this);
		
		selectAll = new JMenuItem("Select All");
		selectAll.addActionListener(this);
		
		search = new JMenuItem("Search...");
		search.addActionListener(this);
		
		menuFile.add(newFile);
		menuFile.add(openFile);
		menuFile.add(saveFile);
		menuFile.add(saveAsFile);
		menuFile.add(exit);
		
		menuEdit.add(undo);
		menuEdit.add(redo);
		menuEdit.add(cut);
		menuEdit.add(copy);
		menuEdit.add(paste);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==openFile)
		{
			
			JFileChooser open = new JFileChooser(); // open up a file chooser (a dialog for the user to  browse files to open)
            int option = open.showOpenDialog(openFile); // get the option that the user selected (approve or cancel)

            /*
             * NOTE: because we are OPENing a file, we call showOpenDialog~ if
             * the user clicked OK, we have "APPROVE_OPTION" so we want to open
             * the file
             */
            if (option == JFileChooser.APPROVE_OPTION) {
                 // clear the TextArea before applying the file contents
                try {
                    // create a scanner to read the file (getSelectedFile().getPath() will get the path to the file)
                    
					@SuppressWarnings("resource")
					Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
                    while (scan.hasNext()) // while there's still something to
                                            // read
                        textArea.append(scan.nextLine() + "\n"); // append the line to the TextArea
                } catch (Exception ex) { // catch any exceptions, and...
                    // ...write to the debug console
                    System.out.println(ex.getMessage());
                }
            }
        }
			
		
		if(e.getSource()==search)
		{
		new Find(textArea);	
		}
			
		}
		
		
	}

	

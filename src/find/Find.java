package find;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;


public class Find implements ActionListener{
	private JTextField textField;
	private JButton buttonFind;
	private JButton buttonFindNext;
	private JTextArea textArea;
	
	public Find(JTextArea textArea)
	{
		this.textArea=textArea;
		JFrame frame = new JFrame();
		frame.setLocation(200, 200);
		frame.setSize(444, 244);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField("");
		textField.setToolTipText("Enter Text");
		textField.setBounds(10, 11, 246, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		buttonFind = new JButton("Find");
		buttonFind.setBounds(280, 10, 138, 23);
		buttonFind.addActionListener(this);
		frame.getContentPane().add(buttonFind);
        
		
		buttonFindNext = new JButton("Find next");
		buttonFindNext.setBounds(280, 44, 138, 23);
		buttonFindNext.addActionListener(this);
		frame.getContentPane().add(buttonFindNext);
		
		frame.setVisible(true);
		
		
	}
	private int[] computeErrorFunction(String pattern)
	{
		
		int h[]=new int[pattern.length()];
		int i=2;
		int j=0;
		
		if(pattern.length()>1)
		h[0]=h[1]=0;
		
		else if (pattern.length() > 0)
		{
			h[0]=0;
			return h;
		}
		
		for(i=2;i<h.length;)
		{
			if(pattern.charAt(i-1)==pattern.charAt(j))
			{
				h[i]=j+1;
				j++;
				i++;
			}
			else
			{
				if(j>0)
				{
					
				j=h[j-1];
				
				}
				else if(j==0)
				{
					
					h[i]=0;
					i++;
				}
				
			}
			
		}
		
		return h;
	}
	
	public int naiveIndexOf (String text,String pattern)
	{
		
		int i=0;
		int j=0;
		int k=0;
		int text_length=text.length();
		int pattern_length=pattern.length();
		
		for(i=0;i<text_length-pattern_length+1;i++)
		{
			
			for(j=0,k=i;j<pattern_length;j++,k++)
			{
				if(text.charAt(k)!=pattern.charAt(j))
				{
					break;
				}
			}
			if(j==pattern_length)
			{
				return i;
			}
			
		}
	
		return -1;
		
	}
	
	public int kmpIndexOf(String text,String pattern)
	{
		int h[]=computeErrorFunction(pattern);
        int i=0;
		int j=0;
		int k=i;
		
		int text_length=text.length();
		int pattern_length=pattern.length();
		
		while(i<text_length && j<pattern_length)
		{
			if(text.charAt(i)!=pattern.charAt(j))
			{
				if(j==0)
				{
					i++;
					k=i-h[j];
				}
				else 
				{
					
					k=i-h[j];
					j=h[j];		
					
				}
				
				
			}
			else
			{
				i++;
				j++;
			}
			
			if(j==pattern_length)
				return k;
		}
		
		
		return -1;
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == buttonFind)
		{
			int index=0;
			int startIndex=0;
			int endIndex=0;
			
			index=kmpIndexOf(textArea.getText(), textField.getText());
			
			if(index!=-1)
			{
				
			startIndex = index;
			endIndex = startIndex + textField.getText().length();
			textArea.select(startIndex, endIndex);
				
			}
			
		}
		
	}
}

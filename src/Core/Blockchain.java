//Этот блокчейн был написан --Bit 
package Core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import Core.validation;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import Core.Frame;
import Core.MySQL;

public class Blockchain {

	static String[] IP = {"185.154.14.147"};
	static int PORT = 25565;
	static Block bchain = new Block();	
	static Block next = new Block();
	static Block back = new Block();
	static Block save = new Block();
	static int seconds;
	static int sec;
	static int i;
	static int hs;
	static boolean start;
	static boolean exitb;
	@SuppressWarnings("deprecation")
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		start = false; exitb = false;
        JFrame frame = Frame.createframe();
        JLabel label = new JLabel();
        JLabel blocks = new JLabel();
        JButton Start = new JButton();
        JButton exit = new JButton();
        JTextArea nickname = new JTextArea();
        JPanel alignmentPanel = new JPanel(new FlowLayout());
        alignmentPanel.setBorder(BorderFactory.createTitledBorder("DorCoin Control Panel"));
        frame.add(alignmentPanel);
        
        Dimension labelSize = new Dimension(190, 20);
        
        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Font font = new Font("Verdana", Font.PLAIN, 12);

        nickname.setPreferredSize(labelSize);
        nickname.setBorder(solidBorder);
        nickname.setFont(font);
        nickname.setText("Введи никнейм");
        
        exit.setVerticalAlignment(JLabel.TOP);
        exit.setHorizontalAlignment(JLabel.CENTER);
        exit.setPreferredSize(labelSize);
        exit.setBorder(solidBorder);
        exit.setFont(font);
        exit.setText("Выход");
        
        Start.setVerticalAlignment(JLabel.TOP);
        Start.setHorizontalAlignment(JLabel.CENTER);
        Start.setPreferredSize(labelSize);
        Start.setBorder(solidBorder);
        Start.setFont(font);
        Start.setText("Старт");
        
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(labelSize);
        label.setBorder(solidBorder);
        label.setFont(font);
        
        blocks.setVerticalAlignment(JLabel.TOP);
        blocks.setHorizontalAlignment(JLabel.CENTER);
        blocks.setPreferredSize(labelSize);
        blocks.setBorder(solidBorder);
        blocks.setFont(font);
        
	    label.setText("0");
	    alignmentPanel.add(blocks);
	    alignmentPanel.add(label);
	    alignmentPanel.add(Start);
	    alignmentPanel.add(exit);
	    alignmentPanel.add(nickname);
	    Start.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if (start == true){
	        		start = false;
	        		Start.setText("Старт");
	        		seconds=0;
	        	}else{
	        		start = true;
	        		Start.setText("Стоп");
	        	}
	             
	        }
	   });
	    exit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		start = false;
	        		System.exit(1);
	             
	        }
	   });
	    MySQL.connect();
        try{
        	next = MySQL.Read();
        	if (next.getData() == null){
        		next = GenesisBlock.getGenesis(nickname.getText());
        	}
        }catch (Exception e) {
        	next = GenesisBlock.getGenesis(nickname.getText());
		}
        back = next;
             do{ 
            	 label.setText("Текущая скорость: "+Integer.toString(hs)+" H/s");
            	 blocks.setText("Блокчейн: "+next.getIndex());
            	if (start == true){
                     next = createNextBlock.getHash(back,nickname.getText());
                if (validation.valid(next, back) == true){
                     MySQL.Add(next.getIndex(), next.getPreviousHash(), next.getTimestamp(), next.getData(), next.getHash());
                     }
                back = next;
                if (seconds == 0){
	                 seconds = new Date().getSeconds();
                }else if(sec-seconds >= 1){
	                 seconds = 0;
	                 hs=i;
	                 i = 0;
                     }
                i = i + 1;
                sec = new Date().getSeconds();
            	}
                }while(true);
	}
}

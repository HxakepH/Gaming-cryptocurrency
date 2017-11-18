package Core;
//Не важно
import java.awt.Dimension;
import javax.swing.JFrame;
public class Frame {
public static JFrame createframe() {
          JFrame.setDefaultLookAndFeelDecorated(true);
          JFrame frame = new JFrame("DorCoin Miner");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
          frame.setPreferredSize(new Dimension(200, 200));
          
          frame.pack();
          frame.setVisible(true);
          return frame;
     }
     
}
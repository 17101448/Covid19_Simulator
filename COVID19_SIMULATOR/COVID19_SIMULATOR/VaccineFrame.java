package COVID19_SIMULATOR;
import java.awt.*;
import javax.swing.*; 


public class VaccineFrame extends JFrame  {
        private JPanel gridPanel;
        private static final int DEFAULT_WIDTH = 10000;
        private static final int DEFAULT_HEIGHT = 10000;

        public VaccineFrame()
        {
            setSize(1000, 1000);
            printGrid(); 
    
            
        }

        private void printGrid(){
            gridPanel = new JPanel(); 
            gridPanel.setLayout(new GridLayout(6,6)); // 실제 개수보다 적으면 행부터 채우므로 조심할 것 
            for(int i=0; i<36; i++)
            {
                gridPanel.add(makeButton(Color.LIGHT_GRAY));
            }
            add(gridPanel);
        }

        private JButton makeButton(final Color backGroundColor)
        {
            JButton button = new JButton();
            button.setBackground(backGroundColor);

            return button; 
        }
}

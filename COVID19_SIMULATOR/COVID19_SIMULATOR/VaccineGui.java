package COVID19_SIMULATOR;
import java.awt.*;
import javax.swing.*;
public class VaccineGui {
    public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
        JFrame frame = new VaccineFrame();
        frame.setTitle("Vaccine");
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); frame.pack();
        frame.setVisible(true);
    });
    }

    

}


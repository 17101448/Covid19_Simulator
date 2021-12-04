package COVID19_SIMULATOR;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.*;

import COVID19_SIMULATOR.Person.State;




public class VaccineFrame extends JFrame  
{
    private JPanel gridPanel;
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 1000;

    private int size = 21;
    private int stepCount =0; 
    private int contactNum = 4; 

    Vaccine sim = new Vaccine(size, 0.1);

    JPanel[][] person = new JPanel[size][size];
    //State[][] state = new State[size][size];
    JButton[] buttonArr  = new JButton[3];
    JButton resetButton, pauseButton, runButton, stepButton;
    JPanel buttonArea; 

    
    public VaccineFrame()
    {
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

   
        JPanel mainArea = new JPanel();
        mainArea.setLayout(new GridLayout(size, size,1,1));
        add(mainArea, BorderLayout.NORTH);


        JPanel UIArea2 = new JPanel();
        UIArea2 .setPreferredSize(new Dimension(400, 600));
        UIArea2.setLayout(new GridLayout(2,2));
        UIArea2.add(new JLabel("1234"));
        UIArea2.add(new JLabel("1234"));

        JPanel sliderControlArea = new JPanel();
        sliderControlArea.setLayout(new GridLayout(3,1));
        sliderControlArea.setPreferredSize(new Dimension(400, 200));
        sliderControlArea.add(new JSlider(1,100));
        sliderControlArea.add(new JSlider(1,100));
        sliderControlArea.add(new JSlider(1,100));
        UIArea2.add(sliderControlArea);

        JPanel buttonArea = new JPanel();
        buttonArea.setPreferredSize(new Dimension(200, 200));
        buttonArea.add(makeStepButton());
        buttonArea.add(makeRunButton());
        buttonArea.add(makeResetButton()); 
        UIArea2.add(buttonArea);
        
       

        
        


        

       for(int y=0; y<size; y++)
       {
           for(int x=0; x<size; x++)
           {
               person[y][x] = new JPanel();
               mainArea.add(person[y][x]);
                person[y][x].setBackground(SetColor(x, y));
           }
       }
       add(mainArea, BorderLayout.NORTH);
    }

    private Color SetColor(int x, int y){
        if(sim.getPeopleState()[y][x] == State.VACCINATED)
        {
            return Color.BLUE;
        }
        else if(sim.getPeopleState()[y][x]  == State.SUSCEPTIBLE)
        {
            return Color.DARK_GRAY;
        }
        else if(sim.getPeopleState()[y][x] == State.INFECTIOUS)
        {
            return Color.RED;
        }
        return null;
    }

    private JButton makeStepButton()
    {   
        JButton button = new JButton("Step");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Counter(); 
                sim.step(0.2, 0.1);
                for(int y=0; y<size; y++)
                {
                    for(int x=0; x<size; x++)
                    {
                            person[y][x].setBackground(SetColor(x, y));
                    }
                }
               
            }
        });
        return button; 
    }

    private JButton makeRunButton()
    {   
        JButton button = new JButton("Run");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                Counter(); 
                sim.step(0.2, 0.1);
                for(int y=0; y<size; y++)
                {
                    for(int x=0; x<size; x++)
                    {
                            person[y][x].setBackground(SetColor(x, y));
                    }
                }
               
            }
        });
        return button; 
    }

    private JButton makeResetButton()
    {   
        JButton button = new JButton("Reset");
        return button; 
    }

    /*private JPanel makeSliderPanel(String string)
    {   
        JPanel sliderArea = new JPanel();
        sliderArea.setLayout(new GridLayout(1,3));
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100);
        //JTextArea textArea = new JTextArea();
        sliderArea.add(new JLabel(string + "(%)"));
        sliderArea.add(slider);
        sliderArea.add(new JLabel(" "));
        

        return sliderArea; 
    }*/

    private void Counter(){
        this.stepCount +=1; 
    }

    private int getCountNumber(){
        return this.stepCount; 
    }
    
}



        


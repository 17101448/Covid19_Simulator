package COVID19_SIMULATOR;

import COVID19_SIMULATOR.Person.State;

public class MainEXE {
    public static void main(String[] args){
        Vaccine sim = new Vaccine(25, 0.1);
        System.out.println(); 
         
        sim.printPeople();
        sim.printStep(0);
        System.out.println(); 
        
        sim.step(1,0); // 이후에 감염된 사람이 없어지면 정상 
        sim.printStep(1);
        
        

        //다른 클래스에서 호출 시에도 정상적인 참조변수로 저장되어 있는지 
        /*State[][] state = sim.getPeopleState();
        for(int i=0; i<25; i++)
        {
            for(int j=0; j<25; j++)
            {
                System.out.println(state[i][j]);
            }
        }
        System.out.println();*/

    }
    
}

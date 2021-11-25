package COVID19_SIMULATOR;
public class MainEXE {
    public static void main(String[] args){
        Vaccine sim = new Vaccine(25);
        System.out.print(sim.getPeopleState()[0][0]);
        System.out.println(); 
        for(int i=0; i<sim.people.length; i++)
        {
            for(int j=0; j<sim.people[0].length; j++)
            {
                System.out.print(sim.people[i][j]);
            }
            System.out.println(); 
        }
    }
    
}

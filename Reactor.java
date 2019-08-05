package app;

public class Reactor {
    public static void main(String[] args) {
    new GUI(); 
    Calcs.init();
    GUI.changeState();
    while (true) {
        Calcs.Tick();
        GUI.update();
        
        while (Calcs.getPause() == 1) {    // pause == 1, when button2 pressed
            try 
                {
                Thread.sleep(10);
                } 
            catch(InterruptedException e)
                { 

                }
            }
           
        }
    }
    
}

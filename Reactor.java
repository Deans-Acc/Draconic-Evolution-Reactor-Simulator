package app;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.BigInteger;



public class Reactor extends JFrame {
    // Inits / Sets variabels
    static int valid; // used to check if the input from the user is valid
    static double maxShield;
    static int maxSaturation;
    static int Saturation;
    static double shieldCharge;
    static int i = 0;
    static int pause = 0;
    static double fieldInputRate;
    static double tempDrainFactor;
    static int fieldDrain;
    static double generationRate;
    static double fuelUseRate;
    static double Temp = 2500;
    static int output;
    static int input;
    static double totalFuel; //10368 max.
    static JTextArea Area = new JTextArea();
    static JTextField typingArea = new JTextField(20);
    static String text ="";
    static BigInteger all = new BigInteger("0");
    static final double MAX_TEMP = 10000;
    static double Fuel; //10368 max.
    public Reactor() { 
        // Creates and start GUI
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 300;
        int frameHeight = 300;
        setSize(frameWidth, frameHeight);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getSize().width) / 2;
        int y = (dim.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Reactor");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        Area.setEditable(false);
        Area.setBounds(25,60,250,160);
        cp.add(Area);
        typingArea.setBounds(95, 20, 110, 30);
        cp.add(typingArea);
        Button1.setBounds(25, 220, 125, 30);
        Button1.setText("Confirm");
        Button1.setMargin(new Insets(2, 2, 2, 2));
        Button1.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
            Button1_ActionPerformed(evt);
        }
    }
    );
        cp.add(Button1);
        Button2.setBounds(150, 220, 125, 30);
        Button2.setText("Pause");
        Button2.setMargin(new Insets(2, 2, 2, 2));
        Button2.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Button2_ActionPerformed(evt);
            }
        }
        );
        cp.add(Button2);
        setVisible(true);
    
    } 
  
 
  
    public static void main(String[] args) {
        new Reactor();
        init();
        Button1.setText("New values");
        while (true) {
            Tick();
            
            if( i == 1 ) {  // i == 1, when button1 pressed
                Button1.setText("Confirm");
                setnew();
                Button1.setText("New values");
            } 
             
            while (pause == 1) {    // pause == 1, when button2 pressed
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
    static private void Tick() {
            // Variabels
            double coreSat = (double) Saturation / (double) maxSaturation;
            double negCSat = (1D - coreSat) *99D;
            double temp50 = Math.min((Temp / MAX_TEMP) * 50, 99);
            double convLVL = (((totalFuel - Fuel) / totalFuel) *1.3D) -0.3D;
            // Temperatur
            final double tempOffset = 444.7;
            double tempRiseExpo = (negCSat * negCSat * negCSat) / (100 - negCSat) + tempOffset;
            double tempRiseResist = (temp50 * temp50 * temp50 * temp50) / (100 - temp50);
            double riseAmount = (tempRiseExpo - (tempRiseResist * (1D - convLVL)) + convLVL * 1000) / 10000;
            Temp += (riseAmount * 10);
            // Energy
            int baseMaxRFt = (int) ((maxSaturation/ 1000D) * 1 * 1.5D);
            int maxRFt = (int) (baseMaxRFt * (1D + (convLVL * 2)));
            generationRate =((1D - coreSat) * maxRFt);
            Saturation +=((int)generationRate);
            getEnergy();
            extractEnergy();
            // Shield
            tempDrainFactor = Temp > 8000 ? 1 + ((Temp - 8000) * (Temp - 8000) * 0.0000025) : Temp > 2000 ? 1 : Temp > 1000 ? (Temp - 1000) / 1000 : 0;
            fieldDrain = (int) Math.min(tempDrainFactor * Math.max(0.01, (1D - coreSat)) * (baseMaxRFt / 10.923556), (double) Integer.MAX_VALUE);
            double fieldNegPercent = 1D - (shieldCharge / maxShield);
            fieldInputRate = (fieldDrain / fieldNegPercent);
            shieldCharge -= (Math.min(fieldDrain, shieldCharge));
            // Fuel
            fuelUseRate = tempDrainFactor *(1D - coreSat) * (0.001 * 1);
            if (Fuel > 0) {
                Fuel -= fuelUseRate;
            }
            if (output >= generationRate) {
                all = all.add((BigInteger.valueOf(output - input)));
            }
            else {
                all = all.add((BigInteger.valueOf( (long) generationRate - input)));
            }
            Debug();
            Test();

    }
    static private void getEnergy() {
        // Simulates the function of the "Energy Injector"
        double tempFactor = 1;
        if (Temp > 15000) {
            tempFactor = 1D - Math.min(1, (Temp - 15000D) / 10000D);
        }

        shieldCharge += (Math.min((input * (1D - (shieldCharge / maxShield))), maxShield - shieldCharge) * tempFactor);
        if (shieldCharge > maxShield) {
            shieldCharge = maxShield;
        }
    }
    static private void extractEnergy() {
        // Simulates the function of the "Reactor Stabilizers"
        if (Saturation >= output) {
            Saturation -= output;
        }
        else {
            Saturation -= Saturation;
        }
    }
    static private void Test() {
        // Looks for 'unpleseant' events and reports them
        if(shieldCharge <= 0 && Temp > 2000) {
            while (true) {
                Debug();
                text += "\n Reactor exploded";
                Area.setText(text);
                while(true) {

                }
            }
        }
        if(Temp < 2001) {
            while (true) {
                Debug();
                text += "\n Reactor cooled down";
                Area.setText(text);
                while(true) {
                    
                }
            }
        }
        if(Temp < 2500 && (double) Saturation / (double) maxSaturation >= 0.99) {
            while (true) {
                Debug();
                text += "\n Fail-Safe triggered";
                Area.setText(text);
                while(true) {
                    
                }
               
            }
        }
    }
    static private void Debug(){
        // Information about the cores status
        text = "";
        text +=("Temperatur: " + (int)Temp + "\n");
        text +=("Shield + Percent: " + (int)shieldCharge + " " + (int)(shieldCharge / maxShield * 100) + "\n");
        text +=("fieldInputRate: " + (int)fieldInputRate +"\n");
        text +=("DrainFactor + fieldDrain: " + tempDrainFactor + " " + fieldDrain + "\n");
        text +=("Saturation: " + Saturation + "\n");
        text +=("Generation Rate: " + (int)generationRate + "\n");
        text +=("Fuel + Percent: " + Fuel + " " + (int)(Fuel / totalFuel * 100) + "\n");
        text +=("fuelUseRate: " + fuelUseRate + "\n");
        text +=("Energy loss / win: " + all);
        Area.setText(text);   
    } 
    public Reactor(String name) {
        super(name);
    }
    
    static private JButton Button1 = new JButton();
    private JButton Button2 = new JButton();
     
    public void Button1_ActionPerformed(ActionEvent evt) {
        i = 1;
        typingArea.requestFocusInWindow();
    } 
    public void Button2_ActionPerformed(ActionEvent evt) {
        // Flip-Flop for pausing
        if(pause == 0) {
            pause = 1;
        }
        else {
            pause = 0;
        }
        typingArea.requestFocusInWindow();
    } 
   public static void init() {
    // Asks details about reactor and uses them
    i = 0;
    valid = 0;
    while (valid == 0) {
        Area.setText("Fuel? 1 Ingot = 1 and 1 Block = 9" + "\n max 8 Blocks or 72");
        while (i == 0) {
            try 
            {
                Thread.sleep(10);
            } 
            catch(InterruptedException e)
            { 

            }

            }
        i = 0;
        Fuel =  144 * Double.parseDouble(typingArea.getText());
        totalFuel = 144 * Double.parseDouble(typingArea.getText());
        if (Fuel > 10368 || Fuel < 144 || Fuel / 144 != Math.round(Fuel / 144)) {
            Area.setText("Impossible amount of fuel");
            try 
            {
                Thread.sleep(1000);
            } 
            catch(InterruptedException e)
            { 

            }
        }
        else {
            valid = 1;
        }
    }
    maxShield = (Fuel *96.45061728395062 * 100);
    maxSaturation = ((int) (maxShield * 10));
    Saturation = maxSaturation / 2;
    shieldCharge = maxShield / 2; 
    valid = 0;
    while ( valid == 0) {
        Area.setText("Energy output?");
        while (i == 0) {
            try 
            {
                Thread.sleep(10);
            } 
            catch(InterruptedException e)
            { 

            }
    
            }
        i = 0;
        output = Integer.parseInt(typingArea.getText());
        if (output < 0) {
        Area.setText("Negative amounts of energy are impossible");
        try 
            {
                Thread.sleep(1000);
            } 
            catch(InterruptedException e)
            { 

            }
        }
        else {
            valid = 1;
        }
    }
    valid = 0;
    while ( valid == 0) {
        Area.setText("Energy input?");
        while (i == 0) {
            try 
            {
                Thread.sleep(10);
            } 
            catch(InterruptedException e)
            { 

            }
    
            }
        i = 0;
        input = Integer.parseInt(typingArea.getText());
        if (input < 0) {
        Area.setText("Negative amounts of energy are impossible");
        try 
            {
                Thread.sleep(1000);
            } 
            catch(InterruptedException e)
            { 

            }
        }
        else {
            valid = 1;
        }}
    }
   public static void setnew() {
        // Gives you the option to change energy input / output
        i = 0;
        valid = 0;
        while ( valid == 0) {
            Area.setText("Energy output?");
            typingArea.setText(String.valueOf(output));
            while (i == 0) {
                try 
                {
                    Thread.sleep(10);
                } 
                catch(InterruptedException e)
                { 
    
                }
        
                }
            i = 0;
            output = Integer.parseInt(typingArea.getText());
            if (output < 0) {
            Area.setText("Negative amounts of energy are impossible");
            try 
            {
                Thread.sleep(1000);
            } 
            catch(InterruptedException e)
            { 

            }
            }
            else {
                valid = 1;
            }
        }
        valid = 0;
        while ( valid == 0) {
            Area.setText("Energy input?");
            typingArea.setText(String.valueOf(input));
            while (i == 0) {
                try 
                {
                    Thread.sleep(10);
                } 
                catch(InterruptedException e)
                { 
    
                }
        
                }
            i = 0;
            input = Integer.parseInt(typingArea.getText());
            if (input < 0) {
            Area.setText("Negative amounts of energy are impossible");
            try 
            {
                Thread.sleep(1000);
            } 
            catch(InterruptedException e)
            { 

            }
            }
            else {
                valid = 1;
            }}
}}

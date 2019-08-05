package app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame{
    static JTextField Input_Dis = new JTextField();
    static JTextField Output_Dis = new JTextField();

    static JTextArea Area = new JTextArea();
    static JTextField typing = new JTextField();
    static JButton confirm = new JButton();
    static JButton pause = new JButton();

    static JTextArea showInput = new JTextArea();
    static JTextArea showOutput = new JTextArea();

    static JButton Input_100 = new JButton();
    static JButton Input_1000 = new JButton();
    static JButton Input_10000 = new JButton();

    static JButton Input_n100 = new JButton();
    static JButton Input_n1000 = new JButton();
    static JButton Input_n10000 = new JButton();

    static JButton Output_100 = new JButton();
    static JButton Output_1000 = new JButton();
    static JButton Output_10000 = new JButton();
    
    static JButton Output_n100 = new JButton();
    static JButton Output_n1000 = new JButton();
    static JButton Output_n10000 = new JButton();

    static int xoff = 0;
    static int yoff = 0;
    static int xoffset = 232 + xoff;
    static int changeinput = 0;
    static int changeoutput = 0;

    public GUI() {
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 600;
        int frameHeight = 600;
        setSize(frameWidth, frameHeight);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getSize().width) / 2;
        int y = (dim.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Reactor");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        
        Input_Dis.setBounds(50 + xoff,50 + yoff,198,50);
        Input_Dis.setEditable(false);
        cp.add(Input_Dis);

        Output_Dis.setBounds(50 + xoffset,50 + yoff,198,50);
        Output_Dis.setEditable(false);
        cp.add(Output_Dis);

        typing.setBounds(150 + xoff, 250 +yoff, 100,50);
        typing.setFont(Fonts.get_middleFont());
        cp.add(typing);

        Area.setBounds(50 + xoff, 350 + yoff, 400, 200);
        Area.setFont(Fonts.get_middleFont());
        Area.setEditable(false);
        cp.add(Area);

        pause.setBounds(300 + xoff, 255 + yoff, 100 ,50);
        pause.setText("Pause");
        pause.setFont(Fonts.get_middleFont());
        pause.setVisible(false);
        pause.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                pause_ActionPerformed(evt);
            }
        });
        cp.add(pause);

        confirm.setBounds(300 + xoff, 250 + yoff, 100, 50);
        confirm.setText("Confirm");
        confirm.setFont(Fonts.get_middleFont());
        confirm.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                confirm_ActionPerformed(evt);
            }
        });
        cp.add(confirm);

        showInput.setBounds(50 + xoff, 0 + yoff, 198,15);
        showInput.setText("Input");
        cp.add(showInput);

        showOutput.setBounds(50 + xoffset, 0 + yoff, 198,15);
        showOutput.setText("Output");
        cp.add(showOutput);

        Input_100.setBounds(50 + xoff,100 + yoff,66,50);
        Input_100.setText("+100");
        
        Input_100.setFont(Fonts.get_bigFont());
        Input_100.setMargin(new Insets(2,2,2,2));
        Input_100.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Input_100_ActionPerformed(evt);
            }
        });
        cp.add(Input_100);
        Input_1000.setBounds(116 + xoff,100 + yoff,66,50);
        Input_1000.setText("+1000");
        Input_1000.setFont(Fonts.get_middleFont());
        Input_1000.setMargin(new Insets(2,2,2,2));
        Input_1000.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Input_1000_ActionPerformed(evt);
            }
        });
        cp.add(Input_1000);
        Input_10000.setBounds(182 + xoff,100 + yoff,66,50);
        Input_10000.setText("+10000");
        Input_10000.setFont(Fonts.get_smallFont());
        Input_10000.setMargin(new Insets(2,2,2,2));
        Input_10000.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Input_10000_ActionPerformed(evt);
            }
        });
        cp.add(Input_10000);

        Input_n100.setBounds(50 + xoff,150 + yoff,66,50);
        Input_n100.setText("-100");
        Input_n100.setFont(Fonts.get_bigFont());
        Input_n100.setMargin(new Insets(2,2,2,2));
        Input_n100.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Input_n100_ActionPerformed(evt);
            }
        });
        cp.add(Input_n100);
        Input_n1000.setBounds(116 + xoff,150 + yoff,66,50);
        Input_n1000.setText("-1000");
        Input_n1000.setFont(Fonts.get_middleFont());
        Input_n1000.setMargin(new Insets(2,2,2,2));
        Input_n1000.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Input_n1000_ActionPerformed(evt);
            }
        });
        cp.add(Input_n1000);
        Input_n10000.setBounds(182 + xoff,150 + yoff,66,50);
        Input_n10000.setText("-10000");
        Input_n10000.setFont(Fonts.get_smallFont());
        Input_n10000.setMargin(new Insets(2,2,2,2));
        Input_n10000.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Input_n10000_ActionPerformed(evt);
            }
        });
        cp.add(Input_n10000);
        setVisible(true);

        Output_100.setBounds(50 + xoffset,100 + yoff,66,50);
        Output_100.setText("+100");
        Output_100.setFont(Fonts.get_bigFont());
        Output_100.setMargin(new Insets(2,2,2,2));
        Output_100.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Output_100_ActionPerformed(evt);
            }
        });
        cp.add(Output_100);
        Output_1000.setBounds(116 + xoffset,100 + yoff,66,50);
        Output_1000.setText("+1000");
        Output_1000.setFont(Fonts.get_middleFont());
        Output_1000.setMargin(new Insets(2,2,2,2));
        Output_1000.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Output_1000_ActionPerformed(evt);
            }
        });
        cp.add(Output_1000);
        Output_10000.setBounds(182 + xoffset,100 + yoff,66,50);
        Output_10000.setText("+10000");
        Output_10000.setFont(Fonts.get_smallFont());
        Output_10000.setMargin(new Insets(2,2,2,2));
        Output_10000.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Output_10000_ActionPerformed(evt);
            }
        });
        cp.add(Output_10000);

        Output_n100.setBounds(50 + xoffset,150 + yoff,66,50);
        Output_n100.setText("-100");
        Output_n100.setFont(Fonts.get_bigFont());
        Output_n100.setMargin(new Insets(2,2,2,2));
        Output_n100.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Output_n100_ActionPerformed(evt);
            }
        });
        cp.add(Output_n100);
        Output_n1000.setBounds(116 + xoffset,150 + yoff,66,50);
        Output_n1000.setText("-1000");
        Output_n1000.setFont(Fonts.get_middleFont());
        Output_n1000.setMargin(new Insets(2,2,2,2));
        Output_n1000.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Output_n1000_ActionPerformed(evt);
            }
        });
        cp.add(Output_n1000);
        Output_n10000.setBounds(182 + xoffset,150 + yoff,66,50);
        Output_n10000.setText("-10000");
        Output_n10000.setFont(Fonts.get_smallFont());
        Output_n10000.setMargin(new Insets(2,2,2,2));
        Output_n10000.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent evt) { 
                Output_n10000_ActionPerformed(evt);
            }
        });
        cp.add(Output_n10000);
        setVisible(true);
        
        
        
    }

    public static void setText(String temp) {
        Area.setText(temp);
    }

    public static String getText() {
        return typing.getText();
    }

    public static void changeState() {
        confirm.setVisible(false);
        typing.setVisible(false);
        Output_Dis.setText(String.valueOf(Calcs.getOutput()));
        Input_Dis.setText(String.valueOf(Calcs.getInput()));
        pause.setVisible(true);
    }

    public void confirm_ActionPerformed(ActionEvent evt) {
        Calcs.seti(1);
        typing.requestFocusInWindow();
        } 

    public void pause_ActionPerformed(ActionEvent evt) {
        if(Calcs.getPause() == 0) {
            Calcs.setPause(1);
        }
        else {
            Calcs.setPause(0);
        }
        } 

    public static void update() {
        if ( 0 > Calcs.getOutput() + changeoutput) {
            Calcs.setOutput(0);
        }
        else {
            Calcs.setOutput(Calcs.getOutput() + changeoutput);
        }
        if ( 0 > Calcs.getInput() + changeinput) {
            Calcs.setInput(0);
        }
        else {
            Calcs.setInput(Calcs.getInput() + changeinput);
        }
        
        changeoutput = 0;
        changeinput = 0;
        Input_Dis.setText(String.valueOf(Calcs.getInput()));
        Output_Dis.setText(String.valueOf(Calcs.getOutput()));

    }
    public void Input_100_ActionPerformed(ActionEvent evt) {
        changeinput += 100;
        } 
    public void Input_1000_ActionPerformed(ActionEvent evt) {
        changeinput += 1000;
        } 
    public void Input_10000_ActionPerformed(ActionEvent evt) {
        changeinput += 10000;
        } 
    public void Input_n100_ActionPerformed(ActionEvent evt) {
        changeinput -= 100;
        } 
    public void Input_n1000_ActionPerformed(ActionEvent evt) {
        changeinput -= 1000;
        } 
    public void Input_n10000_ActionPerformed(ActionEvent evt) {
        changeinput -= 10000;
        } 
    public void Output_100_ActionPerformed(ActionEvent evt) {
        changeoutput += 100;
        } 
    public void Output_1000_ActionPerformed(ActionEvent evt) {
        changeoutput += 1000;
        } 
    public void Output_10000_ActionPerformed(ActionEvent evt) {
        changeoutput += 10000;
        } 
    public void Output_n100_ActionPerformed(ActionEvent evt) {
        changeoutput -= 100;
        } 
    public void Output_n1000_ActionPerformed(ActionEvent evt) {
        changeoutput -= 1000;
        } 
    public void Output_n10000_ActionPerformed(ActionEvent evt) {
        changeoutput -= 10000;
        } 
}
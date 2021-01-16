import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel ageLabel, weightLabel, heightLabel, genderLabel, sportLabel, result;
    private static JTextField ageText, weightText, heightText;
    private static JCheckBox maleBox, femaleBox, yesBox, noBox;
    private static JButton button, refresh_button;
    private static int calorie_needs;

    private static void gui()
    {
        panel = new JPanel();
        frame = new JFrame("Calorie Needs Calculator");

        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        ageLabel = new JLabel("Age");
        ageLabel.setBounds(10, 20, 80, 25);
        panel.add(ageLabel);

        ageText = new JTextField(20);
        ageText.setBounds(140, 20, 100, 25);
        panel.add(ageText);

        weightLabel = new JLabel("Weight (kg)");
        weightLabel.setBounds(10, 50, 80, 25);
        panel.add(weightLabel);

        weightText = new JTextField(20);
        weightText.setBounds(140, 50, 100, 25);
        panel.add(weightText);

        heightLabel = new JLabel("Height (cm)");
        heightLabel.setBounds(10, 80, 80, 25);
        panel.add(heightLabel);

        heightText = new JTextField(20);
        heightText.setBounds(140, 80, 100, 25);
        panel.add(heightText);

        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(10, 120, 80, 25);
        panel.add(genderLabel);

        maleBox = new JCheckBox("Male");
        maleBox.setBounds(140, 120, 80, 25);
        panel.add(maleBox);

        femaleBox = new JCheckBox("Female");
        femaleBox.setBounds(220, 120, 80, 25);
        panel.add(femaleBox);

        sportLabel = new JLabel("Do you do exercises");
        sportLabel.setBounds(10, 150, 150, 25);
        panel.add(sportLabel);

        yesBox = new JCheckBox("Yes");
        yesBox.setBounds(140, 150, 60, 25);
        panel.add(yesBox);

        noBox = new JCheckBox("no");
        noBox.setBounds(220, 150, 80, 25);
        panel.add(noBox);

        button = new JButton("Calculate");
        button.setBounds(10, 250, 120, 25);
        button.addActionListener(new Gui());
        panel.add(button);

        refresh_button = new JButton("Clean");
        refresh_button.setBounds(10, 320, 120, 25);
        refresh_button.addActionListener(new Gui());
        panel.add(refresh_button);

        result = new JLabel();
        result.setBounds(10, 285, 200, 25);
        panel.add(result);

        frame.setVisible(true);
    }

    private static void setValues()
    {
        int age = Integer.parseInt(ageText.getText());
        double weight = Double.parseDouble(weightText.getText());
        double height = Double.parseDouble(heightText.getText());
        boolean gender = maleBox.isSelected();
        double sport;
        if (yesBox.isSelected())
            sport = 1.375;
        else
            sport = 1.2;

        //Calculation cal = new Calculation();
        calorie_needs =(int) Calculation.calculateCalorie(age, weight, height, gender, sport);

    }
    private static boolean error()
    {
        if(ageText.getText().equals(""))
            return true;

        if(weightText.getText().equals(""))
            return true;

        if(heightText.getText().equals(""))
            return true;

        if((maleBox.isSelected() == true && femaleBox.isSelected() == true) || (maleBox.isSelected() == false && femaleBox.isSelected() == false))
            return true;

        if((yesBox.isSelected() == true && noBox.isSelected() == true) || (yesBox.isSelected() == false && noBox.isSelected() == false))
            return true;

        return false;
    }

    public static void unitTest () throws Exception {
        //Calculation test = new Calculation();
        if(-1 == Calculation.calculateCalorie(1,20.0,100.0,true,1.0)) {
            throw new Exception("Calculate method is not working !");
        }

        if(-1 == Calculation.calculateCalorie(1,20.0,100.0,false,1.0)) {
            throw new Exception("Calculate method is not working !");
        }

        if(false == error() && ageText.getText().equals(""))
            throw new Exception("error control method is not working !");

        if(false == error() && weightText.getText().equals(""))
            throw new Exception("error control method is not working for weight !");

        if(false == error() && heightText.getText().equals(""))
            throw new Exception("error control method is not working for height !");

        if(false == error() && (maleBox.isSelected() == true && femaleBox.isSelected() == true))
            throw new Exception("error control method is not working for gender ");

        if(false == error() && (maleBox.isSelected() == false && femaleBox.isSelected() == false))
            throw new Exception("error control method is not working for gender ");

        if(false == error() && (yesBox.isSelected() == true && noBox.isSelected() == true))
            throw new Exception("error control method is not working for sport ");

        if(false == error() && (yesBox.isSelected() == false && noBox.isSelected() == false))
            throw new Exception("error control method is not working for sport ");



    }

    public static void run() {
        gui();
    }


    @Override
    public  void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == refresh_button) {
            frame.dispose();
            gui();
        }
        else if(!error()) {
            setValues();
            result.setText(String.valueOf(calorie_needs) + " calories per day");

        }
        else
            result.setText("Wrong choice ! !");

    }

}


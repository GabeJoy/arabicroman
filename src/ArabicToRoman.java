//import com.sun.tools.javac.comp.Flow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArabicToRoman extends JFrame {
    private final JTextField inputArabic;

    public ArabicToRoman(){

        super("Arabic");
        setLayout(new FlowLayout());

        inputArabic = new JTextField(10);
        inputArabic.setEditable(true);

        add(inputArabic);

        ArabicHandler handler = new ArabicHandler();
        inputArabic.addActionListener(handler);
    }

    //private inner class for event handling
    private class ArabicHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            int arabicInput;
            String romanOutput;
            if (event.getSource() == inputArabic){
                arabicInput = Integer.parseInt(event.getActionCommand());
                romanOutput = ArabicToRomanTranslate(arabicInput);
                System.out.println(arabicInput + " arabic input");
                System.out.println(romanOutput);
            }
        }
    }

    //I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000
    public String ArabicToRomanTranslate(int arabicInput){
        String translation = "";
        StringBuilder concat = new StringBuilder();

        int mod = 0;
        while (arabicInput != 0){
            if (arabicInput >= 1000){
                mod = arabicInput/1000;
                translation = add_digit("M", translation, mod);
                arabicInput = arabicInput%1000;
            } else if (arabicInput >= 500){
                if (arabicInput < 900){
                    mod = arabicInput/500;
                    translation = add_digit("D",translation, mod);
                    arabicInput = arabicInput%500;
                } else {
                    mod = arabicInput/500;
                    translation = subtract_digit("C", "M", translation, mod);
                    arabicInput = arabicInput%100;
                }
            } else if (arabicInput >= 100){
                mod = arabicInput/100;
                if (arabicInput < 400) {
                    translation = add_digit("C", translation, mod);
                    arabicInput = arabicInput%100;
                } else {
                    translation = subtract_digit("C", "D", translation, mod);
                    arabicInput = arabicInput%100;
                }
            } else if (arabicInput >= 50){
                mod = arabicInput/50;
                if (arabicInput < 90){
                    translation = add_digit("L", translation, mod);
                    arabicInput = arabicInput%50;
                } else {
                    translation = subtract_digit("X", "C", translation, mod);
                    arabicInput = arabicInput%10;
                }
            } else if (arabicInput >= 10){
                mod = arabicInput/10;
                if (arabicInput < 40){
                    translation = add_digit("X", translation, mod);
                    arabicInput = arabicInput%10;
                } else {
                    translation = subtract_digit("X", "L", translation, mod);
                    arabicInput = arabicInput%10;
                }
            } else if (arabicInput >= 5){
                mod = arabicInput/5;
                if (arabicInput < 9){
                    translation = add_digit("V", translation, mod);
                    arabicInput = arabicInput%5;
                } else {
                    translation = subtract_digit("I", "X", translation, mod);
                    arabicInput = 0;
                }
            } else if (arabicInput >= 1){
                if (arabicInput < 4){
                    translation = add_digit("I", translation, arabicInput);
                    arabicInput = 0;
                } else {
                    translation = subtract_digit("I", "V", translation, arabicInput);
                    arabicInput = 0;
                }
            }
        }
        return translation;
    }

    private String add_digit(String toAdd, String curr, int mult){
        for (int i = 0; i < mult; i++){
            curr = curr.concat(toAdd);
        }
        return curr;
    }

    private  String subtract_digit(String toAdd1, String toAdd2, String curr, int mult){
        String before = toAdd1 + toAdd2;

        before = curr.concat(before);
        return before;
    }
}

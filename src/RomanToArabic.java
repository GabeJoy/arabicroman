import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RomanToArabic extends JFrame {
        private final JTextField inputRoman;

        public RomanToArabic() {

            super("Roman");
            setLayout(new FlowLayout());

            inputRoman = new JTextField(10);
            inputRoman.setEditable(true);

            add(inputRoman);

            ArabicHandler handler = new ArabicHandler();
            inputRoman.addActionListener(handler);
        }

        //private inner class for event handling
        private class ArabicHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                String romanInput;
                int arabicOutput;
                if (event.getSource() == inputRoman) {
                    romanInput = event.getActionCommand();
                    arabicOutput = RomanToArabicTranslate(romanInput);
                    System.out.println(romanInput + " roman input");
                    System.out.println(arabicOutput);
                }
            }
        }

        public int RomanToArabicTranslate(String romanInputStr){
            romanInputStr = romanInputStr.toUpperCase();
            char[] romanInput = romanInputStr.toCharArray();

            int score;
            int forward_score;
            int sum = 0;

            for (int i = 0; i < romanInput.length; i++){
                score = score(romanInput[i]);
                if (i < romanInput.length - 1) {
                    forward_score = score(romanInput[i + 1]);
                    if (forward_score > score){
                        sum = sum + forward_score - score;
                        i++;
                    } else {
                        sum = sum + score;
                    }
                } else {
                    sum = sum + score;
                    i++;
                }
            }

            return sum;
        }

        //I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000
        private int score(char numeral){
            if (numeral == 'I'){
                return 1;
            } else if (numeral == 'V'){
                return 5;
            } else if (numeral == 'X'){
                return 10;
            } else if (numeral == 'L'){
                return 50;
            } else if (numeral == 'C'){
                return 100;
            } else if (numeral == 'D'){
                return 500;
            } else{
                return 1000;
            }
        }

        public String validate(String toValidate){
            int arabic_translation = this.RomanToArabicTranslate(toValidate);
            ArabicToRoman arabic_obj = new ArabicToRoman();
            return arabic_obj.ArabicToRomanTranslate(arabic_translation);
        }
    }

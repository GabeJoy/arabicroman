import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BothDirections extends JFrame implements KeyListener{
    private final JTextArea arabic_field;
    private final JTextArea roman_field;
    private String arabic_str = "";
    private String roman_str = "";

    public BothDirections() {
        super("Arabic <-> Roman");
        setLayout(new FlowLayout());

        arabic_field = new JTextArea();
        arabic_field.setEditable(true);
        arabic_field.setPreferredSize(new Dimension(150, 20));
        add(arabic_field);

        roman_field = new JTextArea();
        roman_field.setEditable(true);
        roman_field.setPreferredSize(new Dimension(150, 20));
        add(roman_field);

        arabic_field.addKeyListener(this);
        roman_field.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent typed){}

    @Override
    public void keyReleased(KeyEvent released){
        if (released.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            setTextArea(released);
        }
    }

    @Override
    public void keyPressed(KeyEvent pressed){
        if (!pressed.isActionKey()){
            setTextArea(pressed);
        }
    }

    private void setTextArea(KeyEvent toSet){
        String just_typed = KeyEvent.getKeyText(toSet.getKeyCode());

        if (toSet.getSource() == arabic_field){
            roman_str = "";
            if (toSet.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                arabic_str = arabic_field.getText().concat(just_typed);
                ArabicToRoman arabic_obj = new ArabicToRoman();
                int arabicInput = Integer.parseInt(arabic_str);
                String romanOutput = arabic_obj.ArabicToRomanTranslate(arabicInput);
                roman_field.setText(romanOutput);
            } else if (toSet.getKeyCode() == KeyEvent.VK_BACK_SPACE && !isEmpty(arabic_field)){
                arabic_str = arabic_field.getText();
                ArabicToRoman arabic_obj = new ArabicToRoman();
                int arabicInput = Integer.parseInt(arabic_str);
                String romanOutput = arabic_obj.ArabicToRomanTranslate(arabicInput);
                roman_field.setText(romanOutput);
            }
        } else if (toSet.getSource() == roman_field){
            arabic_str = "";
            if (toSet.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                roman_str = roman_field.getText().concat(just_typed);
                RomanToArabic roman_obj = new RomanToArabic();
                roman_str = roman_obj.validate(roman_str);
                int arabicOutput = roman_obj.RomanToArabicTranslate(roman_str);
                arabic_field.setText(String.valueOf(arabicOutput));
            } else if (toSet.getKeyCode() == KeyEvent.VK_BACK_SPACE && !isEmpty(roman_field)){
                roman_str = roman_field.getText();
                RomanToArabic roman_obj = new RomanToArabic();
                roman_str = roman_obj.validate(roman_str);
                int arabicOutput = roman_obj.RomanToArabicTranslate(roman_str);
                arabic_field.setText(String.valueOf(arabicOutput));
            }
        }
    }

    private boolean isEmpty(JTextArea field){
        return (field.getText().equals(""));
    }
}

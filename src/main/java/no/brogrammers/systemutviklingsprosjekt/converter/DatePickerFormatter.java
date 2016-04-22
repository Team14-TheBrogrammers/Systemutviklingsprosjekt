package no.brogrammers.systemutviklingsprosjekt.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * Created by Knut on 22.04.2016.
 */
public class DatePickerFormatter extends AbstractFormatter {

    private String dateFormat = "yyyy-MM-dd";
    private SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(dateFormat);

    @Override
    public Object stringToValue(String text) throws ParseException {
        //Return the text as object by letting the simpleDateFormat parse it
        return simpleDateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        String output = "";
        if(value != null) {
            Calendar calendar = (Calendar) value;
            output = simpleDateFormatter.format(calendar.getTime());
        }
        return output;
    }
}

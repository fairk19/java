package resourceSystem.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import utils.LoggerFactory;
import utils.ReflectionHelper;

import java.util.logging.Logger;

/**
 * Created by alexandr on 29.01.14.
 */
public class SaxHandler extends DefaultHandler {

    private static String CLASSNAME = "class";
    private String element = null;
    private Object object = null;
    private Logger loggerResourceSystem = LoggerFactory.getLogger("LoggerResourceSystem", "./log/resourceSystem-log.txt");

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName != CLASSNAME){
            element = qName;
        }
        else{
            String className = attributes.getValue(0);
            loggerResourceSystem.info("Class name: " + className);
            object = ReflectionHelper.createIntance(className);
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        element = null;
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if(element != null){
            String value = new String(ch, start, length);
            loggerResourceSystem.info(element + " = " + value);
            ReflectionHelper.setFieldValue(object, element, value);
        }
    }

    public Object getObject(){
        return object;
    }
}

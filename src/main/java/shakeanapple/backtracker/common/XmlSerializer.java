package shakeanapple.backtracker.common;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Block;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XmlSerializer {
    public static void serializeToXML (Block block, String path) throws IOException, JAXBException {
        FileOutputStream fos = new FileOutputStream(path);
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(fos, block);
        fos.close();
    }

    public static Block deserializeFromXML(String path) throws IOException, JAXBException {
        FileInputStream fis = new FileInputStream(path);
        XmlMapper xmlMapper = new XmlMapper();
        Block value = xmlMapper.readValue(fis, Block.class);
        fis.close();
        return value;
    }
}

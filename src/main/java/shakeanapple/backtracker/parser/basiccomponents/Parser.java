package shakeanapple.backtracker.parser.basiccomponents;

import shakeanapple.backtracker.common.XmlSerializer;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.BlockDefinition;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Block;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Parser {
    public BlockDefinition parse(String xmlFilePath) throws IOException, JAXBException {
        Block block = XmlSerializer.deserializeFromXML(xmlFilePath);
        return block.translate();
    }
}

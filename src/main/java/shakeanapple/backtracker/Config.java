package shakeanapple.backtracker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class Config {

    private String formula;
    private String diagramPath;
    private String cxPath;

    public Config() throws IOException {
        this.readPropValues();
    }

    public String getFormula() {
        return this.formula;
    }

    public String getDiagramPath() {
        return this.diagramPath;
    }

    public String getCxPath() {
        return this.cxPath;
    }

    private void readPropValues() throws IOException {
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());

            // get the property value and print it out
            this.formula = prop.getProperty("formula");
            this.diagramPath = prop.getProperty("diagram");
            this.cxPath = prop.getProperty("counterexample");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }
}

package shakeanapple.backtracker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class Config {

    private static Config instance;

    private String formula;
    private String diagramPath;
    private String cxPath;

    private boolean useFullCx;
    private boolean useConfig;

    private Config() throws IOException {
        this.readPropValues();
    }

    public static Config instance() throws IOException {
        if (instance == null){
            instance = new Config();
        }
        return instance;
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

    public boolean useConfig() {
        return this.useConfig;
    }

    public boolean useFullCx() {
        return this.useFullCx;
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

            // get the property value and print it out
            this.useConfig = prop.getProperty("useConfig").toLowerCase().trim().equals("true");
            this.formula = prop.getProperty("formula");
            this.diagramPath = prop.getProperty("diagram");
            this.cxPath = prop.getProperty("counterexample");
            this.useFullCx = prop.getProperty("useFullCx") != null && prop.getProperty("useFullCx").toLowerCase().trim().equals("true");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }
}

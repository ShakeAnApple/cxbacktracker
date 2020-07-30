package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation;

import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvStringModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class NusmvRunner {
    private InputStream inputStream;
    private InputStream errorStream;

    private String modelPath;

    public NusmvRunner(String modelPath){
        this.modelPath = modelPath;
    }

    public NusmvRunner(NusmvStringModel stringModel){
        this.modelPath = this.prepareModel(stringModel);
    }

    private String prepareModel(NusmvStringModel stringModel) {
        File file = new File("model.smv");
        try {
            FileWriter writer = new FileWriter(file.getAbsolutePath());
            writer.write(stringModel.getString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(Arrays.toString(e.getStackTrace()));
        }
        return file.getAbsolutePath();
    }

    public void run(boolean throwExceptionOnNusmvErrors, String... args) throws InterruptedException {
        try {
            Process process = new ProcessBuilder("nusmv", "-dcx", "-bmc", this.modelPath).start();
            this.inputStream = process.getInputStream();
            this.errorStream = process.getErrorStream();

            //process.waitFor();
//            String errors = new String(this.errorStream.readAllBytes());
//            if (throwExceptionOnNusmvErrors && !errors.isEmpty()) {
//                System.out.println(errors);
//                //throw new RuntimeException(errors);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public void reset() {
        this.inputStream = null;
        this.errorStream = null;
    }
}

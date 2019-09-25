package shakeanapple.backtracker.ui.infrasructure;

import javafx.beans.NamedArg;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FxmlListCellFactory implements Callback<ListView<Object>, ListCell<Object>> {

    private final URL fxmlSource;

    public FxmlListCellFactory(@NamedArg("fxmlSource") String fxmlSource) throws MalformedURLException {
        this.fxmlSource = new URL(fxmlSource) ;
    }
    @Override
    public ListCell<Object> call(ListView<Object> objectListView) {
        return new ListCell<Object>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(fxmlSource);
                        loader.getNamespace().put("item", item);
                        setGraphic(loader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                        setGraphic(null);
                    }
                }
            }
        };
    }
}

package application;

import java.awt.Desktop;
import java.net.URI;
import java.util.Map;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class AppController {

    @FXML Label targetLabel1;
    @FXML Label targetLabel2;
    @FXML Label targetLabel3;

    @FXML TextArea targetTextArea;

    @FXML AnchorPane anchorPaneText;
    @FXML AnchorPane anchorPaneFont;
    @FXML AnchorPane anchorPaneGraphic;


    private static final String lineSeparetor = System.getProperty("line.separator");
    private final Map<String, String> mapOfStyle = new TreeMap<>();

    @FXML
    private void clear(ActionEvent event) {

        // コンボボックスを未選択状態にする。
        AnchorPane[] anchorPanes = {
                anchorPaneText, anchorPaneFont, anchorPaneGraphic
        };

        for (AnchorPane anchorPane : anchorPanes) {
            ObservableList<Node> nodeList = anchorPane.getChildren();
            for (Node node : nodeList) {
                if (node instanceof ComboBox) {
                    @SuppressWarnings("unchecked")
                    ComboBox<String> comboBox = (ComboBox<String>)node;
                    comboBox.setValue(null);
                }
            }
        }

        // スタイル設定クリアする。
        mapOfStyle.clear();

        targetLabel1.setStyle(null);
        targetLabel2.setStyle(null);
        targetLabel3.setStyle(null);

        targetTextArea.setText(null);

    }

    @FXML
    private void changeStyle(ActionEvent event) {

        // コンボボックスからスタイルを取得する。
        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = ((ComboBox<String>)event.getSource());

        // マップに追加する。
        mapOfStyle.put(comboBox.getId().substring(8), comboBox.getSelectionModel().getSelectedItem() + ";");

        // スタイルを設定する。
        StringBuilder sb = new StringBuilder();
        for (String key : mapOfStyle.keySet()) {
            sb.append(String.format("%s: %s%s", key, mapOfStyle.get(key), lineSeparetor));
        }

        String style = sb.toString();
        targetLabel1.setStyle(style);
        targetLabel2.setStyle(style);
        targetLabel3.setStyle(style);

        targetTextArea.setText(targetLabel1.getStyle());

    }

    @FXML
    public void showWebSite(ActionEvent event) throws Exception {
        Desktop.getDesktop().browse(new URI("http://java-beginner.com/"));
    }

}

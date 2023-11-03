package com.group_srinivasan.scrumui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import java.util.List;



public class SelectedItemsController {

    @FXML
    private VBox selectedItemsView;

    @FXML
    private ListView<String> selectedItemsListView;

    @FXML
    public void setSelectedItems(List<UserStoriesData.DataItem> selectedItems) {
        System.out.println(selectedItems);
        for (UserStoriesData.DataItem item : selectedItems) {
            selectedItemsListView.getItems().add(item.getId() + " - " + item.getBvd());
        }
    }


}

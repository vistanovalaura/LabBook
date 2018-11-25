package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.fxmodels.TaskFxModel;

public class RemoveItemController {

	//private 	ItemDAO itemDao = DAOfactory.INSTANCE.getItemDAO();
		//private ItemFxModel itemModel; 
		private Item item; 
		
	@FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    public RemoveItemController(Item item) {
		this.item = item; 
    //	this.itemModel = new ItemFxModel(item);
	}
    
    @FXML
    void initialize() {
    	yesButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
//				itemDao.deleteItem(itemModel.getItem());
//				itemDao.saveItem(itemModel.getItem());
				yesButton.getScene().getWindow().hide();
			}
		});

		noButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				noButton.getScene().getWindow().hide();
			}
		});
    }
}

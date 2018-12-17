package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.fxmodels.ItemFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ItemDAO;

public class DeleteItemController {

	@FXML
	private Button yesButton;

	@FXML
	private Button noButton;

	private ItemDAO itemDao = DAOfactory.INSTANCE.getItemDAO();
	private ItemFxModel itemModel;

	public DeleteItemController(Item item) {
		this.itemModel = new ItemFxModel(item);
	}

	@FXML
	void initialize() {

		yesButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override

			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					itemDao.deleteItem(itemModel.getItem());
					yesButton.getScene().getWindow().hide();
				}

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

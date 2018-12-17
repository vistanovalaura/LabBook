package sk.upjs.paz1c.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Laboratory;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.fxmodels.ItemFxModel;
import sk.upjs.paz1c.fxmodels.TaskFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ItemDAO;
import sk.upjs.paz1c.persistent.LaboratoryDAO;

public class AddItemInTaskController {

	@FXML
	private Button addButton;

	@FXML
	private ComboBox<Item> selectItemComboBox;

	private ItemFxModel selectedItem;
	private ObservableList<Item> itemModel;
	private LaboratoryDAO laboratoryDao;
	private ItemDAO itemDao;
	private Laboratory laboratory;
	private TaskFxModel taskModel;

	public AddItemInTaskController(Laboratory laboratory, Task task) {
		this.taskModel = new TaskFxModel(task);
		this.laboratory = laboratory;
		taskModel = new TaskFxModel(task);
		selectedItem = new ItemFxModel();
		laboratoryDao = DAOfactory.INSTANCE.getLaboratoryDAO();
		itemDao = DAOfactory.INSTANCE.getItemDAO();
	}

	@FXML
	void initialize() {
		itemModel = FXCollections.observableArrayList(itemDao.getAll());
		selectItemComboBox.setItems(itemModel);
		selectItemComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {

			@Override
			public void changed(ObservableValue<? extends Item> observable, Item oldValue, Item newValue) {
				if (newValue != null) {
					selectedItem.setItem(newValue);
				}
			}
		});

		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				List<Item> items;
				if (taskModel.getItems() != null) {
					items = taskModel.getItems();
				} else {
					items = new ArrayList<>();
				}
				items.add(selectedItem.getItem());
				addButton.getScene().getWindow().hide();
			}
		});

	}

	private List<Item> getItems() {
		List<Item> items = new ArrayList<>();
		List<Item> allItems = itemDao.getAll();
		for (Item i : allItems) {
			if (i.getLaboratory().equals(laboratory)) {
				items.add(i);
			}
		}
		return items;
	}

}

package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sk.upjs.paz1c.entities.Laboratory;
import sk.upjs.paz1c.entities.Note;
import sk.upjs.paz1c.fxmodels.LaboratoryFxModel;
import sk.upjs.paz1c.fxmodels.NoteFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.LaboratoryDAO;

public class DeleteLaboratoryController {

	private LaboratoryDAO laboratoryDao = DAOfactory.INSTANCE.getLaboratoryDAO();
	private LaboratoryFxModel laboratoryModel;
	private Laboratory laboratory;

	@FXML
	private Button yesButton;

	@FXML
	private Button noButton;

	public DeleteLaboratoryController(Laboratory laboratory) {
		this.laboratory = laboratory;
		this.laboratoryModel = new LaboratoryFxModel(laboratory);
	}

	void initialize() {

		yesButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				laboratoryDao.deleteLaboratory(laboratoryModel.getLaboratory());
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

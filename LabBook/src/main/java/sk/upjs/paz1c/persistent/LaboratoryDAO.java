package sk.upjs.paz1c.persistent;

import java.util.List;

import sk.upjs.paz1c.entities.Laboratory;

public interface LaboratoryDAO {

	// pridanie admina do databazy
	void addLaboratory(Laboratory laboratory);

	// vrati vsetkych adminov z databazy
	List<Laboratory> getAll();

	// zmena admina v databaze
	void saveLaboratory(Laboratory laboratory);

	// vymazanie admina z databazy
	void deleteLaboratory(Laboratory laboratory);

}

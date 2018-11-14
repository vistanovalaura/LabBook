package sk.upjs.paz1c.persistent;

import java.util.List;

import sk.upjs.paz1c.entities.Project;

public interface ProjectDAO {
	
	void addProject(Project project); // komentar
	
	List<Project> getAll();

}

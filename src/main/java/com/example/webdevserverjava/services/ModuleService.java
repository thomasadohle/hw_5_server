package com.example.webdevserverjava.services;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpSession;
import com.example.webdevserverjava.model.Course;
import com.example.webdevserverjava.model.Module;
import com.example.webdevserverjava.model.User;

@RestController
public class ModuleService {
	final String origins = "https://pacific-lake-81602.herokuapp.com";
	
	List<Module> modules = new ArrayList<Module>();
	List<Integer> idList = new ArrayList<Integer>();
	
	@PostMapping("/api/courses/{cid}/modules")
	@CrossOrigin(allowCredentials="true")
	public Module createModule(@RequestBody Module module,
			HttpSession session, @PathVariable (value="cid") int courseId) {
//		int id = generateID();
//		module.setId(id);
		module.setcourseId(courseId);
		modules.add(module);
		session.setAttribute("currentModule", module);
		System.out.println(module.toString());
		return module;
	}
	
	@PutMapping("/api/modules/{mid}")
	@CrossOrigin(allowCredentials="true")
	public Module updateModule(@RequestBody Module module, @PathVariable(value="mid") int moduleId) {
		for (Module m : this.modules) {
			if (m.getID() == moduleId) {
				m.setModuleTitle(module.getModuleTitle());
				System.out.println("ModuleTitle successfully updated to " + m.getModuleTitle());
				return m;
			}
		}
		System.out.println("Failed to update Module");
		return module;
	}
	
	@GetMapping("/api/courses/{cid}/modules")
	@CrossOrigin(allowCredentials="true")
	public List<Module> findAllModules(@PathVariable(value="cid") int courseId){
		List<Module> modules = new ArrayList<>();
		for (Module m : this.modules) {
			if (m.getCourseId() == courseId) {
				modules.add(m);
			}
		}
		return modules;
	}
	
	@GetMapping("/api/modules/{mid}")
	@CrossOrigin(allowCredentials="true")
	public Module findModuleById(@PathVariable(value="mid")  int id, HttpSession session) {
		for (Module m : this.modules) {
			if (m.getID() == id) {
				System.out.println("found module: "+ m.toString());
				session.setAttribute("currentModule",m);
				return m;
			}
		}
		System.out.println("could not find module");
		return null;
	}
	
	
	
	@DeleteMapping("/api/modules/{mid}")
	@CrossOrigin(allowCredentials="true")
	public Module deleteModule(@PathVariable(value="mid") int id) {
		for (Module m : this.modules) {
			if (m.getID() == id) {
				modules.remove(m);
				System.out.println("deleted a module");
				return m;
			}
		}
		System.out.println("Could not find module to delete");
		return null;
	}
	
	
	/**
	 * Ensures that all User have unique Id
	 * @return
	 */
	public int generateID () {
		Boolean numberGenerated = false;
		int attempt = 0;
		while (! numberGenerated) {
			Random rand = new Random();
			attempt = rand.nextInt(999) + 1;
			if (! idList.contains(attempt)) {
				numberGenerated = true;
				idList.add(attempt);
			}
		}
		return attempt;
	}
	
}

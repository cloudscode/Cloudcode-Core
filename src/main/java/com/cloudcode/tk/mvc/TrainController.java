package com.cloudcode.tk.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudcode.framework.controller.CrudController;
import com.cloudcode.framework.utils.UUID;
import com.cloudcode.tk.dao.TrainDao;
import com.cloudcode.tk.model.Train;

@Controller
@RequestMapping("/page/trains")
public class TrainController extends CrudController<Train> {

	@Autowired
	private TrainDao trainDao;


	@RequestMapping("/layout")
	public String getTrainPartialPage(ModelMap modelMap) {
		return "/train/layout";
	}

	@RequestMapping("trainslist.json")
	public @ResponseBody
	List<Train> getTrainList() {
		return trainDao.getAll();
	}

	@RequestMapping(value = "/addTrain", method = RequestMethod.POST)
	public @ResponseBody
	void addTrain(@RequestBody Train train) {
		train.setId(UUID.generateUUID());
		trainDao.addTrain(train);
	}

	@RequestMapping(value = "/updateTrain", method = RequestMethod.PUT)
	public @ResponseBody
	void updateTrain(@RequestBody Train train) {
		trainDao.updateTrain(train);
	}

	@RequestMapping(value = "/removeTrain/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	void removeTrain(@PathVariable("id") String id) {
		trainDao.deleteTrainById(id);
	}

	@RequestMapping(value = "/removeAllTrains", method = RequestMethod.DELETE)
	public @ResponseBody
	void removeAllTrains() {
		trainDao.deleteAll();
	}
}

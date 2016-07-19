package com.cloudcode.task.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import com.cloudcode.framework.utils.ExtendUitl;
import com.cloudcode.framework.utils.Json;
import com.cloudcode.task.model.TaskConfig;

public class PlanTask extends TimerTask {
	private TaskConfig taskConfig;

	public PlanTask(TaskConfig taskConfig) {
		this.taskConfig = taskConfig;
	}

	@Override
	public void run() {
		if ("class".equals(taskConfig.getExecType())) {
			String[] formulaArr = taskConfig.getFormula().split("#");
			if (formulaArr.length>1) {
				Map<String, Object> map = new HashMap<String, Object>();
				if (formulaArr.length>2) {
					map= Json.toMap(formulaArr[2]);
				}
				try {
					ExtendUitl.processLoadClass(formulaArr[0], formulaArr[1], map);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
	}
}

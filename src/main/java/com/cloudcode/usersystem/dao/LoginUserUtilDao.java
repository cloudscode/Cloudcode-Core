package com.cloudcode.usersystem.dao;

import org.springframework.stereotype.Service;

import com.cloudcode.organization.model.Organization;

@Service
public class LoginUserUtilDao {
	public String findLoginUserId() {
	/*	Object object = ActionContext.getContext().getSession()
				.get("loginuser");
		if (object != null) {
			HhXtYh hhXtYh = (HhXtYh) object;
			return hhXtYh.getId();
		}*/
		return null;
	}

	public String findLoginUserOrgId() {
		/*Object object = ActionContext.getContext().getSession().get("currOrg");
		if (object != null && !(object instanceof ArrayList)) {
			Organization organization = (Organization) object;
			return organization.getId();
		} else {
			return null;
		}*/return null;
	}

	public Organization findLoginUserOrg() {
		/*Object object = ActionContext.getContext().getSession().get("currOrg");
		if (object != null && !(object instanceof ArrayList)) {
			Organization organization = (Organization) object;
			return organization;
		} else {
			return null;
		}*/return null;
	}

	public Organization queryDataSecurityOrg(String action) {
		/*Object object = ActionContext.getContext().getSession().get("currOrg");
		if (object != null && !(object instanceof ArrayList)) {
			Organization organization = (Organization) object;;
			HhXtYh hhXtYh = findLoginUser();
			List<HhXtCz> hhXtCzList = hhXtYh.getHhXtCzList();
			for (HhXtCz hhXtCz : hhXtCzList) {
				if (action.equals(hhXtCz.getVurl())) {
					if (OperationLevel.BBM.toString().equals(
							hhXtCz.getOperLevel())) {
						organization = organization.getBm();
					} else if (OperationLevel.BJG.toString().equals(
							hhXtCz.getOperLevel())) {
						organization = organization.getJg();
					} else if (OperationLevel.BJT.toString().equals(
							hhXtCz.getOperLevel())) {
						organization = organization.getJt();
					} else if (OperationLevel.BGW.toString().equals(
							hhXtCz.getOperLevel())) {
						organization = findLoginUserOrg();
					}else {
						organization = null;
					}
					return organization;
				}
			}
			return null;
		} else {
			return null;
		}*/return null;
	}
}

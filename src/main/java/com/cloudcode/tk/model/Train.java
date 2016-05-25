package com.cloudcode.tk.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cloudcode.framework.model.BaseModelObject;
import com.cloudcode.tk.ProjectConfig;
@Entity(name = ProjectConfig.NAME+"train")
@Table(name = ProjectConfig.NAME+"_train")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Train extends BaseModelObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6757373231993080044L;
	private String name;
    private Integer speed;
    private Boolean diesel;

    public Train() { }

    public Train(String name, Integer speed, Boolean diesel) {       
        this.name = name;
        this.speed = speed;
        this.diesel = diesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Boolean getDiesel() {
        return diesel;
    }

    public void setDiesel(Boolean diesel) {
        this.diesel = diesel;
    }
}

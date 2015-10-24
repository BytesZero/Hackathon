package com.segmentfault.hackathon.entity;


import java.io.Serializable;

public class FlowerEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 名称、品类、颜色
	private String plant_id;
    private String plant_name, images;

	public FlowerEntity(String plant_id, String plant_name, String images) {
		super();
		this.plant_id = plant_id;
		this.plant_name = plant_name;
		this.images = images;
	}

	public String getPlant_id() {
		return plant_id;
	}

	public void setPlant_id(String plant_id) {
		this.plant_id = plant_id;
	}

	public String getPlant_name() {
		return plant_name;
	}

	public void setPlant_name(String plant_name) {
		this.plant_name = plant_name;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "FlowerEntity [plant_id=" + plant_id + ", plant_name="
				+ plant_name + ", images=" + images + "]";
	}

}

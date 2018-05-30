package beans;

import java.io.Serializable;

public class ItemDataBeans implements Serializable {
	private int id;
	private String name;
	private String detail;
	private int caffeine;
	private int price;
	private String fileName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getCaffeine() {
		return caffeine;
	}

	public void setCaffeine(int caffeine) {
		this.caffeine = caffeine;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}

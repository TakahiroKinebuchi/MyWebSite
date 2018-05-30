package beans;

import java.util.Date;

public class UserDataBeans {
	private int id;
	private String loginId;
	private String name;
	private String address;
	private Date birthDate;
	private String password;
	private String createDate;
	private String updateDate;

	//ログインセッションを保存するためのコンストラクタ
	public UserDataBeans(String loginId, String name) {
		this.loginId = loginId;
		this.name = name;
	}

	//コンストラクタ
	public UserDataBeans() {
		this.loginId = "";
		this.name = "";
		this.address = "";
		this.password = "";
		this.createDate = "";
		this.updateDate = "";
	}

	//全てのデータをセットするコンストラクタ
	public UserDataBeans(int id, String loginId, String name, String address, Date birthDate, String password, String createDate,
			String updateDate) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
		this.address = address;
		this.birthDate = birthDate;
		this.password = password;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getloginId() {
		return loginId;
	}

	public void setloginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
}

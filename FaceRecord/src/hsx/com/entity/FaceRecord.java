package hsx.com.entity;

public class FaceRecord {
 
	private Integer id;
	private String personId;
	private String deviceKey;
	private String type;
	private String ip;
	private String time;
	private String path;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
	public FaceRecord() {
		super();
	}
	
	public FaceRecord(String personId,String deviceKey, String type, String ip, String time, String path) {
		super();
		this.personId = personId;
		this.deviceKey = deviceKey;
		this.type = type;
		this.ip = ip;
		this.time = time;
		this.path = path;
	}


}

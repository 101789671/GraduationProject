package cn.gpms.vo;

/**
 * Switch entity. @author MyEclipse Persistence Tools
 */

public class Switch implements java.io.Serializable {

	// Fields

	private String switchNumber;
	private String switchName;
	private String switchState;

	// Constructors

	/** default constructor */
	public Switch() {
	}

	/** minimal constructor */
	public Switch(String switchNumber) {
		this.switchNumber = switchNumber;
	}

	/** full constructor */
	public Switch(String switchNumber, String switchName, String switchState) {
		this.switchNumber = switchNumber;
		this.switchName = switchName;
		this.switchState = switchState;
	}

	// Property accessors

	public String getSwitchNumber() {
		return this.switchNumber;
	}

	public void setSwitchNumber(String switchNumber) {
		this.switchNumber = switchNumber;
	}

	public String getSwitchName() {
		return this.switchName;
	}

	public void setSwitchName(String switchName) {
		this.switchName = switchName;
	}

	public String getSwitchState() {
		return this.switchState;
	}

	public void setSwitchState(String switchState) {
		this.switchState = switchState;
	}

}
package rafaelalbergaria.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Message type SMS
 * @author Rafael
 *
 */
@Entity
public class MessageSms extends Message {
	
	@Column
	private String phoneNumber;
	
	public MessageSms() {
		super();
	}

	public MessageSms(String message, String phoneNumber) {
		super(message);
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}

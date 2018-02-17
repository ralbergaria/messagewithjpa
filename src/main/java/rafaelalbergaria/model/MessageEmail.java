package rafaelalbergaria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Message type email.
 * @author Rafael
 *
 */
@Entity
public class MessageEmail extends Message {
	@Column
	private String to;
	
	@Column
	private String cc;
	
	@Column
	private String cco;
	
	@Column
	@Lob	
	private byte[] attachment;
	
	public MessageEmail() {
		super();
	}
	public MessageEmail(String message, String to, String cc, String cco, byte[] attachment) {
		super(message);
		this.to = to;
		this.cc = cc;
		this.cco = cco;
		this.attachment = attachment;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getCco() {
		return cco;
	}
	public void setCco(String cco) {
		this.cco = cco;
	}
	public byte[] getAttachment() {
		return attachment;
	}
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

}

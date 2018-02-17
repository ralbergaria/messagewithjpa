package rafaelalbergaria.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Parent entity create with table per class. This is necessary for extendable as more message types.
 * The new types need be mapped below for jackson. 
 * @author Rafael
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "javaclass")
@JsonSubTypes({
    @Type(value = MessageEmail.class),
    @Type(value = MessageSms.class)
})
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Column
	private String message;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSend;
	
	public Message() {
		super();
	}
	
	public Message(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Date getDateSend() {
		return dateSend;
	}
	
	public void setDateSend(Date dateSend) {
		this.dateSend = dateSend;
	}

}

package rafaelalbergaria.service;

import java.util.List;

import rafaelalbergaria.model.Message;

/**
 * Service Layer.
 * @author Rafael
 *
 */
public interface MessageService {
	
	public void sendMessage(Message message);
	public List<Message> getAllMessage();

}	

package rafaelalbergaria.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rafaelalbergaria.model.Message;
import rafaelalbergaria.repository.MessageRepsitory;

/**
 * Implemented service layer.
 * @author Rafael
 *
 */
@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {
	
	  @Autowired
	  private MessageRepsitory repository;
	
	@Override
	@Transactional
	public void sendMessage(Message message) {
		message.setDateSend(new Date());
		repository.save(message);		
	}

	@Override
	public List<Message> getAllMessage() {
		return repository.findAll();
	}

}

package rafaelalbergaria.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rafaelalbergaria.model.Message;
import rafaelalbergaria.service.MessageService;

/**
 * Rest created for send Message and get all message sended.
 * Type exist is MessageEmail and MessageSms, for polymorphism work well that type was mapped with jackson on 
 * Message parent class.
 * @author Rafael
 *
 */
@RestController
public class MessageRestController {

	@Autowired
	MessageService service;

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<?> sendMessage(@RequestBody Message message) {
		try {
			service.sendMessage(message);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getAllMessage", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<Message>> getAllMessage(){
		try {	
			return new ResponseEntity<List<Message>>(service.getAllMessage(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

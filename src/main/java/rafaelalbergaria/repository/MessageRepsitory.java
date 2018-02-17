package rafaelalbergaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rafaelalbergaria.model.Message;

/**
 * JpaRepository was used for data base access.
 * @author Rafael
 *
 */
public interface MessageRepsitory extends JpaRepository<Message, Long> {
	
}

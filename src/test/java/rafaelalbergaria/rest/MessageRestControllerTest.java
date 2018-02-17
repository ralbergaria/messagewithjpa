package rafaelalbergaria.rest;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

import rafaelalbergaria.model.Message;
import rafaelalbergaria.model.MessageEmail;
import rafaelalbergaria.model.MessageSms;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageRestControllerTest {
	@Autowired
    private MockMvc mvc;
	
	@Autowired 
	private ObjectMapper mapper;
	
	@Test
	public void sendAndGetMessage() throws Exception {
		Message message = new MessageEmail("Hi UBS, thanks for your contact! I'm glad for participate that interview of job.", "careers-poland@ubs.com", "r.albergaria85@gmail.com", "teste@teste.com", getPDF());
		String json = mapper.writeValueAsString(message);
		
		 mvc.perform(MockMvcRequestBuilders.post("/sendMessage")
			      .contentType(MediaType.APPLICATION_JSON)
			      .content(json))
			      .andExpect(MockMvcResultMatchers.status().isOk());
		 
		message = new MessageSms("Hi UBS, I hope do you like my project.", "+553199778-5201");
		json = mapper.writeValueAsString(message);
		 
		 mvc.perform(MockMvcRequestBuilders.post("/sendMessage")
			      .contentType(MediaType.APPLICATION_JSON)
			      .content(json))
			      .andExpect(MockMvcResultMatchers.status().isOk());		 
		 
		 mvc.perform(MockMvcRequestBuilders.get("/getAllMessage")
			      .contentType(MediaType.APPLICATION_JSON))
		 		  .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(2)))
			      .andExpect(MockMvcResultMatchers.jsonPath("$[0].message", CoreMatchers.is("Hi UBS, thanks for your contact! I'm glad for participate that interview of job.")))
			      .andExpect(MockMvcResultMatchers.jsonPath("$[0].to", CoreMatchers.is("careers-poland@ubs.com")))
			      .andExpect(MockMvcResultMatchers.jsonPath("$[0].cc", CoreMatchers.is("r.albergaria85@gmail.com")))
			      .andExpect(MockMvcResultMatchers.jsonPath("$[0].cco", CoreMatchers.is("teste@teste.com")))
			      .andExpect(MockMvcResultMatchers.jsonPath("$[0].attachment", CoreMatchers.notNullValue()))
			      .andExpect(MockMvcResultMatchers.jsonPath("$[1].message", CoreMatchers.is("Hi UBS, I hope do you like my project.")))
			      .andExpect(MockMvcResultMatchers.jsonPath("$[1].phoneNumber", CoreMatchers.is("+553199778-5201")));
	}
	
	public byte[] getPDF() throws FileNotFoundException, DocumentException{
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter.getInstance(document, byteArrayOutputStream);
		 
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("Hello UBS!", font);
		 
		document.add(chunk);
		document.close();
		
		return byteArrayOutputStream.toByteArray();
	}
}
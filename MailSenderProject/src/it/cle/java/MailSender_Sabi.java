package it.cle.java;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

//import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
//
//import it.cle.resettami.logging.LoggerHelper;
//import it.cle.resettami.utils.Utils;


public class MailSender_Sabi {
	private Logger logger = Logger.getLogger(MailSender_Sabi.class);	

    public String inviaMail(String mailMittente, String pwd, String host, String smtpAuth, 
    		List<String> destinatari, String testoMail, String oggetto, String pathAllegato) throws Exception{
    	
    	String response = "Si è verificato un errore nell'invio della Mail!";
	

    	
  	
    	final String username = mailMittente;
        final String password = pwd;
        
    Properties props = new Properties();
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", smtpAuth);
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "25");
    
     	
    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
      });

    try {
        Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(username));
      for (String destinatario : destinatari) {
		    message.addRecipient(Message.RecipientType.TO,
		            new InternetAddress(destinatario));
		}
      message.setSubject(oggetto);

      // Create the message part
      BodyPart messageBodyPart = new MimeBodyPart();

      // Now set the actual message
      messageBodyPart.setText(testoMail);

      // Create a multipar message
      Multipart multipart = new MimeMultipart();

      // Set text message part
      multipart.addBodyPart(messageBodyPart);

      // Part two is attachment
      messageBodyPart = new MimeBodyPart();
      String filename = pathAllegato;
      DataSource source = new FileDataSource(filename);
      messageBodyPart.setDataHandler(new DataHandler(source));
      messageBodyPart.setFileName(filename);
      multipart.addBodyPart(messageBodyPart);

      // Send the complete message parts
      message.setContent(multipart);
      
    Transport.send(message);
    response = "La mail è stata inviata correttamente!";
    
    logger.info("La mail è stata inviata correttamente!");

    } catch (Exception e) {
    	logger.error("Si è verificato un errore nel MAIL SENDER : "+e.getMessage());
    }

    return response;	

    	
    }
    

}

package it.cle.java;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

//import org.apache.log4j.Logger;


public class MailSender_ {
	//protected	 Logger logger = Logger.getLogger(MailSender.class);


	public void inviaMail(String nome,String cognome,String destinatarioMail ) {
//	final String username = "smtpuser@clebari.com";
//    final String password = "OutGateway2014.";

		
		final String username = "postmaster@resettami.it";
	    final String password = "Ndb360?";

	    
	    

    Properties props = new Properties();
    
//    props.put("mail.smtp.starttls.enable", "true");
//    props.put("mail.smtp.auth", "true");
//    props.put("mail.smtp.host", "smtp.clebari.com");
//    props.put("mail.smtp.port", "25");

    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", "mail.resettami.it");
    props.put("mail.smtp.port", "25");
    
    
    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
      });

    
    
    
    try {
    	
////		properties.load(new FileInputStream("/WEB-INF/config.properties"));
//	
//    	
//    	String filename2 = "WEB-INF/config.properties";
//		input = new FileInputStream(filename2);
//		if(input==null){
//	            System.out.println("Sorry, unable to find " + filename2);
//		    return;
//		}
//
//		//load a properties file from class path, inside static method
//		properties.load(input);
//		
//		
    	
    	
		
    	
        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress("smtpuser@clebari.com"));
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(destinatarioMail));
        message.setSubject("Produzione");
//        message.setText("Si informa che il PAI dell'Assistito "+nome+" "+ cognome+" è disponibile per la firma presso i Servizi Sociali del Comune."
//            + "\n\n No spam to my email, please!");

        



        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("Si informa che il PAI dell'Assistito "+nome+" "+ cognome+" è disponibile per la firma presso i Servizi Sociali del Comune."
            + "\n\n No spam to my email, please!");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        String filename = "C:\\testMail.pdf";
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        message.setContent(multipart);

        
        
        
        
      Transport.send(message);
        
        
        
        
        
        
        
        

    } catch (MessagingException e) {
    	System.out.println("Si è verificato un errore nel MAIL SENDER : "+e.getMessage());
    }
   
}
}



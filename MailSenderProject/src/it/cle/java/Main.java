package it.cle.java;

import java.util.ArrayList;
import java.util.List;








public class Main {
	public static void main(String[] args)  {
		
//		MailSender mail = new MailSender();
//		
//		mail.inviaMail("leo", "convertini", "convertinileo@gmail.com");
		
		
		
		
		MailSender_Sabi mailSenderSabi = new MailSender_Sabi();
		
		
		String mailMittente="postmaster@resettami.it";
		
		String pwd = "Ndb360?";	
		String host="mail.resettami.it";
		String smtpAuth="true";
		List<String> destinatari=new ArrayList<String>();
		destinatari.add("convertinileo@gmail.com");
		destinatari.add("leo.convertini@clebari.com");
		destinatari.add("sabino.aloia@clebari.com");
		
		
		String testoMail="Testo della MAIL";
		String oggetto="Convocazione UVM";
		String pathAllegato="C:\\testMail.pdf";
		
		
		
		try {
			mailSenderSabi.inviaMail(mailMittente, pwd, host, smtpAuth, destinatari, testoMail, oggetto, pathAllegato);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	      }
	
}
	
	
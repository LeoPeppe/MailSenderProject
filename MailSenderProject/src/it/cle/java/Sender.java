package it.cle.java;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Servlet implementation class Sender
 */
@WebServlet("/Sender")
public class Sender extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Logger logger = Logger.getLogger(Sender.class);
	

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sender() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String mailMittente = "";
		String pwd =  "";
		String host = "";
		String testoMail = "";
		String oggetto ="";
		String pathAllegato = "";
		String smtpAuth = "";
		 List<String> destinatari=new ArrayList<String>();

		JSONObject jsonObject = new JSONObject();
		JSONArray jsDestinatari=  new JSONArray();
		JSONObject jsonParametri = new JSONObject();
		
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		    
		  } catch (Exception e) { /*report an error*/ }
		  logger.info("Json arrivato  "+jb);
		  try {
			jsonObject = new JSONObject(jb.toString());
		    jsDestinatari=  jsonObject.getJSONArray("destinatari");
		    jsonParametri = jsonObject.getJSONObject("parametri");
		   
		   
		   
			 mailMittente = jsonParametri.getString("mailMittente");
			 pwd =  jsonParametri.getString("pwd");
			 host = jsonParametri.getString("host");
			 testoMail = jsonParametri.getString("testoMail");
			 oggetto =jsonParametri.getString("oggetto");
			 pathAllegato = jsonParametri.getString("pathAllegato");
			 smtpAuth = jsonParametri.getString("smtpAuth");
			 
			 for (int i = 0; i < jsDestinatari.length(); i++) {
					destinatari.add(jsDestinatari.getString(i));
				}
		   
		  } catch (JSONException e) {
			  logger.error("Si è verificato un errore : " +e.getMessage());
		    // crash and burn
		    throw new IOException("Error parsing JSON request string :" +e.getMessage());
		  }

		/** per funzionare da frontend
		String mailMittente="postmaster@resettami.it";
//		
		String pwd = "Ndb360?";	
		String host="mail.resettami.it";
		String smtpAuth="true";
		List<String> destinatari=new ArrayList<String>();
		destinatari.add("convertinileo@gmail.com");
		destinatari.add("leo.convertini@clebari.com");
//		destinatari.add("sabino.aloia@clebari.com");
//		destinatari.add(email)
		
		String testoMail="Testo della MAIL";
		String oggetto="Convocazione UVM";
		String pathAllegato="C:\\testMail.pdf";
		
		FINE
//		**/
		
		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		
		MailSender_Sabi mailSenderSabi = new MailSender_Sabi();
		 try {
			 mailSenderSabi.inviaMail(mailMittente, pwd, host, smtpAuth, destinatari, testoMail, oggetto, pathAllegato);
			 
		logger.info("La mail è stata inviata con successo...");
		 } catch (Exception e) {
			 logger.error("Si è verificato un errore : "+e.getMessage());
			}
		 
	}
}

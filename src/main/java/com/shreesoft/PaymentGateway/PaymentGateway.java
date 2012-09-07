package com.shreesoft.PaymentGateway;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;




/**************************************************************************************************************
 * This a generic Payment Gateway that accepts all requirements from a client and forwards the request to the**
 * respective bank's verification site and then completes the order.										 **
 *************************************************************************************************************/
public class PaymentGateway extends HttpServlet
{
	private String cardHolderName,cardNumber,total,cvv;
	private boolean status;
	private Logger log = null;
	final static long serialVersionUID = 00000010001010;
	Connection connection = null;
	Statement st = null;
	public boolean callBank(){
		/*Requirements:
    	 * 1. Need a worker thread to post data securely to bank's secure site.
    	 * 2. Need a hash function to create and store the transaction on DB.
    	 * 3. Need a validator for all data.
    	 * 4. Require to atomize the whole transaction.
    	 * */
    	boolean connect = false;
    	
    	return(connect);
	}
	@Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
    {
    	res.setContentType("text/html");
        log.log(Level.INFO, "In service method for Class PaymentGateway");
        //Getting data from from customer end
        total = req.getParameter("total");
        cardNumber = req.getParameter("cardNumber");
        cardHolderName = req.getParameter("cardHolderName");
        cvv = req.getParameter("cvv");
        
        //Processing Transaction
        status = process(req,res,total,cardNumber,cardHolderName,cvv);
        //Sending Response
        if( status == true){
        	log.log(Level.INFO, "Transaction Completed Successfuly.");
        }
        else
        {
        	log.log(Level.WARNING, "Sorry! the transcation couldn't completed.");
        }
    }
	public boolean process(ServletRequest req, ServletResponse res, String amount, String cardNum, String holder, String cvv){
		log.log(Level.INFO, "process transaction.......");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "mysql");
			Statement st= connection.createStatement();
			st.execute("select * from emp");
			ResultSet rs = st.getResultSet();
			while(rs.next()) {
				String text = rs.getString(1);
				log.log(Level.FINE, text);
			}
			return true;
		}
		catch (Exception e){
			log.log(Level.SEVERE,"Couldn't save message....",e);
			return false;	
		}
		finally{
			//TODO	:	DB.close()
			try{
				if (st!=null|connection!=null)
					{
						st.close();
						connection.close();
					}
			}
			catch (Exception e)
			{
				log.log(Level.WARNING,"Couldn't close connection.....", e);
			}
		}
	}
}

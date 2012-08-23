package com.shreesoft.PaymentGateway;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



/**************************************************************************************************************
 * This a generic Payment Gateway that accepts all requirements from a client and forwards the request to the**
 * respective bank's verification site and then completes the order.										 **
 *************************************************************************************************************/
public class PaymentGateway extends GenericServlet
{
	private String cardHolderName,gateway,cardNumber,total,cvv;
	final static long serialVersionUID = 00000010001010;
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
        PrintWriter out = res.getWriter();
        
        //Getting data from from customer end
        total = req.getParameter("total");
        cardNumber = req.getParameter("cardNumber");
        cardHolderName = req.getParameter("cardHolderName");
        cvv = req.getParameter("cvv");
        //Processing Transaction
        //Sending Response
        if( callBank() == false)
        {
        	out.println("Transaction Completed Successfuly");
        }
        else
        {
        	out.println("Sorry! the transcation couldn't completed.");
        }
    }
}

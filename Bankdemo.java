
	package com.demo;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.Statement;
	import java.util.Scanner;

	import com.mysql.jdbc.ResultSet;

	public class BankApp {
		public static Connection con;
		public static Statement stmt;
		public static Scanner sc;
		
		public static void main(String[] args) {
			
			init();
			gotoMenu();
			
		}

		private static void gotoMenu() {

			try
			{
				System.out.println("Enter your choice : \n 1.Acc Info  \n 2.Deposit \n 3.Withdrawal \n 4.Statement \n 5.Acc opening \n 6.Exit");
				int opt=sc.nextInt();
				
				switch(opt)
				{
				
				case 1:
					   showAccInfo();
					   break;
					   
				case 2:
					   gotodeposit();
					   break;
					   
				case 3:
					   showAccInfo();
					   break;
					   
				case 4:
					   showAccInfo();
					   break;
					   
				case 5:
					   showAccInfo();
					   break;
				
					   
				case 6:
					   showAccInfo();
					   break;
				
				default:
					   System.out.println("Invalid selection");
					   break; 
					   
				}
				
				
			}
			catch (Exception e) {

			System.out.println("gotoMenu():"+e.getMessage());
			
			}
			
		}

		private static void gotodeposit() {
			try
			{
				System.out.println("Enter your account no:");
				int accno=sc.nextInt();
				
				System.out.println("Enter your deposit amount:");
				double depamt=sc.nextDouble();
				
				double oldbalance=0,closingbalnce=0;
			
				//acc holder old balance 
				java.sql.ResultSet rs=stmt.executeQuery("select * from account where Account_no='"+accno+"'");
				while(rs.next())
				{
					oldbalance=rs.getDouble(6);
				}
				
				closingbalnce=oldbalance+depamt;
				
				//update acc table
				stmt.execute("update account set current_bal='"+closingbalnce+"' where Account_no='"+accno+"'");
				
						
				//insert transaction table
				stmt.execute("insert into statement values('"+accno+"','Deposit','"+depamt+"','0','"+closingbalnce+"')");
				
				
				System.out.println("Amount Deposted:"+depamt);
				System.out.println("Opening Balance:"+oldbalance);
				System.out.println("Closing Balance:"+closingbalnce);
				
			}
			catch (Exception e) {
				System.out.println("Deposit failed...");
			System.out.println("gotodeposit():"+e.getMessage());
			
			}
			
		}

		private static void showAccInfo() {
			try
			{
				System.out.println("Enter your account no:");
				int accno=sc.nextInt();
				
				
				java.sql.ResultSet rs=stmt.executeQuery("select * from account where Account_no='"+accno+"'");
				while(rs.next())
				{
					
					System.out.println("********************************\n");
					System.out.println("Account Name:"+rs.getString(3));
					System.out.println("Account No:"+rs.getString(2));
					System.out.println("Addess:"+rs.getString(5));
					System.out.println("********************************\n\n");
				}
				
				
			}
			catch (Exception e) {

			System.out.println("showAccInfo():"+e.getMessage());
			
			}
			
		}

		private static void init() {
				
			try
			{
				sc=new Scanner(System.in);
				Class.forName("com.mysql.jdbc.Driver");  
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/program","root","");  
				stmt=con.createStatement(); 
				
				//table creation
				//stmt.execute("create tab");
				
				
			}
			catch (Exception e) {
			//	con.close();
			System.out.println(e.getMessage());
			
			}
			
			
			
		}

	}

}

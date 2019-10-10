import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * The Gym Class holds the gym details and details of members of the gym
 * 
 * @author Ashraf Mustafa
 */

public class Gym {

	 private String gymName;
	 private String managerName;
	 private String phoneNumber;
	 private ArrayList<Member> members;
	 
	 /**
	     * Constructor for Gym class
	     * @param gymName The name of the Gym
	     * @param managerName The name of the Gym Manager
	     */
	
	 public Gym(String gymName, String managerName)
	    {
		 	members = new ArrayList<Member>();
	        if(gymName.length() <= 30)
				this.gymName = gymName;
			else
				this.gymName = gymName.substring(0, 30);
	        this.managerName = managerName;
	        this.phoneNumber = "unknown";
	    }
	 
	 /**
	     * Constructor for objects of class Gym
	     * @param gymName The name of the Gym
	     * @param managerName The name of the Gym Manager
	     * @param phoneNumber The phone number of the Gym
	     */
	 
	 public Gym(String gymName, String managerName, String phoneNumber)
	    {
	        members = new ArrayList<Member>();
	        if(gymName.length() <= 30)
				this.gymName = gymName;
			else
				this.gymName = gymName.substring(0, 30);
	        this.managerName = managerName;
	        if(isNumeric(phoneNumber))
		  		this.phoneNumber = phoneNumber;
		  	else
		  		this.phoneNumber = "unknown";
	        
	    }
	 
	 public ArrayList<Member> getMembers()
	    {
	    		return members;
	    }
	    
	 public void add (Member member)
	    {
	        members.add (member); 
	    }
	 
	 public static boolean isNumeric(String str)  
	 {  
	   try  
	   {  
	     double z = Double.parseDouble(str);  
	   }  
	   catch(NumberFormatException nfe)  
	   {  
	     return false;  
	   }  
	   return true;  
	 }
	 
	    //   GETTERS
	    
	    
	 public String getGymName()
	    {
	        return gymName;
	    }
	    
	  public String getManagerName()
	    {
	        return managerName;
	    }
	    
	  public String getPhoneNumber()
	    {
	        return phoneNumber;
	    }
	  
	   
	    //  SETTERS
	  
	  public void setGymName(String gymName)
	    {
		  if(gymName.length() <= 30)
				this.gymName = gymName;
			else
				this.gymName = gymName.substring(0, 30);
	    }
	    
	  public void setManagerName(String managerName)
	    {
	        this.managerName = managerName;
	    }
	    
	  public void setPhoneNumber(String phoneNumber)
	    {	
		  if(isNumeric(phoneNumber))
		  		this.phoneNumber = phoneNumber;
		  	else
		  		this.phoneNumber = phoneNumber.replaceAll("\\D+","");
	    }
	  
	   
	    //  METHODS
	    
	  
	  public int numberOfMembers()
	    {
	        return members.size();
	    }
	  
	  public String listMembers()
	    {
	        String listOfMembers = "";
	        int index = 0;
	        for (Member member: members)
	        {
	            listOfMembers = listOfMembers + index + ": " + member.toString() + "\n";
	            index++;
	        }
	        if (listOfMembers.equals(""))
	        {
	            return "There are no members in the gym";
	        }
	        else
	        {
	            return listOfMembers;
	        }
	    }
	  
	  public String listBySpecificBMICategory(String category)
	    {
		  	if(members.size() == 0)
		  		return "There are no members in the gym";
		  	String listOfMembers = "";
	        int index = 0;
	        for (Member member: members)
	        {
	        		if(member.determineBMICategory().contains(category))
	        			{
	       			listOfMembers = listOfMembers + index + ": " + member.toString() + "\n";
	        			index++;
	        			}
	        }
	        if(listOfMembers.equals(""))
	        		return "No members in this BMI category";
	        else
	        		return listOfMembers;
	    }
	  
	  public String listMemberDetailsEmpericalAndMetric()
	    {
		  	String str = "";
		  
		  	if(members.size() == 0)
		  		return "There are no members in the gym";
	        
	        for (Member member : members)
	        {       
	                str += member.getMemberName() + ":\t" + member.getStartingWeight() + " kg (" + member.convertWeightKGtoPounds() + " lbs) \t" 
	                							+ member.getHeight() + " metres (" + member.convertHeightMetresToInches() + " inches)." + "\n";   
	        }
	        return str;
	    }
	  
	  public String listMembersWithIdealWeight()
	    {
		  	String str = "";
		  
		  	if(members.size() == 0)
		  		return "There are no members in the gym";
	        
	        for (Member member : members)
	        {
	           if (member.isIdealBodyWeight()==true)            
	                str += listMembers();   
	        }

	        if (str.equals(""))
	        {
	            return "There are no members in the gym with an ideal weight.";
	        }
	        else 
	            return str;
	    }
	  
	  public void remove(int index)
	  	{
		  if(!(index < 0) || index > (numberOfMembers()-1))
			  members.remove(index);
	  	}
	  
	  public String toString()
	    {
	        return "Gym Name: " + gymName
	             + ", Manager: " + managerName
	             + ", Phone Number: " + phoneNumber
	             + ". List of members in the gym:"
	             + listMembers();
	    }
	  
	  @SuppressWarnings("unchecked")
	    public void load() throws Exception
	    {
	        XStream xstream = new XStream(new DomDriver());
	        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("members.xml"));
	        members = (ArrayList<Member>) is.readObject();
	        is.close();
	    }
	    
	    public void save() throws Exception
	    {
	        XStream xstream = new XStream(new DomDriver());
	        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("members.xml"));
	        out.writeObject(members);
	        out.close();    
	    }
}
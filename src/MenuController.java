import java.util.Scanner;

/**
* The MenuConroller class displays the menu of the GymApp.
* 
* @author Ashraf Mustafa
*/

public class MenuController
{
    private Scanner input;
    private Gym gym;
    
    public MenuController()
    {
        input = new Scanner(System.in);        
        System.out.print("Please enter the Gym...");
        System.out.print("Name: ");
    		String name = input.nextLine();
    		System.out.print("Manager Name: ");
    		String managerName = input.nextLine();
    		System.out.print("Phone Number: ");
    		String phoneNumber = input.nextLine();
    		gym = new Gym(name,managerName,phoneNumber);
        runMenu();
    }
    
    /**
     * Starts the application
     * @param args
     */
    
    public static void main (String[] args)
    {
    		MenuController application = new MenuController();
    }
    
    /**
     * mainMenu() - This method displays the menu for the application, 
     * reads the menu option that the user entered and returns it.
     * 
     * @return     the users menu choice
     */

    private int mainMenu()
    { 
        System.out.println("Gym Menu");
        System.out.println("---------");     
        System.out.println("  1) Add a member");    
        System.out.println("  2) List all members");    
        System.out.println("  3) Remove a member (by index)");        
        System.out.println("  4) Number of members in the gym"); 
        System.out.println("---------");     
        System.out.println("  5) List the gym details");        
        System.out.println("  6) List members with ideal starting weight"); 
        System.out.println("  7) List members with a specific BMI category"); 
        System.out.println("  8) List all members stats emperically and metrically"); 
        System.out.println("---------");     
        System.out.println("  9) Save to XML");        
        System.out.println("  10) Load from XML"); 
        System.out.println("---------");     
        System.out.println("  0) Exit");
        System.out.print("==>> ");
        int option = input.nextInt();
        return option;
    }
    
    /**
     * This is the method that controls the loop.
     */
    
    private void runMenu()
    {
        int option = mainMenu();
        while (option != 0)
        {
           
            switch (option)
            {
               case 1:    addMember();
                          break;
               case 2:    System.out.println(gym.listMembers());
                          break;
               case 3:    System.out.println("Enter index number to be removed: ");
                          int num = input.nextInt();
                          if(num > (gym.numberOfMembers()-1) || num < 0)
                        	  	System.out.println("There is no member of this index number");
                          else
                          {
                        	  	System.out.println("Member deleted.");
                        	  	gym.remove(num);
                          }
            	              break;
               case 4:    System.out.println("Number of members: " + gym.numberOfMembers());
                          break;
               case 5:    System.out.println(gym.toString());
                          break;
               case 6:    System.out.println(gym.listMembersWithIdealWeight());
                          break;
               case 7:    System.out.println("Please enter the category to search for: ");
               		      String category = input.nextLine();
               		      category = input.nextLine();
            	              System.out.println(gym.listBySpecificBMICategory(category.toUpperCase()));
                          break; 
               case 8:    System.out.println(gym.listMemberDetailsEmpericalAndMetric());
                          break; 
               case 9:    try{
   	   						gym.save();
	   			  		  }
	   			  		  catch (Exception e){
	   			  			System.out.println("Error writing to file: " + e);
	   			  		  }      
      			  		  break;
               case 10:   try{
   	   					    gym.load();
      			          }
     			  		  catch (Exception e)
      			          { 
      			  			System.out.println("Error reading from file: " + e);
      			          }
      		              break;
              default:    System.out.println("Invalid option entered: " + option);
                          break;
            }
            
            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("Press any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.
            
            //display the main menu again
            option = mainMenu();
        }
    
    }
    
    public void addMember()
    {
    	System.out.print("Please enter the following member details...");
    	System.out.print("Id (between 100001 and 999999): ");
    	int id = input.nextInt();
    	System.out.print("Name (maximum 30 characters): ");
    	String name = input.nextLine();
    	name = input.nextLine();
    	System.out.print("Address: ");
    	String address = input.nextLine();
    	System.out.print("Height (between 1 and 3 metres): ");
    	double height = input.nextDouble();
    	System.out.print("Starting Weight (between 35kg and 250kg): ");
    	double weight = input.nextDouble();
    	System.out.print("Gender (M/F): ");
    	String gender = input.nextLine();
    	gender = input.nextLine();
    	gym.add(new Member(id, name, address, height, weight, gender));
}
}
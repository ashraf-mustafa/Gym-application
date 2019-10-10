/**
 * The Member class holds all the details of members of the Gym
 * 
 * @author Ashraf Mustafa
 */

public class Member
{
	private int memberId;
    private String memberName;
    private String memberAddress;
    private double height;
    private double startingWeight;
    private String gender;

    public Member( int memberId,String memberName,String memberAddress, double height, double startingWeight, String gender)
    {
    	
    if(memberId>100000 && memberId <=999999)
    	this.memberId = memberId;
    else
    	this.memberId = 100000;
    
    if(memberName.length() <=30)
        this.memberName = memberName; 
    else
    	this.memberName = memberName.substring(0,30);
    
        this.memberAddress = memberAddress;
        
    if(height >=1 && height <=3)
        this.height = height;
        
    if(startingWeight <=250 && startingWeight >=35)
        this.startingWeight = startingWeight;
        
     if(gender.equals("M") || gender.equals("m"))
        this.gender = "M";
   else if(gender.equals("F" )|| gender.equals("f"))
		 this.gender = "F";
	else
			 this.gender = "Unspecified";
    	 
    }
    
   
    //  GETTERS
   
    
    public int getMemberId() 
	{
		return memberId;
	}
    
    public String getMemberName() 
	{
		return memberName;
	}
    
    public String getMemberAddress() 
	{
		return memberAddress;
	}
    
    public double getHeight() 
	{
		return height;
	}

    public double getStartingWeight() 
	{
		return startingWeight;
	}
   
    
    //  SETTERS
    
    
    /**
	 * @param MemberId the memberId to set
	 */
    
	public void setMemberId(int memberId) 
	{		
		if(memberId>100000 && memberId <=999999)
	    	this.memberId = memberId;
	}
	
	/**
	 * @param MemberName the memberName to set
	 */
	
	public void setMemberName(String memberName) 
	{
		if(memberName.length() <=30)
	        this.memberName = memberName; 
	    else
	    	this.memberName = memberName.substring(0,30);
	    
	        this.memberAddress = memberAddress;
	
	}
	
	/**
	 * @param MemberAddress the memberAddress to set
	 */
	
	public void setMemberAddress(String memberAddress) 
	{
		this.memberAddress = memberAddress;
	}

	/**
	 * @param Height the Height to set
	 */
	public void setHeight(double height) 
	{
		if(height<=3 && height>=1)
			this.height = height;
	}

	/**
	 * @param StartingWeight the startingWeight to set
	 */
	public void setStartingWeight(double startingWeight) 
	{
		if(startingWeight>=35 && startingWeight<=250)
		this.startingWeight = startingWeight;
	}

	/**
	 * @param Gender the gender to set
	 */
	public void setGender(String gender) 
	{
		if (gender.equals("M") || gender.equals("m")) 
			this.gender = "M";
		 if (gender.equals("F") || gender.equals("f")) 
			this.gender = "F";		
	}
	

	 private double toTwoDecimalPlaces(double num)
	 {
	        return (int) (num *100 ) /100.0; 
	 }
	 
	 public double calculateBMI() 
	 {
		 double BMI;
		 BMI = startingWeight / (height * height);
		 return toTwoDecimalPlaces(BMI);
	 }

	 public double convertHeightMetresToInches() 
	 {
		 double convert = 39.37;
		 double inches = height * convert;
		 return toTwoDecimalPlaces (inches);
	 }
	 
	public double convertWeightKGtoPounds() 
	{
		double convert = 2.2;
		double weight = startingWeight * convert;
		return toTwoDecimalPlaces (weight);
	}
	
	public String determineBMICategory() 
	{		
		double bmi = calculateBMI();
		if(bmi<15) {
			return "(VERY SEVERELY UNDERWEIGHT)";
		} else if((bmi>= 15 && bmi < 16)) {
			return "(SEVERELY UNDERWEIGHT)";
		} else if((bmi>= 16 && bmi < 18.5)) {
			return "(UNDERWEIGHT)";
		} else if((bmi>= 18.5 && bmi < 25)) {
			return "( NORMAL )";
		} else if((bmi>= 25 && bmi < 30)) {
			return "(OVERWEIGHT)";
		} else if((bmi>= 30 && bmi < 35)) {
			return "(MODERATELY OBESE)";
		} else if((bmi>= 35 && bmi < 40)) {
			return "(SEVERELY OBESE)";
		} else {
			return "(VERY SEVERELY OBESE)";
		}
		
	 }
	
	public boolean isIdealBodyWeight()
	{
		double idealWeight;
		if(convertHeightMetresToInches() <=60)
		{
			if(gender == "M")
				idealWeight = 50;
			else
				idealWeight = 45.5;
		}
		else
		{
			if(gender == "M")
				idealWeight = 50 + ((convertHeightMetresToInches()-60)* 2.3);
			else
				idealWeight = 45.5 + ((convertHeightMetresToInches()-60)* 2.3);
		}
		
		if(idealWeight >= (startingWeight-2) && idealWeight <= (startingWeight+2))
		return true;
	else
		return false;
	}
	

	/**
     * Builds a String representing a user friendly representation of the object state
     * @return Details of the specific member
     */
    public String toString()
    {
        return "Member description: " + memberName
             + ", member Id: " + memberId
             + ", member address: " + memberAddress
             + ", member height: " + height
        	 + ", gender: " + gender
        	 + " BMI of " + calculateBMI() + "" + determineBMICategory();
    }
    
}

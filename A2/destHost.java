public class destHost implements Comparable<destHost> { 	
	private String ip;
	
	public destHost(String address) {
		this.ip = address;
    }
    
    public String toString() {
        return String.format("%s", this.ip);
    }
    
    public int compareTo(destHost other) {
		
		int result = 0;
		
		String thisArray[] = this.ip.split("\\."); 
    	String otherArray[] = other.ip.split("\\.");
    	
		
    	if (thisArray.length == 4 && otherArray.length == 4) {
    		int currentIPthirdOctet = Integer.parseInt(thisArray[2]); 
        	int otherIPthirdOctet = Integer.parseInt(otherArray[2]); 
    	
    		int currentIPfourthOctet = Integer.parseInt(thisArray[3]); 
    		int otherIPfourthOctet = Integer.parseInt(otherArray[3]); 
    		
    		int thirdOctet = currentIPthirdOctet - otherIPthirdOctet; 
    		int fourthOctet = currentIPfourthOctet - otherIPfourthOctet; 
    		
    		if (thirdOctet > 0 && fourthOctet > 0) {
    			result =  1; 
    		} else if (thirdOctet > 0 && fourthOctet < 0) {
    			result =  1; 
    		} else if (thirdOctet < 0 && fourthOctet < 0) {
    			result = -1;
    		} else if (thirdOctet < 0 && fourthOctet > 0) {
    			result = -1;
    		} else if (thirdOctet == 0 && fourthOctet < 0) {
    			result = -1;
    		} else if (thirdOctet == 0 && fourthOctet > 0) {
    			result = 1;
    		} else if (thirdOctet > 0 && fourthOctet == 0) {
    			result = 1;
    		} else if (thirdOctet < 0 && fourthOctet == 0) {
    			result = -1;
    		} else {
    			result =  0;
    		}
    	}
		
		return result;
		
	}
}
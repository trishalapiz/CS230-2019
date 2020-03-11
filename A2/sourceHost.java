public class sourceHost implements Comparable<sourceHost> {
    private String ip;
    
    public sourceHost(String address) {
        this.ip = address;
    }
    
    public String toString() {
        return String.format("%s", this.ip);
    }
    
    public int compareTo(sourceHost other) {
    	
    	int result = 0;
    	
    	String thisArray[] = this.ip.split("\\."); 
    	String otherArray[] = other.ip.split("\\."); 
    	
    	if (thisArray.length == 4 && otherArray.length == 4) {
    		int currentIPfourthOctet = Integer.parseInt(thisArray[3]); 
        	int otherIPfourthOctet = Integer.parseInt(otherArray[3]); 
        	
        	int lastOctet = currentIPfourthOctet - otherIPfourthOctet;
        	
        	if (lastOctet > 0) {
        		result = 1;
        	} else if (lastOctet < 0) {
        		result = -1;
        	} else {
        		result = 0;
        	}
    	}
    	
    	return result;
		
    }
}
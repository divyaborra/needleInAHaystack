/*
 * Copyright 2017 Marc Liberatore.
 */

package log;
public class SuspectEntry implements Comparable<SuspectEntry> {

    private String name;
    private String phoneNumber;
    private String passportNumber;

    public SuspectEntry() {

    }

    public SuspectEntry(String name, String phoneNumber, String passportNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumber) {
    	phoneNumber = phoneNumber.replaceAll("[^a-zA-Z0-9]", "");
        this.phoneNumber = phoneNumber;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
    
    public boolean equals(Object o)
    {
    	
    if (o == this)
    return true;
    if (!(o instanceof SuspectEntry))
    return false;
    SuspectEntry s = (SuspectEntry) o;
    return passportNumber.equals(s.passportNumber);
  
    }

    @Override
    public int compareTo(SuspectEntry s) {
    	if (passportNumber.compareTo(s.passportNumber) < 0) return -1;
    	if (passportNumber.compareTo(s.passportNumber) > 0) return 1;
    	
    	if (name.compareTo(s.name) < 0) return -1;
    	if (name.compareTo(s.name) > 0) return 1;
    	
    	if (phoneNumber.compareTo(s.phoneNumber) < 0) return -1;
    	if (phoneNumber.compareTo(s.phoneNumber) > 0) return 1;
    	
    	return 0;
    }
    
    public String toString() {
    	return "name: " + name + " passport number: " + passportNumber + " phone number: " + phoneNumber;
    }

	public String print() {
		return toString();
	}
    
}




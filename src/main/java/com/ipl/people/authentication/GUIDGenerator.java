package com.ipl.people.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * GUID generator used to generate access tokens.
 * 
 * @author Asha
 *
 */
public class GUIDGenerator {
	
	public static int numberOfUUIDs = 10;
  
  public static final void main(String[] aArgs){
    
	List<UUID> uuids = new ArrayList<UUID>();
	for(int i=0 ; i < numberOfUUIDs ;i++){
		uuids.add(UUID.randomUUID());
	}
	System.out.println(uuids);
    
  }
  
} 

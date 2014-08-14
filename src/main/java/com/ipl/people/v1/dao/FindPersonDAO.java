package com.ipl.people.v1.dao;


import io.dropwizard.hibernate.AbstractDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.ipl.people.v1.core.FindPerson;
import com.sun.jersey.api.NotFoundException;

public class FindPersonDAO extends AbstractDAO<FindPerson> {
    public FindPersonDAO(SessionFactory factory) {
        super(factory);
    }
   
    public static int recordsLimit = 20;    
    public static int recordsOffset = 0;
    
    public List<FindPerson> findPerson(Optional<String> name, Optional<String> surname, Optional<String> ageStr,  Optional<String> dateOfBirth, Optional<String> pncId, 
			 Optional<String> niNumber, Optional<String> driverNo, Optional<String> croNo, Optional<String> passportNo, Optional<Integer> limit,Optional<Integer> offset) throws Exception{
    	
    	String exactMatch = "";
    	String partialMatch = " ( ";
        String sql1 = "(SELECT * FROM homeofficev1.person p , homeofficev1.name_alias n where p.Person_UID=n.Person_UID and n.Name_Type='P' and";
        String sql2 = " UNION (SELECT * FROM homeofficev1.person p , homeofficev1.name_alias n where p.Person_UID=n.Person_UID and n.Name_Type='P' and ";
       
    	
    	if(pncId != null && pncId.isPresent() && pncId.get().length()>0){    		
    		exactMatch = exactMatch.concat(" p.PNC_ID ='"+pncId.get()+"' "); ;  
    		partialMatch = partialMatch.concat(" p.PNC_ID ='"+pncId.get()+"' " );  
    	}
    	
    	if(name != null && name.isPresent() && name.get().length()>0){
    		if(StringUtils.isNotBlank(exactMatch)) {
        		exactMatch = exactMatch.concat(" and ");
        		partialMatch = partialMatch.concat(" or ");
        	}
    		String nameStr = " (n.Forename1 like '%"+name.get()+"%' or n.Forename2 like '%"+name.get()+"%' or n.Forename3 like '%"+name.get()+"%' or n.Forename4 like '%"+name.get()+"%') ";
    		exactMatch = exactMatch.concat(nameStr) ;    
    		partialMatch = partialMatch.concat(nameStr) ;   
    	}     	
    	
    	if(surname != null && surname.isPresent() && surname.get().length()>0){
    		if(StringUtils.isNotBlank(exactMatch)) {
        		exactMatch = exactMatch.concat(" and ");
        		partialMatch = partialMatch.concat(" or ");
        	}
    		exactMatch = exactMatch.concat(" n.surname like '%"+surname.get()+"%' "); ;  
    		partialMatch = partialMatch.concat(" n.surname like '%"+surname.get()+"%' " ); 
    	}
    	
    	if (ageStr != null && ageStr.isPresent() && ageStr.get().length()>0) {
    		if(StringUtils.isNotBlank(exactMatch)) {
        		exactMatch = exactMatch.concat(" and ");
        		partialMatch = partialMatch.concat(" or ");
        	}
    		String age = ageStr.get();
			int hyphenIndex = age.indexOf('-');
			
			if(hyphenIndex == -1){
				int ageInt = 0;
				try { 
					ageInt = Integer.parseInt(age); 
			    } catch(NumberFormatException e) { 
			        throw new NotFoundException("Please provide age in the format number or number-number."); 
			    }

				int currentYear = Calendar.getInstance().get(Calendar.YEAR);
				Calendar c1 = Calendar.getInstance();
				c1.set(currentYear-ageInt,0,1);
				Date dateOfBirthFrom = c1.getTime();
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateRange = " (n.date_of_birth >= '"+formatter.format(dateOfBirthFrom)+"' AND n.date_of_birth <= ";				 
				Calendar c2 = Calendar.getInstance();
				c2.set(currentYear-ageInt,11,31);
				Date dateOfBirthTo = c2.getTime();
				dateRange = dateRange + "'"+ formatter.format(dateOfBirthTo) + "') ";
				exactMatch =  exactMatch.concat(dateRange);  
	    		partialMatch = partialMatch.concat(dateRange);
				
			}  else {
				int age1 = 0; 
				int age2 =0;
				try {
					age1 = Integer.parseInt(age.substring(0, hyphenIndex)); 
					age2 = Integer.parseInt(age.substring(hyphenIndex+1, age.length())); 
					if(age2>age1){
						int temp = age1;
						age1 = age2;
						age2 = temp;
					}
			    } catch(NumberFormatException e) { 
			        throw new NotFoundException("Please provide age in the format number or number-number."); 
			    }
				int currentYear = Calendar.getInstance().get(Calendar.YEAR);
				Calendar c1 = Calendar.getInstance();
				c1.set(currentYear-age1,0,1);
				Date dateOfBirthFrom = c1.getTime();
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateRange = " (n.date_of_birth >= '"+formatter.format(dateOfBirthFrom)+"' AND n.date_of_birth <= ";		
				Calendar c2 = Calendar.getInstance();
				c2.set(currentYear-age2,11,31);
				Date dateOfBirthTo = c2.getTime();
				dateRange = dateRange + "'"+ formatter.format(dateOfBirthTo) + "') ";
				exactMatch =  exactMatch.concat(dateRange);  
	    		partialMatch = partialMatch.concat(dateRange);
				
			}
		}
    	
    	if(dateOfBirth != null && dateOfBirth.isPresent() && dateOfBirth.get().length()>0){
    		if(StringUtils.isNotBlank(exactMatch)) {
        		exactMatch = exactMatch.concat(" and ");
        		partialMatch = partialMatch.concat(" or ");
        	}
    		String[] dates =dateOfBirth.get().split("-");   
    		String dateFormat = "";
    		if(dates.length == 3){
    			dateFormat = "yyyy-MM-dd";
    		} else if(dates.length == 2) {
    			dateFormat = "yyyy-MM";
    		} else {
    			dateFormat = "yyyy";
    		}
    		Date date;
    		try{
	    		DateFormat formatter = new SimpleDateFormat(dateFormat);
				date = formatter.parse(dateOfBirth.get());
    		} catch (ParseException e) {
				throw new NotFoundException("Please provide Date of birth in the format yyyy-MM-dd.");
			}
			if(dates.length == 3){
				exactMatch = exactMatch.concat(" n.date_of_birth = '"+dateOfBirth.get()+"' "); ;  
	    		partialMatch = partialMatch.concat(" n.date_of_birth = '"+dateOfBirth.get()+"' ") ;
			} else if(dates.length == 2) {
    			Calendar c = Calendar.getInstance();
    			c.setTime(date);
    			String dateRange = " (n.date_of_birth >= '"+dateOfBirth.get()+"-"+ c.getActualMinimum(Calendar.DAY_OF_MONTH) 
    					+"' AND n.date_of_birth <= '"+dateOfBirth.get()+"-"+ c.getActualMaximum(Calendar.DAY_OF_MONTH) + "') ";
    			exactMatch =  exactMatch.concat(dateRange);  
	    		partialMatch = partialMatch.concat(dateRange);
			} else {
    			String dateRange = " (n.date_of_birth >= '"+dateOfBirth.get()+"-01-01" 
    					+"' AND n.date_of_birth <= '"+dateOfBirth.get()+"-12-31" + "') ";
    			exactMatch =  exactMatch.concat(dateRange);  
	    		partialMatch = partialMatch.concat(dateRange);
			}
    		  
    	}
    	exactMatch = exactMatch.concat(" order by p.Person_UID ) "); ;  
		partialMatch = partialMatch.concat(" ) order by p.Person_UID ) " ); 
    	
    	
    	/*if(niNumber != null && niNumber.isPresent() && niNumber.get().length()>0){
    		if(StringUtils.isNotBlank(exactMatch)) {
        		exactMatch = exactMatch.concat(" and ");
        		partialMatch = partialMatch.concat(" or ");
        	}
    		exactMatch = exactMatch.concat(" nin = '"+niNumber.get()+"' "); ;  
    		partialMatch = partialMatch.concat(" nin = '"+niNumber.get()+"' " );  
    	}
    	
    	if(driverNo != null && driverNo.isPresent() && driverNo.get().length()>0){
    		if(StringUtils.isNotBlank(exactMatch)) {
        		exactMatch = exactMatch.concat(" and ");
        		partialMatch = partialMatch.concat(" or ");
        	}
    		exactMatch = exactMatch.concat(" dno = '"+driverNo.get()+"' "); ;  
    		partialMatch = partialMatch.concat(" dno = '"+driverNo.get()+"' ") ;  
    	}
    	if(croNo != null && croNo.isPresent() && croNo.get().length()>0){
    		if(StringUtils.isNotBlank(exactMatch)) {
        		exactMatch = exactMatch.concat(" and ");
        		partialMatch = partialMatch.concat(" or ");
        	}
    		exactMatch = exactMatch.concat(" cro = '"+croNo.get()+"' "); ;  
    		partialMatch = partialMatch.concat(" cro = '"+croNo.get()+"' ") ;  
    	}
    	if(passportNo != null && passportNo.isPresent() && passportNo.get().length()>0){
    		if(StringUtils.isNotBlank(exactMatch)) {
        		exactMatch = exactMatch.concat(" and ");
        		partialMatch = partialMatch.concat(" or ");
        	}
    		exactMatch = exactMatch.concat(" pap = '"+passportNo.get()+"' "); ;  
    		partialMatch = partialMatch.concat(" pap = '"+passportNo.get()+"' " );  
    	}*/
    	
    	
    	Query findPersons = currentSession().createSQLQuery(sql1 + exactMatch + sql2 + partialMatch).addEntity(FindPerson.class);
    	
    	if(offset!=null && offset.isPresent() && offset.get() > 0)
    	{
    		findPersons.setFirstResult(offset.get());
    	} else {
    		findPersons.setFirstResult(recordsOffset);
    	}
    	if(limit!=null && limit.isPresent()  && limit.get() > 0)
    	{
    		findPersons.setMaxResults(limit.get());
    	} else {
    		findPersons.setMaxResults(recordsLimit);
    	}
    	
    	
    	return list(findPersons);
    	
    }
}

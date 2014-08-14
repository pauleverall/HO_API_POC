package com.ipl.people.xmltransformer;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;



/*This is just a Test class*/
public class XMLTransformer {

	 
	public static void main(String ars[]) throws Exception {
		
		/*XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
		xmlJsonFormat.setEncoding("UTF-8");
		xmlJsonFormat.setForceTopLevelObject(true);
		xmlJsonFormat.setTrimSpaces(true);
		xmlJsonFormat.setRootName("person");
		xmlJsonFormat.setSkipNamespaces(true);
		xmlJsonFormat.setRemoveNamespacePrefixes(true);*/
		//xmlJsonFormat.setExpandableProperties(Arrays.asList("d", "e"));
		//from("direct:unmarshal").unmarshal(xmlJsonFormat).to("mock:xml");
		 /* final String request = "[" +
	               "{\"event_id\":\"57\",\"subject\":\"1309\",\"event_date\":\"1995-01-13\",\"type\":\"W\",\"end\":\"1999-01-04\",\"force_code\":\"50EC/1437/04121996\",\"offence\":\"BURGLARY\",\"power_of_arrest\":\"ARREST\",\"report_date\":\"1996-01-04\"}"+
	               ",{\"event_id\":\"58\",\"subject\":\"1309\",\"event_date\":\"1996-01-15\",\"type\":\"W\",\"end\":\"1999-01-01\",\"force_code\":\"50EL/0835/01101996\",\"offence\":\"BREACH OF COURT ORDER\",\"power_of_arrest\":\"WARRANT\",\"report_date\":\"1996-01-01\"}" 
	               +"]";*/

	      // final String response = new ProducerTemplate().requestBody("direct:unmarshal", request, String.class);
		final String request="[ {\"person_uid\" : \"106\",  \"gender\" : \"M\",  \"surname\" : \"MARSH\",  \"date_of_birth\" : \"1922-01-29\",  \"place_of_birth\" : \"BURY/EDMUNDS\",  \"given_name\" : [ \"FREDRICK\", \"MARTIN\" ] } ]";
		final CamelContext camelContext = new DefaultCamelContext();    
		camelContext.addRoutes(new XmlJsonRouteBuilder());
		
		ProducerTemplate template = camelContext.createProducerTemplate();
		camelContext.start();
		template.start();
		
		/*PersonXml person = new PersonXml();
		 person.setId("111");
		 person.setName("Scott Cranton");
		 person.setGender("Male");*/
		 String response =  template.requestBody("direct:unmarshalPeople", request, String.class);
		 System.out.println(response);
		 camelContext.stop();
		 template.stop();

	}
}

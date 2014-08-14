/*
 * Copyright (C) Scott Cranton and Jakub Korab
 * https://github.com/CamelCookbook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ipl.people;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.ipl.people.xmltransformer.XmlJsonRouteBuilder;

public class XmlJsonTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new XmlJsonRouteBuilder();
    }

   @Test
    public void testXmlJsonUnmarshalPeople() throws Exception {
      
        final String request="[ {\"person_uid\" : \"106\",  \"gender\" : \"M\",  \"surname\" : \"MARSH\",  \"date_of_birth\" : \"1922-01-29\",  \"place_of_birth\" : \"BURY/EDMUNDS\",  \"given_name\" : [ \"FREDRICK\", \"MARTIN\" ],\"related_records\":[\"Same NI:200\", \"test\"] } ]";
        final String response = template.requestBody("direct:unmarshalPeople", request, String.class);

        log.info(response);
       /* assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
            "<bookstore>" +
            "<book category=\"COOKING\"><author>Giada De Laurentiis</author><price>30.00</price><title lang=\"en\">Everyday Italian</title><year>2005</year></book>" +
            "<book category=\"CHILDREN\"><author>J K. Rowling</author><price>29.99</price><title lang=\"en\">Harry Potter</title><year>2005</year></book>" +
            "<book category=\"WEB\"><author>Erik T. Ray</author><price>39.95</price><title lang=\"en\">Learning XML</title><year>2003</year></book>" +
            "<book category=\"PROGRAMMING\"><author>Scott Cranton</author><author>Jakub Korab</author><price>49.99</price><title lang=\"en\">Apache Camel Developer's Cookbook</title><year>2013</year></book>" +
            "</bookstore>\r\n", response);*/
    }
   @Test
   public void testXmlJsonUnmarshalEvents() throws Exception {
      
       final String request = "[" +
              "{\"event_id\":\"57\",\"subject\":\"1309\",\"event_date\":\"1995-01-13\",\"type\":\"W\",\"end\":\"1999-01-04\",\"force_code\":\"50EC/1437/04121996\",\"offence\":\"BURGLARY\",\"power_of_arrest\":\"ARREST\",\"report_date\":\"1996-01-04\"}"+
              ",{\"event_id\":\"58\",\"subject\":\"1309\",\"event_date\":\"1996-01-15\",\"type\":\"W\",\"end\":\"1999-01-01\",\"force_code\":\"50EL/0835/01101996\",\"offence\":\"BREACH OF COURT ORDER\",\"power_of_arrest\":\"WARRANT\",\"report_date\":\"1996-01-01\"}" 
              +"]";
       
       final String response = template.requestBody("direct:unmarshalEvents", request, String.class);

       log.info(response);
      /* assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
           "<bookstore>" +
           "<book category=\"COOKING\"><author>Giada De Laurentiis</author><price>30.00</price><title lang=\"en\">Everyday Italian</title><year>2005</year></book>" +
           "<book category=\"CHILDREN\"><author>J K. Rowling</author><price>29.99</price><title lang=\"en\">Harry Potter</title><year>2005</year></book>" +
           "<book category=\"WEB\"><author>Erik T. Ray</author><price>39.95</price><title lang=\"en\">Learning XML</title><year>2003</year></book>" +
           "<book category=\"PROGRAMMING\"><author>Scott Cranton</author><author>Jakub Korab</author><price>49.99</price><title lang=\"en\">Apache Camel Developer's Cookbook</title><year>2013</year></book>" +
           "</bookstore>\r\n", response);*/
   }
}

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

package  com.ipl.people.xmltransformer;

import java.util.Arrays;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;

public class XmlJsonRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
      /*  from("direct:marshal")
            .marshal().xmljson()
            .to("mock:marshalResult");

        XmlJsonDataFormat xmlJsonFormat1 = new XmlJsonDataFormat();
        //xmlJsonFormat.setRootName("bookstore");
       // xmlJsonFormat.setElementName("book");
       // xmlJsonFormat.setExpandableProperties(Arrays.asList("author", "author"));
        xmlJsonFormat1.setRootName("person");
        from("direct:unmarshal")
            .unmarshal(xmlJsonFormat1)
            .to("mock:unmarshalResult");*/

        XmlJsonDataFormat xmlJsonEventsFormat = new XmlJsonDataFormat();
        xmlJsonEventsFormat.setRootName("events");
        xmlJsonEventsFormat.setElementName("event");

        from("direct:unmarshalEvents")
            .unmarshal(xmlJsonEventsFormat)
            .to("mock:unmarshalEventsResult");
        
        XmlJsonDataFormat xmlJsonEventFormat = new XmlJsonDataFormat();
        xmlJsonEventFormat.setRootName("event");

        from("direct:unmarshalEvent")
            .unmarshal(xmlJsonEventFormat)
            .to("mock:unmarshalEventResult");
        
        XmlJsonDataFormat xmlJsonPeopleFormat = new XmlJsonDataFormat();
        xmlJsonPeopleFormat.setRootName("people");
        xmlJsonPeopleFormat.setElementName("person");
        xmlJsonPeopleFormat.setExpandableProperties(Arrays.asList("given_name", "related_records"));

        from("direct:unmarshalPeople")
            .unmarshal(xmlJsonPeopleFormat)
            .to("mock:unmarshalPeopleResult");
        
        XmlJsonDataFormat xmlJsonPersonFormat = new XmlJsonDataFormat();
        xmlJsonPersonFormat.setRootName("person");
        xmlJsonPersonFormat.setExpandableProperties(Arrays.asList("given_name", "nationality", "related_records", "alias_dates_of_birth", "aliases", "status" ));

        from("direct:unmarshalPerson")
            .unmarshal(xmlJsonPersonFormat)
            .to("mock:unmarshalPersonResult");
    }
}

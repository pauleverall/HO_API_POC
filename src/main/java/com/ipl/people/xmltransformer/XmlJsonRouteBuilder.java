
package  com.ipl.people.xmltransformer;

import java.util.Arrays;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;


/**
 * JSON to XML transformation class.
 * 
 * @author Asha
 *
 */
public class XmlJsonRouteBuilder extends RouteBuilder {
    /* (non-Javadoc)
     * @see org.apache.camel.builder.RouteBuilder#configure()
     */
    @Override
    public void configure() throws Exception {

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

package com.ipl.people;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.ipl.people.authentication.Authentication;
import com.ipl.people.authentication.AuthenticationDAO;
import com.ipl.people.authentication.AuthenticationFilter;
import com.ipl.people.v2.core.PersonStatusV2;
import com.ipl.people.v2.core.StatusesV2;
import com.ipl.people.xmltransformer.XmlJsonRouteBuilder;

public class PersonApplication extends Application<PersonConfiguration> {
	public static void main(String[] args) throws Exception {
		new PersonApplication().run(args);
	}

	private final HibernateBundle<PersonConfiguration> hibernateBundle = new HibernateBundle<PersonConfiguration>(
			com.ipl.people.v1.core.Person.class,
			com.ipl.people.v1.core.Location.class,
			com.ipl.people.v1.core.NameAlias.class,
			com.ipl.people.v1.core.PersonNationality.class,
			com.ipl.people.v1.core.AliasDOB.class,
			com.ipl.people.v1.core.Identifier.class,
			com.ipl.people.v1.core.DuplicatePerson.class,
			com.ipl.people.v1.core.Event.class,
			com.ipl.people.v1.core.FindPerson.class,
			com.ipl.people.v2.core.PersonV2.class,
			com.ipl.people.v2.core.LocationV2.class,
			com.ipl.people.v2.core.NameAliasV2.class,
			com.ipl.people.v2.core.PersonNationalityV2.class,
			com.ipl.people.v2.core.AliasDOBV2.class,
			com.ipl.people.v2.core.IdentifierV2.class,
			com.ipl.people.v2.core.DuplicatePersonV2.class,
			com.ipl.people.v2.core.EventV2.class,
			com.ipl.people.v2.core.FindPersonV2.class, PersonStatusV2.class,
			StatusesV2.class, Authentication.class) {

		public DataSourceFactory getDataSourceFactory(
				PersonConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}

	};

	@Override
	public String getName() {
		return "People API";
	}

	@Override
	public void initialize(Bootstrap<PersonConfiguration> bootstrap) {
		bootstrap.addBundle(hibernateBundle);
		bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/image/"));
	}

	@Override
	public void run(PersonConfiguration configuration, Environment environment)
			throws Exception {

		// Version 1
		final com.ipl.people.v1.dao.PersonDAO personDAOV1 = new com.ipl.people.v1.dao.PersonDAO(
				hibernateBundle.getSessionFactory());

		final com.ipl.people.v1.resources.PersonResource personResourceV1 = new com.ipl.people.v1.resources.PersonResource(
				personDAOV1);
		environment.jersey().register(personResourceV1);

		final com.ipl.people.v1.dao.FindPersonDAO findPersonDAOV1 = new com.ipl.people.v1.dao.FindPersonDAO(
				hibernateBundle.getSessionFactory());

		final com.ipl.people.v1.resources.FindPersonResource findPersonResourceV1 = new com.ipl.people.v1.resources.FindPersonResource(
				findPersonDAOV1);
		environment.jersey().register(findPersonResourceV1);

		final com.ipl.people.v1.dao.EventDAO eventDAOV1 = new com.ipl.people.v1.dao.EventDAO(
				hibernateBundle.getSessionFactory());

		final com.ipl.people.v1.resources.EventResource eventResourceV1 = new com.ipl.people.v1.resources.EventResource(
				eventDAOV1);
		environment.jersey().register(eventResourceV1);

		// Version 2
		final CamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new XmlJsonRouteBuilder());
		ProducerTemplate template = camelContext.createProducerTemplate();

		final com.ipl.people.v2.dao.PersonDAO personDAOV2 = new com.ipl.people.v2.dao.PersonDAO(
				hibernateBundle.getSessionFactory());

		final com.ipl.people.v2.resources.PersonResource personResourceV2 = new com.ipl.people.v2.resources.PersonResource(
				personDAOV2);
		environment.jersey().register(personResourceV2);
		
		final com.ipl.people.v2.resources.PersonXMLResource personXMLResourceV2 = new com.ipl.people.v2.resources.PersonXMLResource(
				personDAOV2, camelContext, template);
		environment.jersey().register(personXMLResourceV2);

		final com.ipl.people.v2.dao.FindPersonDAO findPersonDAOV2 = new com.ipl.people.v2.dao.FindPersonDAO(
				hibernateBundle.getSessionFactory());

		final com.ipl.people.v2.resources.FindPersonResource findPersonResourceV2 = new com.ipl.people.v2.resources.FindPersonResource(
				findPersonDAOV2);
		environment.jersey().register(findPersonResourceV2);

		final com.ipl.people.v2.resources.FindPersonXMLResource findPersonXMLResourceV2 = new com.ipl.people.v2.resources.FindPersonXMLResource(
				findPersonDAOV2, camelContext, template);
		environment.jersey().register(findPersonXMLResourceV2);

		final com.ipl.people.v2.dao.EventDAO eventDAOV2 = new com.ipl.people.v2.dao.EventDAO(
				hibernateBundle.getSessionFactory());

		final com.ipl.people.v2.resources.EventResource eventResourceV2 = new com.ipl.people.v2.resources.EventResource(
				eventDAOV2);
		environment.jersey().register(eventResourceV2);

		final com.ipl.people.v2.resources.EventXMLResource eventXMLResourceV2 = new com.ipl.people.v2.resources.EventXMLResource(
				eventDAOV2, camelContext, template);
		environment.jersey().register(eventXMLResourceV2);

		final AuthenticationDAO guidDao = new AuthenticationDAO(
				hibernateBundle.getSessionFactory());

		environment
				.servlets()
				.addFilter("AuthenticationFilter",
						new AuthenticationFilter(guidDao))
				.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),
						true, "/v2/*");
		;

	}

}

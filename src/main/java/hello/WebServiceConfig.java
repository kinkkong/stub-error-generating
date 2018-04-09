package hello;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	public static final String HTTP_HAOS_IO_GANT_SERVICE = "http://haos.io/gant/flights";


	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean
	public DefaultWsdl11Definition defaultWsdl11Definition() {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("flightPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(HTTP_HAOS_IO_GANT_SERVICE);
		wsdl11Definition.setSchema(countriesSchema());
		return wsdl11Definition;
	}

//	@Bean(name = "flights")
//	public DefaultWsdl11Definition flightsWsdl11Definition(XsdSchema flightSchema){
//		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
//		defaultWsdl11Definition.setPortTypeName("flightsPort");
//		defaultWsdl11Definition.setLocationUri("/ws");
//		defaultWsdl11Definition.setTargetNamespace(HTTP_HAOS_IO_GANT_FLIGHTS);
//		defaultWsdl11Definition.setSchema(flightSchema);
//		return defaultWsdl11Definition;
//	}

//    @Bean
//    public CommonsXsdSchemaCollection holidayXsd() {
//        CommonsXsdSchemaCollection collection =
//                new CommonsXsdSchemaCollection(new Resource[] { new ClassPathResource("/countries.xsd"), new ClassPathResource("/serviceclass.xsd")});
//        collection.setInline(true);
//        return collection;
//    }

	@Bean
	public XsdSchema countriesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
	}
//
//	@Bean
//	public XsdSchema flightSchema(){
//		return new SimpleXsdSchema(new ClassPathResource("serviceclass.xsd"));
//	}
}

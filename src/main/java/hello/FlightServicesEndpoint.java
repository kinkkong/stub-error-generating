package hello;

import io.haos.gant.flights.GetFlightRequest;
import io.haos.gant.flights.GetFlightResponse;
import io.haos.gant.flights.ServiceList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class FlightServicesEndpoint {

    private final static Logger logger = LoggerFactory.getLogger(FlightServicesEndpoint.class);

    private static final String NAMESPACE_URI = "http://haos.io/gant/flights";

    @Autowired
    private FlightRepository flightRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFlightRequest")
    @ResponsePayload
    public GetFlightResponse getFlightServices(@RequestPayload GetFlightRequest request) {

        logger.debug("Request: {} date: {}", request.getFlightId(), request.getFlightDate() );
        GetFlightResponse response = new GetFlightResponse();
        response.setService( new ServiceList() );
        List<ServiceList.FlightService> listOfServices = flightRepository.findListOfServices(request.getFlightId());
        response.getService().getFlightService().addAll(listOfServices);

        return response;
    }

}

package hello;

import io.haos.gant.service.GetFlightRequest;
import io.haos.gant.service.GetFlightResponse;
import io.haos.gant.service.ServiceList;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class FlightServicesEndpoint {

    private static final String NAMESPACE_URI = "http://haos.io/gant/service";

    private FlightRepository flightRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFlightServices")
    @ResponsePayload
    public GetFlightResponse getFlightServices(@RequestPayload GetFlightRequest request) {
        GetFlightResponse response = new GetFlightResponse();

        List<ServiceList.FlightService> listOfServices = flightRepository.findListOfServices(request.getFlightId());
        response.getService().getFlightService().addAll(listOfServices);

        return response;
    }

}

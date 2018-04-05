package hello;

import com.google.common.collect.Lists;
import io.haos.gant.service.ServiceList;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class FlightRepository {

    Map<String, List<ServiceList.FlightService>> flightServicesList = new HashMap();
    List<String> namesOfServicesAvailable = Arrays.asList("ASER", "BSER", "CSER", "DSER");

    ServiceList findListOfServices(String flightId){

        ServiceList serviceList = new ServiceList();

        if(flightServicesList.containsKey(flightId)){
            List<ServiceList.FlightService> flightServices = flightServicesList.get(flightId);
        }

        return new ServiceList();

    }

    private List<ServiceList.FlightService> createFlightData(String flightId) {

        List<ServiceList.FlightService> listOfServices = Lists.newArrayList();
        Random ran = new Random();
        String nameOfService = namesOfServicesAvailable.get(ran.nextInt(namesOfServicesAvailable.size()));

        ServiceList.FlightService flightService = new ServiceList.FlightService();
        flightService.setStartDate(LocalDateTime.now());
        flightService.setEndDate(LocalDateTime.now().plusHours(1l));
        flightService.setName(nameOfService);

        listOfServices.add(flightService);
        return listOfServices;
    }

}

import com.keyin.ClientApplication;
import com.keyin.RESTClient;
import com.keyin.entity.Aircraft;
import com.keyin.entity.Airport;
import com.keyin.entity.City;
import com.keyin.entity.Passenger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ClientApplicationTest {
    @Mock
    private RESTClient mockRESTClient;

    @Test
    public void testGenerateAirportReport() {
        ClientApplication httpRestCLIApplicationUnderTest = new ClientApplication();

        Airport stJohnsAirport = new Airport();
        stJohnsAirport.setCode("YYT");
        stJohnsAirport.setName("St. John's Airport");
        stJohnsAirport.setCityId(1);

        List<Airport> airportList = new ArrayList<>();
        airportList.add(stJohnsAirport);

        Mockito.when(mockRESTClient.getAllAirports()).thenReturn(airportList);

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateAirportReport().contains("YYT"));
    }

    @Test
    public void testGenerateAircraftReport() {
        ClientApplication httpRestCLIApplicationUnderTest = new ClientApplication();

        Aircraft boeingAircraft = new Aircraft();
        boeingAircraft.setType("Boeing747");
        boeingAircraft.setAirlineName("Sunwing");
        boeingAircraft.setNumberOfPassengers(419);

        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(boeingAircraft);

        Mockito.when(mockRESTClient.getAllAircraft()).thenReturn(aircraftList);

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateAircraftReport().contains("Boeing747"));
    }

    @Test
    public void testGenerateCityReport() {
        ClientApplication httpRestCLIApplicationUnderTest = new ClientApplication();

        City cityOfStJohns = new City();
        cityOfStJohns.setName("St. John's");
        cityOfStJohns.setState("Newfoundland");
        cityOfStJohns.setPopulation(500000);

        List<City> cityList = new ArrayList<>();
        cityList.add(cityOfStJohns);

        Mockito.when(mockRESTClient.getAllCity()).thenReturn(cityList);

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generateCityReport().contains("Newfoundland"));
    }

    @Test
    public void testGeneratePassengerReport() {
        ClientApplication httpRestCLIApplicationUnderTest = new ClientApplication();

        Passenger glenSturge = new Passenger();
        glenSturge.setFirstName("Glen");
        glenSturge.setLastName("Sturge");
        glenSturge.setPhoneNumber("709-722-2222");

        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(glenSturge);

        Mockito.when(mockRESTClient.getAllPassengers()).thenReturn(passengerList);

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.generatePassengerReport().contains("Glen"));
    }

    @Test
    public void testGetAirportById() {
        ClientApplication httpRestCLIApplicationUnderTest = new ClientApplication();

        Airport stJohnsAirport = new Airport();
        stJohnsAirport.setCode("YYT");
        stJohnsAirport.setName("St. John's Airport");
        stJohnsAirport.setCityId(1);
        List<Airport> airportList = new ArrayList<>();
        airportList.add(stJohnsAirport);

        Mockito.when(mockRESTClient.getAirportById(0)).thenReturn(airportList.get(0));

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.getAirportById(0).contains("YYT"));
    }

    @Test
    public void testGetPassengerById() {
        ClientApplication httpRestCLIApplicationUnderTest = new ClientApplication();

        Passenger glenSturge = new Passenger();
        glenSturge.setFirstName("Glen");
        glenSturge.setLastName("Sturge");
        glenSturge.setPhoneNumber("709-722-2222");

        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(glenSturge);

        Mockito.when(mockRESTClient.getPassengerById(0)).thenReturn(passengerList.get(0));

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

        Assertions.assertTrue(httpRestCLIApplicationUnderTest.getPassengerById(0).contains("Glen"));
    }

    @Test
    public void testGetFlightPlan() {
        ClientApplication httpRestCLIApplicationUnderTest = new ClientApplication();

        Airport sampleAirport = new Airport();
        sampleAirport.setId(0);
        sampleAirport.setName("Pearson");
        sampleAirport.setCode("YYZ");

        Aircraft sampleAircraft = new Aircraft();
        sampleAircraft.setType("Boeing747");
        sampleAircraft.setAirlineName("Air Canada");
        sampleAircraft.setNumberOfPassengers(50);
        sampleAircraft.appendAirport(0);

        List<Airport> airportList = new ArrayList<>();
        airportList.add(sampleAirport);

        Mockito.when(mockRESTClient.getAirportById(0)).thenReturn(airportList.get(0));

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

    }

    @Test
    public void testGetAirportsByCity() {
        ClientApplication httpRestCLIApplicationUnderTest = new ClientApplication();

        City cityOfStJohns = new City();
        cityOfStJohns.setName("St. John's");
        cityOfStJohns.setState("Newfoundland");
        cityOfStJohns.appendAirport(0);

        Airport stJohnsAirport = new Airport();
        stJohnsAirport.setCode("YYT");
        stJohnsAirport.setName("St. John's Airport");
        stJohnsAirport.setCityId(0);

        List<Long> airportIdList = new ArrayList<>();
        airportIdList.add(0L);

        Mockito.when(mockRESTClient.getCityById(0L)).thenReturn(cityOfStJohns);
        Mockito.when(mockRESTClient.getAirportById(0L)).thenReturn(stJohnsAirport);

        httpRestCLIApplicationUnderTest.setRestClient(mockRESTClient);

//        Assertions.assertTrue(httpRestCLIApplicationUnderTest.getCityById(0));
    }

    @Test
    public void testShowAirportsByPassenger() {
        ClientApplication httpRestCLIApplicationUnderTest = new ClientApplication();

        Airport stJohnsAirport = new Airport();
        stJohnsAirport.setCode("YYT");
        stJohnsAirport.setName("St. John's Airport");

        Passenger samplePassenger = new Passenger();
        samplePassenger.setFirstName("Jonah");
        samplePassenger.setLastName("Greening");
        samplePassenger.setPhoneNumber("123-1234");
    }

}

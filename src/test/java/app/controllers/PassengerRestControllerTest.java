package app.controllers;

import app.entities.Passenger;
import app.entities.Passport;
import app.services.repositories.PassengerRepository;
import app.util.PassengerAndPassportCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;


import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for passenger REST controller<br>
 * At this moment I don't know exactly which kind of tests should present
 */

//@SpringBootTest

//@TestExecutionListeners({
//        DependencyInjectionTestExecutionListener.class,
//        TransactionDbUnitTestExecutionListener.class
//})
@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@TestExecutionListeners({
//        DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
//        DbUnitTestExecutionListener.class
//})

@ExtendWith(SpringExtension.class)
@DbUnitConfiguration(databaseConnection="dataSource")
//@DbUnitConfiguration(databaseConnection = "newDb")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
class PassengerRestControllerTest {

    /**
     * URL to controller for passenger entity
     */
    final static String URI_TEMPLATE = "/passengers";


//    @Autowired
//    private MockMvc mockMvc;

//    @Autowired
//    private ObjectMapper objectMapper;
//
    @Autowired
    private PassengerRepository passengerRepository;

    @Test
//    @DatabaseSetup("/ds.xml")
//    @ExpectedDatabase("dsasd1.xml")
    public void psvm()
    {
        Passenger p = PassengerAndPassportCreator.createOnePassengersAndPassports();
        passengerRepository.save(p);

        Passport pass = p.getPassport();
        System.out.println( pass.getPassenger().getFirstName());

        System.out.println("This is sparta");
    }


//    /**
//     * Trying to add 3 passengers to DB and check result
//     * @throws Exception
//     */
//    @Test
//    void postThreePassengers() throws Exception {
//
//        Passenger passenger0 = PassengerAndPassportCreator.createThreePassengersAndPassports().get(0);
//        Passenger passenger1 = PassengerAndPassportCreator.createThreePassengersAndPassports().get(1);
//        Passenger passenger2 = PassengerAndPassportCreator.createThreePassengersAndPassports().get(2);
//
//
//        mockMvc.perform(post(URI_TEMPLATE)
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(passenger0)))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(post(URI_TEMPLATE)
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(passenger1)))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(post(URI_TEMPLATE)
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(passenger2)))
//                .andExpect(status().isOk());
//
//        Passenger passenger2FromDB = passengerRepository.findByFirstName("Jacov");
//        assertThat(passenger2FromDB.getEmail()).isEqualTo("jp@italteplo.su");
//        Passenger passenger0FromDB = passengerRepository.findByFirstName("Vadim");
//        assertThat(passenger0FromDB.getEmail()).isEqualTo("vp@italteplo.su");
//        Passenger passenger1FromDB = passengerRepository.findByFirstName("Andrey");
//        assertThat(passenger1FromDB.getEmail()).isEqualTo("ae@italteplo.su");
//    }
//
//
//    /**
//     * Trying to get passenger from DB by presented id
//     * @throws Exception
//     */
//    @Test
//    void getPassengerById() throws Exception {
//        mockMvc.perform(get(URI_TEMPLATE+"/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.firstName").value("Vadim"));
//    }
//
//    /**
//     * Trying to get all passengers from DB
//     * @throws Exception
//     */
//    @Test
//    void getAllPassengers() throws Exception {
//
//        mockMvc.perform(get(URI_TEMPLATE))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(3));
//    }
//
//    /**
//     * Trying to update passenger(presented in DB)
//     * @throws Exception
//     */
//    @Test
//    void putOnePassenger() throws Exception {
//        Passenger passenger = passengerRepository.findByFirstName("JACOV");
//        passenger.setFirstName("MARIA");
//        mockMvc.perform(put(URI_TEMPLATE)
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(passenger)))
//                .andExpect(status().isOk());
//
//        Passenger passengerFromDB = passengerRepository.findByFirstName("MARIA");
//        assertThat(passengerFromDB.getEmail()).isEqualTo("jp@italteplo.su");
//    }
//
//
//    /**
//     * Trying to get passenger with incorrect id
//     * @throws Exception
//     */
//    @Test
//    void getPassengerWithIncorrectId()  throws Exception  {
//        assertThrows(NoSuchElementException.class, () -> {
//            try {
//                mockMvc.perform(get(URI_TEMPLATE+"/-1"))
//                        .andExpect(status().isOk())
//                        .andExpect(jsonPath("$.firstName").value("Vadim"));
//            } catch (NestedServletException nse) {
//                throw nse.getCause();
//            }
//        });
//    }
}
package com.example.testDaZero.zeroTest.model.repositories;
import com.example.testDaZero.zeroTest.model.beans.Taxi;
import com.example.testDaZero.zeroTest.model.exceptions.AlreadyPresentException;
import com.example.testDaZero.zeroTest.model.exceptions.NotFoundException;
import com.example.testDaZero.zeroTest.model.exceptions.NullValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith( SpringExtension.class )
public class TaxiRepositoryTests {
    static private Map<String, Taxi> repoMap = new HashMap<>();
    private Taxi testTaxi=new Taxi( "DEF866CB","Mario","Rossi",true );
    private Taxi replacedTaxi= new Taxi("DEF866CB","Franco","Aghislov",true);



    @TestConfiguration
    static class TaxiRepositoryTestConfiguration{

        @Bean
        public TaxiRepositoryInterface taxiRepository(){
            return new TaxiRepositoryImpl(repoMap);
        }
    }

    @Autowired
    private TaxiRepositoryInterface taxiRepository;

    @BeforeEach
    public void resetMap() {
        repoMap.clear();
    }

    @Test
    public void add_taxi_ok()  {
        assertDoesNotThrow(() -> {
            taxiRepository.addTaxi( testTaxi );
        });
        Assert.isTrue(repoMap.containsKey(testTaxi.getLicensePlate()), "Taxi_ok:failed");
    }

    @Test
    public void add_taxi_null(){
        assertThrows(NullValueException.class, () -> {
            taxiRepository.addTaxi( null);
        });
    }
    @Test
    public void add_taxi_present(){
        add_taxi_ok();

        assertThrows( AlreadyPresentException.class, () -> {
            taxiRepository.addTaxi( testTaxi );
        });
    }

    @Test
    public void remove_taxi_ok(){
        add_taxi_ok();
        assertDoesNotThrow(() -> {
            taxiRepository.removeTaxi( testTaxi.getLicensePlate() );
        });
    }

    @Test
    public void remove_taxi_null(){
        add_taxi_ok();
        assertThrows( NotFoundException.class, () -> {
            taxiRepository.removeTaxi( null);
        });
    }
    @Test
    public void remove_taxi_not_ok(){
        add_taxi_ok();
        assertThrows( NotFoundException.class, () -> {
            taxiRepository.removeTaxi( "ruaèhgq354u");
        });
    }

    @Test
    public void modify_taxi_ok() throws NotFoundException {
        add_taxi_ok();
        taxiRepository.modifyTaxi( replacedTaxi );
        Taxi taxi = taxiRepository.getTaxi( replacedTaxi.getLicensePlate() );
        assertEquals(replacedTaxi,taxi );
        assertNotEquals(testTaxi, taxi );
    }

  /*  public void modify_taxi_null(){

        assertThrows( NotFoundException.class, () -> {
            taxiRepository.modifyTaxi( null);
        });

    }*/
    @Test
    public void get_taxi_ok() {
        add_taxi_ok();
         assertDoesNotThrow(()-> {
            taxiRepository.getTaxi( testTaxi.getLicensePlate());
        });
    }
}

package com.example.testDaZero.zeroTest.model.repositories;
import com.example.testDaZero.zeroTest.model.beans.Taxi;
import com.example.testDaZero.zeroTest.model.exceptions.AlreadyPresentException;
import com.example.testDaZero.zeroTest.model.exceptions.NotFoundException;
import com.example.testDaZero.zeroTest.model.exceptions.NullValueException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TaxiRepositoryImpl implements TaxiRepositoryInterface{     //database dei taxi
    private Map<String, Taxi>taxiMap;

    public TaxiRepositoryImpl(Map<String, Taxi> taxiMap) {
        this.taxiMap = taxiMap;
    }
    public TaxiRepositoryImpl() {
        this.taxiMap = new HashMap<>();
    }

    public void addTaxi(Taxi taxi)throws NullValueException,AlreadyPresentException{
        if(taxi==null){
            throw new NullValueException( "is not allowed to insert an empty taxi " );
        }

        if(taxiMap.putIfAbsent( taxi.getLicensePlate(),taxi )!=null){
            throw new AlreadyPresentException(" Taxi already inserted");
        }

       /*
       Taxi taxiInMap= taxiMap.get( taxi.getLicensePlate() );
        if(taxiInMap!=null){
            throw new AlreadyPresentException( "Taxi already inserted " );
        }

        taxiMap.put( taxi.getLicensePlate(),taxi );
        */
    }

    public void removeTaxi(String licensePlate)throws NotFoundException {
        if(taxiMap.remove( licensePlate )==null){
            throw new NotFoundException( "licence plate is not corresponding to a taxi" );
        }


    }

    public Taxi modifyTaxi(Taxi newTaxi)throws NotFoundException{
      if(taxiMap.replace( newTaxi.getLicensePlate(),newTaxi )==null) {
          throw new NotFoundException( "no taxi no party" );
      }
        return newTaxi;
    }

    public Taxi getTaxi(String licensePlate) throws NotFoundException {
        Taxi foundTaxi = taxiMap.get( licensePlate );
        if((foundTaxi==null)) {
            throw new NotFoundException( "no taxi no party" );
        }
        return foundTaxi;
    }

}

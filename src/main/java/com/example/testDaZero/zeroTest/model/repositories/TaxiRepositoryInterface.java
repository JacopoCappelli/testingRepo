package com.example.testDaZero.zeroTest.model.repositories;

import com.example.testDaZero.zeroTest.model.beans.Taxi;
import com.example.testDaZero.zeroTest.model.exceptions.AlreadyPresentException;
import com.example.testDaZero.zeroTest.model.exceptions.NotFoundException;
import com.example.testDaZero.zeroTest.model.exceptions.NullValueException;

public interface TaxiRepositoryInterface {
    public void addTaxi(Taxi taxi)throws NullValueException, AlreadyPresentException;
    public void removeTaxi(String licensePlate)throws NotFoundException;
    public Taxi modifyTaxi(Taxi newTaxi)throws NotFoundException;
    public Taxi getTaxi(String licensePlate) throws NotFoundException;
}

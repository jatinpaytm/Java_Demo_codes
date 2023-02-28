package com.semanticsquare.assignment;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.*;

public class TicketReservation {

    private static final int CONFIRMEDLIST_LIMIT = 10;
    private static final int WAITINGLIST_LIMIT = 3;

    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();

    // This getter is used only by the junit test case.
    public List<Passenger> getConfirmedList() {
        return confirmedList;
    }

    /**
     * Returns true if passenger could be added into either confirmedList or
     * waitingList. Passenger will be added to waitingList only if confirmedList
     * is full.
     *
     * Return false if both passengerList & waitingList are full
     */
    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass,String confirmationNumber) {



        if(confirmedList.size()<CONFIRMEDLIST_LIMIT){
            Passenger passenger = new Passenger(firstName,lastName,age,gender,travelClass,confirmationNumber);
            confirmedList.add(passenger);
            return true;
        }
        else if(waitingList.size()<WAITINGLIST_LIMIT){
            Passenger passenger = new Passenger(firstName,lastName,age,gender,travelClass,confirmationNumber);
            waitingList.add(passenger);
            return true;
        }
        else
            return false;

    }

    /**
     * Removes passenger with the given confirmationNumber. Passenger could be
     * in either confirmedList or waitingList. The implementation to remove the
     * passenger should go in removePassenger() method and removePassenger method
     * will be tested separately by the uploaded test scripts.

     * If passenger is in confirmedList, then after removing that passenger, the
     * passenger at the front of waitingList (if not empty) must be moved into
     * passengerList. Use poll() method (not remove()) to extract the passenger
     * from waitingList.
     */
    public boolean cancel(String confirmationNumber)
    {
        System.out.println(confirmationNumber);
        boolean isThere=false;
        for(ListIterator iterator = confirmedList.listIterator(); iterator.hasNext();)
        {
            Object x=iterator.next();
            ((Passenger)x).getConfirmationNumber();
            //String x=iterator.next().
            isThere=removePassenger(iterator,confirmationNumber);

            if(isThere) {
                break;
            }

        }

        if(isThere==false) {
            Deque<Passenger> waitingList1=new ArrayDeque<>();

            while(waitingList.isEmpty()==false)
            {

                Passenger obj=waitingList.poll();
                if(obj.getConfirmationNumber()==confirmationNumber)
                {
                    isThere=true;
                }
                else {
                    waitingList1.add(obj);
                }
            }
            waitingList=waitingList1;
            waitingList1.clear();
        }
        return isThere;
    }

    /**
     * Removes passenger with the given confirmation number.
     * Returns true only if passenger was present and removed. Otherwise, return false.
     */
    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {

        boolean found=false;
        Passenger obj = confirmedList.get(((ListIterator<?>)iterator).nextIndex());
        iterator.next();
        System.out.println(obj.getConfirmationNumber());
        if(obj.getConfirmationNumber() == confirmationNumber) {
            iterator.remove();
            found = true;

        }
        return found;

    }

}
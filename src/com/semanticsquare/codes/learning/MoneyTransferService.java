package com.semanticsquare.codes.learning;

// Note : we can acces the other class of same package without and restriction
// like we did in this
public class MoneyTransferService {

    // first make an object so that we can use it to acces its methods
    CurrencyConverter cc = new CurrencyConverter();

    // made below 2 functions
    double computeTransferAmount(int countryIndex, double amount) {
            return cc.computeTransferAmount(countryIndex, amount);		
    }

    double computeTransferFee(int countryIndex, double amount) {
            double transferAmount = computeTransferAmount(countryIndex, amount);
            double transferFee = transferAmount * 0.02;
            return transferFee;
    }
    // below main function given in question
    public static void main(String[] args) {
            MoneyTransferService transferService = new MoneyTransferService();
    
            double transferAmount = transferService.computeTransferAmount(0, 1000);
            double transferFee = transferService.computeTransferFee(0, 1000);
    
            System.out.println("transferAmount: " + transferAmount);
            System.out.println("transferFee: " + transferFee);
    }

}

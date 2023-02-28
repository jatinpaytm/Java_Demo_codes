package com.semanticsquare.codes.learning;

public class IRUtil {

    public static int getFrequencyCount(String term, String doc) 
    {
        String term1=term.toLowerCase();
        String doc1=doc.toLowerCase();
        // Step 1: Convert both term & doc into lower-case
        int frequencyCount=0;

        // Step 2: Initialize variable frequencyCount to 0
        int ind=0;
        // Step 3: Initialize variable index with the index position of term in doc. Hint: Use indexOf() method
        while(ind<doc1.length())
        {
        // if match found increase count
            if (doc1.indexOf(term1,ind)>=0)
            {
                ind=doc1.indexOf(term1,ind);
                //System.out.println(ind);
                frequencyCount++;
                ind+=(term.length());
                //System.out.println(ind);
            }
            else
            {
                break;
            }
            
        }
        // Step 4: If index >= 0 (i.e.., term appears in doc), then go to next step. Else go to last step.

        // Step 5: Increment frequencyCount

        // Step 6: Re-compute doc with the string appearing after term till end of doc. Hint: You can use substring() & length() methods

        // Step 7: Re-compute index with index position of term in the re-computed doc. Go to step 4 to continue processing.

        // Step 8: Return frequencyCount
        //System.out.println(frequencyCount);
        return frequencyCount;

    }

    public static double termFrequency(String term, String doc) {

        int frequencyCount = getFrequencyCount(term, doc);
        
        // Step 2: Create variable totalTermCount to hold the total number of terms appearing in doc. Hint: You can use split() with white-space " " as delimiter
        
        //int totalTermCount=doc.split(" ").length; // can use this also

        String tot[]=doc.split(" ");
        int totalTermCount=tot.length;
        //System.out.println(totalTermCount);
        // Step 3: return frequencyCount / totalTermCount. This is the tf formula.
        double ans=(double)frequencyCount/totalTermCount;
        return ans;
    }

    public static void main(String[] args) {
        String doc = "Hello planet earth, you are a great planet.";
        String term="planet";
        double ans = termFrequency(term, doc);
        System.out.print(ans);
    }
}
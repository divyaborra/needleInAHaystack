/*
 * Copyright 2017 Marc Liberatore.
 */

package log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.opencsv.CSVReader;

public class LogParser {	
	/**
	 * Returns a list of SuspectEntries corresponding to the CSV data supplied by the given Reader.
	 * 
	 * The data contains one or more lines of the format:
	 * 
	 * Marc,413-545-3061,1234567890
	 * 
	 * representing a name, phone number, and passport number.
	 * 
	 * @param r an open Reader object
	 * @return a list of SuspectEntries
	 * @throws IOException
	 */
	public static List<SuspectEntry> parseLog(Reader r) throws IOException {
		    
		    Scanner s = new Scanner(r);
	        List<SuspectEntry> suspectEntries = new ArrayList<SuspectEntry>();
	        String[] myArr;
	        while (s.hasNextLine()) {
	        	myArr = s.nextLine().split(",");
	            suspectEntries.add(new SuspectEntry(myArr[0], myArr[1], myArr[2]));
	        }
	        s.close();
	        //return Arrays.asList(suspectEntries.toArray(a));
	        return suspectEntries;        
	        
		    
	    }
	/**
	 * Returns a sorted list of SuspectEntries whose passport numbers are common to all 
	 * of the supplied entryLists.
	 * 
	 * The list is sorted lexicographically by passport number, breaking ties by name 
	 * and then by phone number.
	 * 
	 * @param entryLists a list of lists of SuspectEntries
	 * @return a sorted list of SuspectEntries whose passport numbers are common to all 
	 * of the supplied entryLists
	 */
	public static List<SuspectEntry> findCommonEntries(List<List<SuspectEntry>> entryLists) {	
		List<Set<String>> listOfSets=getAllPassports(entryLists);
		
		Set<String> commonPassports= findCommonPassports(listOfSets);
		
		List<SuspectEntry> commonSuspects= findSuspects(entryLists, commonPassports);
		
		for (SuspectEntry s: commonSuspects) {
			System.out.println("a2 = " + s);
		}
		
		Collections.sort(commonSuspects);
		return commonSuspects;
	}
	
	public static List<Set<String>> getAllPassports(List<List<SuspectEntry>> entryLists) {
		List<Set<String>> listOfSets= new ArrayList<Set<String>>();
		for (List<SuspectEntry> oneHotelList: entryLists) {
			Set<String> s1 = new HashSet<String>();
			for (SuspectEntry s: oneHotelList) {
				s1.add(s.getPassportNumber());
			}
			//add each hotel's set to list1
			listOfSets.add(s1);
		}
		return listOfSets;
	}
	
	public static List<SuspectEntry> findSuspects(List<List<SuspectEntry>> entryLists, Set<String> commonPassports) {
	
		System.out.println("a3: " + commonPassports);
		
		
		Set<SuspectEntry> commonSuspects= new HashSet<SuspectEntry>();
		
		//find all suspects that correspond with the common passport nums
		for (List<SuspectEntry> ls: entryLists) {
			for (SuspectEntry currentSuspect: ls) {
				if (commonPassports.contains(currentSuspect.getPassportNumber())){
					checkAndAdd(currentSuspect, commonSuspects);
				}
			}
		}
		
		List<SuspectEntry> commonSuspects2= new ArrayList<SuspectEntry>();
		commonSuspects2.addAll(commonSuspects);
		
		return commonSuspects2;
	}
		
	private static void checkAndAdd(SuspectEntry currentSuspect, Set<SuspectEntry> commonSuspects) {
     	for (SuspectEntry s: commonSuspects) {
			if (s.getName().equals(currentSuspect.getName()) && s.getPhoneNumber().equals(currentSuspect.getPhoneNumber())) 
				return;
		}
		commonSuspects.add(currentSuspect);
	}
	
	public static Set<String> findCommonPassports(List<Set<String>> inList) {
		Set<String> result = new HashSet<String>();
		
		if (!inList.isEmpty()) {
			result.addAll(inList.get(0));
		
		for (Set s: inList) 
			result.retainAll(s);
	}
		return result;
	}
}






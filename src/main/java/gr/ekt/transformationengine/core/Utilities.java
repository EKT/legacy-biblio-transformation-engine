// Copyright (c) 2007-2012 National Documentation Centre (EKT, www.ekt.gr)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
// 
//   1. Redistributions of source code must retain the above copyright notice,
//      this list of conditions and the following disclaimer.
// 
//   2. Redistributions in binary form must reproduce the above copyright
//      notice, this list of conditions and the following disclaimer in the
//      documentation and/or other materials provided with the distribution.
// 
//   3. The name of the author may be used to endorse or promote products
//      derived from this software without specific prior written permission.
// 
// THIS SOFTWARE IS PROVIDED BY THE AUTHOR "AS IS" AND ANY EXPRESS OR IMPLIED
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
// EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
// OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
// WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
// OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
// ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
// 
///////////////////////////////////////////////////////////////////////////////

package gr.ekt.transformationengine.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Utilities {
    
	public final static int EXCEL_AUTHORS_TO_ENDNOTE = 5;
	public final static int EXCEL_AUTHORS_TO_ENDNOTE_LC = 3;
	public final static int EXCEL_TITLE = 1;
	public final static int EXCEL_AUTHORS = 2;
	public final static int EXCEL_DEFAULT = 0;
	public final static int JUST_TO_LOWER_CASE = 4;
	
    public Utilities() {
    }
    
    
    /**a method which process the data in a cell, depending the data type
     * @param data 
     * @param dataSpec 
     * @return 
     */
    public static ArrayList<String> dataProcessing(String data,int dataSpec){
        List <String> list=new ArrayList <String>();
        String [] splitedData = null;
        String newName=null;
        int cases=0;
        
        //System.out.println("INIT DATA = "+data);
        
        if (data.equals("")){
            list.add("");
        }else{
            
            switch (dataSpec){ //the deafault is 0, in case we want to put some more data types....
                
                
         /*if the data contains the title*/
            case EXCEL_TITLE :
                
                splitedData=data.split("\"|\"|0x201C|0x94");
                
                
                //Further preprocessing of the splitted words....
                for (int j = 0 ; j < splitedData.length ; j++) {
                    splitedData[j]=splitedData[j].replaceAll("\\b\\s+\\b","");
                    splitedData[j]=splitedData[j].replaceAll(" ","");
                    splitedData[j]=splitedData[j].toLowerCase();
                    
                    list.add(splitedData[j]); //add each value to a list
                } break;
                
           
           /*if the data contains the authors*/
            case EXCEL_AUTHORS :
                
                
                splitedData=data.split("and |,");
                
               
                //Further preprocessing of the splitted words....
                for (int j = 0 ; j <splitedData.length ; j++) {
                    // System.out.println("adsasdasdsa" +j);
                    splitedData[j]=splitedData[j].replaceAll("\\b\\s+\\b","");
                    splitedData[j]=splitedData[j].replaceAll(" ","");
                    splitedData[j] = splitedData[j].substring(splitedData[j].lastIndexOf(".")+1);
                    splitedData[j]=splitedData[j].toLowerCase();
                  
                    //add each value to a list
                    if (splitedData[j].length()>3){
                        list.add(splitedData[j]);
                    }
                }    break;
                
            
                
                /*if the data contains the Excel authors and we want to convert it to EndNote Authors*/
            case EXCEL_AUTHORS_TO_ENDNOTE_LC :
                                //and|,|\\b\\s+\\b
            	data = data.replaceAll(" ","");
                splitedData=data.split("and|,");
                
                List <String> firstNameList=new ArrayList();
                List <String> lastNameList=new ArrayList();
                
                if (splitedData.length>1){
                	System.out.println("Index = "+splitedData[1].indexOf("."));
                	System.out.println("Splitted Data = "+splitedData[1]);
                    if (splitedData[1].indexOf(".")<3 && splitedData[1].indexOf(".")!=-1){
                    	list.add(data.toLowerCase());
                        
                    	
                    	
                    	break;
                    }
                }
                
                //Further preprocessing of the splitted words....
                for (int j = 0 ; j < splitedData.length ; j++) {
                    
                 
                    String [] tempData=null;
                    tempData=(splitedData[j]).split(".");
                    firstNameList.clear();
                    lastNameList.clear();
                    for (int k=0;k<tempData.length;k++) {
                        
                        tempData[k]=tempData[k].replaceAll(" ","");
                        tempData[k]=tempData[k].replaceAll("\\b\\s+\\b","");
                        tempData[k]=tempData[k].replaceAll(",","");
                        if (tempData[k].length()<2){
                            String tmp=tempData[k]+".";
                            
                            firstNameList.add(tmp);
                        } else {
                            
                            lastNameList.add(tempData[k]);
                        }
                        
                    }
                    
                    String lastName=lastNameList.toString().substring(lastNameList.toString().indexOf("[")+1,lastNameList.toString().indexOf("]"));
                    String firstName=firstNameList.toString().substring(firstNameList.toString().indexOf("[")+1,firstNameList.toString().indexOf("]"));
                    StringTokenizer tokens = new StringTokenizer(firstName, ",", false);
                    String commaless = "";
                    while (tokens.hasMoreElements()) {
                        String token = (String) tokens.nextElement();
                        //do something with it;
                        commaless += token;
                    }
                   
                    newName=lastName+", "+commaless;
                    newName=newName.toLowerCase();
                    newName=newName.trim();
                    list.add(newName);//add the newName to the return list
                    
                }
               // System.out.println(data);
               // System.out.println(list);
                break;
                
                //Just convert string to LowerCase....
            case JUST_TO_LOWER_CASE :
                data=data.toLowerCase();
                list.add(data);
                
            case EXCEL_AUTHORS_TO_ENDNOTE :
       		    //Create a pattern to match endnote style
                Pattern p = Pattern.compile("[A-Z]([A-Z]*[a-z]*[\\u0080-\\uFFFF]*)+(\\-[A-Z]([a-z]*[\\u0080-\\uFFFF]*)+)?,\\s?([A-Z][a-z]?\\.\\s?){1,5}");
      		    //Create a pattern to match excel style
                Pattern p2 = Pattern.compile("([A-Z][a-z]?\\.+\\s?){1,5}[A-Z]([A-Z]*[a-z]*[\\u0080-\\uFFFF]*)+");
                // Create a matcher with an input string
                Matcher m = p.matcher(data);
                //Create a matcher with an input string
                Matcher m2 = p2.matcher(data);
                boolean mMatch = m.find();
                boolean m2Match = m2.find();
                boolean doM = false;
                boolean doM2 = false;
                
                if (mMatch & !m2Match)
                	doM = true;
                else if (!mMatch & m2Match){
                	doM2 = true;
                }
                else if (mMatch & m2Match){
                	int iM = m.start();
                	int iM2 = m2.start();
                	if (iM<=iM2) doM=true;
                	else doM2=true;
                }
                	
                if (doM){
                	while(mMatch) {
                		System.out.println("M matcher found: "+m.group());
                		list.add(m.group().trim());
                		mMatch = m.find();
                	}
                }
                else if (doM2){
                	while(m2Match) {
                		String s=m2.group();
                		System.out.println("M2 matcher found: "+m2.group());
                		Pattern p3 = Pattern.compile("([A-Z][a-z]?\\.\\s?){1,6}");
                		Matcher m3 = p3.matcher(s);

                		if (m3.find()){
                			//System.out.println(m2.group());
                			s=s.replace(m3.group(), "")+", "+m3.group();

                			//System.out.println(s);
                			System.out.println("M2 matcher reverse: "+s);
                			list.add(s.trim());
                		}
                		m2Match = m2.find();
                	}
                }
                else
                	list.add(data);
                break;
            	
                /*in every other case*/
            default:
                
                data=data.replaceAll("\\b\\s+\\b","");
                data=data.replaceAll(" ","");
                data=data.toLowerCase();
                list.add(data);
                
                break;
            }
        }
        
        //System.out.println("Size = "+list.size()+" "+list);
        return  (ArrayList<String>) list;
        
    }
    
    
    
    /** ===>  A method which check if there are duplicates between the current data (the row which we are reading) 
     *and the data already in. The check is done between the title, the authors and the journal
     * @param dataToCheck  
     * @param inputData 
     * @param dataToCheckSpec 
     * @param cellID 
     * @return 
     */
    
    public static ArrayList duplicateCheck(ArrayList dataToCheck, ArrayList <ArrayList <ArrayList<String>>>inputData,ArrayList dataToCheckSpec,int cellID){
        String titleTest=null;
        String authorsTest;
        String sourceTest;
        List authorTest=new ArrayList();
        ArrayList <String>input=new ArrayList<String>();
        boolean duplicateFound=false;
        ArrayList <ArrayList <String>> inputTitles=new ArrayList<ArrayList <String>>();
        ArrayList <ArrayList <String>> inputAuthors=new ArrayList<ArrayList <String>>();
        ArrayList <ArrayList <String>> inputJournals=new ArrayList <ArrayList <String>>();
        List <Map> result=new ArrayList<Map>();
        Map <String, Integer> DupTitleCell=new HashMap <String, Integer>();
        Map <String, Integer> DupTitleCounts=new HashMap <String, Integer>();
        Map <String, Integer> DupTitleFound=new HashMap <String, Integer>();
        ArrayList <String> temp=new ArrayList<String>();
        int titleId=new Integer(0);
        System.out.println("Starting Duplicate Check....");
        
        
        //First, we check that there are data to be checked....
        if (dataToCheck.isEmpty()){
            DupTitleFound.put("",0);
            result.add(DupTitleFound);
            result.add(DupTitleCell);
            result.add(DupTitleCounts);
            return (ArrayList) result;
        }
        
        //If we have the first data in our input, we just set found=0 in order to be added to the inputData in the program
        if (inputData.isEmpty()) {
            if (dataToCheckSpec.contains("title"))  ///we return the title with found=0
            {
                
                titleTest=(String) dataToCheck.get(0);
                DupTitleFound.put(titleTest,0);
                result.add(DupTitleFound);
                result.add(DupTitleCell);
                result.add(DupTitleCounts);
                return (ArrayList) result;
                
            }
        }
        
        for (int k=0;k<=dataToCheck.size();k++) {
            if  (dataToCheckSpec.contains("title")){
                
                System.out.println("Start checking duplicates by title...");
                
                //Take the string in the index same as the index of "Title" in specification array
                titleTest=(String) dataToCheck.get(dataToCheckSpec.indexOf("title"));
               
                inputTitles= (ArrayList<ArrayList<String>>) inputData.get(0);
                
                Iterator it=inputTitles.iterator();
                while (it.hasNext()){
                    temp= (ArrayList<String>) it.next();
                    
                    input.add((String) temp.get(0));
                }
                //take the list of Title already in
                if (input.contains((dataProcessing(titleTest, EXCEL_TITLE)).get(0))) {
                    
                    duplicateFound=true;
                    System.out.println("FOUNDTITLE!!!!");
                    titleId=input.indexOf((dataProcessing(titleTest,EXCEL_TITLE)).get(0));
                    
                }
            }
            
            if(!duplicateFound){
                DupTitleFound.put(titleTest,0);
                break;
            }
            
            
            if  (dataToCheckSpec.contains("authors")){
                
                System.out.println("Start checking duplicates by authors...");
                
                authorsTest=(String) dataToCheck.get(dataToCheckSpec.indexOf("authors"));
                authorTest=dataProcessing(authorsTest,EXCEL_AUTHORS);
                
                inputAuthors=(ArrayList<ArrayList<String>>) inputData.get(1);
                input=inputAuthors.get(titleId);
               
                Iterator ai=authorTest.iterator();
                while(ai.hasNext()) {
                    //Check if the paper's authors are the same with the one that is already in the List
                    if( input.contains((String)ai.next()) ) {
                        System.out.println("IT'S DUPLICATE BY AUTHOR ALSO!!!!  IN CELL "+(cellID));
                        duplicateFound=true;
                    } else {
                        duplicateFound=false;
                        break;
                    }
                }
            }
                   
            if(!duplicateFound){
                DupTitleFound.put(titleTest,0);
                break;
            }
            
            if  (dataToCheckSpec.contains("source-title")){
                
                System.out.println("Start checking duplicates by journal...");
                
                inputJournals=(ArrayList<ArrayList<String>>)inputData.get(2);
                input=inputJournals.get(titleId);
                sourceTest=(String) dataToCheck.get(dataToCheckSpec.indexOf("source-title"));
                
                if (input.contains((dataProcessing(sourceTest, EXCEL_DEFAULT)).get(0))) {
                    System.out.println("IT'S SURELY DUPLICATE!!!!  IN CELL"+(cellID));
                    duplicateFound=true;
                } else {
                    duplicateFound=false;
                    //   break;
                }
            }
           
            if(!duplicateFound){
                DupTitleFound.put(titleTest,0);
                
                break;}
            
            else {
                DupTitleFound.put(titleTest,1);
                DupTitleCell.put(titleTest,cellID);
                DupTitleCounts.put(titleTest,1);
                break;
            }
        }
        
        result.add(DupTitleFound);
        result.add(DupTitleCell);
        result.add(DupTitleCounts);
        return (ArrayList) result;
    }
}

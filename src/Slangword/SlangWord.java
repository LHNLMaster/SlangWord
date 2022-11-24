package Slangword;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class SlangWord {
	public final static void clearScreen() {
			System.out.print("");
			System.out.flush();
		}
	
	public static void pauseScreen(){
		System.out.println("Press Any Key To Continue");
		new java.util.Scanner(System.in).nextLine();
	    }

	public static HashMap<String,List<String>> m = new HashMap<String,List<String>>();
	public static List<String> historySlangWord = new ArrayList();
	public static Scanner word = new Scanner(System.in);

	public static void GetHistory() {
		try
	     	{
	        	File f=new File("./data/history.txt");
	        	FileReader fr=new FileReader(f);
	        	BufferedReader br=new BufferedReader(fr);
	        	String line;
	       	 	while((line=br.readLine())!=null) {
	            		historySlangWord.add(line);
	        	}
	        	fr.close();
	        	br.close();
	    	}
	    	catch (Exception ex)
	    	{
	        System.out.println("ERROR"+ex);
	    }
	    }

	    public static void GetData(){
	     try
	     {
	        File f=new File("./data/slang.txt");
	        FileReader fr=new FileReader(f);
	        BufferedReader br=new BufferedReader(fr);
	        String line;
	        while((line=br.readLine())!=null)
	        {
	            if (line.contains("`"))
	            {
	                String[] s = line.split("`");
	                String[] tmp = s[1].split("\\|");
	                List<String> temp=Arrays.asList(tmp);
	                m.put(s[0],temp);
	            }
	        }
	        fr.close();
	        br.close();
	    }
	    catch (Exception ex)
	    {
	        System.out.println("ERROR"+ex);
	    }
	    }
	//1. Search SlangWord
	 public static void searchSlangWord() {
	        clearScreen();
        	System.out.print("Enter a Slang word: ");
        	String key = sc.nextLine();
        	his.add(key);
        	key = key.toUpperCase();
        	if (!m.containsKey(key)) {
           	 	System.out.println("Not Found!!!");
        	}
        	else {
            		List<String> l = m.get(key);
            		System.out.print("Result:");
            		for (String s: l) {
                		System.out.println("- " + s);
            		}
        	}
        	PauseTest();
        	Menu();
	    }
	 
	 //2. Search by definition
	 public static void searchDefinition() {
	        clearScreen();
        	ArrayList<String> slang_means = new ArrayList<String>();
        	System.out.print("Enter any word to find a Slang word: ");
        	String word = sc.nextLine();
        	his.add(word);
        	word = word.toLowerCase();
        	for (String i : m.keySet()) {
            		for (String s: m.get(i)) {
                		if (s.toLowerCase().contains(word)) {
                    			slang_means.add(i);
                		}
            		}
		}
        	if (!slang_means.isEmpty()) {
            		System.out.println("Slang words found: ");
            		for (String i : slang_means) {
                		System.out.print("- " + i + ": ");
                		ShowDefinition(i);
            		}
        	}
        	else {
            		System.out.println("Not Found!!!");
        	}

        	PauseTest();
        	Menu();
	 }
	 
	 //3. Show History
	 public static void showHistory() {
	        clearScreen();
        	System.out.println("History:");
        	for (String i : his) {
            		System.out.println("- " + i);
        	}
        	PauseTest();
       	 	Menu();
	    }
	
	public static void AddSlang(String slang, String means) {
            	ArrayList<String> tmp = new ArrayList<String>();
            	tmp.add(means);
            	m.put(slang.toUpperCase(), tmp);
            	System.out.println("Add successfully!!!");
   	 }
	
	public static void Duplicate(String slang, String means) {
        	List<String> tmp = new ArrayList<String>();
        	tmp = m.get(slang);
        	tmp.add(means);
        	m.put(slang.toUpperCase(), tmp);
        	System.out.println("Add successfully!!!");
    	}
	 
	 //4. Add new Slang word
	 public static void addSlangWord() {
	        clearScreen();
        	System.out.print("Enter Slang word: ");
        	String slang = sc.nextLine();
        	System.out.print("Enter meanings: ");
        	String means = sc.nextLine();      
        	if (m.containsKey(slang)) {
            		System.out.println("This Slang word was existed, Choose what you want to: ");
            		System.out.println("1. Overwrite");
            		System.out.println("2. Dupicate");
            		int c = sc.nextInt();
            		if (c == 1) {
                		AddSlang(slang, means);
            		}
            		else if (c == 2) {
                		Duplicate(slang, means);
            		}
        	}
        	else {
            		AddSlang(slang, means);
        	}
        	PauseTest();
       		Menu();
	    }
	 
	 //5. Edit 1 slang word
	 public static void addSlangWord(){
	        clearScreen();
        	System.out.print("Enter Slang word you want to edit: ");
        	String slang = sc.nextLine();
        	slang = slang.toUpperCase();
        	if (!m.containsKey(slang)) {
            		System.out.println("This Slang word does not exist!!");
        	}
       		else {
            		System.out.print("Enter new Slang word: ");
            		String new_slang = sc.nextLine();
            		System.out.print("Enter new Meaning: ");
            		String new_means = sc.nextLine();
            		List<String> tmp = new ArrayList<String>();
            		tmp.add(new_means);
            		m.put(new_slang.toUpperCase(), tmp);
            		m.remove(slang);
            		System.out.println("Edit successfully!!");
        	}

        	PauseTest();
       	 	Menu();
	  }
	 
	 //6. Delete 1 slang word
	 public static void deleteSlangWord()
	    {
	        clearScreen();
        	System.out.print("Enter Slang word you want to delete: ");
        	String slang = sc.nextLine();
        	slang = slang.toUpperCase();
        	if (!m.containsKey(slang)) {
            		System.out.println("This Slang word does not exist!!");
        	}
        	else {
            		System.out.println("Comfirm delete (y/n)? ");
            		String choice = sc.next();
            		if (choice.equals("y") || choice.equals("Y")) {
                		m.remove(slang);
                		System.out.println("Delete successfully!!");
            		}
            		else {
                		System.out.println("Not delete!!");
            		}
        	}
        	PauseTest();
        	Menu();
	    }
	 
	 //7. Reset the original slang words list
	 public static void resetSlangWord()
	    {
	        clearScreen();
        	m.clear();;
        	if (m.isEmpty()) {
           		ReadFile("slang.txt");
            		System.out.println("Reset successfully!!");
        	}
        	else {
            		System.out.println("Reset Fail!!");
        	}

        	PauseTest();
        	Menu();
	    }
	 
	 //8. Random 1 slang word
	 public static String RamdomSlangword(){
	        clearScreen();
	        int count=0;
	        Random rd=new Random();
	        int magicNumber=rd.nextInt(m.size());
	        String ans="";
	        for (String i: m.keySet())
	        {
	            if (count==magicNumber)
	            {
	                ans=i;
	                break;
	            }else count++;
	        }
	        return ans;
	    }
	 
	 //9. Game One
	 public static void GameOne()
	    {
	        clearScreen();
	        Random rand = new Random();
	        List<String> poll=new ArrayList();

	        String word1 = RamdomSlangword();
	        String qword=word1;
	        List<String> w1=m.get(word1);
	        word1=w1.get(rand.nextInt(w1.size()));
	        String win=word1;
	        poll.add(word1);

	        String word2 = RamdomSlangword();
	        List<String> w2=m.get(word2);
	        word2=w2.get(rand.nextInt(w2.size()));
	        poll.add(word2);

	        String word3 = RamdomSlangword();
	        List<String> w3=m.get(word3);
	        word3=w3.get(rand.nextInt(w3.size()));
	        poll.add(word3);

	        String word4 = RamdomSlangword();
	        List<String> w4=m.get(word4);
	        word4=w4.get(rand.nextInt(w4.size()));
	        poll.add(word4);


	        System.out.println("Question: What is the Definition for " + qword);

	        word1=poll.get(rand.nextInt(poll.size()));
	        poll.remove(word1);
	        System.out.println("A.  " + word1);
	        word2=poll.get(rand.nextInt(poll.size()));
	        poll.remove(word2);
	        System.out.println("B.  " + word2);
	        word3=poll.get(rand.nextInt(poll.size()));
	        poll.remove(word3);
	        System.out.println("C.  " + word3);
	        word4=poll.get(rand.nextInt(poll.size()));
	        poll.remove(word4);
	        System.out.println("D.  " + word4);

	        System.out.println("Your Answer is: ");
	        String choice=word.nextLine();

	        if ( (choice.equals("A") || choice.equals("a")) && word1==win) System.out.println("Congratulations , Your Answer is correct");
	        else if ((choice.equals("B") || choice.equals("b")) && word2==win) System.out.println("Congratulations , Your Answer is correct");
	        else if ((choice.equals("C") || choice.equals("c")) && word3==win) System.out.println("Congratulations , Your Answer is correct");
	        else if ((choice.equals("D") || choice.equals("d")) && word4==win) System.out.println("Congratulations , Your Answer is correct");
	        else System.out.println("Sorry , Your Answer is incorrect . The Answer is " + win);
	        pauseScreen();
	        Menu();
	    }
	 
	 //10. Game Two
	 public static void GameTwo()
	    {
	        clearScreen();
	        Random rand = new Random();
	        List<String> poll=new ArrayList();
	        String word1 = RamdomSlangword();
	        poll.add(word1);
	        String word2 = RamdomSlangword();
	        poll.add(word2);
	        String word3 = RamdomSlangword();
	        poll.add(word3);
	        String word4 = RamdomSlangword();
	        poll.add(word4);
	        List<String> qword=m.get(word1);
	        String win=word1;
	        System.out.println("Question: What is the Slang Word for " + qword.get(rand.nextInt(qword.size())));
	        word1=poll.get(rand.nextInt(poll.size()));
	        poll.remove(word1);
	        System.out.println("A.  " + word1);
	        word2=poll.get(rand.nextInt(poll.size()));
	        poll.remove(word2);
	        System.out.println("B.  " + word2);
	        word3=poll.get(rand.nextInt(poll.size()));
	        poll.remove(word3);
	        System.out.println("C.  " + word3);
	        word4=poll.get(rand.nextInt(poll.size()));
	        poll.remove(word4);
	        System.out.println("D.  " + word4);
	        System.out.println("Your Answer is: ");
	        String choice=word.nextLine();
	        if ( (choice.equals("A") || choice.equals("a")) && word1==win) System.out.println("Congratulations , Your Answer is correct");
	        else if ((choice.equals("B") || choice.equals("b")) && word2==win) System.out.println("Congratulations , Your Answer is correct");
	        else if ((choice.equals("C") || choice.equals("c")) && word3==win) System.out.println("Congratulations , Your Answer is correct");
	        else if ((choice.equals("D") || choice.equals("d")) && word4==win) System.out.println("Congratulations , Your Answer is correct");
	        else System.out.println("Sorry , Your Answer is incorrect . The Answer is " + win);
	        pauseScreen();
	        Menu();
	    }
	 
	 //Update Hstory
	 public static void updateHistory(){
	        try {
	            File f = new File("./data/history.txt");
	            FileWriter fw = new FileWriter(f);
	            BufferedWriter bw = new BufferedWriter(fw);
	            for (String temp : historySlangWord) {
	                fw.write(temp + "\n");
	            }
	            fw.close();
	            bw.close();
	        }
	        catch (Exception ex) {
	            System.out.println("Error: "+ex);
	        }
	    }
	 
	//Update File
	    public static void updateFile()
	    {
	        try {
	            File f = new File("./data/slang.txt");
	            FileWriter fw = new FileWriter(f);
	            BufferedWriter bw = new BufferedWriter(fw);
	            for (String i: m.keySet())
	            {
	                fw.write(i +"`");
	                List<String> temp=m.get(i);
	                for (int t=0;t<temp.size();t++)
	                {
	                    fw.write(temp.get(t));
	                    if (t+1<temp.size()) fw.write("|");
	                }
	                fw.write("\n");
	            }
	            fw.close();
	            bw.close();
	        }
	        catch (Exception ex) {
	            System.out.println("Error: "+ex);
	        }
	    }


	 public static void Menu () {
			
		 System.out.println("------------MENU--------------");
		 System.out.println("1. Search by slang word");
		 System.out.println("2. Search by definition, showing all the slang words that in the defintion contain the keyword typed.");
		 System.out.println("3. Display history, list of searched slang words");
		 System.out.println("4. Add 1 new slang words");
		 System.out.println("5. Edit 1 slang word");
		 System.out.println("6. Delete 1 slang word");
		 System.out.println("7. Reset the original slang words list");
		 System.out.println("8. Random 1 slang word");
		 System.out.println("9. Quiz function, the program displays 1 random slang word, with 4 answers for the user to choose");
		 System.out.println("10. Quiz function, the program displays 1 definition, with 4 slang words the answer for the user to choose");
		 System.out.println("11. Exit");
		 
		 int choose;
		 Scanner sc = new Scanner(System.in);
		 System.out.println("Enter your choose: ");
		 choose = sc.nextInt();
		 switch(choose) {
		 	case 1: 
		 		searchSlangWord();
		 		break;
		 	case 2: 
		 		searchDefinition();
		 		break;
		 	case 3: 
		 		showHistory();
		 		break;
		 	case 4: 
		 		addSlangWord();
		 		break;
		 	case 5: 
		 		editSlangWord();
		 		break;
		 	case 6: 
		 		deleteSlangWord();
		 		break;
		 	case 7: 
		 		resetSlangWord();
		 		break;
		 	case 8: 
		 		randomSlangWord();
		 		break;
		 	case 9: 
		 		gameOne();
		 		break;
		 	case 10:
		 		gameTwo();
		 		break;
		 	case 11:
		 		clearScreen();
		 		WriteHistory("history.txt");
		 		WriteFile("slangwordnew.txt");
		 		System.exit(0);
		 }
		 
	}
	 
	 public static void main(String[] args)
	    {
	        ReadFile("slangwordnew.txt");
       		if (m.isEmpty()) {
            		ReadFile("slang.txt");
        	}
		his = LoadHistory("history.txt");
       		Menu();
	    }
}

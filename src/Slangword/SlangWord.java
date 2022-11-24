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

		public static void GetHistory()
	    {
	        try
	     {
	        File f=new File("./data/history.txt");
	        FileReader fr=new FileReader(f);
	        BufferedReader br=new BufferedReader(fr);
	        String line;
	        while((line=br.readLine())!=null)
	        {
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
	 public static void SearchSlangWord()
	    {
	        clearScreen();
	        System.out.print("What word you want to find: ");
	        String check = word.nextLine();
	        check=check.toUpperCase();
	        List<String> test = m.get(check);
	        historySlangWord.add(check);
	        System.out.println(test);
	        pauseScreen();
	        Menu();
	    }
	 
	 //2. Search by definition
	 public static void SearchDefinition()
	    {
	        clearScreen();
	        System.out.println("What definition you want to find: ");
	        String check=word.nextLine();
	        historySlangWord.add(check);
	        List<String> answer = new ArrayList();
	        for (String i: m.keySet())
	        {
	            if (m.get(i).contains(check))
	            {
	                answer.add(i);
	            }
	        }
	        System.out.println(answer);
	        pauseScreen();
	        Menu();
	    }
	 
	 //3. Show History
	 public static void ShowHistory()
	    {
	        clearScreen();
	        System.out.println("Your history search is: ");
	        for (String temp: historySlangWord)
	        {
	            System.out.println(temp);
	        }
	        pauseScreen();
	        Menu();
	    }
	 
	 //4. Add new Slang word
	 public static void CreateSlangWord()
	    {
	        clearScreen();
	        System.out.println("What is your new Slang Word: ");
	        String check = word.nextLine();
	        check=check.toUpperCase();
	        System.out.println("What is the definition: ");
	        String check1 = word.nextLine();
	        List<String> t=new ArrayList();
	        t.add(check1);
	        if (m.containsKey(check))
	        {
	            System.out.println("Do you want to overwrite: ");
	            String confirm =word.nextLine();
	            if (confirm.equals("Y") || confirm.equals("y") ) m.put(check,t);
	            else
	            {
	                List<String> i=m.get(check);
	                for (String j:i)
	                {
	                    t.add(j);
	                }
	                m.put(check,t);
	            }
	        }
	        else
	        {
	            m.put(check,t);
	            System.out.println("Add New Slang Word Successfully");
	        }
	        Menu();
	    }
	 
	 //5. Edit 1 slang word
	 public static void EditSlangWord(){
	        clearScreen();
	        System.out.print("What slangword you want to edit: ");
	        String check = word.nextLine();
	        check=check.toUpperCase();
	        if (!m.containsKey(check))
	        {
	            System.out.println("This slangword dont't exist");
	            pauseScreen();
	            Menu();
	        }
	        clearScreen();
	        System.out.println("Here is the definition: " );

	        List<String> showCase=m.get(check);
	        List<String> rshowCase=new ArrayList();
	        for (String i:showCase)
	        {
	            rshowCase.add(i);
	        }
	        int count=1;
	        for (String i: showCase)
	        {
	            System.out.println(count+ "." + i);
	            count++;
	        }
	        System.out.println("What word you want to change: ");
	        int index = word.nextInt();
	        clearScreen();
	        System.out.println("What do you want: ");
	        System.out.println("1. Replace Definition ");
	        System.out.println("2. Delete Definition ");
	        System.out.println("YOUR CHOICE:");
	        int choice=word.nextInt();
	        String pass=word.nextLine();
	        if (choice==1) 
	        {
	            rshowCase.remove(index-1);
	            System.out.print("What is the new definition : ");
	            String temp=word.nextLine();
	            rshowCase.add(temp);
	            m.put(check,rshowCase);
	        }
	        else if (choice==2)
	        {
	            if (rshowCase.size()==1) 
	            {
	                System.out.println("You can't delete this ");
	                pauseScreen();
	                Menu();
	            }
	            rshowCase.remove(index-1);
	            m.put(check,rshowCase);
	        }
	        Menu();
	    }
	 
	 //6. Delete 1 slang word
	 public static void RemoveSlangWord()
	    {
	        clearScreen();
	        System.out.println("What slangword you want to remove: ");
	        String check=word.nextLine();
	        if (m.containsKey(check))
	        {
	            System.out.println("Are you sure you want to remove it: ");
	            String confirm=word.nextLine();
	            if (confirm.equals("Y") || confirm.equals("y") ) m.remove(check);
	        }
	        Menu();
	    }
	 
	 //7. Reset the original slang words list
	 public static void ResetSlangDictionary()
	    {
	        clearScreen();
	        m.clear();
	        try
	     {
	        File f=new File("./data/slangwordnew.txt");
	        FileReader fr=new FileReader(f);
	        BufferedReader br=new BufferedReader(fr);
	        String line;
	        while((line=br.readLine())!=null)
	        {
	            if (line.contains("`"))
	            {
	                String[] s=line.split("`");
	                String[] tmp=s[1].split("\\|");
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
	        System.out.println("Reset List To Default !!!");
	        pauseScreen();
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

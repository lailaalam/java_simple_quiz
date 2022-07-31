import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class QuestionBank {
    public static void main(String[] args) throws IOException, ParseException {

        System.out.println("Please Enter a number 1 or 2: \n 1 : Add question\n 2 : Participate in a quiz");
        Scanner input = new Scanner(System.in);
        int userInput = input.nextInt();
        if (userInput == 1) {

            generateQuestion();
        } else if (userInput == 2) {
            System.out.println("You will be asked 5 questions, each questions has 1 marks");
            startQuiz();
        } else {
            System.out.println("Enter Correct Input");
        }

    }

    private static void generateQuestion() throws IOException, ParseException {
        char c = 'n';
        String fileName = "./src/main/resources/quizset.json";

        do {
            System.out.println("Please Add a Question here:");
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(fileName));
            JSONObject quizSet = new JSONObject();

            Scanner input = new Scanner(System.in);
            quizSet.put("Question", input.nextLine());
            System.out.println("Input options:");
            System.out.println("option a:");
            quizSet.put("OPtion a: ", input.nextLine());
            System.out.println("option b:");
            quizSet.put("OPtion b: ", input.nextLine());
            System.out.println("option c:");
            quizSet.put("OPtion c: ", input.nextLine());
            System.out.println("option d:");
            quizSet.put("OPtion d: ", input.nextLine());
            System.out.println("Please input the correct ans:");
            quizSet.put("Correct Answer: ", input.nextLine());

            JSONArray jsonQuizArray = (JSONArray) obj;
            jsonQuizArray.add(quizSet);
            FileWriter file = new FileWriter(fileName);
            file.write(jsonQuizArray.toJSONString());
            file.flush();
            file.close();
            System.out.println("Quiz saved at the database. Do you want to add more? (y/n)");

            c = input.next().charAt(0);

        } while (c == 'y');


    }

    private static void startQuiz() throws IOException, ParseException {

        String fileName = "./src/main/resources/quizset.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        Scanner input=new Scanner(System.in);
        int counter=0;
        for (int i = 1; i <= 5; i++) {
            int rndPosition = new Random().nextInt(jsonArray.size());
            JSONObject jsonObject = (JSONObject) jsonArray.get(rndPosition);

            String qs_no = (String) jsonObject.get("Question");
            String option_a = (String) jsonObject.get("OPtion a: ");
            String option_b= (String) jsonObject.get("OPtion b: ");
            String option_c = (String) jsonObject.get("OPtion c: ");
            String option_d = (String) jsonObject.get("OPtion d: ");
            String correctAnswer = (String) jsonObject.get("Correct Answer: ");
            System.out.println(i+"."+qs_no);
            System.out.println("a."+option_a);
            System.out.println("b."+option_b);
            System.out.println("c."+option_c);
            System.out.println("d."+option_d);
            System.out.println("Enter your answer:");
            String answer=input.nextLine();
            if(answer.equals(correctAnswer))
            {
                System.out.println("Correct!");
                counter++;
            }
            else
            {
                System.out.println("Not correct");
            }

        }
        System.out.println("You got "+counter+" out of five");

    }

}

package advent;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day_4
{
    public static void main(String[] args) {
        File file = new File('4' + ".txt");
        if(!file.exists())
        {
            solve(new ArrayList<>());
            return;
        }
        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new FileReader(file));
        } catch(FileNotFoundException e)
        {
            System.err.println("File not found!!");
            solve(new ArrayList<>());
            return;
        }

        List<String> inputLines = new ArrayList<>();
        try
        {
            String line;
            while((line = reader.readLine()) != null)
                inputLines.add(line);

            reader.close();
        } catch(IOException e)
        {
            e.printStackTrace();
        }

        solve(inputLines);
    }

    static void solve(List<String> input)
    {
        System.out.println(input);
        int validPassports = 0;

        Map<String, String> keyValues = new HashMap<>();

        for(String s : input)
        {
            if(s.isEmpty())
            {
                boolean valid = true;
                if(keyValues.containsKey("byr"))
                {
                    int year = Integer.parseInt(keyValues.get("byr"));
                    if(year < 1920 || year > 2002)
                        valid = false;
                }
                else
                {
                    valid = false;
                }

                if(keyValues.containsKey("iyr"))
                {
                    int year = Integer.parseInt(keyValues.get("iyr"));
                    if(year < 2010 || year > 2020)
                        valid = false;
                }
                else
                {
                    valid = false;
                }

                if(keyValues.containsKey("eyr"))
                {
                    int year = Integer.parseInt(keyValues.get("eyr"));
                    if(year < 2020 || year > 2030)
                        valid = false;
                }
                else
                {
                    valid = false;
                }

                if(keyValues.containsKey("hgt"))
                {
                    String height = keyValues.get("hgt");
                    if(height.endsWith("in"))
                    {
                        int heightIn = Integer.parseInt(height.replace("in", ""));
                        if(heightIn < 59 || heightIn > 76)
                            valid = false;
                    }
                    else if(height.endsWith("cm"))
                    {
                        int heightCm = Integer.parseInt(height.replace("cm", ""));
                        if(heightCm < 150 || heightCm > 193)
                            valid = false;
                    }
                    else
                    {
                        valid = false;
                    }
                }
                else
                {
                    valid = false;
                }

                if(keyValues.containsKey("hcl"))
                {
                    if(!keyValues.get("hcl").matches("#[a-f0-9]{6}"))
                        valid = false;
                }
                else
                {
                    valid = false;
                }

                if(keyValues.containsKey("ecl"))
                {
                    String color = keyValues.get("ecl");
                    if(!color.equals("amb") && !color.equals("blu") && !color.equals("brn") && !color.equals("gry") && !color.equals("grn") && !color.equals("hzl") && !color.equals("oth"))
                        valid = false;
                }
                else
                {
                    valid = false;
                }

                if(keyValues.containsKey("pid"))
                {
                    if(!keyValues.get("pid").matches("[0-9]{9}"))
                        valid = false;
                }
                else
                {
                    valid = false;
                }

                if(valid)
                {
                    validPassports++;
                }
                keyValues.clear();
            }
            else
            {
                String[] lineParts = s.split(" ");
                for(String part : lineParts)
                {
                    String[] keyVal = part.split(":");
                    keyValues.put(keyVal[0], keyVal[1]);
                }
            }
        }
        System.out.println("Valid Passports: " + validPassports);
    }
}

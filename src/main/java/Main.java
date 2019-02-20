import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;


public class Main {
    public static void JsonReading(String fileName)

    {   String result = "";
        try {
            final FileWriter writer = new FileWriter("MovieLensElasticsearch.json");
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();

            // We initialize the number of the document
            int document = 0 ;

            while (line != null) {
                StringBuilder correctedLine = new StringBuilder();

                // We edit the first line of each document
                // {"index" : {"_index" : "mlru" , "_type" : "movielens" , "_id" :"numberOfTheLine" }}:
                JSONObject obj = new JSONObject();

                JSONObject content = new JSONObject();
                content.put("_index" , "mlru");
                content.put("_type", "movielens");
                content.put("_id", document );

                obj.put("index" , content );

                writer.write(obj.toJSONString());

                //correctedLine.append("{");
                JSONObject fields = new JSONObject();
                fields.put("fields",correctedLine );
                //correctedLine.append(fields);



                for(int i = 0; i<line.length(); i++)
                {

                    if((line.charAt(i) == '$') || (line.charAt(i) == '-'))
                    {

                    }

                    else if(line.charAt(i) == '_')
                    {
                        correctedLine.append("rater");
                    }
                    else
                    {
                        correctedLine.append((line.charAt(i)));
                    }


                }


                //correctedLine.append("}");



                // We increment the number of the document
                document = document + 1 ;


                line = br.readLine();

                writer.write("\n");

                //writer.write(correctedLine.toString());
                writer.write(fields.toJSONString());

                writer.write("\n");

            }



        } catch(Exception e) {
            e.printStackTrace();
        }
    }







    public static void main(String[] args){

        JsonReading("MovieLens_ratingUsers.json");
    }



}

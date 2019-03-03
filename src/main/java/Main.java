import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;


public class Main {


    public static void JsonReading(String fileName)
    {
        String result = "";
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
                //JSONObject fields = new JSONObject();
                //fields.put("fields",correctedLine );
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

                writer.write(correctedLine.toString());
                //writer.write(fields.toJSONString());

                writer.write("\n");

            }



        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void SplitFile( String file) {

        try {
            final FileWriter writer1 = new FileWriter("MovieLensElasticsearch1.json");
            final FileWriter writer2 = new FileWriter("MovieLensElasticsearch2.json");
            final FileWriter writer3 = new FileWriter("MovieLensElasticsearch3.json");

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            int number = 0 ;

            while (line != null) {

                if ( number <= 665551){
                    writer1.write(line.toString());
                    writer1.write("\n");

                }
                else if (number > 1331101){
                    writer3.write(line.toString());
                    writer3.write("\n");

                }
                else {
                    writer2.write(line.toString());
                    writer2.write("\n");

                }
                number = number + 1 ;
                line = br.readLine();



            }

            writer3.write("\n");
            writer2.write("\n");
            writer1.write("\n");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args){

        JsonReading("MovieLens_ratingUsers.json");
        SplitFile("MovieLensElasticsearch.json");
    }



}

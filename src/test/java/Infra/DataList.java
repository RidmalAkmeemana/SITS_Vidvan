package Infra;

public class DataList
{
    public static String setJSON(String method, String scenario, String Status)
    {
        String methodName = method;

        if(methodName.equals("logIntoSystem"))
        {
            String Scenario = scenario;
            String RemoveOpenBracket = Scenario.replace("[","");
            String RemoveCloseBracket = RemoveOpenBracket.replace("]", "");

            String[] ScenarioParts = RemoveCloseBracket.split(",");

            String Username = ScenarioParts[0];
            String Password = ScenarioParts[1];

            String username = Username;
            String password = Password;


            String jsonString = "{\n" +
                    "  \""+methodName+"\": [\n" +
                    "    {\n" +
                    "      \"Username\": \""+username+"\",\n" +
                    "      \"Password\": \""+password+"\",\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            return jsonString;
        }

        if(methodName.equals("GoToCoursePage"))
        {
            String Scenario = scenario;
            String RemoveOpenBracket = Scenario.replace("[","");
            String RemoveCloseBracket = RemoveOpenBracket.replace("]", "");

            String[] ScenarioParts = RemoveCloseBracket.split(",");

            String CourseID = ScenarioParts[0];
            String ErrorStatus = Status;

            String jsonString = "{\n" +
                    "  \""+methodName+"\": [\n" +
                    "    {\n" +
                    "      \"Course ID\": \"" + CourseID + "\",\n" +
                    "      \"Status\": \""+ErrorStatus+"\",\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            return jsonString;
        }

        if(methodName.equals("AddEmails"))
        {
            String Scenario = scenario;
            String RemoveOpenBracket = Scenario.replace("[","");
            String RemoveCloseBracket = RemoveOpenBracket.replace("]", "");

            String[] ScenarioParts = RemoveCloseBracket.split(",");

            String ID = ScenarioParts[0];
            String Email = ScenarioParts[1];

            String jsonString = "{\n" +
                    "  \""+methodName+"\": [\n" +
                    "    {\n" +
                    "      \"ID\": \"" + ID + "\",\n" +
                    "      \"Email\": \""+Email+"\",\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            return jsonString;
        }

        return "{}";
    }
}

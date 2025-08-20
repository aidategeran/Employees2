import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        String peopleText = """
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000, yoe=10, iq=140}
            Flinstone2, Fred, 1/1/1900, Programmer, {locpd=1300, yoe=14, iq=100}
            Flinstone3, Fred, 1/1/1900, Programmer, {locpd=2300, yoe=8, iq=105}
            Flinstone4, Fred, 1/1/1900, Programmer, {locpd=1630, yoe=3, iq=115}
            Flinstone5, Fred, 1/1/1900, Programmer, {locpd=5, yoe=10, iq=100}
            Rubble, Barney, 2/2/1905, Manager, {orgSize=300, dr=10}
            Rubble2, Barney, 2/2/1905, Manager, {orgSize=300, dr=4}
            Rubble3, Barney, 2/2/1905, Manager, {orgSize=300, dr=2}
            Rubble4, Barney, 2/2/1905, Manager, {orgSize=300, dr=8}
            Rubble5, Barney, 2/2/1905, Manager, {orgSize=300, dr=20}
            Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
            Flinstone2, Wilma, 3/3/1910, Analyst, {projectCount=4}
            Flinstone3, Wilma, 3/3/1910, Analyst, {projectCount=5}
            Flinstone4, Wilma, 3/3/1910, Analyst, {projectCount=6}
            Flinstone5, Wilma, 3/3/1910, Analyst, {projectCount=9}
            Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
            """;

        String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
        Pattern peoplePat = Pattern.compile(peopleRegex);
        Matcher peopleMat = peoplePat.matcher(peopleText);

        String progRegex = "\\w+=(?<locpd>\\d+),\\s*\\w+=(?<yoe>\\d+),\\s*\\w+=(?<iq>\\d+)";
        Pattern coderPat = Pattern.compile(progRegex);


        String mgrRegex = "\\w+=(?<orgSize>\\d+),\\s*\\w+=(?<dr>\\d+)";
        Pattern mgrPat = Pattern.compile(mgrRegex);

        String analystRegex = "\\w+=(?<projectCount>\\d+)";
        Pattern analystPat = Pattern.compile(analystRegex);

        String ceoRegex = "\\w+=(?<avgStockPrice>\\d+)";
        Pattern ceoPat = Pattern.compile(ceoRegex);

        int totalSalaries = 0;
        while (peopleMat.find()) {
            totalSalaries += switch (peopleMat.group("role")) {
                case "Programmer" -> {
                    String details = peopleMat.group("details");
                    Matcher coderMat = coderPat.matcher(details);
                    int salary = 0;
                    if (coderMat.find()) {
                        int locpd = Integer.parseInt(coderMat.group("locpd"));
                        int yoe = Integer.parseInt(coderMat.group("yoe"));
                        int iq = Integer.parseInt(coderMat.group("iq"));
                        System.out.printf("Programmer locpd:%s, yoe:%s, iq:%s%n", locpd, yoe, iq);
                        salary = 3000 + locpd * yoe * iq;
                    } else {
                         salary = 3000;
                    }
                    String lastName = peopleMat.group("lastName");
                    String firstName = peopleMat.group("firstName");
                    System.out.printf("%s, %s: %s%n", lastName, firstName, NumberFormat.getCurrencyInstance().format(salary));
                    yield salary;
                }
                case "Manager" -> {
                    String details = peopleMat.group("details");
                    Matcher mgrMat = mgrPat.matcher(details);
                    int salary = 0;
                    if (mgrMat.find()) {
                        int orgSize = Integer.parseInt(mgrMat.group("orgSize"));
                        int dr = Integer.parseInt(mgrMat.group("dr"));
                        System.out.printf("Manager orgSize:%s, dr:%s%n", orgSize,dr);
                        salary = 3500 + orgSize * dr;
                    } else {
                        salary = 3500;
                    }
                    String lastName = peopleMat.group("lastName");
                    String firstName = peopleMat.group("firstName");
                    System.out.printf("%s, %s: %s%n", lastName, firstName, NumberFormat.getCurrencyInstance().format(salary));
                    yield salary;
                }

                case "Analyst" -> {
                    String details = peopleMat.group("details");
                    Matcher analystMat = analystPat.matcher(details);
                    int salary = 0;
                    if (analystMat.find()) {
                        int projectCount = Integer.parseInt(analystMat.group("projectCount"));
                        System.out.printf("Analyst projectCount:%s%n", projectCount);
                        salary = 2500 + projectCount * 2;
                    } else {
                        salary = 2500;
                    }
                    String lastName = peopleMat.group("lastName");
                    String firstName = peopleMat.group("firstName");
                    System.out.printf("%s, %s: %s%n", lastName, firstName, NumberFormat.getCurrencyInstance().format(salary));
                    yield salary;
                }

                case "CEO" -> {
                    String details = peopleMat.group("details");
                    Matcher ceoMat = ceoPat.matcher(details);
                    int salary = 0;
                    if (ceoMat.find()){
                        int avgStockPrice = Integer.parseInt(ceoMat.group("avgStockPrice"));
                        System.out.printf("CEO avgStockPrice: %s%n", avgStockPrice);
                        salary = 5000 + avgStockPrice;
                    } else {
                        salary = 5000;
                    }
                    String lastName = peopleMat.group("lastName");
                    String firstName = peopleMat.group("firstName");
                    System.out.printf("%s, %s: %s%n", lastName, firstName, NumberFormat.getCurrencyInstance().format(salary));
                    yield salary;\\\\mmmmkcjfhgkjcfh










                }

                default -> 0;
            };
        }







        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be %s%n", currencyInstance.format(totalSalaries));
    }
}





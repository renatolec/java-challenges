import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherChallenge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pattern emailPattern = Pattern.compile(
                "([a-zA-Z0-9.-]+)@(([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,})");
        Matcher emailMatcher;
        do{
            System.out.print("Digite um endereço de email válido: ");
            String emailUser = scanner.nextLine();
            emailMatcher = emailPattern.matcher(emailUser);
        }while(!emailMatcher.matches());
        emailMatcher.reset();
        emailMatcher.results().forEach(matchResult -> {
            System.out.printf("Endereço válido!\n\tUsuário = %s\n\tDomínio = %s\n", matchResult.group(1), matchResult.group(2));
        });
    }

}

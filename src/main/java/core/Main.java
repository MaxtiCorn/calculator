package core;

import core.business.Calculator;
import core.domain.Token;
import core.business.Tokenizer;
import core.business.readers.StringReader;
import core.business.readers.DoubleReader;
import core.business.readers.OperatorReader;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static core.domain.TokenType.STRING;

public class Main {
    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.setReaders(Arrays.asList(
                new DoubleReader(),
                new OperatorReader(),
                new StringReader(" "),
                new StringReader("abc")
        ));
        String output;
        Scanner in = new Scanner(System.in);
        System.out.println("Введите выражение");
        String input = in.nextLine();
        try {
            List<Token> tokens = tokenizer.tokenize(input)
                    .stream()
                    .filter(token -> !token.getType().equals(STRING))
                    .collect(Collectors.toList());
            Calculator calculator = new Calculator();
            calculator.setInput(tokens);
            output = calculator.calculate().toString();
        } catch (Exception e) {
            output = "Произошла ошибка, проверьте корректность введенных данных";
        }
        System.out.println(output);
    }
}

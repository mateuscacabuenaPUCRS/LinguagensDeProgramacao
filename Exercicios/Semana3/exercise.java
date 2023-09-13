//Estudantes: Carolina Ferreira, Henrique Juchem e Mateus Caçabuena

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class exercise {
        static int charClass;
        static String lexeme = new String(new char[100]);
        static String fileContent;
        static char nextChar;
        static int nextLen;
        static int token;
        static int lexLen;
        static int nextToken;
        static int nextCharIndex = 0;

        static Scanner in_fp;

        final static int LETTER = 0;
        final static int DIGIT = 1;
        final static int UNKNOWN = 99;

        final static int INT_LIT = 10;
        final static int IDENT = 11;
        final static int ASSIGN_OP = 20;
        final static int ADD_OP = 21;
        final static int SUB_OP = 22;
        final static int MULT_OP = 23;
        final static int DIV_OP = 24;
        final static int LEFT_PAREN = 25;
        final static int RIGHT_PAREN = 26;
        final static int EOF = -1;

        public static void main(String[] args) {
             String filePath = "src\\front.in";
        try {
            File file = new File(filePath);
            in_fp = new Scanner(file);
            fileContent = in_fp.nextLine();
            if (in_fp == null)
                throw new Exception(file.getName());
            else {
                getChar();
                do {
                    lex();
                } while (nextToken != EOF);
            }
        } catch (Exception e) {
            System.out.println("ERROR - cannot open file " + e.getMessage());
        }
        }
    
    static int lookup(char ch) {
        switch (ch) {
            case '(':
                addChar();
                nextToken = LEFT_PAREN;
                break;
            case ')':
                addChar();
                nextToken = RIGHT_PAREN;
                break;
            case '+':   
                addChar();
                nextToken = ADD_OP;
                break;
            case '=':
                addChar();
                nextToken = ASSIGN_OP;
                break;
            case '*':
                addChar();
                nextToken = MULT_OP;
                break;
            case '/':
                addChar();
                nextToken = DIV_OP;
                break;
            default:
                addChar();
                nextToken = -1;
                break;
        }
        return nextToken;
    }

    static void addChar() {
        if (lexLen <= 98) {
            lexeme += nextChar;
            lexLen++;
        } else {
            System.out.println("Error - lexeme is too long \n");
        }
    }

    static void getChar() throws IOException {
        try {
            nextChar = fileContent.charAt(nextCharIndex++);
            if (Character.isAlphabetic(nextChar))
                charClass = LETTER;
            else if (Character.isDigit(nextChar))
                charClass = DIGIT;
            else
                charClass = UNKNOWN;
        } catch (Exception e) {
            charClass = EOF;
        }
    }

    static void getNonBlank() throws IOException {
        while (Character.isWhitespace(nextChar)) {
            getChar();
        }
    }

    static int lex() throws IOException {
        lexeme="";
        lexLen = 0;
        getNonBlank();
        switch (charClass) {
            case LETTER:
                addChar();
                getChar();
                while (charClass == LETTER || charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = IDENT;
                break;
            case DIGIT:
                addChar();
                getChar();
                while (charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = INT_LIT;
                break;
            case UNKNOWN:
                lookup(nextChar);
                getChar();
                break;
            case -1:
                nextToken = -1;
                lexeme = "EOF";
                break;
        }
        System.out.println("Next token is: " + nextToken + " Next lexeme is: " + lexeme);
        return nextToken;
    }
}

        
        


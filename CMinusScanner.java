/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compilerproj1;

/**
 * 
 * @author jacksonadams
 */

import com.mycompany.compilerproj1.Token.TokenType;
import java.io.BufferedReader;
import java.io.IOException;

public class CMinusScanner implements Scanner {
    
    private BufferedReader inFile;
    private Token nextToken;
    
    public CMinusScanner (BufferedReader file){
        inFile = file;
        nextToken = scanToken();
    }
    
    public Token getNextToken () {
        Token returnToken = nextToken;
        if(nextToken.getType() != Token.TokenType.EOF_TOKEN){
            nextToken = scanToken();
        }
        return returnToken;
    }
    public Token viewNextToken(){
        return nextToken;
    }

    public enum StateType {
        START,
        DONE,
        INID,
        INNUM,
        INSTART_COMMENT,
        INEND_COMMENT,
        INLESS,
        INGREATER,
        INEQUAL,
        INNOT_EQUAL,
    }

    public Token scanToken() {
        TokenType currentToken;
        StateType state = StateType.START;

        while(state != StateType.DONE) {
            // Get next character
            char c = (char)inFile.read();
            
            switch(state){
                case START:
                    if(Character.isDigit(c)){
                        state = StateType.INNUM;
                    } else if (Character.isLetter(c)){
                        state = StateType.INID;
                    } else if (c == '!'){
                        state = StateType.INNOT_EQUAL;
                    } else if (c == '>'){
                        state = StateType.INGREATER;
                    } else if (c == '<'){
                        state = StateType.INLESS;
                    } else if (c == '='){
                        state = StateType.INEQUAL;
                    } else if (c == '/'){
                        state = StateType.INSTART_COMMENT;
                    } else {
                        state = StateType.DONE;
                        switch(c){
                            case '+':
                                currentToken = TokenType.PLUS_TOKEN;
                                break;
                            case '-':
                                currentToken = TokenType.MINUS_TOKEN;
                                break;
                            case '*':
                                currentToken = TokenType.MULT_TOKEN;
                                break;
                            case ';':
                                currentToken = TokenType.SEMI_TOKEN;
                                break;
                            case ',':
                                currentToken = TokenType.COMMA_TOKEN;
                                break;
                            case '(':
                                currentToken = TokenType.LEFT_PAREN_TOKEN;
                                break;
                            case ')':
                                currentToken = TokenType.RIGHT_PAREN_TOKEN;
                                break;
                            case '[':
                                currentToken = TokenType.LEFT_BRACKET_TOKEN;
                                break;
                            case ']':
                                currentToken = TokenType.RIGHT_BRACKET_TOKEN;
                                break;
                            case '{':
                                currentToken = TokenType.LEFT_BRACE_TOKEN;
                                break;
                            case '}':
                                currentToken = TokenType.LEFT_BRACE_TOKEN;
                                break;
                        }
                    }
                    break;
            }
        }
    }
}
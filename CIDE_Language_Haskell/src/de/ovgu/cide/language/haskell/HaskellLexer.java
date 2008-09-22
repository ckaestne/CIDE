package de.ovgu.cide.language.haskell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cide.gparser.CharStream;
import cide.gparser.Token;
import cide.gparser.TokenMgrError;
import tmp.generated_haskell.HaskellParserTokenManager;

public class HaskellLexer extends HaskellParserTokenManager {

	private Token special = null;

	public HaskellLexer(CharStream stream) {
		super(stream);
		tokenizeStream();
		tokenIterator = tokens.iterator();
	}

	private void tokenizeStream() {
		while (true) {
			if (specialTokens())
				continue;
			if (checkTextToken("module", MODULE))
				continue;
			if (checkTextToken("where", WHERE))
				continue;
			if (checkTextToken("import", IMPORT))
				continue;
			if (checkTextToken("qualified", QUALIFIED))
				continue;
			if (checkTextToken("deriving", DERIVING))
				continue;
			if (checkTextToken("as", AS))
				continue;
			if (checkTextToken("hiding", HIDING))
				continue;
			if (checkTextToken("type", TYPE))
				continue;
			if (checkTextToken("data", DATA))
				continue;
			if (checkTextToken("newtype", NEWTYPE))
				continue;
			if (checkTextToken("class", CLASS))
				continue;
			if (checkTextToken("instance", INSTANCE))
				continue;
			if (checkTextToken("default", DEFAULTTOKEN))
				continue;
			if (checkTextToken("let", LET))
				continue;
			if (checkTextToken("in", IN))
				continue;
			if (checkTextToken("do", DO))
				continue;
			if (checkTextToken("case", CASE))
				continue;
			if (checkTextToken("of", OF))
				continue;
			if (checkTextToken("if", IF))
				continue;
			if (checkTextToken("then", THEN))
				continue;
			if (checkTextToken("else", ELSE))
				continue;
			if (checkTextToken("infixl", INFIXL))
				continue;
			if (checkTextToken("infixr", INFIXR))
				continue;
			if (checkTextToken("infix", INFIX))
				continue;
			if (checkSymbolToken("=>", CONTEXT_ARROW,false))
				continue;
			if (checkSymbolToken("=", EQUALS,false))
				continue;
			if (checkSymbolToken("|", ALT,false))
				continue;
			if (checkSymbolToken("::", OFTYPE,false))
				continue;
			if (checkSymbolToken("..", OTHER12,false))
				continue;
			if (checkSymbolToken(".", OTHER1,false))
				continue;
			if (checkSymbolToken("->", OTHER7,false))
				continue;
			if (checkSymbolToken("--", OTHER11,false))
				continue;
			if (checkSymbolToken("-", OTHER2,false))
				continue;
			if (checkSymbolToken("@", OTHER3,false))
				continue;
			if (checkSymbolToken("_", OTHER4,false))
				continue;
			if (checkSymbolToken("~", OTHER5,false))
				continue;
			if (checkSymbolToken(":", OTHER6,false))
				continue;
			if (checkSymbolToken("+", OTHER8,false))
				continue;
			if (checkSymbolToken("\\", OTHER9,false))
				continue;
			if (checkSymbolToken("<-", OTHER10,false))
				continue;

			if (checkSymbolToken("{", LEFT_CURLY,true))
				continue;
			if (checkSymbolToken("}", RIGHT_CURLY,true))
				continue;
			if (checkSymbolToken(";", SEMICOLON,true))
				continue;
			if (checkSymbolToken("(#", LEFT_HPAREN,true))
				continue;
			if (checkSymbolToken("#)", RIGHT_HPAREN,true))
				continue;
			if (checkSymbolToken("(", LEFT_PAREN,true))
				continue;
			if (checkSymbolToken(")", RIGHT_PAREN,true))
				continue;
			if (checkSymbolToken("[", LEFT_BRACKET,true))
				continue;
			if (checkSymbolToken("]", RIGHT_BRACKET,true))
				continue;
			if (checkSymbolToken(",", COMMA,true))
				continue;
			if (checkSymbolToken("`", INFIX_QUOTE,true))
				continue;

			if (checkConstructor())
				continue;
			if (checkVariable())
				continue;
			if (checkNumber())
				continue;
			if (checkString())
				continue;
			if (checkVarsym())
				continue;

			if (peek() != 0)
				throw new TokenMgrError("Unknown characters left in stream: "
						+ getRemains(), 0);
			tokens.add(createToken(null, EOF));
			input_stream.Done();
			return;

		}

	}

	private boolean checkVarsym() {
		char first = getNext();
		if (isSymbol(first) || first == ':') {
			String symbol = "" + first;

			char c = getNext();
			while (isSymbol(c) || c == ':') {
				symbol += c;
				c = getNext();
			}
			backup(1);
			foundToken(symbol, first == ':' ? CONSYM : VARSYM);
			return true;
		}
		backup(1);
		return false;
	}

	private boolean isSymbol(char c) {
		if (c == '!')
			return true;
		if (c == '#')
			return true;
		if (c == '$')
			return true;
		if (c == '%')
			return true;
		if (c == '&')
			return true;
		if (c == '*')
			return true;
		if (c == '+')
			return true;
		if (c == '.')
			return true;
		if (c == '/')
			return true;
		if (c == '<')
			return true;
		if (c == '=')
			return true;
		if (c == '>')
			return true;
		if (c == '?')
			return true;
		if (c == '@')
			return true;
		if (c == '\\')
			return true;
		if (c == '^')
			return true;
		if (c == '-')
			return true;
		if (c == '~')
			return true;
		if (c == '|')
			return true;
		return false;
	}

	/**
	 * note, this does not detect correct use of linebreaks or quoted characters
	 * other than \"
	 * 
	 * @return
	 */
	private boolean checkString() {
		if (peek() != '"' && peek() != '\'')
			return false;

		char first = getNext();
		String l = "" + first;

		char c = getNext();
		while (!(c == first && l.charAt(l.length() - 1) != '\\')) {
			if (c == 0)
				throw new TokenMgrError();
			l += c;
			c = getNext();
		}
		l += c;

		foundToken(l, first == '"' ? STRING_LITERAL : CHARACTER_LITERAL);
		return true;
	}

	private boolean checkNumber() {
		if (!Character.isDigit(peek()))
			return false;

		String number = "" + getNext();
		char c = getNext();
		if (c == 'x' || c == 'X') {
			parseHex(number + c);
			return true;
		}
		if (c == 'o' || c == 'O') {
			parseOct(number + c);
			return true;
		}
		while (Character.isDigit(c)) {
			number += c;
			c = getNext();
		}
		if (c == '.' && Character.isDigit(peek())) {
			parseFloat(number + c);
			return true;
		} else {
			backup(1);
			foundToken(number, INTEGER);
			return true;
		}
	}

	private void parseFloat(String number) {
		char c = getNext();
		while (Character.isDigit(c)) {
			number += c;
			c = getNext();
		}
		if ((c == 'e' || c == 'E')
				&& (Character.isDigit(peek()) || ((peek() == '+' || peek() == '-') && Character
						.isDigit(peek(2))))) {
			number += c;
			number += getNext();
			c = getNext();
			while (Character.isDigit(c)) {
				number += c;
				c = getNext();
			}
		}
		backup(1);
		foundToken(number, FLOAT);
	}

	private void parseHex(String number) {
		char c = getNext();
		while (Character.isDigit(c) || c == 'a' || c == 'b' || c == 'c'
				|| c == 'd' || c == 'e' || c == 'f' || c == 'A' || c == 'B'
				|| c == 'C' || c == 'D' || c == 'E' || c == 'F') {
			number += c;
			c = getNext();
		}
		backup(1);
		foundToken(number, INTEGER);
	}

	private void parseOct(String number) {
		char c = getNext();
		while (c == '0' || c == '1' || c == '2' || c == '3' || c == '4'
				|| c == '5' || c == '6' || c == '7') {
			number += c;
			c = getNext();
		}
		backup(1);
		foundToken(number, INTEGER);
	}

	private boolean checkVariable() {
		if (!Character.isLowerCase(peek()))
			return false;

		String id = "" + getNext();
		char c = getNext();
		while (Character.isLetter(c) || Character.isDigit(c) || c == '\''
				|| c == '#') {
			id += c;
			c = getNext();
		}
		backup(1);
		foundToken(id, VARIABLE_ID);
		return true;
	}

	private boolean checkConstructor() {
		if (!Character.isUpperCase(peek()))
			return false;

		String id = "" + getNext();
		char c = getNext();
		while (Character.isLetter(c) || Character.isDigit(c) || c == '\''
				|| c == '#') {
			id += c;
			c = getNext();
		}
		backup(1);
		foundToken(id, CONSTRUCTOR_ID);
		return true;
	}

	private boolean specialTokens() {
		return checkSpace() || checkComment();
	}

	private boolean checkComment() {
		char c1 = getNext();
		char c2 = getNext();
		if (c1 == '-' && c2 == '-') {
			String comment = "--";
			char c;
			while ((c = getNext()) != 0) {
				comment += c;
				if (c == '\n' || (c == '\r' && peek() != '\n')) {
					foundSpecialToken(comment);
					return true;
				}
			}
			// EOF before line break
			backup(comment.length());
			return false;
		} else if (c1 == '{' && c2 == '-') {
			String comment = "{-";
			char c;
			while ((c = getNext()) != 0) {
				comment += c;
				if (c == '}' && comment.charAt(comment.length() - 2) == '-') {
					foundSpecialToken(comment);
					return true;
				}
			}
			// EOF before closing bracket
			throw new TokenMgrError("Comment not closed before EOF.", 0);
		} else {
			backup(2);
			return false;
		}
	}

	private char peek() {
		char c = getNext();
		backup(1);
		return c;
	}

	private char peek(int steps) {
		char c = getNext();
		;
		for (int i = 1; i < steps; i++)
			c = getNext();
		backup(steps);
		return c;
	}

	// " "
	// | "\t"
	// | "\n"
	// | "\r"
	// | <"--" (~["\n","\r"])* ("\n" | "\r" | "\r\n")>
	// | <"{-"(~["-"])*"-"("-" | ~["-", "}"](~["-"])*"-")*"}">

	private boolean checkSpace() {
		char c = getNext();
		if (c == ' ') {
			foundSpecialToken(" ");
			return true;
		} else if (c == '\t') {
			foundSpecialToken("\n");
			return true;
		} else if (c == '\r') {
			foundSpecialToken("\r");
			return true;
		} else if (c == '\n') {
			foundSpecialToken("\n");
			return true;
		} else {
			backup(1);
			return false;
		}
	}

	private void foundSpecialToken(String string) {
		Token s = new Token();
		s.image = string;
		s.next = special;
		special = s;
	}

	private boolean checkSymbolToken(String image, int kind,
			boolean mayBeFollowedBySymbol) {
		int steps = 0;
		char c = getNext();
		while (steps < image.length() && c == image.charAt(steps)) {
			steps++;
			c = getNext();
		}

		if (steps == image.length()
				&& (mayBeFollowedBySymbol || (!isSymbol(c) && c != ':'))) {
			backup(1);
			foundToken(image, kind);
			return true;
		} else {
			backup(steps + 1);
			return false;
		}
	}

	private String getRemains() {
		String r = "";
		char c;
		while ((c = getNext()) != 0)
			r += c;
		return r;
	}

	private boolean checkTextToken(String image, int kind) {
		int steps = 0;
		char c = getNext();
		while (steps < image.length() && c == image.charAt(steps)) {
			steps++;
			c = getNext();
		}

		if (steps == image.length() && !Character.isLetter(c)) {
			backup(1);
			foundToken(image, kind);
			return true;
		} else {
			backup(steps + 1);
			return false;
		}
	}

	private int beyondEOF;

	private char getNext() {
		try {
			return input_stream.readChar();
		} catch (IOException e) {
			beyondEOF++;
			return 0;
		}
	}

	private void backup(int i) {
		if (beyondEOF > 0) {
			i -= beyondEOF;
			beyondEOF = 0;
		}
		input_stream.backup(i);
	}

	private void foundToken(String image, int kind) {
		Token t = createToken(image, kind);

		tokens.add(t);

	}

	protected final List<Token> tokens = new ArrayList<Token>();
	protected final Iterator<Token> tokenIterator;

	/**
	 * replaces the functionality of the original lexer
	 */
	public Token getNextToken() {
		return tokenIterator.next();
	}

	private Token createToken(String image, int kind) {
		Token t = new Token();
		t.image = image;
		t.kind = kind;
		t.specialToken = special;
		special = null;
		return t;
	}

	public String debugPrintTokens() {
		String result = "";
		for (Token t : tokens) {
			Token s = t.specialToken;
			while (s != null) {
				result += s.image;
				s = s.next;
			}
			result += "[" + t.image + ":" + tokenImage[t.kind] + "]";
		}
		result = result.replace("\n", "\\n").replace("\r", "\\r");
		return result;
	}

}

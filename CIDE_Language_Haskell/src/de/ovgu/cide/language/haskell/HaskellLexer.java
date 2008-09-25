package de.ovgu.cide.language.haskell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import tmp.generated_haskell.HaskellParserTokenManager;
import cide.gparser.CharStream;
import cide.gparser.Token;
import cide.gparser.TokenMgrError;

public class HaskellLexer extends HaskellParserTokenManager {

	private Token special = null;
	public static final int LAYOUTMIN = Integer.MAX_VALUE - 3;
	public static final int LAYOUTCURLY = Integer.MAX_VALUE - 1;
	public static final int LAYOUTPOINTY = Integer.MAX_VALUE - 2;

	public HaskellLexer(CharStream stream) {
		this(stream, Integer.MAX_VALUE);
	}

	/**
	 * debuglevels are used for unit tests where only partial results are needed
	 * 
	 * @param stream
	 * @param debugLevel
	 */
	HaskellLexer(CharStream stream, int debugLevel) {
		super(stream);
		if (debugLevel >= 1)
			tokenizeStream();
		if (debugLevel >= 2)
			insertLayoutToken();
		if (debugLevel >= 3)
			tokens = rewriteLayoutToken(tokens);
		fixNext();

		tokenIterator = tokens.iterator();
	}

	private void fixNext() {
		Token last = null;
		for (Token t : tokens) {
			if (last != null)
				last.next = t;
			last = t;
		}

	}

	private void tokenizeStream() {
		while (true) {
			try {
				input_stream.BeginToken();
				input_stream.backup(1);
			} catch (IOException e) {
			}

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
			if (checkSymbolToken("=>", CONTEXT_ARROW, false))
				continue;
			if (checkSymbolToken("=", EQUALS, false))
				continue;
			if (checkSymbolToken("|", ALT, false))
				continue;
			if (checkSymbolToken("::", OFTYPE, false))
				continue;
			if (checkSymbolToken("..", OTHER12, false))
				continue;
			if (checkSymbolToken(".", OTHER1, false))
				continue;
			if (checkSymbolToken("->", OTHER7, false))
				continue;
			if (checkSymbolToken("--", OTHER11, false))
				continue;
			if (checkSymbolToken("-", OTHER2, false))
				continue;
			if (checkSymbolToken("@", OTHER3, false))
				continue;
			if (checkTextToken("_", OTHER4))
				continue;
			if (checkSymbolToken("~", OTHER5, false))
				continue;
			if (checkSymbolToken(":", OTHER6, false))
				continue;
			if (checkSymbolToken("+", OTHER8, false))
				continue;
			if (checkSymbolToken("\\", OTHER9, false))
				continue;
			if (checkSymbolToken("<-", OTHER10, false))
				continue;

			if (checkSymbolToken("{", LEFT_CURLY, true))
				continue;
			if (checkSymbolToken("}", RIGHT_CURLY, true))
				continue;
			if (checkSymbolToken(";", SEMICOLON, true))
				continue;
			if (checkSymbolToken("(#", LEFT_HPAREN, true))
				continue;
			if (checkSymbolToken("#)", RIGHT_HPAREN, true))
				continue;
			if (checkSymbolToken("(", LEFT_PAREN, true))
				continue;
			if (checkSymbolToken(")", RIGHT_PAREN, true))
				continue;
			if (checkSymbolToken("[", LEFT_BRACKET, true))
				continue;
			if (checkSymbolToken("]", RIGHT_BRACKET, true))
				continue;
			if (checkSymbolToken(",", COMMA, true))
				continue;
			if (checkSymbolToken("`", INFIX_QUOTE, true))
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
		while (!(c == first && !escaped(l))) {
			if (c == 0) {
				System.out.println(debugPrintTokens());
				throw new TokenMgrError(
						"Reached EOF before String literal was closed: " + l, 0);
			}
			l += c;
			c = getNext();
		}
		l += c;

		foundToken(l, first == '"' ? STRING_LITERAL : CHARACTER_LITERAL);
		return true;
	}

	/**
	 * counts the number of tailing backslashes. it escapes the following
	 * character if there is an uneven number
	 */
	private boolean escaped(String string) {
		boolean escaped = false;
		int i = string.length() - 1;
		while (i > 0 && string.charAt(i) == '\\') {
			i--;
			escaped = !escaped;
		}
		return escaped;
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
		char c = peek();
		if (!(Character.isLowerCase(c) || c == '_'))
			return false;

		String id = "" + getNext();
		c = getNext();
		while (Character.isLetter(c) || Character.isDigit(c) || c == '\''
				|| c == '#' || c == '_') {
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
				|| c == '#' || c == '_') {
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
				if (c == '\n' || c == '\r') {
					backup(1);
					foundSpecialToken(comment);
					return true;
				}
				comment += c;
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
			foundSpecialToken("\t");
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

		if (special == null)
			special = s;
		else {
			Token lastSpecial = special;
			while (lastSpecial.next != null)
				lastSpecial = lastSpecial.next;
			lastSpecial.next = s;
		}
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

		if (tokens.size() > 0)
			tokens.get(tokens.size() - 1).next = t;
		tokens.add(t);

	}

	protected List<Token> tokens = new ArrayList<Token>();
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

		t.beginLine = input_stream.getBeginLine();
		t.beginColumn = input_stream.getBeginColumn();
		t.endLine = input_stream.getEndLine();
		t.endColumn = input_stream.getEndColumn();
		t.offset = input_stream.getOffset();
		t.length = input_stream.getLength();

		special = null;
		return t;
	}

	public String debugPrintTokens() {
		return debugPrintTokens(true);

	}

	public String debugPrintTokens(boolean nl) {
		String result = "";
		for (Token t : tokens) {
			Token s = t.specialToken;
			while (s != null) {
				result += s.image;
				s = s.next;
			}
			result += "[" + t.image + ":" + tokenImage[t.kind] + "]";
		}
		if (nl)
			result = result.replace("\n", "\\n").replace("\r", "\\r");
		return result;
	}

	public String debugSerialize() {
		return debugSerialize(true);

	}

	public String debugSerialize(boolean nl) {
		String result = "";
		for (Token t : tokens) {
			Token s = t.specialToken;
			while (s != null) {
				result += s.image;
				s = s.next;
			}
			if (t.kind != EOF)
				result += t.image;
		}
		if (nl)
			result = result.replace("\n", "\\n").replace("\r", "\\r");
		return result;
	}

	private void insertLayoutToken() {
		// A stream of lexemes as specified by the lexical syntax in the Haskell
		// report, with the following additional tokens:
		// If a let, where, do, or of keyword is not followed by the lexeme {,
		// the token {n} is inserted after the keyword, where n is the
		// indentation of the next lexeme if there is one, or 0 if the end of
		// file has been reached.

		for (Token t : new ArrayList<Token>(tokens)) {
			if (t.kind == LET || t.kind == WHERE || t.kind == DO
					|| t.kind == OF) {
				if (t.next.kind != LEFT_CURLY) {
					int indent = 0;
					if (t.next.kind != EOF)
						indent = getTokenIndentation(t.next);
					Token layoutToken = new LayoutTokenCurly(indent);
					tokens.add(tokens.indexOf(t) + 1, layoutToken);
					layoutToken.next = t.next;
					t.next = layoutToken;
				}
			}
		}

		// If the first lexeme of a module is not { or module, then it is
		// preceded by {n} where n is the indentation of the lexeme.
		if (tokens.get(0).kind != MODULE && tokens.get(0).kind != LEFT_CURLY)
			tokens.add(0, new LayoutTokenCurly(getTokenIndentation(tokens
					.get(0))));

		// Where the start of a lexeme is preceded only by white space on the
		// same line, this lexeme is preceded by <n> where n is the indentation
		// of the lexeme, provided that it is not, as a consequence of the first
		// two rules, preceded by {n}. (NB: a string literal may span multiple
		// lines -- Section 2.6. So in the fragment
		//
		// f = ("Hello \
		// \Bill", "Jake")
		//
		// There is no <n> inserted before the \Bill, because it is not the
		// beginning of a complete lexeme; nor before the ,, because it is not
		// preceded only by white space.)

		boolean first = true;
		Token last = null;
		for (Token t : new ArrayList<Token>(tokens)) {
			if (t.kind < LAYOUTMIN) {
				Token specialToken = t.specialToken;
				boolean succeedsLineBreak = first;
				first = false;
				while (!succeedsLineBreak && specialToken != null) {
					if (specialToken.image.equals("\n")
							|| specialToken.image.equals("\r"))
						succeedsLineBreak = true;
					specialToken = specialToken.next;
				}

				// ... provided that it is not, as a consequence of the first
				// two rules, preceded by {n} ...
				if (succeedsLineBreak && last != null
						&& last.kind == LAYOUTCURLY)
					succeedsLineBreak = false;

				if (succeedsLineBreak) {
					Token layoutToken = new LayoutTokenPointy(
							getTokenIndentation(t));
					tokens.add(tokens.indexOf(t), layoutToken);
					layoutToken.next = t;
					if (last != null)
						last.next = layoutToken;
				}
			}
			last = t;
		}
		// A stack of "layout contexts", in which each element is either:
		// Zero, indicating that the enclosing context is explicit (i.e. the
		// programmer supplied the opening brace. If the innermost context is 0,
		// then no layout tokens will be inserted until either the enclosing
		// context ends or a new context is pushed.
		// A positive integer, which is the indentation column of the enclosing
		// layout context.
	}

	private static class LayoutTokenCurly extends Token {
		private int indentation;

		LayoutTokenCurly(int indentation) {
			this.indentation = indentation;
			this.image = "{" + indentation + "}";
			this.kind = LAYOUTCURLY;
		}

		public int getIndentation() {
			return indentation;
		}
	}

	private static class LayoutTokenPointy extends Token {
		private int indentation;

		LayoutTokenPointy(int indentation) {
			this.indentation = indentation;
			this.image = "<" + indentation + ">";
			this.kind = LAYOUTPOINTY;
		}

		public int getIndentation() {
			return indentation;
		}
	}

	/**
	 * searches backward for the line-beginning and counts the characters
	 * 
	 * @return
	 */
	protected int getTokenIndentation(Token t) {
		/*
		 * The "indentation" of a lexeme is the column number of the first
		 * character of that lexeme; the indentation of a line is the
		 * indentation of its leftmost lexeme. To determine the column number,
		 * assume a fixed-width font with the following conventions:
		 * 
		 * The characters newline, return, linefeed, and formfeed, all start a
		 * new line.
		 * 
		 * The first column is designated column 1, not 0.
		 * 
		 * Tab stops are 8 characters apart.
		 * 
		 * A tab character causes the insertion of enough spaces to align the
		 * current position with the next tab stop.
		 */

		int tokenIdx = tokens.indexOf(t);

		String prefix = tokenIdx == 0 ? "\n" : "";
		String line = "";
		while (tokenIdx >= 0) {
			Token s = t.specialToken;
			while (s != null) {
				prefix += s.image;
				s = s.next;
			}
			line = prefix + line;
			int start = Math
					.max(line.lastIndexOf('\n'), line.lastIndexOf('\r'));
			if (start >= 0)
				return calcTabIndent(line.substring(start + 1)) + 1;
			else {
				t = tokens.get(--tokenIdx);
				if (t.kind < LAYOUTMIN)
					line = t.image + line;
				prefix = tokenIdx == 0 ? "\n" : "";
			}
		}
		return 0;
	}

	static int calcTabIndent(String text) {
		while (text.indexOf('\t') >= 0) {
			int tabPos = text.indexOf('\t');
			int shiftAmount = 8 - (tabPos % 8);
			String spaces = "";
			for (int i = 0; i < shiftAmount; i++)
				spaces += " ";
			text = text.substring(0, tabPos) + spaces
					+ text.substring(tabPos + 1);
		}
		// System.out.println(text);
		return text.length();
	}

	/**
	 * iterates over the token list and replaces layout annotations by new
	 * tokens as described in sec. 9.3
	 */
	private List<Token> rewriteLayoutToken(List<Token> input) {
		assert !input.isEmpty();
		input = new LinkedList<Token>(input);
		List<Token> result = new LinkedList<Token>();
		Stack<Integer> layoutContext = new Stack<Integer>();
		Token eofToken = input.remove(input.size() - 1);

		while (!input.isEmpty()) {
			Token currentToken = input.get(0);

			// L (<n>:ts) (m:ms) = ; : (L ts (m:ms)) if m = n
			// = } : (L (<n>:ts) ms) if n < m
			// L (<n>:ts) ms = L ts ms
			if (currentToken.kind == LAYOUTPOINTY) {
				int n = ((LayoutTokenPointy) currentToken).getIndentation();
				int m = 0;
				if (!layoutContext.isEmpty())
					m = layoutContext.peek();
				if (m == n) {
					createExtraToken(result, ";", SEMICOLON);
					input.remove(0);
					continue;
				} else if (n < m) {
					createExtraToken(result, "}", RIGHT_CURLY);
					layoutContext.pop();
					continue;
				} else {
					input.remove(0);
					continue;
				}
			}

			// L ({n}:ts) (m:ms) = { : (L ts (n:m:ms)) if n > m (Note 1)
			// L ({n}:ts) [] = { : (L ts [n]) if n > 0 (Note 1)
			// L ({n}:ts) ms = { : } : (L (<n>:ts) ms) (Note 2)
			if (currentToken.kind == LAYOUTCURLY) {
				int n = ((LayoutTokenCurly) currentToken).getIndentation();
				int m = 0;
				if (!layoutContext.isEmpty())
					m = layoutContext.peek();
				if (n > m) {
					createExtraToken(result, "{", LEFT_CURLY);
					input.remove(0);
					layoutContext.push(n);
					continue;
				} else {
					createExtraToken(result, "{", LEFT_CURLY);
					createExtraToken(result, "}", RIGHT_CURLY);
					input.remove(0);
					input.add(0, new LayoutTokenPointy(n));
					continue;
				}

			}

			// L (}:ts) (0:ms) = } : (L ts ms) (Note 3)
			// L (}:ts) ms = parse-error (Note 3)
			if (currentToken.kind == RIGHT_CURLY) {
				if (layoutContext.isEmpty() || layoutContext.peek() != 0)
					throw new TokenMgrError(
							"Brakets {} set incorrectly (Sec. 9.3, Note 3) "
									+ layoutContext.toString(), 0);
				layoutContext.pop();
				result.add(input.remove(0));
				continue;
			}

			// L ({:ts) ms = { : (L ts (0:ms)) (Note 4)
			if (currentToken.kind == LEFT_CURLY) {
				result.add(input.remove(0));
				layoutContext.push(0);
				continue;
			}

			// TODO: L (t:ts) (m:ms) = } : (L (t:ts) ms) if m /= 0 and
			// parse-error(t) (Note 5)

			// L (t:ts) ms = t : (L ts ms)
			result.add(input.remove(0));

		}
		// L [] [] = []
		// L [] (m:ms) = } : L [] ms if m /=0 (Note 6)
		while (!layoutContext.isEmpty()) {
			int m = layoutContext.pop();
			if (m != 0)
				createExtraToken(result, "}", RIGHT_CURLY);
			else
				throw new TokenMgrError(
						"Layout error, closing brackets do not match (see Sec. 9.3, Note 6)",
						0);
		}

		result.add(eofToken);
		return result;
	}

	/**
	 * appends an extra token to the token list
	 * 
	 * @param tokenList
	 * @return
	 */
	private static Token createExtraToken(List<Token> tokenList, String image,
			int kind) {
		Token t = new ExtraLayoutToken();
		t.image = image;
		t.kind = kind;

		if (!tokenList.isEmpty()) {
			Token lastToken = tokenList.get(tokenList.size() - 1);
			t.beginColumn = t.endColumn = lastToken.endColumn;
			t.beginLine = t.endLine = lastToken.endLine;
			t.offset = lastToken.getEndOffset();
			t.length = 0;

			if (kind == RIGHT_CURLY && lastToken.kind == SEMICOLON
					&& (lastToken instanceof ExtraLayoutToken))
				tokenList.remove(tokenList.size() - 1);
		}

		tokenList.add(t);
		return t;
	}

	private static class ExtraLayoutToken extends Token {

	}

}

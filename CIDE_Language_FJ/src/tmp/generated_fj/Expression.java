package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Expression extends GenASTNode {
  public Expression(Term term, ArrayList<PlusOrMinus> plusOrMinus, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Term>("term", term),
      new PropertyZeroOrMore<PlusOrMinus>("plusOrMinus", plusOrMinus)
    }, firstToken, lastToken);
  }
  public Expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Expression(cloneProperties(),firstToken,lastToken);
  }
  public Term getTerm() {
    return ((PropertyOne<Term>)getProperty("term")).getValue();
  }
  public ArrayList<PlusOrMinus> getPlusOrMinus() {
    return ((PropertyZeroOrMore<PlusOrMinus>)getProperty("plusOrMinus")).getValue();
  }
}

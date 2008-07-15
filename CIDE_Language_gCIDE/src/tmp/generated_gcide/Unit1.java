package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Unit1 extends Unit {
  public Unit1(NonTerminal nonTerminal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<NonTerminal>("nonTerminal", nonTerminal)
    }, firstToken, lastToken);
  }
  public Unit1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Unit1(cloneProperties(),firstToken,lastToken);
  }
  public NonTerminal getNonTerminal() {
    return ((PropertyOne<NonTerminal>)getProperty("nonTerminal")).getValue();
  }
}

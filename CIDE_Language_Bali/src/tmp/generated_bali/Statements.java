package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statements extends GenASTNode {
  public Statements(ArrayList<Statement> statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<Statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public Statements(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statements(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Statement> getStatement() {
    return ((PropertyOneOrMore<Statement>)getProperty("statement")).getValue();
  }
}

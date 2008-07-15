package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StatementList extends GenASTNode {
  public StatementList(ArrayList<Statement> statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<Statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public StatementList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StatementList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Statement> getStatement() {
    return ((PropertyOneOrMore<Statement>)getProperty("statement")).getValue();
  }
}

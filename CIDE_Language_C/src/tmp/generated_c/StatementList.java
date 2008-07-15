package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StatementList extends GenASTNode {
  public StatementList(Statement statement, ArrayList<Statement> statement1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Statement>("statement", statement),
      new PropertyZeroOrMore<Statement>("statement1", statement1)
    }, firstToken, lastToken);
  }
  public StatementList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StatementList(cloneProperties(),firstToken,lastToken);
  }
  public Statement getStatement() {
    return ((PropertyOne<Statement>)getProperty("statement")).getValue();
  }
  public ArrayList<Statement> getStatement1() {
    return ((PropertyZeroOrMore<Statement>)getProperty("statement1")).getValue();
  }
}

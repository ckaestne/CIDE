package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class statement_list extends GenASTNode {
  public statement_list(ArrayList<statement> statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public statement_list(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new statement_list(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<statement> getStatement() {
    return ((PropertyOneOrMore<statement>)getProperty("statement")).getValue();
  }
}

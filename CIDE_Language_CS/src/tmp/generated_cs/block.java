package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class block extends GenASTNode {
  public block(ArrayList<statement> statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public block(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new block(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<statement> getStatement() {
    return ((PropertyZeroOrMore<statement>)getProperty("statement")).getValue();
  }
}

package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class statement1 extends statement {
  public statement1(identifier identifier, statement statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyOne<statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public statement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new statement1(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public statement getStatement() {
    return ((PropertyOne<statement>)getProperty("statement")).getValue();
  }
}

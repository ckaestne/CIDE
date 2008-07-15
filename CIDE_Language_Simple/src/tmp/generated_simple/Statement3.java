package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement3 extends Statement {
  public Statement3(Assignment assignment, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Assignment>("assignment", assignment)
    }, firstToken, lastToken);
  }
  public Statement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement3(cloneProperties(),firstToken,lastToken);
  }
  public Assignment getAssignment() {
    return ((PropertyOne<Assignment>)getProperty("assignment")).getValue();
  }
}

package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class embedded_statement4 extends embedded_statement {
  public embedded_statement4(iteration_statement iteration_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<iteration_statement>("iteration_statement", iteration_statement)
    }, firstToken, lastToken);
  }
  public embedded_statement4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new embedded_statement4(cloneProperties(),firstToken,lastToken);
  }
  public iteration_statement getIteration_statement() {
    return ((PropertyOne<iteration_statement>)getProperty("iteration_statement")).getValue();
  }
}

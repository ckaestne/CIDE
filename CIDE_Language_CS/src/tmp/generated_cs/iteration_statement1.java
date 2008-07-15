package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class iteration_statement1 extends iteration_statement {
  public iteration_statement1(while_statement while_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<while_statement>("while_statement", while_statement)
    }, firstToken, lastToken);
  }
  public iteration_statement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new iteration_statement1(cloneProperties(),firstToken,lastToken);
  }
  public while_statement getWhile_statement() {
    return ((PropertyOne<while_statement>)getProperty("while_statement")).getValue();
  }
}

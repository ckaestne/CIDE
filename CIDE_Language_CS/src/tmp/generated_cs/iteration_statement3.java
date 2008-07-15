package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class iteration_statement3 extends iteration_statement {
  public iteration_statement3(for_statement for_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<for_statement>("for_statement", for_statement)
    }, firstToken, lastToken);
  }
  public iteration_statement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new iteration_statement3(cloneProperties(),firstToken,lastToken);
  }
  public for_statement getFor_statement() {
    return ((PropertyOne<for_statement>)getProperty("for_statement")).getValue();
  }
}

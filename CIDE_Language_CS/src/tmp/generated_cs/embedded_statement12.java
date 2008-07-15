package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class embedded_statement12 extends embedded_statement {
  public embedded_statement12(fixed_statement fixed_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<fixed_statement>("fixed_statement", fixed_statement)
    }, firstToken, lastToken);
  }
  public embedded_statement12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new embedded_statement12(cloneProperties(),firstToken,lastToken);
  }
  public fixed_statement getFixed_statement() {
    return ((PropertyOne<fixed_statement>)getProperty("fixed_statement")).getValue();
  }
}

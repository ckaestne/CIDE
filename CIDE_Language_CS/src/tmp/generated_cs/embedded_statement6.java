package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class embedded_statement6 extends embedded_statement {
  public embedded_statement6(try_statement try_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<try_statement>("try_statement", try_statement)
    }, firstToken, lastToken);
  }
  public embedded_statement6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new embedded_statement6(cloneProperties(),firstToken,lastToken);
  }
  public try_statement getTry_statement() {
    return ((PropertyOne<try_statement>)getProperty("try_statement")).getValue();
  }
}

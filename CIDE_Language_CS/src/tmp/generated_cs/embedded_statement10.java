package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class embedded_statement10 extends embedded_statement {
  public embedded_statement10(using_statement using_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<using_statement>("using_statement", using_statement)
    }, firstToken, lastToken);
  }
  public embedded_statement10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new embedded_statement10(cloneProperties(),firstToken,lastToken);
  }
  public using_statement getUsing_statement() {
    return ((PropertyOne<using_statement>)getProperty("using_statement")).getValue();
  }
}

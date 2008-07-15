package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class embedded_statement7 extends embedded_statement {
  public embedded_statement7(checked_statement checked_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<checked_statement>("checked_statement", checked_statement)
    }, firstToken, lastToken);
  }
  public embedded_statement7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new embedded_statement7(cloneProperties(),firstToken,lastToken);
  }
  public checked_statement getChecked_statement() {
    return ((PropertyOne<checked_statement>)getProperty("checked_statement")).getValue();
  }
}

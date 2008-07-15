package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class embedded_statement3 extends embedded_statement {
  public embedded_statement3(selection_statement selection_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<selection_statement>("selection_statement", selection_statement)
    }, firstToken, lastToken);
  }
  public embedded_statement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new embedded_statement3(cloneProperties(),firstToken,lastToken);
  }
  public selection_statement getSelection_statement() {
    return ((PropertyOne<selection_statement>)getProperty("selection_statement")).getValue();
  }
}

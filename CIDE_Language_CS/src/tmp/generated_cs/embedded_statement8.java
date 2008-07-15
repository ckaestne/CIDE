package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class embedded_statement8 extends embedded_statement {
  public embedded_statement8(unchecked_statement unchecked_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<unchecked_statement>("unchecked_statement", unchecked_statement)
    }, firstToken, lastToken);
  }
  public embedded_statement8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new embedded_statement8(cloneProperties(),firstToken,lastToken);
  }
  public unchecked_statement getUnchecked_statement() {
    return ((PropertyOne<unchecked_statement>)getProperty("unchecked_statement")).getValue();
  }
}

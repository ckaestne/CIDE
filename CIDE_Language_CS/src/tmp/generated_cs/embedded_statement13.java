package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class embedded_statement13 extends embedded_statement {
  public embedded_statement13(expression_statement expression_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expression_statement>("expression_statement", expression_statement)
    }, firstToken, lastToken);
  }
  public embedded_statement13(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new embedded_statement13(cloneProperties(),firstToken,lastToken);
  }
  public expression_statement getExpression_statement() {
    return ((PropertyOne<expression_statement>)getProperty("expression_statement")).getValue();
  }
}

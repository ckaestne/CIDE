package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class do_statement extends GenASTNode {
  public do_statement(embedded_statement embedded_statement, expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<embedded_statement>("embedded_statement", embedded_statement),
      new PropertyOne<expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public do_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new do_statement(cloneProperties(),firstToken,lastToken);
  }
  public embedded_statement getEmbedded_statement() {
    return ((PropertyOne<embedded_statement>)getProperty("embedded_statement")).getValue();
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
}

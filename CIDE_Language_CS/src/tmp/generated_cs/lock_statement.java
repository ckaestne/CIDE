package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class lock_statement extends GenASTNode {
  public lock_statement(expression expression, embedded_statement embedded_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expression>("expression", expression),
      new PropertyOne<embedded_statement>("embedded_statement", embedded_statement)
    }, firstToken, lastToken);
  }
  public lock_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new lock_statement(cloneProperties(),firstToken,lastToken);
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
  public embedded_statement getEmbedded_statement() {
    return ((PropertyOne<embedded_statement>)getProperty("embedded_statement")).getValue();
  }
}

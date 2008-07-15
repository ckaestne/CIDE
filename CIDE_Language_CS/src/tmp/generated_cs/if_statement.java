package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class if_statement extends GenASTNode {
  public if_statement(expression expression, embedded_statement embedded_statement, embedded_statement embedded_statement1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expression>("expression", expression),
      new PropertyOne<embedded_statement>("embedded_statement", embedded_statement),
      new PropertyZeroOrOne<embedded_statement>("embedded_statement1", embedded_statement1)
    }, firstToken, lastToken);
  }
  public if_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new if_statement(cloneProperties(),firstToken,lastToken);
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
  public embedded_statement getEmbedded_statement() {
    return ((PropertyOne<embedded_statement>)getProperty("embedded_statement")).getValue();
  }
  public embedded_statement getEmbedded_statement1() {
    return ((PropertyZeroOrOne<embedded_statement>)getProperty("embedded_statement1")).getValue();
  }
}

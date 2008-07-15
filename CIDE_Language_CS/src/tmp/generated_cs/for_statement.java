package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class for_statement extends GenASTNode {
  public for_statement(for_initializer for_initializer, expression expression, expression_list expression_list, embedded_statement embedded_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<for_initializer>("for_initializer", for_initializer),
      new PropertyZeroOrOne<expression>("expression", expression),
      new PropertyZeroOrOne<expression_list>("expression_list", expression_list),
      new PropertyOne<embedded_statement>("embedded_statement", embedded_statement)
    }, firstToken, lastToken);
  }
  public for_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new for_statement(cloneProperties(),firstToken,lastToken);
  }
  public for_initializer getFor_initializer() {
    return ((PropertyZeroOrOne<for_initializer>)getProperty("for_initializer")).getValue();
  }
  public expression getExpression() {
    return ((PropertyZeroOrOne<expression>)getProperty("expression")).getValue();
  }
  public expression_list getExpression_list() {
    return ((PropertyZeroOrOne<expression_list>)getProperty("expression_list")).getValue();
  }
  public embedded_statement getEmbedded_statement() {
    return ((PropertyOne<embedded_statement>)getProperty("embedded_statement")).getValue();
  }
}

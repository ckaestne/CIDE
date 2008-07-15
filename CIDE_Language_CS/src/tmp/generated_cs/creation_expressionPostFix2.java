package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class creation_expressionPostFix2 extends creation_expressionPostFix {
  public creation_expressionPostFix2(expression_list expression_list, rank_specifiers rank_specifiers, array_initializer array_initializer, array_creation_postfix_expression array_creation_postfix_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expression_list>("expression_list", expression_list),
      new PropertyZeroOrOne<rank_specifiers>("rank_specifiers", rank_specifiers),
      new PropertyZeroOrOne<array_initializer>("array_initializer", array_initializer),
      new PropertyZeroOrOne<array_creation_postfix_expression>("array_creation_postfix_expression", array_creation_postfix_expression)
    }, firstToken, lastToken);
  }
  public creation_expressionPostFix2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new creation_expressionPostFix2(cloneProperties(),firstToken,lastToken);
  }
  public expression_list getExpression_list() {
    return ((PropertyOne<expression_list>)getProperty("expression_list")).getValue();
  }
  public rank_specifiers getRank_specifiers() {
    return ((PropertyZeroOrOne<rank_specifiers>)getProperty("rank_specifiers")).getValue();
  }
  public array_initializer getArray_initializer() {
    return ((PropertyZeroOrOne<array_initializer>)getProperty("array_initializer")).getValue();
  }
  public array_creation_postfix_expression getArray_creation_postfix_expression() {
    return ((PropertyZeroOrOne<array_creation_postfix_expression>)getProperty("array_creation_postfix_expression")).getValue();
  }
}

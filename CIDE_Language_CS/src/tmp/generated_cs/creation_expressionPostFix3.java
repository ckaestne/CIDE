package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class creation_expressionPostFix3 extends creation_expressionPostFix {
  public creation_expressionPostFix3(rank_specifiers rank_specifiers1, array_initializer array_initializer1, array_creation_postfix_expression array_creation_postfix_expression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<rank_specifiers>("rank_specifiers1", rank_specifiers1),
      new PropertyOne<array_initializer>("array_initializer1", array_initializer1),
      new PropertyZeroOrOne<array_creation_postfix_expression>("array_creation_postfix_expression1", array_creation_postfix_expression1)
    }, firstToken, lastToken);
  }
  public creation_expressionPostFix3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new creation_expressionPostFix3(cloneProperties(),firstToken,lastToken);
  }
  public rank_specifiers getRank_specifiers1() {
    return ((PropertyOne<rank_specifiers>)getProperty("rank_specifiers1")).getValue();
  }
  public array_initializer getArray_initializer1() {
    return ((PropertyOne<array_initializer>)getProperty("array_initializer1")).getValue();
  }
  public array_creation_postfix_expression getArray_creation_postfix_expression1() {
    return ((PropertyZeroOrOne<array_creation_postfix_expression>)getProperty("array_creation_postfix_expression1")).getValue();
  }
}

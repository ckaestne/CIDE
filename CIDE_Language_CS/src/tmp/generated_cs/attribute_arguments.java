package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class attribute_arguments extends GenASTNode {
  public attribute_arguments(expression_list expression_list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<expression_list>("expression_list", expression_list)
    }, firstToken, lastToken);
  }
  public attribute_arguments(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new attribute_arguments(cloneProperties(),firstToken,lastToken);
  }
  public expression_list getExpression_list() {
    return ((PropertyZeroOrOne<expression_list>)getProperty("expression_list")).getValue();
  }
}

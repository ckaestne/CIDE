package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class creation_expressionPostFix1 extends creation_expressionPostFix {
  public creation_expressionPostFix1(argument_list argument_list, primary_expression_postfix primary_expression_postfix, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<argument_list>("argument_list", argument_list),
      new PropertyZeroOrOne<primary_expression_postfix>("primary_expression_postfix", primary_expression_postfix)
    }, firstToken, lastToken);
  }
  public creation_expressionPostFix1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new creation_expressionPostFix1(cloneProperties(),firstToken,lastToken);
  }
  public argument_list getArgument_list() {
    return ((PropertyZeroOrOne<argument_list>)getProperty("argument_list")).getValue();
  }
  public primary_expression_postfix getPrimary_expression_postfix() {
    return ((PropertyZeroOrOne<primary_expression_postfix>)getProperty("primary_expression_postfix")).getValue();
  }
}

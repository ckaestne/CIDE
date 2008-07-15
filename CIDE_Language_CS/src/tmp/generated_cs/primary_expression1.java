package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression1 extends primary_expression {
  public primary_expression1(primary_expression_start primary_expression_start, primary_expression_postfix primary_expression_postfix, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<primary_expression_start>("primary_expression_start", primary_expression_start),
      new PropertyZeroOrOne<primary_expression_postfix>("primary_expression_postfix", primary_expression_postfix)
    }, firstToken, lastToken);
  }
  public primary_expression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression1(cloneProperties(),firstToken,lastToken);
  }
  public primary_expression_start getPrimary_expression_start() {
    return ((PropertyOne<primary_expression_start>)getProperty("primary_expression_start")).getValue();
  }
  public primary_expression_postfix getPrimary_expression_postfix() {
    return ((PropertyZeroOrOne<primary_expression_postfix>)getProperty("primary_expression_postfix")).getValue();
  }
}

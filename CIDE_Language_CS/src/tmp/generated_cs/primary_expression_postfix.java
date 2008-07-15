package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_postfix extends GenASTNode {
  public primary_expression_postfix(ArrayList<primary_expression_postfixInternal> primary_expression_postfixInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<primary_expression_postfixInternal>("primary_expression_postfixInternal", primary_expression_postfixInternal)
    }, firstToken, lastToken);
  }
  public primary_expression_postfix(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_postfix(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<primary_expression_postfixInternal> getPrimary_expression_postfixInternal() {
    return ((PropertyOneOrMore<primary_expression_postfixInternal>)getProperty("primary_expression_postfixInternal")).getValue();
  }
}

package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class array_creation_postfix_expression extends GenASTNode {
  public array_creation_postfix_expression(ArrayList<array_creation_postfix_expressionInternal> array_creation_postfix_expressionInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<array_creation_postfix_expressionInternal>("array_creation_postfix_expressionInternal", array_creation_postfix_expressionInternal)
    }, firstToken, lastToken);
  }
  public array_creation_postfix_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new array_creation_postfix_expression(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<array_creation_postfix_expressionInternal> getArray_creation_postfix_expressionInternal() {
    return ((PropertyOneOrMore<array_creation_postfix_expressionInternal>)getProperty("array_creation_postfix_expressionInternal")).getValue();
  }
}

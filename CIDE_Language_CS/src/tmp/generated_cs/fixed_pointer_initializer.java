package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class fixed_pointer_initializer extends GenASTNode {
  public fixed_pointer_initializer(ASTTextNode text281, expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text281", text281),
      new PropertyOne<expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public fixed_pointer_initializer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new fixed_pointer_initializer(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText281() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text281")).getValue();
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
}

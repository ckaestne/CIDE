package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class attribute_target3 extends attribute_target {
  public attribute_target3(ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public attribute_target3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new attribute_target3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}

package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class type2 extends type {
  public type2(ASTStringNode variable_id, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("variable_id", variable_id)
    }, firstToken, lastToken);
  }
  public type2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new type2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getVariable_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("variable_id")).getValue();
  }
}

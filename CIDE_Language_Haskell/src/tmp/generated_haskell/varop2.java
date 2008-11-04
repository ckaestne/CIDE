package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varop2 extends varop {
  public varop2(ASTStringNode variable_id, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("variable_id", variable_id)
    }, firstToken, lastToken);
  }
  public varop2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new varop2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getVariable_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("variable_id")).getValue();
  }
}

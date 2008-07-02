package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class klasseTypeVar2 extends klasseTypeVar {
  public klasseTypeVar2(ASTStringNode variable_id1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("variable_id1", variable_id1)
    }, firstToken, lastToken);
  }
  public klasseTypeVar2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new klasseTypeVar2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getVariable_id1() {
    return ((PropertyOne<ASTStringNode>)getProperty("variable_id1")).getValue();
  }
}

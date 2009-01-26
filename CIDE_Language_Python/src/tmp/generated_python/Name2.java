package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Name2 extends Name {
  public Name2(ASTStringNode as, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("as", as)
    }, firstToken, lastToken);
  }
  public Name2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Name2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAs() {
    return ((PropertyOne<ASTStringNode>)getProperty("as")).getValue();
  }
}

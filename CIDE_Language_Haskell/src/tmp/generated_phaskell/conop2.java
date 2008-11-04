package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class conop2 extends conop {
  public conop2(ASTStringNode constructor_id, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("constructor_id", constructor_id)
    }, firstToken, lastToken);
  }
  public conop2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new conop2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getConstructor_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("constructor_id")).getValue();
  }
}

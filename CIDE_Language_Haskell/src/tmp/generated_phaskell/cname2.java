package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class cname2 extends cname {
  public cname2(ASTStringNode constructor_id, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("constructor_id", constructor_id)
    }, firstToken, lastToken);
  }
  public cname2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new cname2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getConstructor_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("constructor_id")).getValue();
  }
}

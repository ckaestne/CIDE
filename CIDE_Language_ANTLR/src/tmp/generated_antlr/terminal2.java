package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class terminal2 extends terminal {
  public terminal2(ASTStringNode token_ref, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("token_ref", token_ref)
    }, firstToken, lastToken);
  }
  public terminal2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new terminal2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getToken_ref() {
    return ((PropertyOne<ASTStringNode>)getProperty("token_ref")).getValue();
  }
}

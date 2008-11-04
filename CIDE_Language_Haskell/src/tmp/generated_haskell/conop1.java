package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class conop1 extends conop {
  public conop1(ASTStringNode consym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("consym", consym)
    }, firstToken, lastToken);
  }
  public conop1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new conop1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getConsym() {
    return ((PropertyOne<ASTStringNode>)getProperty("consym")).getValue();
  }
}

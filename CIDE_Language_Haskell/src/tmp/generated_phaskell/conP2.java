package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class conP2 extends conP {
  public conP2(ASTStringNode consym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("consym", consym)
    }, firstToken, lastToken);
  }
  public conP2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new conP2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getConsym() {
    return ((PropertyOne<ASTStringNode>)getProperty("consym")).getValue();
  }
}

package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class constructorOperatorMain1 extends constructorOperatorMain {
  public constructorOperatorMain1(ASTStringNode consym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("consym", consym)
    }, firstToken, lastToken);
  }
  public constructorOperatorMain1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new constructorOperatorMain1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getConsym() {
    return ((PropertyOne<ASTStringNode>)getProperty("consym")).getValue();
  }
}

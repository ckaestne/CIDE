package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class var2 extends var {
  public var2(ASTStringNode varsym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("varsym", varsym)
    }, firstToken, lastToken);
  }
  public var2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new var2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getVarsym() {
    return ((PropertyOne<ASTStringNode>)getProperty("varsym")).getValue();
  }
}

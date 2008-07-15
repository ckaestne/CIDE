package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varop1 extends varop {
  public varop1(ASTStringNode varsym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("varsym", varsym)
    }, firstToken, lastToken);
  }
  public varop1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new varop1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getVarsym() {
    return ((PropertyOne<ASTStringNode>)getProperty("varsym")).getValue();
  }
}

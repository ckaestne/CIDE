package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varsym1 extends varsym {
  public varsym1(ASTStringNode varsym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("varsym", varsym)
    }, firstToken, lastToken);
  }
  public varsym1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new varsym1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getVarsym() {
    return ((PropertyOne<ASTStringNode>)getProperty("varsym")).getValue();
  }
}

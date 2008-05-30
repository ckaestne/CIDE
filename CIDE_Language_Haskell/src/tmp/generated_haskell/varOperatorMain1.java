package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varOperatorMain1 extends varOperatorMain {
  public varOperatorMain1(ASTStringNode varsym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("varsym", varsym)
    }, firstToken, lastToken);
  }
  public varOperatorMain1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new varOperatorMain1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getVarsym() {
    return ((PropertyOne<ASTStringNode>)getProperty("varsym")).getValue();
  }
}

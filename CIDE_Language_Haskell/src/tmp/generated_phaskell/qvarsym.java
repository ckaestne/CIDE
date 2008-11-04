package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qvarsym extends GenASTNode {
  public qvarsym(ASTStringNode varsym, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("varsym", varsym)
    }, firstToken, lastToken);
  }
  public qvarsym(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qvarsym(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getVarsym() {
    return ((PropertyOne<ASTStringNode>)getProperty("varsym")).getValue();
  }
}

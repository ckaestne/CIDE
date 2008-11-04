package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class constr extends GenASTNode {
  public constr(conP conP, ASTStringNode findconrest, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<conP>("conP", conP),
      new PropertyZeroOrOne<ASTStringNode>("findconrest", findconrest)
    }, firstToken, lastToken);
  }
  public constr(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new constr(cloneProperties(),firstToken,lastToken);
  }
  public conP getConP() {
    return ((PropertyOne<conP>)getProperty("conP")).getValue();
  }
  public ASTStringNode getFindconrest() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("findconrest")).getValue();
  }
}

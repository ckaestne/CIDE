package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class declrhs extends GenASTNode {
  public declrhs(ASTStringNode findnonstddeclrest, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("findnonstddeclrest", findnonstddeclrest)
    }, firstToken, lastToken);
  }
  public declrhs(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new declrhs(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindnonstddeclrest() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("findnonstddeclrest")).getValue();
  }
}

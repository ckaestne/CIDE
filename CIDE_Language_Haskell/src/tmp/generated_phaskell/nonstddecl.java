package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class nonstddecl extends GenASTNode {
  public nonstddecl(ASTStringNode findnonstddeclrest, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("findnonstddeclrest", findnonstddeclrest)
    }, firstToken, lastToken);
  }
  public nonstddecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new nonstddecl(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindnonstddeclrest() {
    return ((PropertyOne<ASTStringNode>)getProperty("findnonstddeclrest")).getValue();
  }
}

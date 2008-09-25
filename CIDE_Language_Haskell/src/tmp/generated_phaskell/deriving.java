package tmp.generated_phaskell;

import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class deriving extends GenASTNode {
  public deriving(ASTStringNode findnonstddeclrest, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("findnonstddeclrest", findnonstddeclrest)
    }, firstToken, lastToken);
  }
  public deriving(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new deriving(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindnonstddeclrest() {
    return ((PropertyOne<ASTStringNode>)getProperty("findnonstddeclrest")).getValue();
  }
}

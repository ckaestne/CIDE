package tmp.generated_phaskell;

import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

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

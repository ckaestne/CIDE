package tmp.generated_phaskell;

import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class funlhsR2 extends funlhsR {
  public funlhsR2(ASTStringNode finduntilsemiorequals, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("finduntilsemiorequals", finduntilsemiorequals)
    }, firstToken, lastToken);
  }
  public funlhsR2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new funlhsR2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFinduntilsemiorequals() {
    return ((PropertyOne<ASTStringNode>)getProperty("finduntilsemiorequals")).getValue();
  }
}

package tmp.generated_phaskell;

import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class signdecl extends GenASTNode {
  public signdecl(vars vars, ASTStringNode finduntilsemiorccb, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<vars>("vars", vars),
      new PropertyZeroOrOne<ASTStringNode>("finduntilsemiorccb", finduntilsemiorccb)
    }, firstToken, lastToken);
  }
  public signdecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new signdecl(cloneProperties(),firstToken,lastToken);
  }
  public vars getVars() {
    return ((PropertyOne<vars>)getProperty("vars")).getValue();
  }
  public ASTStringNode getFinduntilsemiorccb() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("finduntilsemiorccb")).getValue();
  }
}

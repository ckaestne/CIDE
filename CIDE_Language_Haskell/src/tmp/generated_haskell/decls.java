package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class decls extends GenASTNode {
  public decls(declarationList declarationList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<declarationList>("declarationList", declarationList)
    }, firstToken, lastToken);
  }
  public decls(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new decls(cloneProperties(),firstToken,lastToken);
  }
  public declarationList getDeclarationList() {
    return ((PropertyZeroOrOne<declarationList>)getProperty("declarationList")).getValue();
  }
}

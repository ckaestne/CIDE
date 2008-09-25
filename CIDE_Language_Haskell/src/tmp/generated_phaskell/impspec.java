package tmp.generated_phaskell;

import cide.gast.ASTTextNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class impspec extends GenASTNode {
  public impspec(ASTTextNode text367, imports imports, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text367", text367),
      new PropertyZeroOrOne<imports>("imports", imports)
    }, firstToken, lastToken);
  }
  public impspec(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new impspec(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText367() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text367")).getValue();
  }
  public imports getImports() {
    return ((PropertyZeroOrOne<imports>)getProperty("imports")).getValue();
  }
}

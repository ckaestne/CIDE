package tmp.generated_haskell;

import cide.gast.ASTTextNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class hiding extends GenASTNode {
  public hiding(ASTTextNode text3, exportList exportList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text3", text3),
      new PropertyZeroOrOne<exportList>("exportList", exportList)
    }, firstToken, lastToken);
  }
  public hiding(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new hiding(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText3() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text3")).getValue();
  }
  public exportList getExportList() {
    return ((PropertyZeroOrOne<exportList>)getProperty("exportList")).getValue();
  }
}

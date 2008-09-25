package tmp.generated_haskell;

import cide.gast.ASTTextNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class exports extends GenASTNode {
  public exports(exportList exportList, ASTTextNode text1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<exportList>("exportList", exportList),
      new PropertyZeroOrOne<ASTTextNode>("text1", text1)
    }, firstToken, lastToken);
  }
  public exports(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exports(cloneProperties(),firstToken,lastToken);
  }
  public exportList getExportList() {
    return ((PropertyZeroOrOne<exportList>)getProperty("exportList")).getValue();
  }
  public ASTTextNode getText1() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text1")).getValue();
  }
}

package tmp.generated_phaskell;

import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class list extends GenASTNode {
  public list(ASTStringNode findlistcontent, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("findlistcontent", findlistcontent)
    }, firstToken, lastToken);
  }
  public list(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new list(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindlistcontent() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("findlistcontent")).getValue();
  }
}

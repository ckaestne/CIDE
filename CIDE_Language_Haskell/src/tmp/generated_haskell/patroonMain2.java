package tmp.generated_haskell;

import cide.gast.ASTTextNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class patroonMain2 extends patroonMain {
  public patroonMain2(ASTTextNode text7, literal literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text7", text7),
      new PropertyOne<literal>("literal", literal)
    }, firstToken, lastToken);
  }
  public patroonMain2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new patroonMain2(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText7() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text7")).getValue();
  }
  public literal getLiteral() {
    return ((PropertyOne<literal>)getProperty("literal")).getValue();
  }
}

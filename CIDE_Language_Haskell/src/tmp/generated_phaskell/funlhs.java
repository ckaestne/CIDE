package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class funlhs extends GenASTNode {
  public funlhs(funlhsL funlhsL, funlhsR funlhsR, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<funlhsL>("funlhsL", funlhsL),
      new PropertyZeroOrOne<funlhsR>("funlhsR", funlhsR)
    }, firstToken, lastToken);
  }
  public funlhs(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new funlhs(cloneProperties(),firstToken,lastToken);
  }
  public funlhsL getFunlhsL() {
    return ((PropertyOne<funlhsL>)getProperty("funlhsL")).getValue();
  }
  public funlhsR getFunlhsR() {
    return ((PropertyZeroOrOne<funlhsR>)getProperty("funlhsR")).getValue();
  }
}

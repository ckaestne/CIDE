package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class valdef extends GenASTNode {
  public valdef(funlhs funlhs, declrhs declrhs, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<funlhs>("funlhs", funlhs),
      new PropertyOne<declrhs>("declrhs", declrhs)
    }, firstToken, lastToken);
  }
  public valdef(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new valdef(cloneProperties(),firstToken,lastToken);
  }
  public funlhs getFunlhs() {
    return ((PropertyOne<funlhs>)getProperty("funlhs")).getValue();
  }
  public declrhs getDeclrhs() {
    return ((PropertyOne<declrhs>)getProperty("declrhs")).getValue();
  }
}

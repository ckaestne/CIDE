package tmp.generated_haskell;

import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class literal2 extends literal {
  public literal2(ASTStringNode float_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("float_kw", float_kw)
    }, firstToken, lastToken);
  }
  public literal2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new literal2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFloat_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("float_kw")).getValue();
  }
}
